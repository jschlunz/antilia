/**
 * This software is provided as IS by Antilia-Soft SL.
 * Copyright 2006-2007.
 */
package com.antilia.web.osgi;

import java.net.URL;
import java.util.Dictionary;
import java.util.Properties;

import org.apache.log4j.PropertyConfigurator;
import org.osgi.framework.Bundle;
import org.osgi.framework.BundleContext;

import com.antilia.common.osgi.IServiceActivator;
import com.antilia.common.util.StringUtils;

/**
 * @author Ernesto Reinaldo Barreiro (reiern70@gmail.com)
 *
 */
public class LoggingServiceActivator implements IServiceActivator {
private static final long serialVersionUID = 1L;
	
	/**
     * Dictionary lookup key
     */
    public static final String LOG4J_CONFIG_FILE = "Log4J-ConfigFile";
    
    
	public boolean isMandatory() {
		return false;
	}

	/**
	 * @see com.isencia.sherpa.commons.osgi.IServiceActivator#start(org.osgi.framework.BundleContext)
	 */
	@SuppressWarnings("unchecked")
	public void start(BundleContext context) throws Exception {		
		Bundle bundle = context.getBundle();
		if(bundle != null) {
			Dictionary dictionary = bundle.getHeaders();
	        String configFileName = (String) dictionary.get(LOG4J_CONFIG_FILE);
	        if(StringUtils.isEmpty(configFileName)) {
	        	configFileName = "log4j.properties";
	        } 
	        Properties m_MergedProperties = new Properties();
	        m_MergedProperties.put( "log4j.rootLogger", "INFO, A1" );
	        m_MergedProperties.put( "log4j.appender.A1", "org.apache.log4j.ConsoleAppender" );
	        m_MergedProperties.put( "log4j.appender.A1.layout", "org.apache.log4j.PatternLayout" );
	        m_MergedProperties.put( "log4j.appender.A1.layout.ConversionPattern", "%d{ABSOLUTE} %5p %c{1}:%L - %m%n" );
	        configure(bundle, m_MergedProperties, configFileName );
		}

	}
	
	public void stop(BundleContext context) throws Exception {
		
	}
	
	public void configure( Bundle bundle, Properties prop, String configFileName)
    {
        try {
        	URL resource = bundle.getResource(configFileName);
        	if(resource == null)
        		resource = this.getClass().getResource( configFileName );
            if(resource != null)
            	configure(resource);
            else 
            	PropertyConfigurator.configure(prop);
        } catch (Throwable e) {
            PropertyConfigurator.configure(prop);
        }                
    }
    
    public void configure( URL resource ) {       
        PropertyConfigurator.configure(resource);
    }
}

	