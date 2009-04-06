/**
 * 
 */
package com.antilia.web.resources;

import java.util.HashMap;
import java.util.Map;

import org.apache.wicket.ResourceReference;

/**
 * @author Ernesto Reinaldo Barreiro (reirn70@gmail.com)
 *
 */
public class ResourceLocator {
	
	private Map<String, ResourceReference> resources = new HashMap<String, ResourceReference>();
	
	private static final ResourceLocator instance = new ResourceLocator();

	public static ResourceLocator getInstance() {
		return instance;
	}

	public ResourceLocator registerResource(String name, ResourceReference reference) {
		resources.put(name, reference);
		return this;
	}
	
	public ResourceLocator deregisterResource(String name) {
		resources.remove(name);
		return this;
	}
	
	public ResourceReference getResource(String name) {
		return resources.get(name);
	}
}
