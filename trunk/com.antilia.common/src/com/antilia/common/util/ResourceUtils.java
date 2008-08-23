/**
 * This software is provided as IS by Antilia-Soft SL.
 * Copyright 2006-2007.
 */
package com.antilia.common.util;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.MissingResourceException;
import java.util.ResourceBundle;
import java.util.StringTokenizer;

public class ResourceUtils {
	
	public static final String DEFAULT_BUNDLE_NAME = "Resources";
	public static final String BUNDLE_KEY_SEPARATOR = ":";
	public static final String KEYS_SEPARATOR = ";";
	public static final String PROPERTY_SEPARATOR = ".";

	public static class BundleResource implements Serializable {
		protected String bundle;
		protected String key;
		
		private static final long serialVersionUID = 1L;
		
		/*
		 * Build a Resource parsing a resourceKey in the format bundleName:key
		 */
		public BundleResource(String resourceKey) {
			int index = resourceKey.indexOf(BUNDLE_KEY_SEPARATOR);
			if(index == -1)
				throw new IllegalArgumentException("Resource key should contain a pair bundle: key");
			this.bundle = resourceKey.substring(0,index);
			this.key = resourceKey.substring(index+1);
		}
		
		public BundleResource(String bundle,String key) {
			this.bundle = bundle;
			this.key = key;
		}

		public String getBundle() {
			return bundle;
		}

		public String getKey() {
			return key;
		}
		
		public String toString() {
			return bundle + BUNDLE_KEY_SEPARATOR + key;
		}
	}
	
	public static List<ResourceUtils.BundleResource> getBundleResources(String keys) {
		ArrayList<ResourceUtils.BundleResource> resources = new ArrayList<ResourceUtils.BundleResource>();
		
		StringTokenizer tokenizer = new StringTokenizer(keys, KEYS_SEPARATOR);
		while(tokenizer.hasMoreTokens()) {
			String key = tokenizer.nextToken();
			if (key.contains(BUNDLE_KEY_SEPARATOR))
				resources.add(new ResourceUtils.BundleResource(key));
		}
		return resources;
	}
	
	public static String getBundle(Class<?> contextClass) {
		return (contextClass.getName().replace("/","."));
	}

	public static String getDefaultBundle(Class<?> contextClass) {
		StringBuilder bundle = new StringBuilder();
		bundle.append(contextClass.getPackage().getName().replace("/","."));
		bundle.append(".");
		bundle.append(DEFAULT_BUNDLE_NAME);
		return bundle.toString();
	}
	
	public static String getResourceKey(String bundle,String resourceKey) {
		return bundle + BUNDLE_KEY_SEPARATOR + resourceKey;
	}
	
	public static boolean isResourceKey(String resourceKey) {
		if (resourceKey == null)
			return false;
		return resourceKey.indexOf(BUNDLE_KEY_SEPARATOR) != -1;
	}
	
	public static String appendResourceKey(String resourceKey1,String resourceKey2) {
		if (StringUtils.isEmpty(resourceKey1))
			return resourceKey2;
		if (StringUtils.isEmpty(resourceKey2))
			return resourceKey1;
		StringBuilder key = new StringBuilder();
		key.append(resourceKey1);
		key.append(KEYS_SEPARATOR);
		key.append(resourceKey2);
		return key.toString();
	}
	
	public static String getBeanResourceKey(Class<?> beanClass) {
		StringBuilder key = new StringBuilder();
		
		key.append(getDefaultBundle(beanClass));
		key.append(BUNDLE_KEY_SEPARATOR);
		key.append(ClassUtils.getUnqualifiedName(beanClass));
		if(beanClass.getSuperclass()!= null && !beanClass.getSuperclass().equals(Object.class)) {
			key.append(KEYS_SEPARATOR);
			key.append(getBeanResourceKey(beanClass.getSuperclass()));
		}
	
		return key.toString();
	}
		
	public static String getPropertyResourceKey(Class<?> beanClass,String propertyPath) {
		StringBuilder key = new StringBuilder();
		
		key.append(getDefaultBundle(beanClass));
		key.append(BUNDLE_KEY_SEPARATOR);
		key.append(ClassUtils.getUnqualifiedName(beanClass));		
		if(StringUtils.isEmpty(propertyPath)) {
			return key.toString();
		}			
		key.append(PROPERTY_SEPARATOR);
		key.append(propertyPath);		
		if(beanClass.getSuperclass()!=null && !beanClass.getSuperclass().equals(Object.class) &&
			ReflectionUtils.isFieldDeclared(beanClass.getSuperclass(), propertyPath)) {
			key.append(KEYS_SEPARATOR);
			key.append(getPropertyResourceKey(beanClass.getSuperclass(), propertyPath));
		}
		
		// property name is a path
		if(propertyPath.indexOf('.') !=-1) {
			try {
				Field field = ReflectionUtils.getField(beanClass, propertyPath);
				beanClass = field.getDeclaringClass();
				propertyPath = field.getName();
				key.append(KEYS_SEPARATOR);
				key.append(getPropertyResourceKey(beanClass, propertyPath));
			} catch (NoSuchFieldException e) {
				return(null);
			}
		}
		else {
			try {
				// Field Type Entity
				Field field = ReflectionUtils.getField(beanClass, propertyPath);
				Class<?> type = ReflectionUtils.getTargetType(field);
				if (!String.class.isAssignableFrom(type) &&
					!Number.class.isAssignableFrom(type) &&
					!Boolean.class.isAssignableFrom(type) &&
					!type.isSynthetic() &&
					!type.isPrimitive()) {
					key.append(KEYS_SEPARATOR);
					key.append(getDefaultBundle(beanClass));
					key.append(BUNDLE_KEY_SEPARATOR);
					key.append(ClassUtils.getUnqualifiedName(type));
				}
			} catch (NoSuchFieldException e) {
				return(null);
			}
		}
	
		return key.toString();
	}
	
	public static String loadStringResource(String bundleName,String resourceKey,Locale locale,Object... arguments) {
		final ResourceBundle bundle = ResourceBundle.getBundle(bundleName, locale);
		if (bundle == null)
			throw new MissingResourceException("Bundle not found",ResourceBundle.class.getName(),resourceKey);
		String value = bundle.getString(resourceKey);
		return (MessageFormat.format(value, arguments));
	}
	
	public static String getStringResource(Class<?> contextClass,String resourceKey,Locale locale,Object... arguments) {
		
		String bundleName = null;
		if (resourceKey.indexOf(BUNDLE_KEY_SEPARATOR) != -1) {
			bundleName = resourceKey.substring(0,resourceKey.indexOf(BUNDLE_KEY_SEPARATOR));
			resourceKey = resourceKey.substring(resourceKey.indexOf(BUNDLE_KEY_SEPARATOR)+1);
		}
		else
			bundleName = getDefaultBundle(contextClass);
		
		try {
			return loadStringResource(bundleName, resourceKey, locale, arguments);
		}
		catch(MissingResourceException e) {
			return resourceKey;
		}
	}

	
	public static String getStringResource(Enum<?> enumValue,Locale locale) {
		String bundleName = getDefaultBundle(enumValue.getClass());
		String resourceKey = ClassUtils.getUnqualifiedName(enumValue.getDeclaringClass())+"."+enumValue.toString();
		
		try {
			return loadStringResource(bundleName, resourceKey, locale);
		}
		catch(MissingResourceException e) {
			return enumValue.toString();
		}
	}
}