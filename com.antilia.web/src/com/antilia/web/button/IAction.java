/**
 * This software is provided as IS by Antilia-Soft SL.
 * Copyright 2006-2007.
 */
package com.antilia.web.button;

import java.io.Serializable;

import org.apache.wicket.Component;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.IAjaxCallDecorator;
import org.apache.wicket.markup.html.form.Form;

/**
 * Reusable atomic action.
 *
 * @author Ernesto Reinaldo Barreiro (reiern70@gmail.com)
 *
 */
public interface IAction extends Serializable {

	void setTrigger(Component  component);
	
	Component getTrigger();
	
	/**
	 * AJAX submit.
	 * 
	 * @param target
	 * @param form
	 */
	void onSubmit(AjaxRequestTarget target, Form<?> form);
	
	/**
	 * NON-AJAX submit
	 */
	void onSubmit();
	
	/**
	 * Returns an IAjaxCallDecorator (it might be null).
	 * @return
	 */
	 IAjaxCallDecorator getAjaxCallDecorator();

}
