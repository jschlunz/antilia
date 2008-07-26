/**
 * This software is provided as IS by Antilia-Soft SL.
 * Copyright 2006-2007.
 */
package com.antilia.common.osgi;

import org.osgi.framework.BundleContext;

/**
 * 
 * Interface defining a service activator: i.e. class capable of registering/de-registering services.
 * 
 * @author Ernesto Reinaldo Barreiro (reiern70@gmail.com)
 *
 */
public interface IServiceActivator {
	
	/**
	 * Starts the service(s).
	 * 
	 * @param context
	 * @throws Exception
	 */
	public void start(BundleContext context) throws Exception;
	
	/**
	 * Stops the service(s).
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
