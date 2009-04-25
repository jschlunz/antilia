/**
 * This software is provided as IS by Antilia-Soft SL.
 * Copyright 2008-2009.
 */
package com.antilia.demo.manager.components.workspace;

import java.io.Serializable;

import org.apache.wicket.Component;
import org.apache.wicket.markup.html.panel.EmptyPanel;

import com.antilia.demo.manager.entities.City;
import com.antilia.demo.manager.entities.Country;
import com.antilia.web.button.IMenuItemHolder;
import com.antilia.web.dialog.DefaultDialog;
import com.antilia.web.layout.BackToHomeTopMenuPanel;
import com.antilia.web.workspace.WorkSpaceCrudDialogLink;

/**
 * @author Ernesto Reinaldo Barreiro (reiern70@gmail.com)
 *
 */
public class WorkSpacePanel extends BackToHomeTopMenuPanel<Serializable> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * @param id
	 */
	public WorkSpacePanel(String id) {
		super(id);
	}
	
	@Override
	protected Component createBody(String id) {
		return new EmptyPanel(id);
	}

	@Override
	public void populateMenuItems(String menuId, IMenuItemHolder itemHolder) {
		super.populateMenuItems(menuId, itemHolder);
		itemHolder.addMenuItem(new WorkSpaceCrudDialogLink<City>("city", City.class) {
			
			private static final long serialVersionUID = 1L;

			@Override
			protected void configureDialog(DefaultDialog defaultDialog) {
				defaultDialog.getDialogStyle().setBackgroundColor("#b54e25");
			}
		});
		itemHolder.addMenuItem(new WorkSpaceCrudDialogLink<Country>("country", Country.class) {
			
			private static final long serialVersionUID = 1L;

			@Override
			protected void configureDialog(DefaultDialog defaultDialog) {
				defaultDialog.getDialogStyle().setBackgroundColor("#3e4d52");
				defaultDialog.getDialogStyle().setTitleStyle("color: #ffffff;");
			}
		});
	}
	
}
