/**
 * 
 */
package com.antilia.letsplay.crud;

import java.io.Serializable;

import org.apache.wicket.markup.html.panel.Panel;

import com.antilia.letsplay.RoundBasePage;
import com.antilia.web.crud.CRUDPanel;
import com.antilia.web.layout.ScopedCrudPanel;
import com.antilia.web.login.IProtectedPage;

/**
 * @author Ernesto Reinaldo Barreiro (reiern70@gmail.com)
 *
 */
public class CRUDPage<E extends Serializable> extends RoundBasePage implements IProtectedPage  {

	private Class<E> beanClass;
	
	public CRUDPage(Class<E> beanClass) {
		super();
		this.beanClass = beanClass;
	}

	@Override
	protected Panel newContentPanel(String id) {
		return new ScopedCrudPanel<E>(id, beanClass) {
			
			private static final long serialVersionUID = 1L;

			protected CRUDPanel<E> createCrudPanel(String id) {
				return new BackToHomeCRUD<E>(id, beanClass);
			}
		};
	}
}
