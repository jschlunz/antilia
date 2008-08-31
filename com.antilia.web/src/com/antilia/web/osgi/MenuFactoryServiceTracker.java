/**
 * This software is provided as IS by Antilia-Soft SL.
 * Copyright 2006-2007.
 */
package com.antilia.web.osgi;

import org.osgi.framework.BundleContext;

import com.antilia.common.osgi.Aggregator;
import com.antilia.common.osgi.IServiceActivator;
import com.antilia.common.osgi.TypedServiceTracker;
import com.antilia.web.button.IMenuFactoryPopulator;
import com.antilia.web.button.IMenuItem;

/**
 * 
 *
 * @author Ernesto Reinaldo Barreiro (reiern70@gmail.com)
 *
 */
public class MenuFactoryServiceTracker extends Aggregator<IMenuFactoryPopulator> implements IServiceActivator {

	private TypedServiceTracker<IMenuFactoryService> tracker;
	/**
	 * @param context
	 * @param clazz
	 */
	public MenuFactoryServiceTracker() {
	}

	@Override
	public boolean isMandatory() throws Exception {
		return false;
	}
	
	@Override
	public void start(BundleContext context) throws Exception {
		tracker = new TypedServiceTracker<IMenuFactoryService>(context, IMenuFactoryService.class) {
			
			@Override
			public void onAddingService(IMenuFactoryService service) {
				for(IMenuFactoryPopulator source : elements()) {
					service.add(source);
				}
			}
			
			@Override
			public void onServiceRemoved(IMenuFactoryService service) {
				for(IMenuFactoryPopulator source : elements()) {
					service.delete(source);
				}
			}
			
		};
		tracker.open();
	}
	
	@Override
	public void stop(BundleContext context) throws Exception {
		tracker.close();
		tracker = null;
	}
}
