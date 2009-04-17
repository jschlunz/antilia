/**
 * This software is provided as IS by Antilia-Soft SL.
 * Copyright 2008-2009.
 */
package com.antilia.web.field.impl;

import java.io.Serializable;
import java.util.Arrays;

import org.apache.wicket.markup.html.form.DropDownChoice;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.Model;
import org.apache.wicket.model.PropertyModel;

import com.antilia.hibernate.query.Operator;
import com.antilia.web.field.IFieldModel;

/**
 * @author Ernesto Reinaldo Barreiro (reiern70@gmail.com)
 *
 */
public class OperatorsPanel<B extends Serializable> extends Panel {

	private static final long serialVersionUID = 1L;

	private IFieldModel<B> model;
	/**
	 * @param id
	 */
	public OperatorsPanel(String id, IFieldModel<B> model) {
		super(id);
		this.model = model;
		setRenderBodyOnly(true);
		DropDownChoice<Operator> operator = new DropDownChoice<Operator>("operator", new Model<Operator>() {
			private static final long serialVersionUID = 1L;

			@Override
			public Operator getObject() {
				return OperatorsPanel.this.model.getSelectedOperator();
			}
			
			@Override
			public void setObject(Operator object) {
				OperatorsPanel.this.model.setSelectedOperator(object);
			}
		}, Arrays.asList(model.getOperators()));
		operator.setRequired(false);
		operator.setNullValid(true);
		add(operator);
	}	

}
