/**
 * 
 */
package com.antilia.common.misc;

import com.antilia.common.FrameworkException;

/**
 * @author Ernesto Reinaldo Barreiro (reirn70@gmail.com)
 *
 */
public class InstanceHolder<T> {

	private T instance;
	
	private Class<? extends T> clazz;
	
	/**
	 * 
	 */
	public InstanceHolder(Class<? extends T> clazz) {
		if(clazz == null)
			throw new IllegalArgumentException("Factoied class cannot be null");
		this.clazz = clazz;
	}

	/**
	 * @return the instance
	 */
	public T getInstance() {
		if(instance == null) {
			instance  = newInstance();
		}
		return instance;
	}

	protected T newInstance() {
		try {
			return this.clazz.newInstance();
		} catch (Exception e) {
			throw new FrameworkException("XXX", e);
		}
	}
}
