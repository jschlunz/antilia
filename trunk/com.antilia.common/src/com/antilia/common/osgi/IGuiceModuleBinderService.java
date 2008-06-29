package com.antilia.common.osgi;

import com.google.inject.Module;

/**
 * IGuiceService
 *
 *
 * @author Ernesto Reinaldo Barreiro (reiern70@gmail.com)
 */
public interface IGuiceModuleBinderService {
	
	public IGuiceModuleBinderService registerModules(Iterable<Module> modules);
}
