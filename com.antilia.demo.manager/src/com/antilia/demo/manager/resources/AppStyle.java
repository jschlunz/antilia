package com.antilia.demo.manager.resources;

import org.apache.wicket.ResourceReference;

/**
 * 
 *
 * @author Ernesto Reinaldo Barreiro (reiern70@gmail.com)
 *
 */
public class AppStyle {
	
	public static ResourceReference LOGO = new ResourceReference(AppStyle.class, "Logo.gif");
	
	public static ResourceReference CSS_MANAGER  = new ResourceReference(AppStyle.class, "manager.css");
	
	public static ResourceReference YUI_SAM_MENU = new ResourceReference(AppStyle.class, "sam/menu.css");	
	
	public static ResourceReference CSS_TABS = new ResourceReference(AppStyle.class, "tabs.css");
	
	public static ResourceReference IMG_TABLE = new ResourceReference(AppStyle.class, "table.gif");

}
