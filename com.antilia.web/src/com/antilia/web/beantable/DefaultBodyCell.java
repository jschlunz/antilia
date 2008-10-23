/**
 * This software is provided as IS by Antilia-Soft SL.
 * Copyright 2006-2007.
 */
package com.antilia.web.beantable;

import java.io.Serializable;

import org.apache.wicket.markup.ComponentTag;
import org.apache.wicket.markup.MarkupStream;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.PropertyModel;

import com.antilia.common.util.StringUtils;
import com.antilia.web.beantable.model.IColumnModel;

/**
 * 
 * @author Ernesto Reinaldo Barreiro (reiern70@gmail.com)
 */
public class DefaultBodyCell<E extends Serializable> extends Panel {

	private static final long serialVersionUID = 1L;


	/**
	 * @param id
	 * @param model
	 */
	public DefaultBodyCell(String id, IColumnModel<E> columnModel, E object) {
		super(id, columnModel);		
		Label label = new Label("cell",newBodyCellModel(columnModel, object)) {
			
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
			protected void onComponentTagBody(MarkupStream markupStream, ComponentTag openTag) {
				if(!StringUtils.isEmpty(getDefaultModelObjectAsString().trim())) {
					super.onComponentTagBody(markupStream, openTag);
					return;
				}
				replaceComponentTagBody(markupStream, openTag, "&nbsp;");
			}
		
		};
		label.setRenderBodyOnly(true);
		add(label);
	}
	
	
	protected IModel<E> newBodyCellModel(IColumnModel<E> columnModel, E object) {
		return new PropertyModel<E>(object, columnModel.getPropertyPath());
	}

	@SuppressWarnings("unchecked")
	protected IColumnModel<E> getColumnModel() {
		return (IColumnModel<E>)getDefaultModel();
	}
}
