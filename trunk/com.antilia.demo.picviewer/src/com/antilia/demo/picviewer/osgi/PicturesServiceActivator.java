/**
 * This software is provided as IS by Antilia-Soft SL.
 * Copyright 2006-2007.
 */
package com.antilia.demo.picviewer.osgi;

import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;

import com.antilia.common.osgi.IServiceActivator;

/**
 * 
 *
 * @author Ernesto Reinaldo Barreiro (reiern70@gmail.com)
 *
 */
public class PicturesServiceActivator implements IServiceActivator {

	/**
	 * 
	 */
	public PicturesServiceActivator() {
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
		context.registerService(IPicturesService.class.getName(), PicturesService.getInstance() , null);
	}

	/* (non-Javadoc)
	 * @see com.antilia.common.osgi.IServiceActivator#stop(org.osgi.framework.BundleContext)
	 */
	public void stop(BundleContext context) throws Exception {
		ServiceReference reference = context.getServiceReference(IPicturesService.class.getName());
		if(reference != null)
			context.ungetService(reference);
	}

}
