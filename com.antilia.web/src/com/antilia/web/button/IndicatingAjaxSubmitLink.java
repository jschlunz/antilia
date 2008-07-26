/**
 * This software is provided as IS by Antilia-Soft SL.
 * Copyright 2006-2007.
 */
package com.antilia.web.button;

import org.apache.wicket.ajax.IAjaxIndicatorAware;
import org.apache.wicket.ajax.markup.html.form.AjaxSubmitLink;
import org.apache.wicket.markup.html.form.Form;

/**
 * 
 *
 * @author Ernesto Reinaldo Barreiro (reiern70@gmail.com)
 *
 */
public abstract class IndicatingAjaxSubmitLink extends AjaxSubmitLink implements
		IAjaxIndicatorAware {

	private static final long serialVersionUID = 1L;

	private final AjaxIndicatorAppender indicatorAppender = new AjaxIndicatorAppender();
	
	/**
	 * @param id
	 */
	public IndicatingAjaxSubmitLink(String id) {
		super(id);
		add(indicatorAppender);
	}

	/**
	 * @param id
	 * @param form
	 */
	public IndicatingAjaxSubmitLink(String id, Form form) {
		super(id, form);
		add(indicatorAppender);
	}


	/* (non-Javadoc)
	 * @see org.apache.wicket.ajax.IAjaxIndicatorAware#getAjaxIndicatorMarkupId()
	 */
	@Override
	public String getAjaxIndicatorMarkupId() {		
		return indicatorAppender.getMarkupId();
	}

}
