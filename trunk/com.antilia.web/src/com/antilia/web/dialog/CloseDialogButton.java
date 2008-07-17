/**
 * This software is provided as IS by Antilia-Soft SL.
 * Copyright 2006-2007.
 */
package com.antilia.web.dialog;

import java.util.Iterator;

import org.apache.wicket.ResourceReference;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.IAjaxCallDecorator;
import org.apache.wicket.markup.html.form.Form;

import com.antilia.web.button.AbstractButton;
import com.antilia.web.resources.DefaultStyle;


/**
 * 
 * @author Ernesto Reinaldo Barreiro (reiern70@gmail.com)
 */
public class CloseDialogButton extends AbstractButton {

	private static final long serialVersionUID = 1L;

	private DefaultDialog dialog; 
	
	public CloseDialogButton(DefaultDialog dialog) {
		super("close", true);
		this.dialog = dialog;
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
	protected void onSubmit(AjaxRequestTarget target, Form form) {
		dialog.setVisible(false);
		target.addComponent(dialog);
		// hide all sub dialogs as well
		for(Iterator<DefaultDialog> it = dialog.getDialogs();it.hasNext();) {
			DefaultDialog subDialog = it.next();
			if(subDialog.isVisible())
				subDialog.setVisible(false);
		}
		if(target != null) {
			if(dialog.getDialogButton() != null) {
				target.addComponent(dialog.getDialogButton().getButton());
			}		
		}
	}


	@Override
	protected IAjaxCallDecorator getAjaxCallDecorator() {
		return new IAjaxCallDecorator() 
		{
			private static final long serialVersionUID = 1L;

			public CharSequence decorateOnFailureScript(CharSequence script) {
				return script;
			}

			public CharSequence decorateOnSuccessScript(CharSequence script) {
				return "dragPanels.deletePanel('"+dialog.getDialogId()+"');"+script;
			}

			public CharSequence decorateScript(CharSequence script) {
				return  script;
			}
			
		};
	}
}
