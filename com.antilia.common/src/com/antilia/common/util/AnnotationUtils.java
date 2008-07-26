package com.antilia.common.util;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

/**
 * 
 *
 * @author Ernesto Reinaldo Barreiro (reiern70@gmail.com)
 *
 */
public class AnnotationUtils {

	public static Method findAnnotatedMethod(Class<?> clazz, Class<? extends Annotation> annotationClass) {
		for (Method method : clazz.getMethods())
			if( method.isAnnotationPresent(annotationClass))
				return(method);
		return(null);		
	}
	
	public static List<Method> findAnnotatedMethods(Class<?> clazz, Class<? extends Annotation> annotationClass) {
		Method[] methods = clazz.getMethods();
		List<Method> annotatedMethods = new ArrayList<Method>(methods.length);
		for (Method method : methods) {
			if( method.isAnnotationPresent(annotationClass)){
				annotatedMethods.add(method);
			}
		}
		return annotatedMethods;		
	}
	
	public static Field[] findAnnotatedFields(Class<?> clazz, Class<? extends Annotation> annotationClass) {
		Field[] declaredFields = clazz.getDeclaredFields();
		List<Field> annotatedFields = new ArrayList<Field>(declaredFields.length);
		for (Field field : declaredFields) {
			if( field.isAnnotationPresent(annotationClass)){
				annotatedFields.add(field);
			}
		}
		return annotatedFields.toArray(new Field[annotatedFields.size()]);		
	}
	
	public static Annotation[] findFieldAnnotations(Class<?> clazz,String fieldName) throws NoSuchFieldException {
		Field field = clazz.getDeclaredField(fieldName);
		return field.getAnnotations();
	}
	
	public static <T extends Annotation> T findFieldAnnotation(Class<?> clazz,String fieldName, Class<T> annotationClass) throws NoSuchFieldException {
		Field field = clazz.getDeclaredField(fieldName);
		return field.getAnnotation(annotationClass);
	}
	
	@SuppressWarnings("unchecked")
	public static<T> T getAnnotationDefault(Class<? extends Annotation> annotationClass,String element) throws Exception {
		Method method = annotationClass.getMethod(element,(Class[])null);
		return((T)method.getDefaultValue());
	}

	@SuppressWarnings("unchecked")
	public static<T> T getAnnotationValue(Class<?> clazz,Class<? extends Annotation> annotationClass,String element) throws Exception {
		Annotation annotation = clazz.getAnnotation(annotationClass);
		Method method = annotationClass.getMethod(element,(Class[])null);
		if (annotation == null)
			return((T)method.getDefaultValue());
		return((T)method.invoke(annotation,(Object[])null));
	}

	@SuppressWarnings("unchecked")
	public static<T> T getFieldAnnotationValue(Class<?> clazz,String fieldName,Class<? extends Annotation> annotationClass,String element) throws Exception {
		Annotation annotation = findFieldAnnotation(clazz,fieldName,annotationClass);
		Method method = annotationClass.getMethod(element,(Class[])null);
		if (annotation == null)
			return((T)method.getDefaultValue());
		return((T)method.invoke(annotation,(Object[])null));
	}

	public static boolean isFieldAnnotationPresent(Class<?> clazz, String fieldName, Class<? extends Annotation> annotationClass) throws NoSuchFieldException {
		Field field = clazz.getDeclaredField(fieldName);
		return(field.isAnnotationPresent(annotationClass));
	}
}
