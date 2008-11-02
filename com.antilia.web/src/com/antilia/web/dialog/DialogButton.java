/**
 * This software is provided as IS by Antilia-Soft SL.
 * Copyright 2006-2007.
 */
package com.antilia.web.dialog;

import org.apache.wicket.ResourceReference;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.IAjaxCallDecorator;
import org.apache.wicket.behavior.AttributeAppender;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.Model;

import com.antilia.web.button.AbstractButton;
import com.antilia.web.button.IMenuItem;
import com.antilia.web.toolbar.IToolbarItem;


/**
 * 
 * @author Ernesto Reinaldo Barreiro (reiern70@gmail.com)
 */
public abstract class DialogButton extends Panel implements IMenuItem, IToolbarItem {

	private static final long serialVersionUID = 1L;

	private DefaultDialog dialog;
	
	private int order = AbstractButton.NO_ORDER;
	
	private AbstractButton button;
	
	/**
	 * If the dialog should be opened at mouse position!
	 */
	private boolean showAtMousePosition = false;
	
	public DialogButton(String id) {
		super(id);
		button = new AbstractButton("button", true){			
			private static final long serialVersionUID = 1L;

			@Override
			protected ResourceReference getImage() {
				return DialogButton.this.getImage();
			}
			
			@Override
			protected String getLabel() {
				return DialogButton.this.getLabel();
			}
			
			@Override
			protected String getLabelKey() {
				return DialogButton.this.getLabelKey();
			}
			
			@Override
			protected String getTitleKey() {
				return DialogButton.this.getTitleKey();
			}
			 
			@Override
			public boolean isEnabled() {
				return !DialogButton.this.dialog.isVisible();
			}
			 
			@Override
			protected void onSubmit(AjaxRequestTarget target, Form<?> form) {
				DialogButton.this.onSubmit(target, form);
			}
			
			@Override
			protected IAjaxCallDecorator getAjaxCallDecorator() {
				return DialogButton.this.getAjaxCallDecorator();
			}			
		};
		
		add(button);
		
		
	}
	
	@Override
	protected void onBeforeRender() {
		super.onBeforeRender();	
		if(dialog == null) {
			dialog = newDialog("dialog"); 
			dialog.setVisible(false);				
			dialog.setOutputMarkupPlaceholderTag(true);
			dialog.add(new AttributeAppender("style", new Model<String>("position: relavive; z-index: 2;"), ";"));
			dialog.setDialogButton(this);
			add(dialog);
		}
	}
	
	protected abstract String getLabelKey();
	
	protected String getTitleKey() {
		return this.getClass().getSimpleName()+ ".title";
	}
	
	protected abstract String getLabel();

	protected abstract ResourceReference getImage();

	
	protected IAjaxCallDecorator getAjaxCallDecorator() {
		return new IAjaxCallDecorator() 
		{
			private static final long serialVersionUID = 1L;

			public CharSequence decorateOnFailureScript(CharSequence script) {
				return script;
			}

			public CharSequence decorateOnSuccessScript(CharSequence script) {
				if(showAtMousePosition)
					return  script  + "Antilia_dragPanels.showPanel('"+dialog.getDialogId()+"','"+button.getMarkupId()+"');";
				return  script ;
			}

			public CharSequence decorateScript(CharSequence script) {
				return   script;
			}
			
		};
	}
	
	protected void onSubmit(AjaxRequestTarget target, Form<?> form) {
		if(showDialog(target, form)) {
			dialog.setVisible(true);
			target.addComponent(dialog);
			target.addComponent(button);
		}
	}
	

	/**
	 * Override this function if you want to check a condition in order to show the dialog.
	 * By default  the function returns true.
	 * 
	 * @param target
	 * @param form
	 * @return
	 */
	protected boolean showDialog(AjaxRequestTarget target, Form<?> form) {
		return true;
	}
	
	public abstract DefaultDialog newDialog(String id);

	public int getOrder() {
		return order;
	}

	public void setOrder(int order) {
		this.order = order;
	}

	public AbstractButton getButton() {
		return button;
	}

	/**
	 * @return the showAtMousePoistion
	 */
	public boolean isShowAtMousePosition() {
		return showAtMousePosition;
	}

	/**
	 * @param showAtMousePoistion the showAtMousePoistion to set
	 */
	public void setShowAtMousePosition(boolean showAtMousePoistion) {
		this.showAtMousePosition = showAtMousePoistion;
	}
}
