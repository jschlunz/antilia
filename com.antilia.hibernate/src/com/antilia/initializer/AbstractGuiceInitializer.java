/**
 * 
 */
package com.antilia.initializer;

import com.google.inject.Binder;
import com.google.inject.Module;


/**
 * @author Ernesto Reinaldo Barreiro (reirn70@gmail.com)
 *
 */
public abstract class AbstractGuiceInitializer implements Initializer<Module> {

	
	protected AbstractGuiceInitializer() {
	}
	
	public final Module intialize() {
		return new Module(){
			public void configure(Binder binder) {
				AbstractGuiceInitializer.this.configure(binder);
			}			
		};
	}
	
	/**
	 * Override this method to implement actual initialization.
	 * 
	 * @param binder
	 */
	protected  abstract void configure(Binder binder);
}
