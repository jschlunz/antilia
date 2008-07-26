/**
 * This software is provided as IS by Antilia-Soft SL.
 * Copyright 2006-2007.
 */
package com.antilia.web.beantable;

import java.io.Serializable;

import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.panel.Panel;

/**
 * 
 * @author Ernesto Reinaldo Barreiro (reiern70@gmail.com)
 */
public class FirstBodyCell<E extends Serializable> extends Panel {

	private static final long serialVersionUID = 1L;

	private WebMarkupContainer imagePanel;
	
	private Table<E> table;
	
	int row;
	/**
	 * @param id
	 * @param model
	 */
	public FirstBodyCell(String id, int row, Table<E> table, RowItem<E> item) {
		super(id);
		this.table = table;
		this.row = row;	
		imagePanel = new WebMarkupContainer("imagePanel");
		/*
		Image image = new Image("checkboxImage") {
			private static final long serialVersionUID = 1L;

			@Override
			protected ResourceReference getImageResourceReference() {
				return FirstBodyCell.this.getImage();
			}
		};
		*/
		ToggleSelectRowButton<E> selectRowButton = new ToggleSelectRowButton<E>("checkboxImage", table, row);
		imagePanel.setOutputMarkupId(true);
		//if(item != null)
		//	item.setImagePanel(imagePanel);
		//imagePanel.add(image);
		imagePanel.add(selectRowButton);
		table.addRowCheckBox(imagePanel);
		add(imagePanel);
	}
	
	/*
	protected ResourceReference getImage() {
		IPageableSource<E> source = table.getPageableSource();
		boolean selected = source.isSelected(row);
		if(selected)
			return DefaultStyle.IMG_CHECKBOX_CHECKED;
		else
			return DefaultStyle.IMG_CHECKBOX_UNCHECKED;
	}
	*/
	
	public Table<E> getTable() {
		return table;
	}

}
