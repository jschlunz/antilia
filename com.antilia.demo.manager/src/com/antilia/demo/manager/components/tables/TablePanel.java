/**
 * This software is provided as IS by Antilia-Soft SL.
 * Copyright 2008-2009.
 */
package com.antilia.demo.manager.components.tables;

import org.apache.wicket.markup.html.panel.Panel;

import com.antilia.demo.manager.components.beans.Person;
import com.antilia.web.beantable.Table;
import com.antilia.web.beantable.model.TableModel;

/**
 * @author Ernesto Reinaldo Barreiro (reiern70@gmail.com)
 *
 */
public class TablePanel extends Panel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * @param id
	 */
	public TablePanel(String id) {
		super(id);
		TableModel<Person> tableModel = new TableModel<Person>(Person.class, "id", "name", "lastName1");
		add(new Table<Person>("table",tableModel, Person.createPersons()));
	}
	
}
