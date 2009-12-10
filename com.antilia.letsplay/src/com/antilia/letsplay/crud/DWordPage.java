/**
 * 
 */
package com.antilia.letsplay.crud;

import com.antilia.letsplay.domain.DWord;
import com.antilia.web.login.IProtectedPage;

/**
 * @author Ernesto Reinaldo Barreiro (reiern70@gmail.com)
 *
 */
public class DWordPage extends CRUDPage<DWord> implements IProtectedPage  {

	public DWordPage() {
		super(DWord.class);
	}
}
