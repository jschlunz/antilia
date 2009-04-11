/**
 * This software is provided as IS by Antilia-Soft SL.
 * Copyright 2006-2007.
 */
package com.antilia.web.layout;

import java.io.Serializable;

import org.apache.wicket.Component;
import org.apache.wicket.ResourceReference;
import org.apache.wicket.ajax.AjaxRequestTarget;

import com.antilia.common.util.ClassUtils;
import com.antilia.web.button.AbstractLink;
import com.antilia.web.resources.DefaultStyle;

/**
 * 
 *
 * @author Ernesto Reinaldo Barreiro (reiern70@gmail.com)
 *
 */
public class CRUDButton<T extends Serializable> extends AbstractLink {

	private static final long serialVersionUID = 1L;

	private String label;
	
	private IContainer page;
	
	private Class<T> beanClass;
	
	private Class<?> crudClass;
	

	public CRUDButton(String label, IContainer page, Class<?> crudClass, Class<T> beanClass) {
		super(crudClass.getSimpleName());
		this.label = label;
		this.page = page;
		this.crudClass = crudClass;		
		this.beanClass = beanClass;
	}
	
	/**
	 * @param id
	 */
	public CRUDButton(String label, IContainer page, Class<T> beanClass) {
		super(beanClass.getSimpleName());
		this.label = label;
		this.page = page;
		this.beanClass = beanClass;
	}

	/* (non-Javadoc)
	 * @see com.antilia.web.button.AbstractLink#getImage()
	 */
	@Override
	protected ResourceReference getImage() {
		return DefaultStyle.IMG_TRANSPARENT;
	}

	/* (non-Javadoc)
	 * @see com.antilia.web.button.AbstractLink#getLabel()
	 */
	@Override
	protected String getLabel() {
		return this.label;
	}
	
	@Override
	protected String getLabelKey() {
		return this.label;
	}
	

	/* (non-Javadoc)
	 * @see com.antilia.web.button.AbstractLink#onClick(org.apache.wicket.ajax.AjaxRequestTarget)
	 */
	@Override
	protected void onClick(AjaxRequestTarget target) {
		if(target != null) {
			ScopedPanel modalContainer = new ScopedPanel(IContainer.BODY_CONTENT_ID) {
				
				private static final long serialVersionUID = 1L;

				@Override
				protected Component createBody(String id) {
					try {
						if(CRUDButton.this.crudClass != null)
							return (Component)ClassUtils.createInstance(CRUDButton.this.crudClass, id);
					} catch (Exception e) {
						// fall-back to default						
					}
					return new BackToHomeCRUD<T>(id, beanClass);
				}
			}; 
			this.page.getBody().addOrReplace(modalContainer);
			target.addComponent(this.page.getBody());
		}
	}

}
