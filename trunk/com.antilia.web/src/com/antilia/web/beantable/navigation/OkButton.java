/**
 * 
 */
package com.antilia.web.beantable.navigation;

import com.antilia.web.dialog.DefaultDialog;
import com.antilia.web.dialog.util.OkDialogButton;

/**
 * @author EReinaldoB
 *
 */
public abstract class OkButton extends OkDialogButton {

	private static final long serialVersionUID = 1L;

	/**
	 * @param id
	 * @param dialog
	 */
	public OkButton(String id, DefaultDialog dialog) {
		super(id, dialog);
	}
	
	@Override
	protected String getLabelKey() {
		return "OkButton.label";
	}
	
	@Override
	protected String getTitleKey() {
		return "OkButton.title";
	}
}
