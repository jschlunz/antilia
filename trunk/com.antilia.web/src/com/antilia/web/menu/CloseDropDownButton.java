/**
 * This software is provided as IS by Antilia-Soft SL.
 * Copyright 2006-2007.
 */
package com.antilia.web.menu;

import org.apache.wicket.ResourceReference;

import com.antilia.web.button.ScriptButton;
import com.antilia.web.resources.DefaultStyle;

/**
 * 
 *
 * @author Ernesto Reinaldo Barreiro (reiern70@gmail.com)
 *
 */
public class CloseDropDownButton extends ScriptButton {

	private static final long serialVersionUID = 1L;

	public CloseDropDownButton(String id) {
		super(id);
		setRenderBodyOnly(true);
	}
	/* (non-Javadoc)
	 * @see com.antilia.web.button.ScriptButton#getImage()
	 */
	@Override
	protected ResourceReference getImage() {
		return DefaultStyle.IMG_CLOSE;
	}

	@Override
	protected String getTitleKey() {
		return null;
	}
	
	/* (non-Javadoc)
	 * @see com.antilia.web.button.ScriptButton#getJavaScript()
	 */
	@Override
	protected String onClickScript() {
		return "this.parentNode.parentNode.parentNode.parentNode.style.display='none';";
	}

	/* (non-Javadoc)
	 * @see com.antilia.web.button.ScriptButton#getLabel()
	 */
	@Override
	protected String getLabel() {
		return null;
	}

}
