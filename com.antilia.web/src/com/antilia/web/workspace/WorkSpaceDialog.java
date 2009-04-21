/**
 * 
 */
package com.antilia.web.workspace;

import com.antilia.web.dialog.DefaultDialog;
import com.antilia.web.dialog.DefaultDialogStyle;
import com.antilia.web.dialog.DialogButton;
import com.antilia.web.dialog.DialogStyle;

/**
 * @author Ernesto Reinaldo Barreiro (reirn70@gmail.com)
 *
 */
public abstract class WorkSpaceDialog extends DefaultDialog {

	private static final long serialVersionUID = 1L;

	/**
	 * @param id
	 * @param button
	 */
	public WorkSpaceDialog(String id, DialogButton button) {
		this(id, button, new DefaultDialogStyle());
	}

	/**
	 * @param id
	 * @param button
	 * @param dialogStyle
	 */
	public WorkSpaceDialog(String id, DialogButton button, DialogStyle dialogStyle) {
		super(id, button, dialogStyle);
		setModal(false);
		setResizable(true);
		setFoldable(true);
	}

	
}
