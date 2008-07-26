/**
 * This software is provided as IS by Antilia-Soft SL.
 * Copyright 2006-2007.
 */
package com.antilia.web.resources;

import java.util.List;
import java.util.Locale;
import java.util.MissingResourceException;

import org.apache.wicket.Component;
import org.apache.wicket.resource.loader.IStringResourceLoader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.antilia.common.ILoggable;
import com.antilia.common.util.ResourceUtils;

/**
 * @author Ernesto Reinaldo Barreiro (reiern70@gmail.com)
 */
public class PackageResourceLoader implements IStringResourceLoader, ILoggable {

	private static final PackageResourceLoader instance = new PackageResourceLoader();
	
	/**
	 * 
	 */
	private PackageResourceLoader() {
	}

	/* (non-Javadoc)
	 * @see org.apache.wicket.resource.loader.IStringResourceLoader#loadStringResource(org.apache.wicket.Component, java.lang.String)
	 */
	public String loadStringResource(Component component, String key) {
		return loadStringResource(null, key, component.getLocale(),null);
	}

	
	@SuppressWarnings("unchecked")
	public String loadStringResource(Class clazz, String key, Locale locale,
			String style) {
		if (!key.contains(":")) 
    		return null;
    	
        if (locale == null) 
            locale = Locale.getDefault();
        
        List<ResourceUtils.BundleResource> resources = ResourceUtils.getBundleResources(key);
        for(ResourceUtils.BundleResource resource:resources) {
    		try {
    			String result = ResourceUtils.loadStringResource(resource.getBundle(), resource.getKey(), locale);
     			return result;
     		} catch (MissingResourceException e) {
    		}
        }
        return null;
	}
	
	public Logger getLogger() {
		return LoggerFactory.getLogger(this.getClass());
	}

	public static PackageResourceLoader getInstance() {
		return instance;
	}

}
