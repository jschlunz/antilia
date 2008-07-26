/**
 * This software is provided as IS by Antilia-Soft SL.
 * Copyright 2006-2007.
 */
package com.antilia.common.osgi;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;

/**
 * Abstract base class for BundleActivators wanting to register several 
 * IServiceActivators..
 *
 * @author Ernesto Reinaldo Barreiro (reiern70@gmail.com)
 */
public abstract class AggregatedActivator implements BundleActivator {

	private AggregatedServiceActivator aggregatedServiceActivator;
	
	public AggregatedActivator() {
		aggregatedServiceActivator = new AggregatedServiceActivator();
	}
	
	/*
	 * (non-Javadoc)
	 * @see org.osgi.framework.BundleActivator#start(org.osgi.framework.BundleContext)
	 */
	public void start(BundleContext context) throws Exception {
		aggregatedServiceActivator.start(context);		
	}

	/*
	 * (non-Javadoc)
	 * @see org.osgi.framework.BundleActivator#stop(org.osgi.framework.BundleContext)
	 */
	public void stop(BundleContext context) throws Exception {
		aggregatedServiceActivator.stop(context);		
	}
	
	/**
	 * Adds a service activator.
	 * 
	 * @param activator
	 * @return
	 */
	public void addServiceActivator(IServiceActivator activator) {
		aggregatedServiceActivator.addServiceActivator(activator);
	}

	/**
	 * Removes a service activator.
	 * 
	 * @param activator
	 * @return
	 */
	public void removeServiceActivator(IServiceActivator activator) {
		aggregatedServiceActivator.removeServiceActivator(activator);
	}

}
