/**
 * This software is provided as IS by Antilia-Soft SL.
 * Copyright 2006-2007.
 */
package com.antilia.demo.manager;

import java.io.Serializable;

import org.apache.wicket.Component;
import org.apache.wicket.ResourceReference;
import org.apache.wicket.ajax.AjaxRequestTarget;

import com.antilia.common.util.ClassUtils;
import com.antilia.demo.manager.Index;
import com.antilia.demo.manager.crud.ManagerCRUD;
import com.antilia.web.button.AbstractLink;
import com.antilia.web.dialog.ModalContainer;
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
	
	private Index page;
	
	private Class<T> beanClass;
	
	private Class<?> crudClass;
	
	private String contentId;
	
	public CRUDButton(String label, Index page, Class<?> crudClass, Class<T> beanClass, String contentId) {
		super(crudClass.getSimpleName());
		this.label = label;
		this.page = page;
		this.crudClass = crudClass;		
		this.beanClass = beanClass;
		this.contentId = contentId;
	}
	
	/**
	 * @param id
	 */
	public CRUDButton(String label, Index page, Class<T> beanClass, String contentId) {
		super(beanClass.getSimpleName());
		this.label = label;
		this.page = page;
		this.beanClass = beanClass;
		this.contentId = contentId;
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
			ModalContainer modalContainer = new ModalContainer(contentId) {
				
				private static final long serialVersionUID = 1L;

				@Override
				protected Component createBody(String id) {
					try {
						if(CRUDButton.this.crudClass != null)
							return (Component)ClassUtils.createInstance(CRUDButton.this.crudClass, id);
					} catch (Exception e) {
						// fall-back to default
					}
					return new ManagerCRUD<T>(id, beanClass);
				}
			}; 
			this.page.getBody().addOrReplace(modalContainer);
			target.addComponent(this.page.getBody());
		}
	}

}
