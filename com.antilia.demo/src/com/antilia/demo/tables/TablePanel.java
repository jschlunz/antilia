/**
 * This software is provided as IS by Antilia-Soft SL.
 * Copyright 2006-2007.
 */
package com.antilia.demo.tables;

import org.apache.wicket.markup.html.panel.Panel;

import com.antilia.demo.beans.Person;
import com.antilia.demo.dialogs.HelloDialogButton;
import com.antilia.web.beantable.Table;
import com.antilia.web.beantable.model.TableModel;
import com.antilia.web.beantable.navigation.NextPageButton;
import com.antilia.web.beantable.navigation.PageNumberItem;
import com.antilia.web.toolbar.SubToolbar;
import com.antilia.web.toolbar.Toolbar;

/**
 * 
 * @author Ernesto Reinaldo Barreiro (reiern70@google.com)
 */
public class TablePanel extends Panel {

	private static final long serialVersionUID = 1L;

	/**
	 * @param id
	 */
	public TablePanel(String id) {
		super(id);
		Toolbar toolbar = new Toolbar("adxMenu");
		
		//toolbar.addItem(new ProductPanel("products1"));
		//toolbar.addItem(new ProductPanel("products2"));
		
		SubToolbar subToolbar = new SubToolbar("Products3", toolbar) {
			
			private static final long serialVersionUID = 1L;

			@Override
			protected String getTitle() {
				return "Products3";
			}
		};
		
		toolbar.addItem(subToolbar);
		
		subToolbar.addItem(new NextPageButton() {
			
			@Override
			protected String getLabel() {
				return "Next Page";
			}
		});
		
		subToolbar.addItem(new HelloDialogButton("hello"));
		subToolbar.addItem(new PageNumberItem());		
		
		
		toolbar.addItem(new NextPageButton());
		toolbar.addItem(new HelloDialogButton("hello"));
		toolbar.addItem(new PageNumberItem());
		
		
		//add(new TestMenu("adxMenu"));
		add(toolbar);
		TableModel<Person> tableModel = new TableModel<Person>(Person.class, "id", "name", "lastName1");
		add(new Table<Person>("table",tableModel, TablesPanel.createPersons()));
	}
}
