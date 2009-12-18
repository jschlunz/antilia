/**
 * 
 */
package com.antilia.letsplay.crud;

import com.antilia.letsplay.domain.Translation;
import com.antilia.web.login.IProtectedPage;

/**
 * @author Ernesto Reinaldo Barreiro (reiern70@gmail.com)
 *
 */
public class TranslationPage extends CRUDPage<Translation> implements IProtectedPage  {

	public TranslationPage() {
		super(Translation	.class);
	}
}
