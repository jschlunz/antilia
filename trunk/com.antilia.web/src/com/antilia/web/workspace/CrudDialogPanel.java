/**
 * 
 */
package com.antilia.web.workspace;

import java.io.Serializable;

import org.apache.wicket.markup.html.panel.Panel;

import com.antilia.web.crud.CRUDPanel;

/**
 * @author Ernesto Reinaldo Barreiro (reirn70@gmail.com)
 *
 */
public class CrudDialogPanel<T extends Serializable> extends Panel {

	private static final long serialVersionUID = 1L;

	/**
	 * @param id
	 */
	public CrudDialogPanel(String id, Class<T> beanClass) {
		super(id);
		add(new CRUDPanel<T>("crud", beanClass));		
	}
}
