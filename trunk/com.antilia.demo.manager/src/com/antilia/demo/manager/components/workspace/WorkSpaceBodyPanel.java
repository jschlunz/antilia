/**
 * This software is provided as IS by Antilia-Soft SL.
 * Copyright 2008-2009.
 */
package com.antilia.demo.manager.components.workspace;

import org.apache.wicket.markup.html.panel.Panel;

import com.antilia.demo.manager.entities.City;
import com.antilia.demo.manager.entities.Country;
import com.antilia.web.dialog.DefaultDialog;
import com.antilia.web.workspace.WorkSpaceCrudDialogLink;

/**
 * @author Ernesto Reinaldo Barreiro (reiern70@gmail.com)
 *
 */
public class WorkSpaceBodyPanel extends Panel {

	private static final long serialVersionUID = 1L;

	/**
	 * @param id
	 */
	public WorkSpaceBodyPanel(String id) {
		super(id);
		
		WorkSpaceCrudDialogLink<City> workSpaceCrudDialogLink = new WorkSpaceCrudDialogLink<City>("city", City.class) {
			
			private static final long serialVersionUID = 1L;

			@Override
			protected void configureDialog(DefaultDialog defaultDialog) {
				defaultDialog.getDialogStyle().setBackgroundColor("#a6b8cb");
			}
		};
		add(workSpaceCrudDialogLink);
		
		WorkSpaceCrudDialogLink<Country> workSpaceCrudDialogLink1 = new WorkSpaceCrudDialogLink<Country>("country", Country.class) {
			
			private static final long serialVersionUID = 1L;

			@Override
			protected void configureDialog(DefaultDialog defaultDialog) {
				defaultDialog.getDialogStyle().setBackgroundColor("#a6b8cb");
			}
		};
		add(workSpaceCrudDialogLink1);
	}
}
