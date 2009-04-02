/**
 * This software is provided as IS by Antilia-Soft SL.
 * Copyright 2006-2007.
 */
package com.antilia.demo.tables;

import java.util.ArrayList;
import java.util.List;

import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.panel.Panel;

import com.antilia.demo.beans.Person;
import com.antilia.web.beantable.Table;
import com.antilia.web.beantable.model.TableModel;

/**
 * 
 * @author Ernesto Reinaldo Barreiro (reiern70@gmail.com)
 */
public class TablesPanel extends Panel {

	private static final long serialVersionUID = 1L;

	/**
	 * @param id
	 */
	public TablesPanel(String id) {
		super(id);
		Form form = new Form("form");
		add(form);
		//form.add(Menu.createMenu("menu", NavigationItemsFactory.getInstance()));
		
		TableModel<Person> tableModel = new TableModel<Person>(Person.class, "id", "name", "lastName1");
		form.add(new Table<Person>("table",tableModel, createPersons()));
		
		TestDialog dialog = new TestDialog("dialog");
		
		form.add(dialog);
		
		dialog = new TestDialog("dialog2");
		dialog.setPosX(300);
		dialog.setPosY(300);
		dialog.setWidth(700);
		dialog.setTitle("Second Dialog");
		form.add(dialog);
		
	}
	
	public static List<Person> createPersons() {
		ArrayList<Person> persons = new ArrayList<Person>();
		for (int i=0; i < 104; i++) {
			persons.add(createPerson(i));
		}
		return persons;
	}
	
	private static Person createPerson(int i) {
		Person person = new Person();
		person.setId(new Long(i));
		person.setName("Person "+i);
		person.setLastName1("Lastname "+i);
		return person;
	}
}
