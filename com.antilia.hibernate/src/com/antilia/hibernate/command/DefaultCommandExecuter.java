package com.antilia.hibernate.command;

import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.antilia.common.iterator.ArrayIterable;
import com.antilia.common.iterator.SingleIterable;
import com.antilia.common.util.AnnotationUtils;
import com.antilia.common.util.ExceptionUtils;
import com.antilia.hibernate.PersistenceException;
import com.antilia.hibernate.annotation.AfterExecute;
import com.antilia.hibernate.annotation.BeforeExecute;
import com.antilia.hibernate.annotation.Detach;
import com.antilia.hibernate.cfg.IPersistenceUnit;
import com.antilia.hibernate.context.RequestContext;
import com.antilia.hibernate.context.Tier;
import com.antilia.hibernate.transaction.Propagation;
import com.antilia.hibernate.transaction.Transactional;
import com.antilia.hibernate.util.CommandUtils;

class DefaultCommandExecuter  implements ICommandExecuter, Serializable {
	private static final long serialVersionUID = 1L;

	private static DefaultCommandExecuter instance = null;

	static public ICommandExecuter getInstance() {
		if (instance == null)
			instance = new DefaultCommandExecuter();
		return instance;
	}

	protected DefaultCommandExecuter() {
	}
	
	protected void beginTransaction(Session session, Transaction transaction,int timeout) {		
		if (timeout != Transactional.TIMEOUT_DEFAULT) {
			transaction.setTimeout(timeout);
			transaction.begin();			
		} else {
			session.beginTransaction();
		}
		
	}

	protected void detachResult(Serializable result) throws Throwable {			
		// call any @Detach annotated methods on the entities in the result
		Method detachMethod = null;
		Iterable<?> list = (result instanceof Iterable)?((Iterable<?>)result):
			(result instanceof Object[])?(new ArrayIterable<Object>((Object[])result)):
			(new SingleIterable<Object>(result));	

		for (Object object : list) {
			if (object == null)
				continue;
			
			if (detachMethod == null) {
				detachMethod = AnnotationUtils.findAnnotatedMethod(object.getClass(), Detach.class);
				if (detachMethod == null)
					break;
			}
			
			try {
				detachMethod.invoke(object,(Object[])null);
			} catch (InvocationTargetException e) {
				throw e.getTargetException();
			}
		}
		
		// detach all entities from the sessionContext
		//EntityManagerPool.getEntityManager().clear();
		
		//getSession().clear();
		
		// replace lazy loading proxies in the result by a proxy that can perform
		// lazy loading outside the persistence context.
		CommandUtils.detachObject(list);
	}
	
	@SuppressWarnings("unchecked")
	public <T> T doAfterExecute(ICommand<T> service, T result) throws Throwable {
		Method postExecute = AnnotationUtils.findAnnotatedMethod(service
				.getClass(), AfterExecute.class);
		if (postExecute != null)
			return((T)postExecute.invoke(service,new Object[]{result}));
		return (result);
	}

	public <T> void doBeforeExecute(ICommand<T> service) throws Throwable {
		Method preExecute = AnnotationUtils.findAnnotatedMethod(service.getClass(), BeforeExecute.class);
		if (preExecute != null)
			preExecute.invoke(service, (Object[]) null);
	}

