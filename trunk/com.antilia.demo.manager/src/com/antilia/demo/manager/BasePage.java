package com.antilia.demo.manager;

import org.apache.wicket.behavior.HeaderContributor;
import org.apache.wicket.markup.html.WebPage;
import org.wicketstuff.yui.markup.html.menu2.YuiMenuBar;

import com.antilia.demo.manager.resources.AppStyle;
import com.antilia.web.resources.DefaultStyle;

public class BasePage extends WebPage {

	private static final long serialVersionUID = 1L;	
	
	
	public BasePage() {		
		
		add(HeaderContributor.forJavaScript(DefaultStyle.JS_COMMON));
		add(HeaderContributor.forJavaScript(DefaultStyle.JS_TABLE));
		
		add(HeaderContributor.forCss(DefaultStyle.CSS_MAIN));
		add(HeaderContributor.forCss(AppStyle.CSS_MANAGER));		
		add(HeaderContributor.forJavaScript(AppStyle.CSS_TABLE));
		add(HeaderContributor.forCss(AppStyle.YUI_SAM_MENU));	
		
		add(createMenu2("menuBar", "menuBar"));
								
	}
	
	public YuiMenuBar createMenu2(String wicketId, String elementId) {
		YuiMenuBar mb = new YuiMenuBar(wicketId, elementId);		
		mb.setAutosubmenudisplay(true);
		mb.setClicktohide(true);
		mb.setHidedelay(1200);
		mb.setLazyload(false);
		return mb;		
	}
}
