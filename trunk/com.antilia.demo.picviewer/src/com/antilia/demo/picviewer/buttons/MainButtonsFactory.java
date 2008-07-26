/**
 * This software is provided as IS by Antilia-Soft SL.
 * Copyright 2006-2007.
 */
package com.antilia.demo.picviewer.buttons;

import com.antilia.demo.picviewer.Activator;
import com.antilia.demo.picviewer.Index;
import com.antilia.demo.picviewer.osgi.IPicturesService;
import com.antilia.demo.picviewer.osgi.IPicturesSource;
import com.antilia.web.button.IMenuItemHolder;
import com.antilia.web.button.IMenuItemsFactory;

/**
 * 
 * @author Ernesto Reinaldo Barreiro (reiern70@gmail.com)
 */
public class MainButtonsFactory implements IMenuItemsFactory {
	
	private static final long serialVersionUID = 1L;

	private Index page;
	
	public MainButtonsFactory(Index page) {
		this.page=page;
	}
	
	public void populateMenuItems(String menuId, IMenuItemHolder itemHolder) {
		IPicturesService service =  Activator.getPicturesService();
		if(service != null) {
			for(IPicturesSource source: service.getSources()) {
				itemHolder.addMenuItem(new PicturesPageButton(page, source));		
			}
		}
	}

}
