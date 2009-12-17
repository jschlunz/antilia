package com.antilia.letsplay.domain;

import java.io.Serializable;

/**
 * @author  Ernesto Reinaldo Barreiro (reiern70@gmail.com)
 *
 */
public interface ITranslatable extends Serializable {

	/**
	 * The key identifying the translatable element.
	 * 
	 * @return
	 */
	String getTranslationKey();
	
}
