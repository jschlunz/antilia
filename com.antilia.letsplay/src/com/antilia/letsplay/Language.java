/**
 * 
 */
package com.antilia.letsplay;

import java.util.Locale;

/**
 * @author Ernesto Reinaldo Barreiro (reiern70@gmail.com)
 *
 */
public enum Language {
	

	ENGLISH("en",Locale.ENGLISH),
	SPANISH("es",new Locale("es", "ES")),
	CATALAN("ca", new Locale("es", "CA")),;
	
	
	private Locale locale;
	
	private String shortName;
	
	public Locale getLocale() {
		return locale;
	}

	public void setLocale(Locale locale) {
		this.locale = locale;
	}

	private Language(String shortName, Locale locale) {
		this.shortName = shortName;
		this.locale = locale;
	}

	public String getShortName() {
		return shortName;
	}

	public void setShortName(String shortName) {
		this.shortName = shortName;
	}
	
}
