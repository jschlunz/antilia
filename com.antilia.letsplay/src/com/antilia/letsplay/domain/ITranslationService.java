/**
 * 
 */
package com.antilia.letsplay.domain;

/**
 * @author  Ernesto Reinaldo Barreiro (reiern70@gmail.com)
 *
 */
public interface ITranslationService {

		/**
		 * Provides a translation for an ITranslatable.
		 * @param translatable
		 * @return
		 */
		public String translate(ITranslatable translatable);
}
