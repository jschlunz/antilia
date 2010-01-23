/**
 * 
 */
package com.antilia.letsplay.crud;

import java.io.Serializable;

import org.apache.wicket.markup.html.panel.Panel;

import com.antilia.letsplay.RoundBasePage;
import com.antilia.web.crud.CRUDPanel;
import com.antilia.web.crud.CrudStyler;
import com.antilia.web.layout.ScopedCrudPanel;
import com.antilia.web.login.IProtectedPage;

/**
 * @author Ernesto Reinaldo Barreiro (reiern70@gmail.com)
 *
 */
public class CRUDPage<E extends Serializable> extends RoundBasePage implements IProtectedPage  {

	private Class<E> beanClass;
	
	private CrudStyler<E> crudStyler;
	
	
	public CRUDPage(Class<E> beanClass) {
		super();
		this.beanClass = beanClass;
	}
	
	
	public CRUDPage(CrudStyler<E> crudStyler) {
		super();
		this.crudStyler = crudStyler;
	}
	
	@Override
	protected Panel newContentPanel(String id) {
		return new ScopedCrudPanel<E>(id, beanClass) {
			
			private static final long serialVersionUID = 1L;

			protected CRUDPanel<E> createCrudPanel(String id) {
				if(CRUDPage.this.crudStyler != null) 
					return new BackToHomeCRUD<E>(id, CRUDPage.this.crudStyler);
				
				return new BackToHomeCRUD<E>(id, CRUDPage.this.beanClass);
			}
		};
	}


	public CrudStyler<E> getCrudStyler() {
		return crudStyler;
	}


	public void setCrudStyler(CrudStyler<E> crudStyler) {
		this.crudStyler = crudStyler;
	}
}
