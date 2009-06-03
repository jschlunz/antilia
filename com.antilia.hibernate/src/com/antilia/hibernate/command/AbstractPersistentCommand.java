/**
 * This software is provided as IS by Antilia-Soft SL.
 * Copyright 2007-2008.
 */
package com.antilia.hibernate.command;

import java.io.Serializable;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

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
public abstract class AbstractPersistentCommand<E extends Serializable,R extends Object> extends BasePersistentCommand<E,R> {
	
	private static Log log = LogFactory.getLog(AbstractPersistentCommand.class);
	
	
	public AbstractPersistentCommand(Class<E> persistentClass) {		
		super(persistentClass);
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
}
