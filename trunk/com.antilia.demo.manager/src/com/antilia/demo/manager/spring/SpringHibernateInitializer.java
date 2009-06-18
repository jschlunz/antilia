/**
 * 
 */
package com.antilia.demo.manager.spring;

import java.io.InputStream;

import com.antilia.common.misc.InstanceHolder;
import com.antilia.initializer.AbstractSpringInitializer;

/**
 * 
 * @author Ernesto Reinaldo Barreiro (reirn70@gmail.com)
 *
 */
public class SpringHibernateInitializer extends AbstractSpringInitializer {

	private static InstanceHolder<SpringHibernateInitializer> instance = new InstanceHolder<SpringHibernateInitializer>(SpringHibernateInitializer.class) {
		
		@Override
		protected SpringHibernateInitializer newInstance() {
			return new SpringHibernateInitializer();
		}
	};
	
	private SpringHibernateInitializer() {
	}
		
	@Override
	protected InputStream getXmlConfiguration() {
		return SpringHibernateInitializer.class.getResourceAsStream("hibernateApplicationContext.xml");
	}

	/**
	 * @return the instance
	 */
	public static SpringHibernateInitializer getInstance() {
		return instance.getInstance();
	}


}
