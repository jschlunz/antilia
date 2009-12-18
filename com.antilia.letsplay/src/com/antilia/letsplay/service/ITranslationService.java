/**
 * 
 */
package com.antilia.letsplay.service;

import com.antilia.letsplay.Language;
import com.antilia.letsplay.domain.ITranslatable;

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
