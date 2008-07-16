/**
 * This software is provided as IS by Antilia-Soft SL.
 * Copyright 2006-2007.
 */
package com.antilia.web.crud;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import org.apache.wicket.markup.html.panel.Panel;

import com.antilia.hibernate.query.IQuery;
import com.antilia.web.beantable.provider.IProviderSelector;
import com.antilia.web.field.AutoFieldModel;
import com.antilia.web.field.AutoFieldPanel;
import com.antilia.web.field.BeanForm;
import com.antilia.web.field.BeanProxy;
import com.antilia.web.field.IAutoFieldModel;
import com.antilia.web.menu.Menu;

/**
 * 
 * @author Ernesto Reinaldo Barreiro (reiern70@gmail.com)
 */
public abstract class EditPanel<B extends Serializable> extends Panel {

	private static final long serialVersionUID = 1L;

	private BeanProxy<B> beanProxy;
	
	private Collection<B> beans;

	
	/**
	 * 
	 * @param id
	 * @param beanClass
	 * @param filterQuery
	 */
	public EditPanel(String id) {
		super(id);
		
		setOutputMarkupId(true);
				
		this.beans = new ArrayList<B>();
			
		
	}
	
	@Override
	protected void onBeforeRender() {
		this.beans.clear();
		IProviderSelector<B> selector = getCRUDPanel().getSelected();		
		Iterator<B> it = selector.getSelected();
		while(it.hasNext() ) {
			B bean = it.next();
			this.beans.add(bean);
		}
		
		this.beanProxy = new BeanProxy<B>(beans.iterator().next());
		
		IAutoFieldModel<B> autoFieldModel = newAutoFieldModel(null, this.beanProxy);
		configureFieldModel(autoFieldModel);
		
		BeanForm<B> beanForm = newForm("form", this.beanProxy);
		addOrReplace(beanForm);
				
				
		 Menu menu = Menu.createMenu("topMenu", EditPanelButtonsFactory.getInstance());
		 
		beanForm.addOrReplace(menu);
		 
		AutoFieldPanel<B> autoFieldPanel = newAutoFieldPanel("autofield",autoFieldModel);
		
		beanForm.addOrReplace(autoFieldPanel);
		super.onBeforeRender();
	}
	
	@SuppressWarnings("unchecked")
	private CRUDPanel<B> getCRUDPanel() {
		return (CRUDPanel<B>) findParent(CRUDPanel.class);
		
	}
	
	protected void configureFieldModel(IAutoFieldModel<B> autoFieldModel) {
		String[] searchFields = getEditFields();
		if(searchFields != null) {
			for(String propertyPath: searchFields) {
				autoFieldModel.newFieldModel(propertyPath);
			}
		}
	}
	
	protected void populateTopMenu(Menu topMenu) {
		
	}
	
	protected AutoFieldPanel<B> newAutoFieldPanel(String id, IAutoFieldModel<B> autoFieldModel) {
		return new AutoFieldPanel<B>(id,autoFieldModel);
	}
	
	protected BeanForm<B> newForm(String id, BeanProxy<B> beanProxy) {
		return new BeanForm<B>(id, beanProxy);
	}
	
	protected IAutoFieldModel<B> newAutoFieldModel(IQuery<B> query, BeanProxy<B> beanProxy) {
		return new AutoFieldModel<B>(query, beanProxy);
	}
	
	protected abstract String[] getEditFields();


	/**
	 * @return the beanProxy
	 */
	public BeanProxy<B> getBeanProxy() {
		return beanProxy;
	}
}
