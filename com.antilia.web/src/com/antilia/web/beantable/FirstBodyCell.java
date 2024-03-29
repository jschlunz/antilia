/**
 * This software is provided as IS by Antilia-Soft SL.
 * Copyright 2006-2007.
 */
package com.antilia.web.beantable;

import java.io.Serializable;

import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.panel.Panel;

import com.antilia.web.button.IMenuItemHolder;
import com.antilia.web.button.IMenuItemsFactory;
import com.antilia.web.menu.Menu;
import com.antilia.web.provider.SelectionMode;

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
	public FirstBodyCell(String id, int row, Table<E> table, final RowItem<E> item) {
		super(id);
		this.table = table;
		this.row = row;			
		imagePanel = new WebMarkupContainer("imagePanel");
		imagePanel.setOutputMarkupId(true);
		/*
		Image image = new Image("checkboxImage") {
			private static final long serialVersionUID = 1L;

			@Override
			protected ResourceReference getImageResourceReference() {
				return FirstBodyCell.this.getImage();
			}
		};
		*/
		SelectionMode mode = table.getTableModel().getSelectionMode();
		if(mode.equals(SelectionMode.MULTIPLE) || mode.equals(SelectionMode.SINGLE)) {
			ToggleSelectRowButton<E> selectRowButton = new ToggleSelectRowButton<E>("checkboxImage", table, row);
			imagePanel.add(selectRowButton);
		} else {
			Label label = new Label("checkboxImage", "");
			imagePanel.add(label);
		}
		
		table.addRowCheckBox(imagePanel);
		add(imagePanel);
		
		Menu menu = Menu.createMenu("menu", null,new IMenuItemsFactory() {
			
			private static final long serialVersionUID = 1L;

			public void populateMenuItems(String menuId, IMenuItemHolder itemHolder) {				
				E bean = (E)item.getModel().getObject();
				FirstBodyCell.this.table.populateRowMenu(itemHolder,FirstBodyCell.this.row, bean) ;
			}
		});
		menu.setMenuStyle("width: auto; background: transparent; height: 18px;");
		menu.setRenderBodyOnly(true);
		menu.setOutputMarkupId(false);
		imagePanel.add(menu);

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
