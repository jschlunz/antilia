/**
 * This software is provided as IS by Antilia-Soft SL.
 * Copyright 2006-2007.
 */
package com.antilia.web.adxmenu.htb;

import org.apache.wicket.ResourceReference;
import org.apache.wicket.markup.html.panel.Panel;

/**
 * 
 *
 * @author Ernesto Reinaldo Barreiro (reiern70@gmail.com)
 *
 */
public class AdxMenu extends Panel {

	private static final long serialVersionUID = 1L;

	public static ResourceReference HTB_CSS = new ResourceReference(TestPanel.class, "htb.css");
	
	private boolean topMenu = false;
	/**
	 * @param id
	 */
	public AdxMenu(String id) {
		super(id);
		
	}
	
	@Override
	protected void onBeforeRender() {
		
		super.onBeforeRender();
	}
	
	private boolean isTopMenu() {
		if(!topMenu) {
			topMenu = findAdxMenu() == null;
		}
		return topMenu;
	}
	
	private AdxMenu findAdxMenu() {
		return (AdxMenu)findParent(AdxMenu.class);
	}
	
}
