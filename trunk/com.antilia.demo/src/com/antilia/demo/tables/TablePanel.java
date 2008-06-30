/**
 * This software is provided as IS by Antilia-Soft SL.
 * Copyright 2006-2007.
 */
package com.antilia.demo.tables;

import org.apache.wicket.markup.html.panel.Panel;

import com.antilia.demo.beans.Person;
import com.antilia.web.beantable.Table;
import com.antilia.web.beantable.model.TableModel;

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
		TableModel<Person> tableModel = new TableModel<Person>(Person.class, "id", "name", "lastName1");
		add(new Table<Person>("table",tableModel, TablesPanel.createPersons()));
	}
}
