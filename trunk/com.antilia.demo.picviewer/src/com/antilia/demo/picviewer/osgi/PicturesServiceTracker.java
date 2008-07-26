/**
 * This software is provided as IS by Antilia-Soft SL.
 * Copyright 2006-2007.
 */
package com.antilia.demo.picviewer.osgi;

import org.osgi.framework.BundleContext;

import com.antilia.common.osgi.Aggregator;
import com.antilia.common.osgi.IServiceActivator;
import com.antilia.common.osgi.TypedServiceTracker;

/**
 * 
 *
 * @author Ernesto Reinaldo Barreiro (reiern70@gmail.com)
 *
 */
public class PicturesServiceTracker extends Aggregator<IPicturesSource> implements IServiceActivator {

	private TypedServiceTracker<IPicturesService> tracker;
	
	public PicturesServiceTracker() {		
		super();
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
		tracker = new TypedServiceTracker<IPicturesService>(context, IPicturesService.class) {
						
			@Override
			public void onAddingService(IPicturesService service) {
				for(IPicturesSource source : elements()) {
					service.addPicturesSource(source);
				}
			}
			
			@Override
			public void onServiceRemoved(IPicturesService service) {
				for(IPicturesSource source : elements()) {
					service.removePicturesSource(source);
				}
			}
			
		};
		tracker.open();
	}

	/* (non-Javadoc)
	 * @see com.antilia.common.osgi.IServiceActivator#stop(org.osgi.framework.BundleContext)
	 */
	public void stop(BundleContext context) throws Exception {
		tracker.close();
		tracker = null;
	}

}
