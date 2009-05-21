/**
 * This software is provided as IS by Antilia-Soft SL.
 * Copyright 2006-2007.
 */
package com.antilia.hibernate.command;

import java.io.Serializable;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Session;

import com.antilia.hibernate.cfg.IPersistenceUnit;
import com.antilia.hibernate.context.RequestContext;
import com.antilia.hibernate.transaction.Propagation;
import com.antilia.hibernate.transaction.Transactional;

/**
 * Base class for all persistence related commands...
 *
 *@param <E> The type of the entity associated with the command. 
 *@param <R> The type of the returned result.
 *
 * @author Ernesto Reinaldo Barreiro (reiern70@gmail.com)
 *
 */
public abstract class AbstractPersistentCommand<E extends Serializable,R extends Object> implements ICommand<R> {
	
	private static Log log = LogFactory.getLog(AbstractPersistentCommand.class);
	
	/**
	 * The persistence unit associated with the persistent command.
	 */
	private IPersistenceUnit persistenceUnit;
	
	private Class<E> persistentClass;
	
	public AbstractPersistentCommand(Class<E> persistentClass) {		
		this.persistentClass = persistentClass;
	}
	
	@Transactional(propagation=Propagation.REQUIRED)
	public final R execute() throws Throwable {		 
        //beginTransaction(); 
		try {
        	 R result = doExecute();
        	// commitTransaction();
        	 return result;
         } catch (Throwable e) {
        	 log.error("Error executing command " + this.getClass().getName());
			//rollbackTransaction();			
			throw new CommandExecutionException(CommandExecutionException.COMMAND_EXECUTION_EXCEPTION, e);
		}
	}
	
	protected abstract R doExecute() throws Throwable;
	
	/*
	public void beginTransaction() {
		boolean isActive = getSession().getTransaction().isActive();
        if ( !isActive) {
           log.debug("Starting a database transaction");
           getSession().beginTransaction();
        }	else {
        	log.debug("joining active database transaction");
        }
	}
	*/
	
	/*
	private void commitTransaction() {
		Transaction tx = getSession().getTransaction();
		if(!tx.wasCommitted() && !tx.wasRolledBack()) {
			tx.commit();
			log.debug("transaction commited");
		} else {
			if(tx.wasCommitted()) {
				log.warn("tried to commit transaction that was commited");
			} else {
				log.warn("tried to commit rollbacked transtraction");
			}
		}
	}
	*/
	
	/*
	public void rollbackTransaction() {
		Transaction tx = getSession().getTransaction();
		if(!tx.wasCommitted() && !tx.wasRolledBack()) {
			tx.rollback();
			log.debug("transaction rolled back");
		} else {
			if(tx.wasRolledBack()) {
				log.warn("transaction was already rolled-back");
			} else {
				log.warn("tried to roll back a commited transtraction");
			}
		}
	}*/
	
	
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
		if(persistenceUnit == null)
			persistenceUnit = RequestContext.get().getPersistenceUnit();;
		return persistenceUnit;
	}



	/**
	 * @param persistenceUnit the persistenceUnit to set
	 */
	public void setPersistenceUnit(IPersistenceUnit persistenceUnit) {
		this.persistenceUnit = persistenceUnit;
	}


	/**
	 * @return the persistentClass
	 */
	public Class<E> getPersistentClass() {
		return persistentClass;
	}


	/**
	 * @param persistentClass the persistentClass to set
	 */
	public void setPersistentClass(Class<E> persistentClass) {
		this.persistentClass = persistentClass;
	}

}
