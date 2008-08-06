/**
 * This software is provided as IS by Antilia-Soft SL.
 * Copyright 2006-2007.
 */
package com.antilia.web.beantable.navigation;

import java.io.Serializable;

import org.apache.wicket.AttributeModifier;
import org.apache.wicket.ResourceReference;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.image.Image;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.Model;

import com.antilia.web.beantable.Table;
import com.antilia.web.button.AbstractButton;
import com.antilia.web.button.IMenuItem;
import com.antilia.web.resources.DefaultStyle;

/**
 * 
 * @author Ernesto Reinaldo Barreiro (reiern70@gmail.com)
 */
public class DropColumnItem<E extends Serializable> extends Panel implements IMenuItem {

	private static final long serialVersionUID = 1L;

	private int order = AbstractButton.NO_ORDER;
	
	/**
	 * @param id
	 */
	public DropColumnItem() {
		super("dropColumn");				
		WebMarkupContainer drop = new WebMarkupContainer("drop");
		drop.setOutputMarkupId(true); 
		drop.add(new AttributeModifier("id",  new Model() {
			private static final long serialVersionUID = 1L;

			@Override
			public Object getObject() {
				Table<E> table = DropColumnItem.this.findTable();
				return  table.getMarkupId() + "_dropCol";
			}
		}));
		
		add(drop);
		Image image = new Image("dropImage", getImage());
		drop.add(image);
	}
	
	@SuppressWarnings("unchecked")
	private Table<E> findTable() {
		return (Table<E>)findParent(Table.class);
	}
	
	/* (non-Javadoc)
	 * @see com.antilia.web.toolbar.AButton#getImage()
	 */
	protected ResourceReference getImage() {
		return DefaultStyle.IMG_DELETE_COLS;
	}

	/**
	 * @return the order
	 */
	public int getOrder() {
		return order;
	}

	/**
	 * @param order the order to set
	 */
	public void setOrder(int order) {
		this.order = order;
	}

}
