/**
 * This software is provided as IS by Antilia-Soft SL.
 * Copyright 2006-2007.
 */
package com.antilia.web.layout;

import java.io.Serializable;

import org.apache.wicket.ResourceReference;
import org.apache.wicket.ajax.AjaxRequestTarget;

import com.antilia.web.button.AbstractLink;
import com.antilia.web.crud.CRUDPanel;
import com.antilia.web.resources.DefaultStyle;

/**
 * 
 *
 * @author Ernesto Reinaldo Barreiro (reiern70@gmail.com)
 *
 */
public class CRUDLink<T extends Serializable> extends AbstractLink {

	private static final long serialVersionUID = 1L;

	private String label;
		
	private Class<T> beanClass;
	
	private Class<CRUDPanel<T>> crudClass;
	

	/**
	 * 
	 * @param label
	 * @param page
	 * @param crudClass
	 * @param beanClass
	 */
	public CRUDLink(String label, IContainer page, Class<CRUDPanel<T>> crudClass, Class<T> beanClass) {
		super(crudClass.getSimpleName());
		this.label = label;
		this.crudClass = crudClass;		
		this.beanClass = beanClass;
	}
	
	/**
	 * @param id
	 */
	public CRUDLink(String label, Class<T> beanClass) {
		super(beanClass.getSimpleName());
		this.label = label;
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
			ScopedCrudPanel<T> modalContainer = new ScopedCrudPanel<T>(IContainer.BODY_CONTENT_ID, CRUDLink.this.beanClass, CRUDLink.this.crudClass) {
				private static final long serialVersionUID = 1L;

				@Override
				protected CRUDPanel<T> createCrudPanel(String id) {
					CRUDPanel<T> crud =  CRUDLink.this.createCRUDPanel(id);
					if(crud != null) {
						return crud;
					}
					return super.createCrudPanel(id);
				}				
			};
			IContainer container = findContainer();
			container.getBody().addOrReplace(modalContainer);
			target.addComponent(container.getBody());
		}
	}
	
	protected CRUDPanel<T> createCRUDPanel(String id) {
		return null;
	}
	
	private IContainer findContainer() {
		return findParent(IContainer.class);
	}

}
