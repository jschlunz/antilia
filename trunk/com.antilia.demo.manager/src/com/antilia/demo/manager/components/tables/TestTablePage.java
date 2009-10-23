/**
 * This software is provided as IS by Antilia-Soft SL.
 * Copyright 2006-2007.
 */
package com.antilia.demo.manager.components.tables;

import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.form.Form;

import com.antilia.demo.manager.components.beans.Person;


/**
 * 
 * @author Ernesto Reinaldo Barreiro (reiern70@gmail.com)
 *
 */
public class TestTablePage extends WebPage  {

	private static final long serialVersionUID = 1L;	
	
	public TestTablePage() {		
		super();			
		Form<Person> form = new Form<Person>("form");
		add(form);
		form.add(new TablePanel("test"));
	}

	
}
