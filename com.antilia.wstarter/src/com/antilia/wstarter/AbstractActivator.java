/**
 * This software is provided as IS by Antilia-Soft SL.
 * Copyright 2008.
 */
package com.antilia.wstarter;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;

/**
 * 
 * @author Ernesto Reinaldo Barreiro (reiern70@gmail.com)
 */
public abstract class AbstractActivator implements BundleActivator {

	private ServiceGroupActivator groupActivator;
	
	public AbstractActivator() {
		groupActivator = new ServiceGroupActivator();
	}
	
	/*
	 * (non-Javadoc)
	 * @see org.osgi.framework.BundleActivator#start(org.osgi.framework.BundleContext)
	 */
	public void start(BundleContext context) throws Exception {
		groupActivator.start(context);		
	}

	/*
	 * (non-Javadoc)
	 * @see org.osgi.framework.BundleActivator#stop(org.osgi.framework.BundleContext)
	 */
	public void stop(BundleContext context) throws Exception {
		groupActivator.stop(context);		
	}
	
	/**
	 * Adds a service activator.
	 * 
	 * @param activator
	 * @return
	 */
	public void addServiceActivator(IServiceActivator activator) {
		groupActivator.addServiceActivator(activator);
	}

	/**
	 * Removes a service activator.
	 * 
	 * @param activator
	 * @return
	 */
	public void removeServiceActivator(IServiceActivator activator) {
		groupActivator.removeServiceActivator(activator);
	}

}
