/**
 * This software is provided as IS by Antilia-Soft SL.
 * Copyright 2006-2007.
 */
package com.antilia.web.dialog;

import java.util.Iterator;

import org.apache.wicket.Component;

/**
 * @author Ernesto Reinaldo Barreiro (reiern70@gmail.com)
 *
 */
public interface IDialogScope {

	public static final String BODY_ID = "body";
	
	boolean isVisible();
	
	Component setVisible(boolean visible);
	
	String getDialogId();
	
	void addDialog(IDialogScope dialog);
	
	//TODO: see if I can come up with an interface for Dialogs.
	Iterator<IDialogScope> getDialogs();

	public void replaceBody(Component body);
}
