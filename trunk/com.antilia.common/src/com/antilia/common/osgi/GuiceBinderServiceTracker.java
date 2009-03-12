package com.antilia.common.osgi;

import java.util.ArrayList;
import java.util.List;

import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;
import org.osgi.util.tracker.ServiceTracker;

import com.google.inject.Module;

/**
 * Service tracker for the application GuiceBinder service.
 * 
 * @author Ernesto Reinaldo Barreiro (reiern70@gmail.com)
 */
public class GuiceBinderServiceTracker extends ServiceTracker {

	private List<Module> modules = new ArrayList<Module>();
	
	public GuiceBinderServiceTracker(BundleContext context) {
		super(context, IGuiceModuleBinderService.class.getName(), null);
	}

	public Object addingService(ServiceReference reference) {
		IGuiceModuleBinderService moduleBinderService = (IGuiceModuleBinderService)context.getService(reference);
		moduleBinderService.registerModules(modules);
		return moduleBinderService;
	}		
	
	public void removedService(ServiceReference reference, Object service) {
		// listener = (IGuiceModuleBinderService)service;
		// do nothing for the moment!
		super.removedService(reference, service);
	}
	
	/**
	 * @see com.isencia.sherpa.commons.osgi.IServiceActivator#isMandatory()
	 */
	public boolean isMandatory() {
		return false;
	}

	public GuiceBinderServiceTracker addModule(Module module) {
		this.modules.add(module);
		return this;
	}
}
