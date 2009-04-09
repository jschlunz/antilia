/**
 * 
 */
package com.antilia.web.layout;

import java.io.Serializable;

import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.IModel;

import com.antilia.web.button.IMenuItemsFactory;
import com.antilia.web.menu.Menu;

/**
 * @author Ernesto Reinaldo Barreiro (reirn70@gmail.com)
 *
 */
public abstract class TopMenuPanel<B extends Serializable> extends Panel implements IMenuItemsFactory {

	private static final long serialVersionUID = 1L;

	private Form<B> form;
	
	/**
	 * @param id
	 */
	public TopMenuPanel(String id) {
		super(id);
		initialize();
	}

	/**
	 * @param id
	 * @param model
	 */
	public TopMenuPanel(String id, IModel<?> model) {
		super(id, model);
		initialize();
	}
	
	private void initialize() {
		form = new Form<B>("form");		
		form.add(Menu.createMenu("menu", this));
	}

}
