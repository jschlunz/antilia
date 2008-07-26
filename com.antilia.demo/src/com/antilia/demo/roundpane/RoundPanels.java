/**
 * This software is provided as IS by Antilia-Soft SL.
 * Copyright 2006-2007.
 */
package com.antilia.demo.roundpane;

import java.util.ArrayList;
import java.util.List;

import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.panel.Panel;

import com.antilia.demo.beans.Person;
import com.antilia.web.roundpane.RoundPane;


/**
 * 
 * @author Ernesto Reinaldo Barreiro (reiern70@gmail.com)
 */
public class RoundPanels extends Panel {

	private static final long serialVersionUID = 1L;

	/**
	 * @param id
	 */
	public RoundPanels(String id) {
		super(id);
		Form form = new Form("form");
		add(form);	
		RoundPane roundBox = new LatinRoundPane("roundbox", "Orange box", new OrangeStyle());
		roundBox.setResizable(false);
		roundBox.setFoldable(false);
		form.add(roundBox);
		RoundPane roundBox1 = new LatinRoundPane("roundbox1", "Blue box", new BlueStyle());
		roundBox1.setResizable(true);
		roundBox1.setMinHeight(60);
		form.add(roundBox1);
		RoundPane nested = new NestedRoundPane("nested","nested", new BlueStyle());
		nested.setResizable(true);
		form.add(nested);
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
