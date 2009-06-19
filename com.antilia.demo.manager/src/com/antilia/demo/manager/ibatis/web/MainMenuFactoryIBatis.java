package com.antilia.demo.manager.ibatis.web;

import org.apache.wicket.Component;

import com.antilia.demo.manager.Index;
import com.antilia.web.layout.ContainerButton;
import com.antilia.web.toolbar.IToolbar;
import com.antilia.web.toolbar.IToolbarItemsFactory;
import com.antilia.web.toolbar.SubToolbar;

/**
 * 
 *
 * @author Ernesto Reinaldo Barreiro (reiern70@gmail.com)
 *
 */
public class MainMenuFactoryIBatis implements IToolbarItemsFactory {

	private static final long serialVersionUID = 1L;

	private Index page;
	
	private String contentId;
	/**
	 * 
	 */
	public MainMenuFactoryIBatis(Index page, String contentId) {
		this.page = page;
		this.contentId = contentId;
	}

	/* (non-Javadoc)
	 * @see com.antilia.web.toolbar.IToolbarItemsFactory#populateMenuItems(java.lang.String, com.antilia.web.toolbar.IToolbar)
	 */
	public void populateMenuItems(String menuId, IToolbar toolbar) {
		// AGENUNED	
		SubToolbar subToolbar = new SubToolbar("Entidades", toolbar) {
			private static final long serialVersionUID = 1L;

			@Override
			protected String getTitle() {
				return "Entidades";
			}
		};
		toolbar.addItem(subToolbar);
			
		subToolbar.addItem(new ContainerButton("Countries", this.page, this.contentId) {
			
			private static final long serialVersionUID = 1L;

			@Override
			protected Component createBody(String id) {
				return new ListCountriesPanel(id);
			}
		});

	}

}
