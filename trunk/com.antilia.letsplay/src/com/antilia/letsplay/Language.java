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
	

	ENGLISH(Locale.ENGLISH),
	SPANISH(new Locale("es", "ES"));
	
	private Locale locale;
	
	public Locale getLocale() {
		return locale;
	}

	public void setLocale(Locale locale) {
		this.locale = locale;
	}

	private Language(Locale locale) {
		this.locale = locale;
	}
	
}
