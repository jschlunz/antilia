/**
 * This software is provided as IS by Antilia-Soft SL.
 * Copyright 2006-2007.
 */
package com.antilia.demo.nestedtables;

import java.util.ArrayList;
import java.util.List;

import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.panel.Panel;

import com.antilia.demo.beans.Person;
import com.antilia.web.beantable.Table;
import com.antilia.web.beantable.model.IColumnModel;
import com.antilia.web.beantable.model.TableModel;

/**
 * 
 * @author Ernesto Reinaldo Barreiro (reiern70@gmail.com)
 */
public class NestedTablesPanel extends Panel {

	private static final long serialVersionUID = 1L;

	/**
	 * @param id
	 */
	public NestedTablesPanel(String id) {
		super(id);
		Form form = new Form("form");
		add(form);
		//form.add(Menu.createMenu("menu", NavigationItemsFactory.getInstance()));
		
		TableModel<Person> tableModel = new TableModel<Person>(Person.class, "id", "name", "lastName1", "friends");
		Table<Person>  table = new Table<Person>("table",tableModel, createPersons()) {

			private static final long serialVersionUID = 1L;
			
			@Override
			protected WebMarkupContainer newBodyCell(String id, IColumnModel<Person> columnModel, Person object) {
				if(columnModel.getPropertyPath().equalsIgnoreCase("friends")) {
					return new TablePanel<Person>(id, Person.class,  object.getFriends(), "id", "name", "lastName1");
				}
				return super.newBodyCell(id, columnModel, object);
			}
			
		};
		
		form.add(table);
	}
	
	public static List<Person> createPersons() {
		ArrayList<Person> persons = new ArrayList<Person>();
		for (int i=0; i < 104; i++) {
			persons.add(createPerson(i, persons));
		}
		return persons;
	}
	
	private static Person createPerson(int i, ArrayList<Person> persons) {
		Person person = new Person();
		person.setId(new Long(i));
		person.setName("Person "+i);
		person.setLastName1("Lastname "+i);
		for(int j=0; j<i; j++) {
			person.addFriend(persons.get(j));
		}
		return person;
	}
}
