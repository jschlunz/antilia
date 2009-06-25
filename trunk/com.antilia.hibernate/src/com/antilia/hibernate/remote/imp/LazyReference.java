package com.antilia.hibernate.remote.imp;

import java.io.ObjectStreamException;
import java.io.Serializable;



/**
 * 
 * @author Ernesto Reinaldo Barreiro (reirn70@gmail.com)
 *
 */
public class LazyReference implements Serializable {

    private static final long serialVersionUID = 1;

    private Serializable id;

    private int token;

    private Class<? extends Serializable> entityClass;

    
    public LazyReference(){
        
    }
    public LazyReference(int token, Class<? extends Serializable> entityClass,Serializable id){
        this.token = token;
        this.entityClass = entityClass;
        this.id = id;
    }

    Object readResolve() throws ObjectStreamException,
            ClassNotFoundException {
        return Proxy.create(this);

    }

    public Serializable getId() {
        return id;
    }

    public void setId(Serializable id) {
        this.id = id;
    }

    public int getToken() {
        return token;
    }

    public void setToken(int token) {
        this.token = token;
    }
	/**
	 * @return the entityClass
	 */
	public Class<? extends Serializable> getEntityClass() {
		return entityClass;
	}
	/**
	 * @param entityClass the entityClass to set
	 */
	public void setEntityClass(Class<? extends Serializable> entityClass) {
		this.entityClass = entityClass;
	}   
}