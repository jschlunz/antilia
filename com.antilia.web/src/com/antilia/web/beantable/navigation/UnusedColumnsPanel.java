/**
 * This software is provided as IS by Antilia-Soft SL.
 * Copyright 2006-2007.
 */
package com.antilia.web.beantable.navigation;

import java.io.Serializable;
import java.util.Iterator;

import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.markup.repeater.RepeatingView;

import com.antilia.web.beantable.Table;
import com.antilia.web.beantable.model.IColumnModel;

/**
 * 
 *
 * @author Ernesto Reinaldo Barreiro (reiern70@gmail.com)
 *
 */
public class UnusedColumnsPanel<E extends Serializable> extends Panel {

	private static final long serialVersionUID = 1L;

	/**
	 * @param id
	 */
	public UnusedColumnsPanel(String id) {
		super(id);
	}
	
	@Override
	protected void onBeforeRender() {
		super.onBeforeRender();
		Table<E> table =  findTable();
		Iterator<IColumnModel<E>> it = table.getTableModel().getHiddenModels(); 
		if(it.hasNext()) {
			RepeatingView columns = new RepeatingView("columns");		
			while(it.hasNext()) {
				IColumnModel<E> model = it.next();
				columns.add(new Label(columns.newChildId(), model.getPropertyPath()));
			}
			addOrReplace(columns);
		} else {
			/*
			Label columns = new Label("columns", "");
			addOrReplace(columns);
			*/
				RepeatingView columns = new RepeatingView("columns");		
				columns.add(new Label(columns.newChildId(), "1-one"));
				columns.add(new Label(columns.newChildId(), "1-two"));
				columns.add(new Label(columns.newChildId(), "1-three"));
				addOrReplace(columns);
		}
	}
	
	
	@SuppressWarnings("unchecked")
	private Table<E> findTable() {
		return (Table<E>)findParent(Table.class);
	}
}
