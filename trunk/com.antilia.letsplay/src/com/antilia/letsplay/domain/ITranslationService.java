/**
 * 
 */
package com.antilia.letsplay.domain;

import com.antilia.letsplay.Language;

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
		public String translate(ITranslatable translatable, Language original, Language target);
}
