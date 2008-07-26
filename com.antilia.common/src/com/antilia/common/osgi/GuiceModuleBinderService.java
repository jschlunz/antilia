package com.antilia.common.osgi;

import java.util.ArrayList;
import java.util.List;

import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.Module;

/**
 * GuiceModuleBinderService
 *
 *
 * @author Ernesto Reinaldo Barreiro (reiern70@gmail.com)
 */
public class GuiceModuleBinderService implements IGuiceModuleBinderService, IServiceActivator {

	private static final long serialVersionUID = 1L;

	private static GuiceModuleBinderService instance = new GuiceModuleBinderService();
	
	private GuiceModuleBinderService() {		
	}	
	
	private List<Module> modules = new ArrayList<Module>();
	
	private static Injector injector;
	
	/**
	 * @see com.isencia.sherpa.commons.osgi.IGuiceModuleBinderService#registerModule(com.google.inject.Module)
	 */
	public IGuiceModuleBinderService registerModules(Iterable<Module> modules) {
		synchronized(this) {
			if(modules != null) {
				for(Module module: modules) {
					this.modules.add(module);
				}
			}
			// setting injector to null will fore recreating it next time.
			injector = null;
		}
		return this;
	}

	protected final Injector createInjector() {
		return Guice.createInjector(this.modules);
	}
	
	/**
	 * @see com.isencia.sherpa.commons.osgi.IServiceActivator#isMandatory()
	 */
	public boolean isMandatory() {
		return false;
	}

	/**
	 * @see com.isencia.sherpa.commons.osgi.IServiceActivator#start(org.osgi.framework.BundleContext)
	 */
	public void start(BundleContext context) throws Exception {
		context.registerService(IGuiceModuleBinderService.class.getName(), GuiceModuleBinderService.getInstance(), null);
	}

	/**
	 * @see com.isencia.sherpa.commons.osgi.IServiceActivator#stop(org.osgi.framework.BundleContext)
	 */
	public void stop(BundleContext context) throws Exception {
		ServiceReference reference = context.getServiceReference(IGuiceModuleBinderService.class.getName());
		if(reference != null) {
			context.ungetService(reference);
		}
	}

	public static Injector getInjector() {
		if(injector == null) {
			injector = GuiceModuleBinderService.getInstance().createInjector();
		}
		return injector;
	}

	public static GuiceModuleBinderService getInstance() {
		return instance;
	}

}
