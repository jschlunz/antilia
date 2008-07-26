/**
 * This software is provided as IS by Antilia-Soft SL.
 * Copyright 2006-2007.
 */
package com.antilia.web.dialog;

import java.util.Iterator;

/**
 * @author Ernesto Reinaldo Barreiro (reiern70@gmail.com)
 *
 */
public interface IDialogScope {

	String getDialogId();
	
	void addDialog(DefaultDialog dialog);
	
	//TODO: see if I can come up with an interface for Dialogs.
	Iterator<DefaultDialog> getDialogs();
}