	public <T> T doExecute(ICommand<T> command) throws Throwable {
		// set tier to BUSINESS
		Tier oldTier = RequestContext.get().getTier();
		RequestContext.get().setTier(com.antilia.hibernate.context.Tier.BUSINESS);
		
		Transaction transaction = null;
		boolean newTransaction = false;
		
		// get transaction attributes from Transactional annotation (first on execute() method, then on type)
		Method executeMethod = command.getClass().getMethod("execute",(Class[])null);
		Transactional transactional = executeMethod.getAnnotation(Transactional.class);
		Propagation propagation = (transactional != null)?(transactional.propagation()):((Propagation)AnnotationUtils.getAnnotationDefault(Transactional.class,"propagation"));
		int timeout = (transactional != null)?(transactional.timeout()):((Integer)AnnotationUtils.getAnnotationDefault(Transactional.class,"timeout"));
		
		if (propagation != Propagation.SUPPORTS)
			transaction = getSession().getTransaction();
		if (propagation == Propagation.NEVER) {
			if (transaction != null && transaction.isActive())
				throw new PersistenceException("Service with propagation '"+propagation+"' cannot execute within an active transaction");
		}
		else if (propagation == Propagation.NOT_SUPPORTED) {
			if (transaction != null && transaction.isActive())
				throw new PersistenceException("Service with propagation '"+propagation+"': unable to suspend the active transaction");
		}
		else if (propagation == Propagation.MANDATORY) {
			if (transaction == null || !transaction.isActive())
				throw new PersistenceException("Service with propagation '"+propagation+"' should execute within an active transaction");
		}
		else if (propagation == Propagation.REQUIRED) {
			if (transaction == null)
				throw new PersistenceException("Service with propagation '"+propagation+"': unable to obtain a transaction");
			if (!transaction.isActive()) {
				beginTransaction(getSession(), transaction,timeout);
				newTransaction = true;
			}
		}
		else if (propagation == Propagation.REQUIRES_NEW) {
			if (transaction == null)
				throw new PersistenceException("Service with propagation '"+propagation+"': unable to obtain a transaction");
			if (transaction.isActive())
				throw new PersistenceException("Service with propagation '"+propagation+"': unable to suspend the active transaction");
			beginTransaction(getSession(), transaction,timeout);
			newTransaction = true;
		}

		try {
			// execute the service
			T result = command.execute();
			if (result == null)
				return (null);

			// close transaction if we started it
			if (newTransaction && transaction.isActive())
				transaction.commit();

			// detach the result before returning it to the presentation tier
			if (oldTier == Tier.PRESENTATION)
				detachResult((Serializable)result);

			return (result);
		} catch (Throwable e) {
			// rollback transaction
			if (transaction != null && transaction.isActive())
				// if we started it, rollback
				if (newTransaction)
					transaction.rollback();
				// if we participate in an existing transaction, mark it for rollback 
				//else
				//	transaction.setRollbackOnly();

			throw e;
		} finally {
			// close the session if we're returning to the presentation tier
			if (oldTier == Tier.PRESENTATION)
				getSession().close();
			
			// restore the old tier
			RequestContext.get().setTier(oldTier);
		}
	}

	/**
	 * @return Returns the hibernate sesion to be used on the command...
	 */
	protected Session getSession() {
		return HibernateUtil.getSessionFactory(getPersistenceUnit()).getCurrentSession();
	}
	
	/**
	 * @return the persistenceUnit
	 */
	public IPersistenceUnit getPersistenceUnit() {
		return RequestContext.get().getPersistenceUnit();
	}
	
	public  <T> T execute(ICommand<T> command)  {
		if (command == null)
			return (null);

		//String oldPersistenceUnit = null;
		try {				
			// call annotated beforeExecute
			doBeforeExecute(command);
			// execute service 
			T result = doExecute(command);
			// call annotated afterExecute
			return (doAfterExecute(command,result));
		} catch (Throwable e) {
			if (e instanceof PersistenceException)
				throw (PersistenceException)e;
			// get the root exception
			e = ExceptionUtils.getRootCause(e);
			// wrap non-runtime exceptions in a BusinessException
			if (e instanceof RuntimeException)
				throw (RuntimeException) e;
			throw new PersistenceException(PersistenceException.COMMAND_EXECUTION,e);
		} finally {
			// restore the old persistenceUnit if it was changed
			//if (!StringUtils.isEmpty(oldPersistenceUnit))
				//PersistenceRequestContext.setPersistenceUnit(oldPersistenceUnit);
			
			// restore the old mode
			//BusinessRequestContext.setMode(oldMode);
		}
	}
}