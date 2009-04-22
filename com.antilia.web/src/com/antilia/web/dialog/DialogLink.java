/**
 * This software is provided as IS by Antilia-Soft SL.
 * Copyright 2006-2007.
 */
package com.antilia.web.dialog;

import org.apache.wicket.ResourceReference;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.IAjaxCallDecorator;
import org.apache.wicket.behavior.AttributeAppender;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.Model;
import org.wicketstuff.minis.veil.VeilResources;

import com.antilia.web.button.AbstractButton;
import com.antilia.web.button.AbstractLink;


/**
 * 
 * @author Ernesto Reinaldo Barreiro (reiern70@gmail.com)
 */
public abstract class DialogLink extends Panel implements IDialogLink {

	private static final long serialVersionUID = 1L;

	private DefaultDialog dialog;
	
	private int order = AbstractButton.NO_ORDER;
	
	private AbstractLink button;
	
	private IDialogScope dialogScope;
	
	private String linkClass = "smallbutton";
	
	/**
	 * If the dialog should be opened at mouse position!
	 */
	private boolean showAtMousePosition = false;
	
	public DialogLink(String id) {
		super(id);
		
		
		
	}
	
	@Override
	protected void onBeforeRender() {			
		if(button == null) {
			button = new AbstractLink("button"){			
				private static final long serialVersionUID = 1L;

				@Override
				protected ResourceReference getImage() {
					return DialogLink.this.getImage();
				}
								
				@Override
				protected String getLabel() {
					return DialogLink.this.getLabel();
				}
				
				@Override
				protected String getLabelKey() {
					return DialogLink.this.getLabelKey();
				}
				
				@Override
				protected String getTitleKey() {
					return DialogLink.this.getTitleKey();
				}
				 
				@Override
				public boolean isEnabled() {
					return !DialogLink.this.dialog.isVisible();
				}
				
				@Override
				protected void onClick(AjaxRequestTarget target) {
					DialogLink.this.onClick(target);
				}
				 
				
				@Override
				protected IAjaxCallDecorator getAjaxCallDecorator() {
					return DialogLink.this.getAjaxCallDecorator();
				}			
			};
			
			button.setLinkClass(getLinkClass());
			
			add(button);
		}
		if(dialog == null) {
			dialog = newDialog("dialog"); 
			dialog.setVisible(false);				
			dialog.setOutputMarkupPlaceholderTag(true);
			dialog.add(new AttributeAppender("style", new Model<String>("position: relavive; z-index: 2;"), ";"));
			dialog.setDialogButton(this);
			add(dialog);
		}
		super.onBeforeRender();
	}
	
	protected abstract String getLabelKey();
	
	protected String getTitleKey() {
		return this.getClass().getSimpleName()+ ".title";
	}
	
	protected abstract String getLabel();

	protected abstract ResourceReference getImage();

	
	protected IAjaxCallDecorator getAjaxCallDecorator() {
		return new IAjaxCallDecorator() {
			
			private static final long serialVersionUID = 1L;

			public CharSequence decorateOnFailureScript(CharSequence script) {
				IDialogScope dialogScope = getDialogScope();
				String errorMessage = ";alert('"+DialogLink.this.getString("ServerDown", null, "Server Down!")+"');";
				if(dialogScope != null) {
					return script + ";" + VeilResources.Javascript.Generic.toggle(dialogScope.getDialogId()) + errorMessage ;
				} 
				return script + ";" + VeilResources.Javascript.Generic.toggle("AT_body") + errorMessage;
			}
			
			public CharSequence decorateOnSuccessScript(CharSequence script) {
				IDialogScope dialogScope = getDialogScope();
				if(dialogScope != null) {
					return script + ";" + VeilResources.Javascript.Generic.toggle(dialogScope.getDialogId()) + ";" 
					+ "Antilia_dragPanels.showPanel('"+dialog.getDialogId()+"','"+button.getMarkupId()+"');"; 
				}
				return script + ";" + VeilResources.Javascript.Generic.toggle("AT_body") + ";" 
				+ "Antilia_dragPanels.showPanel('"+dialog.getDialogId()+"','"+button.getMarkupId()+"');";
			}
			
			public CharSequence decorateScript(CharSequence script) {
				IDialogScope dialogScope = getDialogScope();
				if(dialogScope != null) {
					return VeilResources.Javascript.Generic.show(dialogScope.getDialogId()) + ";" + script;
				}
				return VeilResources.Javascript.Generic.show("AT_body") + ";" + script;
			}
		};
	}
	
	public IDialogScope getDialogScope() {
		if(dialogScope == null)
			dialogScope = findParentDialog();
		return dialogScope;
	}
	
	private IDialogScope findParentDialog() {
		return (IDialogScope)findParent(IDialogScope.class);
	}
	
	
	protected void onClick(AjaxRequestTarget target) {
		if(showDialog(target)) {
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
	protected boolean showDialog(AjaxRequestTarget target) {
		return true;
	}
	
	public abstract DefaultDialog newDialog(String id);

	public int getOrder() {
		return order;
	}

	public void setOrder(int order) {
		this.order = order;
	}

	public AbstractLink getButton() {
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

	/**
	 * @return the linkClass
	 */
	public String getLinkClass() {
		return linkClass;
	}

	/**
	 * @param linkClass the linkClass to set
	 */
	public void setLinkClass(String linkClass) {
		this.linkClass = linkClass;
	}
}
