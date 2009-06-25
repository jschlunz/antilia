package com.antilia.hibernate.remote.imp;

import java.io.ObjectStreamException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.AbstractSet;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.Set;

import net.sf.cglib.core.NamingPolicy;
import net.sf.cglib.core.Predicate;
import net.sf.cglib.proxy.Callback;
import net.sf.cglib.proxy.CallbackFilter;
import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.InvocationHandler;
import net.sf.cglib.proxy.NoOp;

import com.antilia.hibernate.command.DefaultCommander;
/**
 * Client side proxy, it interceps methods calls statefull bean to initialize entity
 * @author Juozas
 *
 */
public class Proxy {
    
    private static final Class<?>[] CALLBACK_TYPES = new Class[]{ InvocationHandler.class,NoOp.class };
    
    private static final class CollectionProxy<E> extends AbstractSet<E> {
        
        private final CollectionReference reference;
        private Set<E> remote;

        private CollectionProxy(CollectionReference reference) {
            super();
            this.reference = reference;
        }

        Object writeReplare()throws ObjectStreamException{
            
            if(remote != null){
                return remote;
            }else {
                return reference;
            }
        }

        private Set<E> load() {
            try{
                if(remote == null){
                    remote = (Set<E>) null;
                }
            }catch(Exception e){
                throw new RuntimeException(e);
            }
            
            return remote;
            
        }

        public int size() {
            
            return load().size();
        }

        public boolean isEmpty() {
            
            return load().isEmpty();
        }

        public Object[] toArray() {
            
            return load().toArray();
        }

        public boolean contains(Object o) {
            
            return load().contains(o);
        }

        public boolean containsAll(Collection<?> c) {            
            return load().containsAll(c);
        }

        public Iterator<E> iterator() {
            
            return load().iterator();
        }

        public <T> T[] toArray(T[] a) {
        	return load().toArray(a);
        }
       
    }

    public interface WriteReplace {
        
        Object writeReplace() throws ObjectStreamException;
    }
    
    private static final class Interceptor implements InvocationHandler {
        
        
        private Object entity;
        private LazyReference ref;
        
        Interceptor(LazyReference ref){
            this.ref = ref;
        }
        
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            
            if( args.length == 0 && 
                    method.getDeclaringClass() == WriteReplace.class &&
                    method.getName().equals("writeReplace")){
                    if(entity != null){
                         return entity;
                    }else {
                         return ref;
                    }
            }
            if(entity == null){
            	DefaultCommander.findById(ref.getEntityClass(), ref.getId());
                //entity = ClientSession.getLoader().initializeEntity(ref.getClassName(),ref.getId(),ref.getToken());
            }
            try{
                
             return method.invoke(entity,args);
             
            }catch(InvocationTargetException ite){
                throw ite;
            }
        }
    }
    
    
    
    public static Object create( final LazyReference ref) throws ClassNotFoundException{        
        Class<?> cls = ref.getEntityClass();
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(cls);
        enhancer.setCallbackTypes(CALLBACK_TYPES);
        enhancer.setInterfaces(new Class[]{WriteReplace.class});
        enhancer.setCallbackFilter(new CallbackFilter(){
            public int accept(Method m) {
                return Modifier.isPublic(m.getModifiers()) ? 0 : 1;
            }
        });
        enhancer.setCallbacks(new Callback[]{ new Interceptor(ref), NoOp.INSTANCE});
        enhancer.setNamingPolicy(
                 new NamingPolicy(){
                    public String getClassName(String arg0, String arg1, Object arg2, Predicate arg3) {
                            return ref.getEntityClass().getName() + "$ClientProxy";
                    }
                 }        
                );
        
        return enhancer.create();
    }

    public static <E> Set<E> create(final CollectionReference reference) {
        
        Set<E> set = new CollectionProxy<E>(reference);
        
        return Collections.unmodifiableSet(set);
    }
}
