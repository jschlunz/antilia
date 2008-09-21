/**
 * This software is provided as IS by Antilia-Soft SL.
 * Copyright 2006-2007.
 */
package com.antilia.web.button;

import org.apache.wicket.ajax.IAjaxIndicatorAware;
import org.apache.wicket.ajax.markup.html.AjaxLink;
import org.apache.wicket.model.IModel;

/**
 * 
 *
 * @author Ernesto Reinaldo Barreiro (reiern70@gmail.com)
 *
 */
public abstract class IndicatingAjaxLink<T> extends AjaxLink<T> implements
		IAjaxIndicatorAware {

	private static final long serialVersionUID = 1L;

	private final AjaxIndicatorAppender indicatorAppender = new AjaxIndicatorAppender();
	
	/**
	 * @param id
	 */
	public IndicatingAjaxLink(String id) {
		super(id);
		add(indicatorAppender);
	}

	/**
	 * @param id
	 * @param form
	 */
	public IndicatingAjaxLink(String id, final IModel<T> model) {
		super(id, model);
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
