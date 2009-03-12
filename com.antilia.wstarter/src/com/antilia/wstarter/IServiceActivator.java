/**
 * This software is provided as IS by Antilia-Soft SL.
 * Copyright 2008.
 */
package com.antilia.wstarter;

import org.osgi.framework.BundleContext;

/**
 * @author Ernesto Reinaldo Barreiro (reiern70@gmail.com)
 *
 */
public interface IServiceActivator {
	
	/**
	 * Starts the service.
	 * 
	 * @param context
	 * @throws Exception
	 */
	public void start(BundleContext context) throws Exception;
	
	/**
	 * Stops the service
	 * 
	 * @param context
	 * @throws Exception
	 */
	public void stop(BundleContext context) throws Exception;
	
	/**
	 * Returns true if the service is Mandatory, false otherwise.
	 * If the service is mandatory then if it fails to start the 
	 * whole bundle will fail to start.
	 * 
	 * @return
	 * @throws Exception
	 */
	public boolean isMandatory() throws Exception;
	
}
