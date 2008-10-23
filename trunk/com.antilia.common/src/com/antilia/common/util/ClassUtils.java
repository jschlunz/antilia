/**
 * This software is provided as IS by Antilia-Soft SL.
 * Copyright 2006-2007.
 */
package com.antilia.common.util;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;

public class ClassUtils {
	
	public static String getUnqualifiedName(Class<?> clazz) {
		if (clazz == null)
			return "";
		String name = clazz.getName();
		return (name.substring(name.lastIndexOf('.') + 1));
	}
	
	/**
	 * @param clz
	 * @param expression
	 * @return introspected field
	 */
	public static Field findField(Class<?> clz, String expression) {
		Field field = null;
		try {
			field = clz.getField(expression);
		} catch (Exception e) {
			Class<?> tmp = clz;
			while(tmp != null && tmp != Object.class) {
				Field[] fields = tmp.getDeclaredFields();
				for (int i = 0; i < fields.length; i++) {
					if(fields[i].getName().equals(expression)) {
						fields[i].setAccessible(true);
						return fields[i];
					}
				}
				tmp = tmp.getSuperclass();
			}
		}
		return field;
	}
	
	
	public static <T> T  createInstance(Class<T> clz, String id) {
		try {
			Constructor<T> constructor = clz.getConstructor(String.class);
			return constructor.newInstance(id);
		} catch (Exception e) {
			// do nothing and return null
		}
		return null;
	}
}