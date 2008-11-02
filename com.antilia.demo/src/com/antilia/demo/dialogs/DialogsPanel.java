/**
 * This software is provided as IS by Antilia-Soft SL.
 * Copyright 2006-2007.
 */
package com.antilia.demo.dialogs;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.wicket.ResourceReference;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.panel.Panel;

import com.antilia.demo.beans.Person;
import com.antilia.web.dialog.DefaultDialog;
import com.antilia.web.dialog.DialogButton;

/**
 * 
 * @author Ernesto Reinaldo Barreiro (reiern70@gmail.com)
 */
public class DialogsPanel extends Panel {

	private static final long serialVersionUID = 1L;
	
	/**
	 * @param id
	 */
	public DialogsPanel(String id) {
		super(id);
		Form form = new Form("form");
		add(form);
	
		form.add(new TestDialog("dialog"));
		TestDialog dialog = new TestDialog("dialog2");
		dialog.setPosX(300);
		dialog.setPosY(300);
		dialog.setWidth(700);
		dialog.setTitle("Second Dialog");
		form.add(dialog);
		DialogButton button = new DialogButton("dialogButton") {
			private static final long serialVersionUID = 1L;

			@Override
			public DefaultDialog newDialog(String id) {
				return new TestDialog(id);
			}
			
			@Override
			protected String getLabel() {
				return "ShowDialog";				
			}
			
			@Override
			protected ResourceReference getImage() {
				return null;
			}
			
			@Override
			protected String getLabelKey() {
				return null;
			}
		};
		form.add(button);
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
		person.setBirthDay(new Date());
		return person;
	}
}
