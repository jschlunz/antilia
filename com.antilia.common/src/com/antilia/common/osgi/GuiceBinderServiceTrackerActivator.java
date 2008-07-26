package com.antilia.common.osgi;

import java.util.ArrayList;
import java.util.List;

import org.osgi.framework.BundleContext;

import com.google.inject.Module;


/**
 * Service activator for the ApplicationListenerTracker.
 *
 * @author Ernesto Reinaldo Barreiro (reiern70@gmail.com)
 */
public class GuiceBinderServiceTrackerActivator implements IServiceActivator {

	private static final long serialVersionUID = 1L;

	public GuiceBinderServiceTrackerActivator() {}
	
	private List<Module> modules = new ArrayList<Module>();

	public GuiceBinderServiceTrackerActivator addModule(Module module){
		modules.add(module);		
		return this;
	}
	
	public GuiceBinderServiceTrackerActivator removeModule(Module module){		
		modules.remove(module);		
		return this;
	}
	
	GuiceBinderServiceTracker tracker;
	
	/**
	 * @see com.isencia.sherpa.commons.osgi.IServiceActivator#isMandatory()
	 */
	public boolean isMandatory() {
		return true;
	}
	/**
	 * @see com.isencia.sherpa.commons.osgi.IServiceActivator#start(org.osgi.framework.BundleContext)
	 */
	public void start(BundleContext context) throws Exception {
		tracker = new GuiceBinderServiceTracker(context);
		for(Module module: modules)
			tracker.addModule(module);
		tracker.open();
	}

	/**
	 * @see com.isencia.sherpa.commons.osgi.IServiceActivator#stop(org.osgi.framework.BundleContext)
	 */
	public void stop(BundleContext context) throws Exception {
		tracker.close();
		tracker = null;
	}	
	
}
