/**
 * This software is provided as IS by Antilia-Soft SL.
 * Copyright 2006-2007.
 */
package com.antilia.web.button;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.IAjaxIndicatorAware;
import org.apache.wicket.ajax.markup.html.form.AjaxButton;
import org.apache.wicket.markup.html.form.Form;

/**
 * 
 * @author Ernesto Reinaldo Barreiro (reiern70@gmail.com)
 */
public abstract class IndicatingAjaxSubmitButton extends AjaxButton implements IAjaxIndicatorAware {

	private static final long serialVersionUID = 1L;
	
	private final AjaxIndicatorAppender indicatorAppender = new AjaxIndicatorAppender();

	/**
	 *
	 * @param id
	 * @param form
	 */
	public IndicatingAjaxSubmitButton(String id)
	{
		super(id);
		add(indicatorAppender);
	}
	
	/**
	 *
	 * @param id
	 * @param form
	 */
	public IndicatingAjaxSubmitButton(String id, Form<?> form)
	{
		super(id, form);
		add(indicatorAppender);
	}

	protected abstract void onSubmit(AjaxRequestTarget target, Form<?> form);

	/**
	 * @see IAjaxIndicatorAware#getAjaxIndicatorMarkupId()
	 * @return the markup id of the ajax indicator
	 *
	 */
	public String getAjaxIndicatorMarkupId()
	{
        return indicatorAppender.getMarkupId();
	}

}