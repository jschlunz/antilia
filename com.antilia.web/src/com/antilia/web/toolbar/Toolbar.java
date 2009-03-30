/**
 * This software is provided as IS by Antilia-Soft SL.
 * Copyright 2006-2007.
 */
package com.antilia.web.toolbar;

import java.util.ArrayList;
import java.util.List;

import org.apache.wicket.Component;
import org.apache.wicket.behavior.HeaderContributor;
import org.apache.wicket.markup.ComponentTag;
import org.apache.wicket.markup.MarkupStream;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.markup.repeater.RepeatingView;
import org.apache.wicket.model.Model;

import com.antilia.web.resources.DefaultStyle;

/**
 * 
 *
 * @author Ernesto Reinaldo Barreiro (reiern70@gmail.com)
 *
 */
public class Toolbar extends Panel implements IToolbar {

	List<IToolbarItem> items = new ArrayList<IToolbarItem>();
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private WebMarkupContainer table;
	
	private WebMarkupContainer toolbar;
	
	public Toolbar(String id) { 
		this(id, (IToolbarItemsFactory[])null);
	}
	
	/**
	 * @param id
	 */
	public Toolbar(String id, IToolbarItemsFactory... factories) {
		super(id);
		
		add(HeaderContributor.forJavaScript(DefaultStyle.JS_YUI_DOM_EVENT));
		add(HeaderContributor.forJavaScript(DefaultStyle.JS_YUI_DOM_MIN));
		add(HeaderContributor.forJavaScript(DefaultStyle.JS_YUI_EVENT));
		add(HeaderContributor.forJavaScript(DefaultStyle.JS_YUI_ANIMATION));
		add(HeaderContributor.forJavaScript(DefaultStyle.JS_YUI_DRAG_DROP));
		
		add(HeaderContributor.forCss(DefaultStyle.CC_menu));
		add(HeaderContributor.forJavaScript(DefaultStyle.JS_ie5));
		add(HeaderContributor.forJavaScript(DefaultStyle.JS_DropDownMenuX));
	
		toolbar = new WebMarkupContainer("toolbar");		
		add(toolbar);
		
		table = new WebMarkupContainer("table");		
		table.setOutputMarkupId(true);		
		toolbar.add(table);		
		
		if(factories != null) {
			for(IToolbarItemsFactory factory : factories) {
				factory.populateMenuItems(id, this);
			}
		}
	}
	
	public static Toolbar createToolbar(String id, IToolbarItemsFactory... factories) {
		return new Toolbar(id, factories);
	}
	
	@Override
	protected void onBeforeRender() {
		super.onBeforeRender();
		
		RepeatingView items = new RepeatingView("items");		
		table.addOrReplace(items);
		
		for(IToolbarItem item: this.items) {
			if(item instanceof Component) {
				items.add((Component)item);
			}
		}
		
		Label script = new Label("script", new Model<String>()) {
			private static final long serialVersionUID = 1L;

			@Override
			protected void onComponentTagBody(MarkupStream markupStream, ComponentTag openTag) {
				String toolbarId =  Toolbar.this.table.getMarkupId()!=null?Toolbar.this.table.getMarkupId():"toolbar";
				StringBuffer sb = new StringBuffer();
				sb.append("var ");
				sb.append(toolbarId);
				sb.append(" = new DropDownMenuX('" + toolbarId + "');\n");				
				sb.append(toolbarId);
				sb.append(".delay.show = 0;\n");
				sb.append(toolbarId);
				sb.append(".delay.hide = 400;\n");
				sb.append(toolbarId);
				sb.append(".position.levelX.left = 2;\n");
				sb.append("YAHOO.util.Event.onDOMReady(function(){"+toolbarId+".init();})");
				replaceComponentTagBody(markupStream, openTag, sb.toString());
			}
		};
		toolbar.addOrReplace(script);
		

	}
	
	
	public IToolbar addItem(IToolbarItem item) {
		items.add(item);
		return this;
	}
	
	public IToolbar removeItem(IToolbarItem item) {
		items.add(item);
		return this;
	}
	
	public boolean isOnTop() {
		return true;
	}
	
}
