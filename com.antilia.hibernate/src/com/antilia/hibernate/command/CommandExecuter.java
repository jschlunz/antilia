/**
 * This software is provided as IS by Antilia-Soft SL.
 * Copyright 2006-2007.
 */
package com.antilia.hibernate.command;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

import org.hibernate.criterion.Criterion;

import com.antilia.hibernate.query.IQuery;
import com.antilia.hibernate.query.Query;


/**
 * Main entry point to the service layer. 
 *
 * @author Ernesto Reinaldo Barreiro (reiern70@gmail.com)
 *
 */
public class CommandExecuter {
	
	public static <T extends Serializable> Long countAll(Class<T> entityClass)  {
		return CommandExecuter.execute(new CountCommand<T>(entityClass));
	}
	
	public static <T extends Serializable> Long count(Query<T> query)  {
		return CommandExecuter.execute(new CountCommand<T>(query));
	}
		
	public static <T extends Serializable> Long count(Class<T> entityClass, Criterion... criteria)  {
		return CommandExecuter.execute(new CountCommand<T>(entityClass, criteria));
	}
	
	public static <T extends Serializable> Long countAllLike(Class<T> entityClass, T sample)  {
		return CommandExecuter.execute(new CountCommand<T>(sample));
	}
	
	/**
	 * Loads all the entities of a certain type.
	 * 
	 * @param <T>
	 * @param entityClass
	 * @return
	 */
	public static <T extends Serializable> List<T> loadAll(Class<T> entityClass)  {
		return loadList(entityClass, (String)null);
	}
	
	/**
	 * Loads a list of entities based on a HQL string.
	 * 
	 * @param <T>
	 * @param entityClass
	 * @return
	 */
	public static <T extends Serializable> List<T> loadList(Class<T> entityClass, String hql)  {
		LoadListCommand<T> command = new LoadListCommand<T>(entityClass, hql) ;
		List<T> list = DefaultCommandExecuter.getInstance().execute(command);
		return list;
	}
	
	/**
	 * 
	 * Loads a list based on an IQuery object.
	 * 
	 * @param <T>
	 * @param entityClass
	 * @param query
	 * @return
	 */
	public static <T extends Serializable> List<T> loadList(IQuery<T> query)  {
		LoadListCommand<T> command = new LoadListCommand<T>(query) ;
		List<T> list = DefaultCommandExecuter.getInstance().execute(command);
		return list;
	}
	
	/**
	 * Loads a list of entities based on a certain criteria.
	 * 
	 * @param <T>
	 * @param entityClass
	 * @return
	 */
	public static <T extends Serializable> List<T> loadList(Class<T> entityClass, Criterion... criteria)  {
		LoadListCommand<T> command = new LoadListCommand<T>(entityClass, criteria) ;
		List<T> list = DefaultCommandExecuter.getInstance().execute(command);
		return list;
	}
	
	/**
	 * Loads a list by example.
	 * @param <T>
	 * @param bean
	 * @return
	 */
	public static <T extends Serializable> List<T> loadListByExample(T bean)  {
		LoadListCommand<T> command = new LoadListCommand<T>(bean) ;
		List<T> list = DefaultCommandExecuter.getInstance().execute(command);
		return list;
	}
	
	/**
	 * Finds a single entity based on its primary key.
	 * 
	 * @param <T>
	 * @param entityClassName
	 * @param key
	 * @return
	 */
	public static <T extends Serializable> T findById(Class<T> entityClass, Serializable key)  {
		FindByIdCommand<T, Serializable> command = new FindByIdCommand<T, Serializable>(entityClass, key) ;
		T entity = DefaultCommandExecuter.getInstance().execute(command);
		return entity;
	}
		
	
	/**
	 * Finds a single entity based on its primary key.
	 * 
	 * @param <T>
	 * @param entityClassName
	 * @param key
	 * @return
	 */
	public static <T extends Serializable> T findById(String entityClassName, Serializable key)  {
		FindByIdCommand<T, Serializable> command = new FindByIdCommand<T, Serializable>(entityClassName, key) ;
		T entity = DefaultCommandExecuter.getInstance().execute(command);
		return entity;
	}
	
	/**
	 * Finds a single entity based on its primary key.
	 * 
	 * @param <T>
	 * @param entityClassName
	 * @param key
	 * @return
	 */
	public static <T extends Serializable> T findByExample(T example)  {
		FindByExampleCommand<T> command = new FindByExampleCommand<T>(example.getClass().getName(), example) ;
		T entity = DefaultCommandExecuter.getInstance().execute(command);
		return entity;
	}
	
	/**
	 * Make an entity persistent and returns a reference to the persisted entity.
	 * 
	 * @param <E>
	 * @param entity
	 * @return
	 */
	public static <E extends Serializable> E persist(E entity)  {
		PersistCommand<E> command = new PersistCommand<E>(entity) ;
		return DefaultCommandExecuter.getInstance().execute(command);
	}	

	public static <E extends Serializable> Collection<E>persistAll(Collection<E> entities)  {
		PersistAllCommand<E> command = new PersistAllCommand<E>(entities) ;
		return DefaultCommandExecuter.getInstance().execute(command);
	}

	/**
	 * Update a single entity.
	 * 
	 * @param <E>
	 * @param entity
	 * @return
	 */
	public static <E extends Serializable> E update(E entity)  {
		UpdateCommand<E> command = new UpdateCommand<E>(entity) ;
		return DefaultCommandExecuter.getInstance().execute(command);
	}
	
	/**
	 * Update a collection of entities.
	 * 
	 * @param <E>
	 * @param entities
	 * @return
	 */
	public static <E extends Serializable> Collection<E> updateAll(Collection<E> entities)  {
		UpdateAllCommand<E> command = new UpdateAllCommand<E>(entities) ;
		return DefaultCommandExecuter.getInstance().execute(command);
	}
	
	/**
	 * Delete the Entity <code>entity</code> and returns a reference to a transient entity.
	 * 
	 * @param <E>
	 * @param entity
	 * @return
	 */
	public static <E extends Serializable> E delete(E entity)  {
		DeleteCommand<E> command = new DeleteCommand<E>(entity) ;
		return DefaultCommandExecuter.getInstance().execute(command);
	}
	
	
	/**
	 * Deletes all entities in collection <code>entities</code>.
	 * 
	 * @param <E>
	 * @param entities
	 * @return
	 */
	public static <E extends Serializable> Collection<E>deleteAll(Collection<E> entities)  {
		DeleteAllCommand<E> command = new DeleteAllCommand<E>(entities) ;
		return DefaultCommandExecuter.getInstance().execute(command);
	}
	
	/**
	 * Executes an arbitrary ICommand...
	 * 
	 * @param <T>
	 * @param command
	 * @return
	 */
	public static <T extends Serializable> T execute(ICommand<T> command)  {
		return DefaultCommandExecuter.getInstance().execute(command);
	}	

}
