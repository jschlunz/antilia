/**
 * This software is provided as IS by Antilia-Soft SL.
 * Copyright 2006-2007.
 */
package com.antilia.web.menu;

import org.apache.wicket.AttributeModifier;
import org.apache.wicket.Component;
import org.apache.wicket.markup.html.CSSPackageResource;
import org.apache.wicket.markup.html.JavascriptPackageResource;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.markup.repeater.RepeatingView;
import org.apache.wicket.model.Model;

import com.antilia.web.button.AbstractButton;
import com.antilia.web.button.IMenuItem;
import com.antilia.web.button.IMenuItemHolder;
import com.antilia.web.button.IMenuItemsFactory;
import com.antilia.web.button.MenuItemsFactory;
import com.antilia.web.effect.Effect;
import com.antilia.web.effect.EffectAttributeModifier;
import com.antilia.web.resources.DefaultStyle;

/**
 * A Menu class
 * 
 * @author Ernesto Reinaldo Barreiro (reiern70@gmail.com)
 */
public  class Menu extends Panel implements IMenuItem, IMenuItemHolder {

	private static final long serialVersionUID = 1L;

	private int order = AbstractButton.NO_ORDER;
	
	private RepeatingView toolBar;
	
	private String horizontalStyleClass = "nav-menu";
	
	private String verticalStyleClass = "vertical-menu";
	
	private String menuStyle = "";
	
	private IMenuItemsFactory[] factories;
	
	private IMenuItemsAuthorizer authorizer;
	
	public static enum Type {
		HORIZONTAL,
		VERTICAL,
	};
	
	private Type type;
	
	private  Menu(String id, String title, IMenuItemsAuthorizer authorizer, IMenuItemsFactory... factories) {
		this(id,Type.HORIZONTAL, title, authorizer, factories);
	}
	/**
	 * @param id
	 */
	private Menu(String id, Type type, String title, IMenuItemsAuthorizer authorizer, IMenuItemsFactory... factories) {
		super(id);
	
		this.authorizer = authorizer;
		
		add(CSSPackageResource.getHeaderContribution(DefaultStyle.CSS_MAIN));	
		add(JavascriptPackageResource.getHeaderContribution(DefaultStyle.JS_COMMON));
		
		setOutputMarkupId(true);
		
		this.type = type;
		this.factories = factories;
		
		toolBar = new RepeatingView("toolbar");	
		
		WebMarkupContainer mainDiv = new WebMarkupContainer("mainDiv");		
		mainDiv.add(new AttributeModifier("class", new Model<String>() {
			private static final long serialVersionUID = 1L;

			@Override
			public String getObject() {
				if(Menu.this.getType().equals(Type.HORIZONTAL)) 
					return Menu.this.getHorizontalStyleClass();
				else 
					return Menu.this.getVerticalStyleClass();
							
			}
		}));
		mainDiv.add(new AttributeModifier("style", new Model<String>() {
			private static final long serialVersionUID = 1L;

			@Override
			public String getObject() {
				return menuStyle;							
			}
		}));
		add(mainDiv);
		WebMarkupContainer menu = new WebMarkupContainer("menu");
		Label handle = new Label("handle", new Model<String>(title)) {
			private static final long serialVersionUID = 1L;

			@Override
			public boolean isVisible() {
				return Menu.this.getType().equals(Type.VERTICAL);
			}
		};
		mainDiv.add(handle);		
		if(Menu.this.getType().equals(Type.VERTICAL)) {
			menu.setOutputMarkupId(true);
			new EffectAttributeModifier(menu, handle, "ondblclick", true){
				private static final long serialVersionUID = 1L;

				@Override
				protected Effect getEffect() {
					Effect.toggle toggle = new Effect.toggle(Effect.toggle.Type.slide);
					toggle.setDuration(0.5f);
					return toggle;
				}
			};
		} else {
			menu.setRenderBodyOnly(true);
		}		
		populateMenuItems(id);
		menu.add(toolBar);		
		mainDiv.add(menu);
	}	
	
	public String getHorizontalStyleClass() {
		return horizontalStyleClass;
	}
	
	protected String getVerticalStyleClass() {
		return verticalStyleClass;
	}
	
	public IMenuItemHolder addMenuItem(IMenuItem menuItem) {
		if(menuItem instanceof Component) {
			if(authorizer != null) {
				if(authorizer.isMenuItemAuthorized(menuItem))
					toolBar.add((Component)menuItem);
			} else {
				toolBar.add((Component)menuItem);
			}
		}
		return this;
	}
	
	public void populateMenuItems(String menuId) {
		if(factories == null | factories.length == 0) {
			return;
		}
		for(IMenuItemsFactory factory: factories) {
			if(factory != null) {
				factory.populateMenuItems(menuId, this);
			}	
			
		}
	}
		
	public static final Menu createMenu(String menuId, IMenuItemsAuthorizer authorizer, final IMenuItemsFactory... factories) {		
		return new Menu(menuId, "", authorizer, factories);
	}

	public static final Menu createVerticalMenu(String menuId, String title, IMenuItemsAuthorizer authorizer, final IMenuItemsFactory... factories) {		
		return new Menu(menuId, Type.VERTICAL, title, authorizer, factories);
	}
	
	public static final Menu createVerticalMenu(String menuId, String title,IMenuItemsAuthorizer authorizer,  final IMenuItem[] items) {	
		return new Menu(menuId, Type.VERTICAL, title, authorizer, new MenuItemsFactory(items)) ;
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

	/**
	 * @return the type
	 */
	protected Type getType() {
		return type;
	}

	/**
	 * @param type the type to set
	 */
	protected Menu setType(Type type) {
		this.type = type;
		return this;
	}
	public void setHorizontalStyleClass(String horizontalStyleClass) {
		this.horizontalStyleClass = horizontalStyleClass;
	}
	public void setVerticalStyleClass(String verticalStyleClass) {
		this.verticalStyleClass = verticalStyleClass;
	}
	public String getMenuStyle() {
		return menuStyle;
	}
	public void setMenuStyle(String menuStyle) {
		this.menuStyle = menuStyle;
	}
	
	public String newItemId() {
		return toolBar.newChildId();
	}
}
