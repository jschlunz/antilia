/**
 * This software is provided as IS by Antilia-Soft SL.
 * Copyright 2006-2007.
 */
package com.antilia.web.button;

import org.apache.wicket.AttributeModifier;
import org.apache.wicket.ResourceReference;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.AjaxFallbackLink;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.image.Image;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.Model;

import com.antilia.web.toolbar.IToolbarItem;

/**
 * 
 * @author Ernesto Reinaldo Barreiro (reiern70@gmail.com)
 */
public abstract class AbstractLink extends Panel implements IMenuItem, IToolbarItem {

	private static final long serialVersionUID = 1L;

	private int order = AbstractButton.NO_ORDER;
	
	private WebMarkupContainer link;
	
	private String linkClass = "smallbutton";
				
	public static final String LABEL_ID = "label";
	
	/**
	 * Constructor.
	 * @param id
	 */
	public AbstractLink(String id) {
		super(id);
		link = newLink("link");
		link.add(new AttributeModifier("class", true, new Model() {
			
			private static final long serialVersionUID = 1L;

			@Override
			public Object getObject() {
				return AbstractLink.this.getLinkClass();
			}
		}));
		add(link);
		Image image = newImage("image");
		link.add(image);
		link.add(newLabel(LABEL_ID));
	}
	
	protected WebMarkupContainer newLink(String id) {
		return new AjaxFallbackLink(id) {
			
			private static final long serialVersionUID = 1L;

			@Override
			public void onClick(AjaxRequestTarget target) {
				AbstractLink.this.onClick(target);
			}
		};
	}
	
	protected Label newLabel(String id) {
		return new Label(id, getLabel());
	}
	
	protected abstract void onClick(AjaxRequestTarget target);
	
	/**
	 * Override this method  to provide your own image.
	 * 
	 * @param id
	 * @return
	 */
	protected Image newImage(String id) {		
		Image image = new Image(id) {
			private static final long serialVersionUID = 1L;

			@Override
			protected ResourceReference getImageResourceReference() {
				if(isEnabled()) {
					return getImage();
				} 
				ResourceReference reference = getDisabledImage();
				if(reference != null) {
					return reference;
				}
				return getImage();
			}
			
			@Override
			public boolean isVisible() {
				return (getImage() != null);
			}
		};
		return image;
	}
	
	protected abstract String getLabel();

	protected abstract ResourceReference getImage();
	
	/**
	 * Returns the image of the disable button. By default is null which means 
	 * image will be used.
	 * 
	 * @return
	 */
	protected  ResourceReference getDisabledImage() {
		return null;
	}

	/**
	 * @return the order
	 */
	public int getOrder() {
		return order;
	}

	/**
	 * @param order the order to set
	 */
	public void setOrder(int order) {
		this.order = order;
	}

	/**
	 * @return the link
	 */
	public WebMarkupContainer getLink() {
		return link;
	}

	/**
	 * @param link the link to set
	 */
	public void setLink(WebMarkupContainer link) {
		this.link = link;
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
