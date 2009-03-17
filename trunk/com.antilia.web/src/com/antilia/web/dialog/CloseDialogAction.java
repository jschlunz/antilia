/**
 * This software is provided as IS by Antilia-Soft SL.
 * Copyright 2006-2007.
 */
package com.antilia.web.dialog;

import java.util.Iterator;

import org.apache.wicket.Component;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.IAjaxCallDecorator;
import org.apache.wicket.markup.html.form.Form;

import com.antilia.web.button.AbstractAction;

/**
 * 
 *
 * @author Ernesto Reinaldo Barreiro (reiern70@gmail.com)
 *
 */
public class CloseDialogAction extends AbstractAction {

	private static final long serialVersionUID = 1L;
	
	private DefaultDialog dialog;
	
	/**
	 * @param trigger
	 */
	public CloseDialogAction(Component trigger,  DefaultDialog dialog) {
		super(trigger);
		this.dialog = dialog;
	}

	/* (non-Javadoc)
	 * @see com.antilia.web.button.IAjaxAction#onSubmit(org.apache.wicket.ajax.AjaxRequestTarget, org.apache.wicket.markup.html.form.Form)
	 */
	public void onSubmit(AjaxRequestTarget target, Form<?> form) {
		dialog.setVisible(false);
		target.addComponent(dialog);
		// hide all sub dialogs as well
		for(Iterator<IDialogScope> it = dialog.getDialogs();it.hasNext();) {
			IDialogScope subDialog = it.next();
			if(subDialog.isVisible())
				subDialog.setVisible(false);
		}
		if(target != null) {
			if(dialog.getDialogButton() != null) {
				target.addComponent(dialog.getDialogButton().getButton());
			}		
		}
	}

	public void onSubmit() {
		// do nothing a dialogs onli works with AJAX..
	}
	
	/**
	 * @return the dialog
	 */
	public DefaultDialog getDialog() {
		return dialog;
	}
	
	public IAjaxCallDecorator getAjaxCallDecorator() {
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
	}
	
	

}
