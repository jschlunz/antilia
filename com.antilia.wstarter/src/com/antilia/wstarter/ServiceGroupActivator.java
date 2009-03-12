/**
 * This software is provided as IS by Antilia-Soft SL.
 * Copyright 2006-2007.
 */
package com.antilia.wstarter;

import java.util.ArrayList;
import java.util.List;

import org.osgi.framework.BundleContext;

/**
 * @author Ernesto Reinaldo Barreiro (ereinald@antilia.com)
 */
public class ServiceGroupActivator implements IServiceActivator {

	List<IServiceActivator> activators;
	/**
	 * 
	 */
	public ServiceGroupActivator() {
		activators = new ArrayList<IServiceActivator>();
	}

	/* (non-Javadoc)
	 * @see com.antilia.common.osgi.IServiceActivator#isMandatory()
	 */
	public boolean isMandatory() throws Exception {
		return true;
	}

	/* (non-Javadoc)
	 * @see com.antilia.common.osgi.IServiceActivator#start(org.osgi.framework.BundleContext)
	 */
	public void start(BundleContext context) throws Exception {
		for(IServiceActivator activator:activators) {
			try {
				activator.start(context);
			} catch (Throwable e) {
				if(activator.isMandatory())
					throw new OsgiException(OsgiException.SERVICE_ACTIVATION_FAILURE, e);
			}
		}
	}

	/* (non-Javadoc)
	 * @see com.antilia.common.osgi.IServiceActivator#stop(org.osgi.framework.BundleContext)
	 */
	public void stop(BundleContext context) throws Exception {
		for(IServiceActivator activator:activators) {
			try {
				activator.stop(context);
			} catch (Throwable e) {
			}
		}
	}
	
	/**
	 * Adds a service activator.
	 * 
	 * @param activator
	 * @return
	 */
	public ServiceGroupActivator addServiceActivator(IServiceActivator activator) {
		if(activators == null)
			activators = new ArrayList<IServiceActivator>();
		activators.add(activator);
		return this;
	}

	/**
	 * Removes a service activator.
	 * 
	 * @param activator
	 * @return
	 */
	public ServiceGroupActivator removeServiceActivator(IServiceActivator activator) {
		if(activators == null)
			return this;
		activators.remove(activator);
		return this;
	}
}
