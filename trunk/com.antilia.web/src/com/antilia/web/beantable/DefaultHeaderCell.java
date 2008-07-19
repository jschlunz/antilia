/**
 * This software is provided as IS by Antilia-Soft SL.
 * Copyright 2006-2007.
 */
package com.antilia.web.beantable;

import java.io.Serializable;

import org.apache.wicket.AttributeModifier;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.HiddenField;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.model.StringResourceModel;

import com.antilia.common.util.ResourceUtils;
import com.antilia.web.beantable.model.IColumnModel;
import com.antilia.web.beantable.navigation.ColumnMenuItemsFactory;
import com.antilia.web.menu.Menu;

/**
 * 
 * @author Ernesto Reinaldo Barreiro (reiern70@gmail.com)
 */
public class DefaultHeaderCell<E extends Serializable> extends Panel {

	private static final long serialVersionUID = 1L;

	private Table<E> table;

	private int column;
	
	private Class<E> beanClass;
	
	/**
	 * @param id
	 * @param model
	 */
	public DefaultHeaderCell(String id, int column, Table<E> table, IColumnModel<E> columnModel, Class<E> beanClass) {
		super(id, columnModel);
		this.table = table;
		this.column = column;
		this.beanClass  = beanClass;
		setRenderBodyOnly(true);
		add(new HiddenField("colWidth", new Model() {
	
			private static final long serialVersionUID = 1L;

			@Override
			public Object getObject() {
				return DefaultHeaderCell.this.getColumnModel().getWidth();
			}
			
			@Override
			public void setObject(Object object) {
				if(object instanceof Integer) {
					DefaultHeaderCell.this.getColumnModel().setWidth(((Integer)object).intValue());
				}
			}
			
			
		}, Integer.class));
		
		Menu menu = Menu.createMenu("menu",ColumnMenuItemsFactory.getInstance());
		menu.setMenuStyle("width: auto; background: transparent; right: 0px;  float: right;");
		add(menu);
		add(new Label("title",DefaultHeaderCell.this.getLabelModel()));
		WebMarkupContainer dragTd = new WebMarkupContainer("dragTd");
		dragTd.add(new AttributeModifier("id", new Model() {
			private static final long serialVersionUID = 1L;

			@Override
			public Object getObject() {
				return DefaultHeaderCell.this.getTable().getMarkupId()+"_c_"+DefaultHeaderCell.this.getColumn();
			}
		}));	
		//dragTd.add(new Image("dragImage",  DefaultStyle.IMG_RESIZE));
		add(dragTd);
	}
	
	@SuppressWarnings("unchecked")
	protected IModel getLabelModel() {
		String key = ResourceUtils.getPropertyResourceKey(getBeanClass(), getColumnModel().getPropertyPath());
		return new StringResourceModel(key, this, null, "Not found");
	}
	
	@SuppressWarnings("unchecked")
	protected IColumnModel<E> getColumnModel() {
		return (IColumnModel<E>)getModel();
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

	public Class<E> getBeanClass() {
		return beanClass;
	}

	public void setBeanClass(Class<E> beanClass) {
		this.beanClass = beanClass;
	}
}
