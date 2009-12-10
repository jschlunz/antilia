/**
 * 
 */
package com.antilia.letsplay.crud;

import com.antilia.letsplay.domain.DImage;
import com.antilia.web.login.IProtectedPage;

/**
 * @author Ernesto Reinaldo Barreiro (reiern70@gmail.com)
 *
 */
public class ImagesPage extends CRUDPage<DImage> implements IProtectedPage  {

	public ImagesPage() {
		super(DImage.class);
	}
}
