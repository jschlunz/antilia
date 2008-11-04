/**
 * This software is provided as IS by Antilia-Soft SL.
 * Copyright 2006-2007.
 */
package com.antilia.web.menu;

import org.apache.wicket.ResourceReference;
import org.apache.wicket.markup.html.panel.Panel;

import com.antilia.web.button.AbstractButton;
import com.antilia.web.button.IMenuItem;
import com.antilia.web.button.MenuItemsFactory;
import com.antilia.web.button.ScriptButton;


/**
 * 
 * @author Ernesto Reinaldo Barreiro (reiern70@gmail.com)
 */
public abstract class DropDownButton extends Panel implements IMenuItem {

	private static final long serialVersionUID = 1L;

	private DropDownMenu menu;
	
	private int order = AbstractButton.NO_ORDER;
	
	private ScriptButton button;
	
	public DropDownButton(String id) {
		super(id);
		button = new ScriptButton("button"){			
			private static final long serialVersionUID = 1L;

			@Override
			protected ResourceReference getImage() {
				return DropDownButton.this.getImage();
			}
			
			 @Override
			protected String getLabel() {
				return DropDownButton.this.getLabel();
			}
			 
			 @Override
			protected String getTitleKey() {
				return null;
			}
			
			@Override
			protected String getJavaScript() {
				return "document.getElementById('"+menu.getMarkupId()+"').style.display = 'block';"; 
			}
		};
		
		add(button);
		
		menu = newMenu("menu"); 
		menu.setOutputMarkupId(true);
		
		add(menu);
	}
	
	public static DropDownButton createDropDownButton(String id, final String label, final ResourceReference image, final IMenuItem[] items)  {
		return new DropDownButton(id) {

			private static final long serialVersionUID = 1L;

			@Override
			protected ResourceReference getImage() {
				// TODO Auto-generated method stub
				return image;
			}

			@Override
			protected String getLabel() {
				return label;
			}

			@Override
			public DropDownMenu newMenu(String id) {
				return new DropDownMenu(id, new MenuItemsFactory(items));
			}						
		};
	}
	
	protected abstract String getLabel();

	protected abstract ResourceReference getImage();
	

	public abstract DropDownMenu newMenu(String id);

	public int getOrder() {
		return order;
	}

	public void setOrder(int order) {
		this.order = order;
	}
}
