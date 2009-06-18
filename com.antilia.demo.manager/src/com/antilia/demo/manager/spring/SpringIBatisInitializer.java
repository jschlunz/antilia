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
public class SpringIBatisInitializer extends AbstractSpringInitializer {

	private static InstanceHolder<SpringIBatisInitializer> instance = new InstanceHolder<SpringIBatisInitializer>(SpringIBatisInitializer.class) {
		
		@Override
		protected SpringIBatisInitializer newInstance() {
			return new SpringIBatisInitializer();
		}
	};
	
	private SpringIBatisInitializer() {
	}
		
	@Override
	protected InputStream getXmlConfiguration() {
		return SpringIBatisInitializer.class.getResourceAsStream("ibatisApplicationContext.xml");
	}

	/**
	 * @return the instance
	 */
	public static SpringIBatisInitializer getInstance() {
		return instance.getInstance();
	}


}
