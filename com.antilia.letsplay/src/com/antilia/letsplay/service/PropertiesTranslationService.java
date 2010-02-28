/**
 * 
 */
package com.antilia.letsplay.service;

import java.io.IOException;
import java.util.HashMap;
import java.util.Properties;

import com.antilia.common.util.StringUtils;
import com.antilia.letsplay.domain.ITranslatable;
import com.google.api.translate.Language;

/**
 * @author  Ernesto Reinaldo Barreiro (reiern70@gmail.com)
 *
 */
public class PropertiesTranslationService implements ITranslationService {
	
	private Language from = Language.ENGLISH;
	
	private Language to = Language.SPANISH;
	
	private static final int NO_PORT = -1;
	
	private HashMap<String, Properties> properties = new HashMap<String, Properties>();
	
	/**
	 * 
	 */
	public PropertiesTranslationService() {
	}

	/* (non-Javadoc)
	 * @see org.inqle.ui.model.ITranslationService#translate(org.inqle.ui.model.ITranslatable)
	 */
	@Override
	public String translate(ITranslatable translatable, com.antilia.letsplay.Language original, com.antilia.letsplay.Language target) {
		String key = translatable.getTranslationKey().toLowerCase();
		if(!StringUtils.isEmpty(key)) {
			try {
				return translate(key, original, target);
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		}
		return key;
	}
	
	private String translate(String key, com.antilia.letsplay.Language original, com.antilia.letsplay.Language target) throws IOException {
		Properties properties = getProperties(original, target);
		if(properties != null) {
			String trans = properties.getProperty(key);
			if(trans != null)
				return trans;
		}
		return key;
	}
	
	private Properties getProperties(com.antilia.letsplay.Language original, com.antilia.letsplay.Language target) throws IOException{
		String propertyFile = "words_" + original.getShortName() + "_" +target.getShortName()+".properties";
		Properties propert = this.properties.get(propertyFile);
		if(propert == null) {
			propert = new Properties();
			propert.load(PropertiesTranslationService.class.getResourceAsStream(propertyFile ));		
		}
		return propert;
	}
		
	protected String getProxyHost() {
		return null;
	}
	
	protected int getProxyPort() {
		return NO_PORT;
	}

	public Language getFrom() {
		return from;
	}

	public void setFrom(Language from) {
		this.from = from;
	}

	public Language getTo() {
		return to;
	}

	public void setTo(Language to) {
		this.to = to;
	}

}
