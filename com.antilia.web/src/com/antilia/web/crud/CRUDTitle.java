/**
 * This software is provided as IS by Antilia-Soft SL.
 * Copyright 2006-2007.
 */
package com.antilia.web.crud;

import java.io.Serializable;

import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.StringResourceModel;

import com.antilia.common.util.ResourceUtils;

/**
 * 
 *
 * @author Ernesto Reinaldo Barreiro (reiern70@gmail.com)
 *
 */
public class CRUDTitle<B extends Serializable> extends Panel {

	private static final long serialVersionUID = 1L;

	/**
	 * @param id
	 */
	public CRUDTitle(String id, Class<B> beanClass) {
		super(id);
		setRenderBodyOnly(true);
		String key = ResourceUtils.getPropertyResourceKey(beanClass, null);
		add(new Label("title",  new StringResourceModel(key, this, null, beanClass.getSimpleName())));
	}
}
