/**
 * 
 */
package com.antilia.initializer;

import java.io.IOException;
import java.io.InputStream;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.core.io.InputStreamResource;
import org.springframework.web.context.support.XmlWebApplicationContext;

/**
 * 
 * @author Ernesto Reinaldo Barreiro (reirn70@gmail.com)
 *
 */
public abstract class AbstractSpringInitializer implements Initializer<XmlWebApplicationContext> {

	
	protected AbstractSpringInitializer() {
	}
	
	public final XmlWebApplicationContext intialize() {
		// we are hacking a bit the normal way to read bean definitions
		// because we are using OSGI and we do not have WEB-INF folder.
		XmlWebApplicationContext applicationContext = new XmlWebApplicationContext() {
			
			protected void loadBeanDefinitions(XmlBeanDefinitionReader reader) throws BeansException, IOException {
				InputStream is = getXmlConfiguration();
				reader.setValidationMode(getValidationMode());
				reader.loadBeanDefinitions(new InputStreamResource(is));
			}
		};		
		applicationContext.refresh();
		
		return applicationContext;
	}

	/**
	 * Return an InputStrem with the XML configuration file.
	 * 
	 * @return
	 */
	protected abstract InputStream getXmlConfiguration();

	/**
	 * Override to provide a different validation mode.
	 * 
	 * @return
	 */
	protected int getValidationMode() {
		return XmlBeanDefinitionReader.VALIDATION_XSD;
	}
}
