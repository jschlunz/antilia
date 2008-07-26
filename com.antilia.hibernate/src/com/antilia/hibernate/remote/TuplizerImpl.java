package com.antilia.hibernate.remote;

import org.hibernate.mapping.PersistentClass;
import org.hibernate.property.Getter;
import org.hibernate.property.Setter;
import org.hibernate.proxy.ProxyFactory;
import org.hibernate.tuple.entity.EntityMetamodel;
import org.hibernate.tuple.entity.PojoEntityTuplizer;

import com.antilia.hibernate.remote.imp.ProxyFactoryImpl;

/**
 * Custom tuplizer
 * @author Juozas
 *
 */
public class TuplizerImpl extends PojoEntityTuplizer {

    public TuplizerImpl(EntityMetamodel entityMetamodel, PersistentClass mappedEntity) {
        super(entityMetamodel, mappedEntity);
    }
    
    protected ProxyFactory createProxyFactory(){
        return new ProxyFactoryImpl();
    }

    @Override
    protected ProxyFactory buildProxyFactoryInternal(PersistentClass persistentClass, Getter idGetter, Setter idSetter) {
    	return new ProxyFactoryImpl();
    }

}
