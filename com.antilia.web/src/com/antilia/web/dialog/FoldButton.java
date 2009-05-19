/**
 * This software is provided as IS by Antilia-Soft SL.
 * Copyright 2006-2007.
 */
package com.antilia.web.dialog;

import org.apache.wicket.ResourceReference;

import com.antilia.web.button.ScriptButton;
import com.antilia.web.resources.DefaultStyle;


/**
 * 
 * @author Ernesto Reinaldo Barreiro (reiern70@gmail.com)
 */
public class FoldButton extends ScriptButton {

	private static final long serialVersionUID = 1L;
	
	private DefaultDialog dialog;

	public FoldButton(DefaultDialog dialog) {
		super("fold");
		this.dialog = dialog;
	}
	
	/* (non-Javadoc)
	 * @see com.antilia.web.toolbar.AButton#getImage()
	 */
	@Override
	protected ResourceReference getImage() {
		return DefaultStyle.IMG_FOLD;
	}
	
	@Override
	public boolean isVisible() {
		return dialog.isFoldable();
	}

	/* (non-Javadoc)
	 * @see com.antilia.web.toolbar.AButton#getLabel()
	 */
	@Override
	protected String getLabel() {
		return "";
	}
	
	@Override
	protected String getTitleKey() {
		return "FoldButton.title";
	}

	@Override
	protected String onClickScript() {
		return "Antilia_dragPanels.foldPanel('"+dialog.getDialogId()+"'); return false;";
	}
}
