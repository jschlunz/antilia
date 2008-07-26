/**
 * This software is provided as IS by Antilia-Soft SL.
 * Copyright 2006-2007.
 */
package com.antilia.hibernate.command;


/**
 * A command that returns an object  of type <code>R</code> as result  of its execution...
 *
 * @author Ernesto Reinaldo Barreiro (reiern70@gmail.com)
 *
 */
public interface ICommand<R extends Object> {

	/**
	 * The method performing the logic of the command. 
	 * @return returns an object of Type <code>R</code>.
	 */
	R execute() throws Throwable;
	
}
