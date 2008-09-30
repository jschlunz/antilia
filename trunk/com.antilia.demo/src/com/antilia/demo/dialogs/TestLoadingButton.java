/**
 * This software is provided as IS by Antilia-Soft SL.
 * Copyright 2006-2007.
 */
package com.antilia.demo.dialogs;

import org.apache.wicket.ResourceReference;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.IAjaxCallDecorator;
import org.apache.wicket.markup.html.form.Form;

import com.antilia.web.button.AbstractButton;
import com.antilia.web.dialog.IDialogScope;
import com.antilia.web.resources.DefaultStyle;


/**
 * 
 * @author Ernesto Reinaldo Barreiro (reiern70@gmail.com)
 */
public class TestLoadingButton extends AbstractButton {

	private static final long serialVersionUID = 1L;

	private static class LoadingDecorartor implements IAjaxCallDecorator {
		
		private static final long serialVersionUID = 1L;
		
		private IDialogScope scope;
		
		public LoadingDecorartor(IDialogScope scope) {
			this.scope = scope;
			System.out.println(scope);
		}
		
		public CharSequence decorateOnFailureScript(CharSequence script) {
			return script;
		}

		public CharSequence decorateOnSuccessScript(CharSequence script) {
			return script + ";dragPanels.showLoading('"+scope.getDialogId()+"');" ;
		}

		public CharSequence decorateScript(CharSequence script) {
			return "dragPanels.showLoading('"+scope.getDialogId()+"');" +   script;
		}
	}
	
	public TestLoadingButton() {
		super("testLoading", true);
	}
	
	/* (non-Javadoc)
	 * @see com.antilia.web.toolbar.AButton#getImage()
	 */
	@Override
	protected ResourceReference getImage() {
		return DefaultStyle.IMG_CANCEL;
	}

	/* (non-Javadoc)
	 * @see com.antilia.web.toolbar.AButton#getLabel()
	 */
	@Override
	protected String getLabel() {
		return "";
	}
	
	@SuppressWarnings("static-access")
	@Override
	protected void onSubmit(AjaxRequestTarget target, Form<?> form) {
		for(int i = 0; i < 50; i++) {
			try {
				Thread.currentThread().sleep(100);
			} catch (Exception e) {
			}
		}
	}


	public IDialogScope getDialogScope() {
		return (IDialogScope)findParent(IDialogScope.class);
	}
	
	
	@Override
	protected IAjaxCallDecorator getAjaxCallDecorator() {
		return new LoadingDecorartor(getDialogScope());		
	}
}
