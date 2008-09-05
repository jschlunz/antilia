/**
 * This software is provided as IS by Antilia-Soft SL.
 * Copyright 2006-2007.
 */
package com.antilia.web.beantable;

import java.io.Serializable;

import org.apache.wicket.AttributeModifier;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.form.HiddenField;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.Model;

import com.antilia.web.button.MenuItemsFactory;
import com.antilia.web.menu.Menu;

/**
 * 
 * @author Ernesto Reinaldo Barreiro (reiern70@gmail.com)
 */
public class FirstHeaderCell<E extends Serializable> extends Panel {

	private static final long serialVersionUID = 1L;

	private Table<E> table;

	private int column;
	/**
	 * @param id
	 * @param model
	 */
	public FirstHeaderCell(String id, int column, Table<E> table) {
		super(id);
		this.table = table;
		this.column = column;
		setRenderBodyOnly(true);		
		add(new HiddenField("colWidth", new Model<Integer>() {
			
			private static final long serialVersionUID = 1L;

			@Override
			public Integer getObject() {
				return FirstHeaderCell.this.getTable().getFirstColumnModel().getWidth();
			}
			
			@Override
			public void setObject(Integer object) {
				if(object instanceof Integer) {
					FirstHeaderCell.this.getTable().getFirstColumnModel().setWidth((Integer)object);
				}
			}
			
			
		}, Integer.class));
		MenuItemsFactory factory = getTable().getFirstHeaderMenuItemsFactory();		
		Menu menu = Menu.createMenu("headerMenu", factory);
		menu.setHorizontalStyleClass("trans-menu");
		add(menu);
		WebMarkupContainer dragTd = new WebMarkupContainer("dragTd");
		dragTd.add(new AttributeModifier("id", new Model<String>() {
			private static final long serialVersionUID = 1L;

			@Override
			public String getObject() {
				return FirstHeaderCell.this.getTable().getMarkupId()+"_c_"+FirstHeaderCell.this.getColumn();
			}
		}));
		//dragTd.add(new Image("dragImage",  DefaultStyle.IMG_RESIZE));
		add(dragTd);
	}
	public Table<E> getTable() {
		return table;
	}
	public void setTable(Table<E> table) {
		this.table = table;
	}
	public int getColumn() {
		return column;
	}
	public void setColumn(int column) {
		this.column = column;
	}
}
