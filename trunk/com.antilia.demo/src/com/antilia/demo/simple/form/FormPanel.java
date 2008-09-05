/**
 * This software is provided as IS by Antilia-Soft SL.
 * Copyright 2006-2007.
 */
package com.antilia.demo.simple.form;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.form.AjaxButton;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.model.Model;

import com.antilia.demo.beans.Person;

/**
 * 
 * @author Ernesto Reinaldo Barreiro (reiern70@gmail.com)
 */
public class FormPanel extends Panel {

	private static final long serialVersionUID = 1L;

	private Person person;
	
	Label hidden;
	
	/**
	 * @param id
	 */
	public FormPanel(String id) {
		super(id);
		person = new Person();
		person.setName("Albert");
		person.setLastName1("Einstein");
		
		Form form = new Form("form", new CompoundPropertyModel(person));
		add(form);
		
		form.add(new Label("nameL", "Name:"));	
		form.add(new TextField("name"));
		
		form.add(new Label("lastName1L", "Lastnane:"));	
		form.add(new TextField("lastName1"));
				
		AjaxButton button = new AjaxButton("submit", form) {
			
			private static final long serialVersionUID = 1L;

			@Override
			protected void onSubmit(AjaxRequestTarget target, Form form) {
				hidden.setVisible(true);
				target.addComponent(hidden);
			}
		};
		
		form.add(button);
		
		hidden = new Label("hidden", new Model<String>( ) {
			
			private static final long serialVersionUID = 1L;

			@Override
			public String getObject() {
				return "Submit button clicked. Full name:  " + person.getName() + " " + person.getLastName1(); 
			}					
		});
		
		hidden.setOutputMarkupId(true);
		hidden.setVisible(false);
		hidden.setOutputMarkupPlaceholderTag(true);
		form.add(hidden);
	}
	
}
