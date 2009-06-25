package com.antilia.hibernate.util;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.util.List;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Id;

import com.antilia.common.util.AnnotationUtils;
import com.antilia.common.util.FileUtils;
import com.antilia.common.util.QueryUtils;
import com.antilia.common.util.ReflectionUtils;
import com.antilia.hibernate.PersistenceException;
import com.antilia.hibernate.cfg.IPersistenceSet;

/**
 * 
 * 
 *
 * @author Ernesto Reinaldo Barreiro (reiern70@gmail.com)
 *
 */
public class EntityUtils extends QueryUtils {
	
	public static boolean isEntity(Serializable serializable) {
		if(serializable == null)
			return false;
		Class<?> clazz = serializable.getClass();
		return isEntity(clazz);
	}
	
	public static boolean isEntity(Class<?> clazz) {
		// test if it is either JPA annotated class or a Hibernate annotated entity.
		if(clazz.getAnnotation(Entity.class) != null) {
			return true;
		} else {
			return clazz.getAnnotation(org.hibernate.annotations.Entity.class) != null;
		}
	}
	
	public static void addEntitiesInSamePackageAs(Class<?> entityClass, IPersistenceSet entitiesSet) {
		try {
			List<Class<?>> classes = FileUtils.getClassesInSamePackage(entityClass);
			for(Class<?> clazz: classes) {
				if(EntityUtils.isEntity(clazz))
					entitiesSet.addEntityClass(clazz);
			}
		}  catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static Object getKeyValue(Object entity) {
		if (entity == null)
			return null;
		Field[] fields = AnnotationUtils.findAnnotatedFields(entity.getClass(),Id.class);
		if (fields == null || fields.length == 0) {
			fields = AnnotationUtils.findAnnotatedFields(entity.getClass(),EmbeddedId.class);
		}			
		if (fields != null && fields.length > 0)
			try {
				fields[0].setAccessible(true);
				return fields[0].get(entity);
			} catch (IllegalAccessException e) {
				throw new PersistenceException(PersistenceException.KEY_VALUE_ILLEGAL_ACCESS,e);
			}
		return null;
	}
		
	public static Object getPropertyValue(Object bean,String propertyPath) throws NoSuchFieldException {
		if (bean == null)
				throw new IllegalArgumentException("bean cannot be null");
			Field field = ReflectionUtils.getField(bean.getClass(), propertyPath);
			field.setAccessible(true);
			try {
				return(field.get(bean));
			} catch (IllegalAccessException e) {
				return(null);
		}
	}
		
	/**
	 * This method recursivelly searches for a field with path <code>propertyPath</code> 
	 * on the class <code>beanClass</code>. Nested properties are expressed with the dot 
	 * notation, e.g. "a.b.c" means field c of field b of field a of the 
	 * class <code>beanClass</code>.
	 * 
	 * @param beanClass The class of the bean to inspect.
	 * @param propertyPath The dot separated path to the wanted field.
	 * @return Returns the field or null if no such field is found.
	 */
	public static Field getPropertyField(Class<?> beanClass,String propertyPath) throws NoSuchFieldException {		
		return ReflectionUtils.getField(beanClass, propertyPath);
	}

}
