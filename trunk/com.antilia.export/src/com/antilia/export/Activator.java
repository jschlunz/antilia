package com.antilia.export;

import com.antilia.common.osgi.AggregatedActivator;

public class Activator extends AggregatedActivator {

		public Activator() {
		
			addServiceActivator(new ExportMenuFactoryServiceTracker());
		
		}
}
