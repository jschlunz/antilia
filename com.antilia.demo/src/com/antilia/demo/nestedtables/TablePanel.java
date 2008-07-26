/**
 * This software is provided as IS by Antilia-Soft SL.
 * Copyright 2006-2007.
 */
package com.antilia.demo.nestedtables;

import java.io.Serializable;
import java.util.List;

import org.apache.wicket.markup.html.panel.Panel;

import com.antilia.web.beantable.Table;
import com.antilia.web.beantable.model.TableModel;

/**
 * 
 * @author Ernesto Reinaldo Barreiro (reiern70@google.com)
 */
public class TablePanel<B extends Serializable> extends Panel {

	private static final long serialVersionUID = 1L;

	/**
	 * @param id
	 */
	public TablePanel(String id, Class<B> beanClass, List<B> beans,  String... columns) {
		super(id);
		TableModel<B> tableModel = new TableModel<B>(beanClass, columns);
		Table<B> table = new Table<B>("table",tableModel, beans);
		add(table );
	}
}
