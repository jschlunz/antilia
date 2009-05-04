/**
 * This software is provided as IS by Antilia-Soft SL.
 * Copyright 2006-2007.
 */
package com.antilia.web.dialog;

import org.apache.wicket.ResourceReference;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.IAjaxCallDecorator;

import com.antilia.web.button.AbstractLink;
import com.antilia.web.resources.DefaultStyle;


/**
 * 
 * @author Ernesto Reinaldo Barreiro (reiern70@gmail.com)
 */
public class CloseDialogLink extends AbstractLink {

	private static final long serialVersionUID = 1L;

	private DefaultDialog dialog; 
	
	public CloseDialogLink(String id, DefaultDialog dialog) {
		super(id);
		this.dialog = dialog;
	}
	
	
	public CloseDialogLink(DefaultDialog dialog) {
		this("close", dialog);
	}
	
	/* (non-Javadoc)
	 * @see com.antilia.web.toolbar.AButton#getImage()
	 */
	@Override
	protected ResourceReference getImage() {
		return DefaultStyle.IMG_CLOSE;
	}

	/* (non-Javadoc)
	 * @see com.antilia.web.toolbar.AButton#getLabel()
	 */
	@Override
	protected String getLabel() {
		return "";
	}
	
	@Override
	protected String getLabelKey() {
		return null;
	}
	
	@Override
	protected String getTitleKey() {
		return "CloseDialogButton.title";
	}
	
	@Override
	protected void onClick(AjaxRequestTarget target) {
		new CloseDialogAction(this, dialog).onClick(target);
	}

	
	@Override
	public boolean isVisible() {
		return dialog.isCloseable();
	}
	

	@Override
	protected IAjaxCallDecorator getAjaxCallDecorator() {
		/*
		return new IAjaxCallDecorator() 
		{
			private static final long serialVersionUID = 1L;

			public CharSequence decorateOnFailureScript(CharSequence script) {
				return script;
			}

			public CharSequence decorateOnSuccessScript(CharSequence script) {
				return "Antilia_dragPanels.deletePanel('"+dialog.getDialogId()+"');"+script;
			}

			public CharSequence decorateScript(CharSequence script) {
				return  script;
			}
			
		};
		*/
		return new CloseDialogAction(this, dialog).getAjaxCallDecorator();
	}
}
