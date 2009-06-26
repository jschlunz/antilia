/**
 * 
 */
package com.antilia.web.provider;

import java.io.Serializable;

import org.apache.wicket.model.IModel;

import com.antilia.hibernate.EntityNotFoundException;

/**
 * @author Ernesto Reinaldo Barreiro (reirn70@gmail.com)
 *
 */
public abstract class AbstractEntityModel<T extends Serializable> implements IModel<T>
{
    private static final long serialVersionUID = 1L;
	
	private final Class<T> clazz;
    private Serializable id;
 
    private T entity;
 
    @SuppressWarnings("unchecked")
	public AbstractEntityModel(T entity)
    {
        clazz = (Class<T>)entity.getClass();
        id = getId(entity);
        this.entity = entity;
    }
 
    public AbstractEntityModel(Class<T> clazz, Serializable id)
    {
        this.clazz = clazz;
        this.id = id;
    }
 
    public T getObject()
    {
        if (entity == null)
        {
            if (id != null)
            {
                entity = load(clazz, id);
                if (entity == null)
                {
                    throw new EntityNotFoundException(clazz, id);
                }
            }
        }
        return entity;
    }
 
    public void detach()
    {
        if (entity != null)
        {
            if (getId(entity) != null)
            {
                id = getId(entity);
                entity = null;
            }
        }
    }
 
    /**
     * Retrieves the ID of an entity.
     * 
     * @param entity
     * @return
     */
    protected abstract Serializable getId(T entity);
    
    /**
     * Loads the entity.
     * 
     * @param clazz
     * @param id
     * @return
     */
    protected abstract T load(Class<T> clazz, Serializable id);
    
    
 
    public void setObject(T object)
    {
        throw new UnsupportedOperationException(getClass() +
                " does not support #setObject(T entity)");
    }
}
