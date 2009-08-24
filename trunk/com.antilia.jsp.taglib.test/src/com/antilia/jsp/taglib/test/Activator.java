package com.antilia.jsp.taglib.test;

import com.antilia.common.osgi.AggregatedActivator;

/**
 * 
 * @author Ernesto Reinaldo Barreiro (reirn70@gmail.com)
 *
 */
public class Activator extends AggregatedActivator {

	public Activator() {
		addServiceActivator(new TestServiceActivator());
	}
}
