/**
 * 
 */
package com.antilia.web.workspace;

import java.io.Serializable;

import org.apache.wicket.markup.html.panel.Panel;

import com.antilia.web.button.IMenuItemHolder;
import com.antilia.web.button.IMenuItemsFactory;
import com.antilia.web.crud.AutoCrudStyler;
import com.antilia.web.crud.ReloadButton;
import com.antilia.web.crud.SearchPanel;
import com.antilia.web.menu.Menu;

/**
 * @author Ernesto Reinaldo Barreiro (reirn70@gmail.com)
 *
 */
public class SearchDialogPanel<T extends Serializable> extends Panel {

	private static final long serialVersionUID = 1L;

	/**
	 * @param id
	 */
	public SearchDialogPanel(String id, Class<T> beanClass) {
		super(id);
		add(new SearchPanel<T>("crud", new AutoCrudStyler<T>(beanClass)) {
						
			private static final long serialVersionUID = 1L;

			@Override
			protected Menu newTopMenuMenu(String id) {
				return Menu.createMenu(id, null, new IMenuItemsFactory() {
					private static final long serialVersionUID = 1L;

					public void populateMenuItems(String menuId, IMenuItemHolder itemHolder) {
						itemHolder.addMenuItem(new ReloadButton<T>());
					}
				});
			}
		});		
	}
}
