/**
 * This software is provided as IS by Antilia-Soft SL.
 * Copyright 2006-2007.
 */
package com.antilia.letsplay;

import org.apache.wicket.markup.html.CSSPackageResource;
import org.apache.wicket.markup.html.panel.Panel;

import com.antilia.letsplay.resources.AppStyle;
import com.antilia.web.login.IProtectedPage;
import com.antilia.web.toolbar.Toolbar;


/**
 * 
 * @author Ernesto Reinaldo Barreiro (reiern70@gmail.com)
 *
 */
public class Index extends RoundBasePage implements IProtectedPage {

	private static final long serialVersionUID = 1L;	
	
	public Index() {		
		super();		
		add(CSSPackageResource.getHeaderContribution((AppStyle.CSS)));
		
	}
	
	@Override
	protected Panel newContentPanel(String id) {
		return Toolbar.createToolbar(id);
	}
}
