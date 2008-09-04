/**
 * This software is provided as IS by Antilia-Soft SL.
 * Copyright 2006-2007.
 */
package com.antilia.web.crud;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.wicket.markup.html.panel.Panel;

import com.antilia.hibernate.query.IQuery;
import com.antilia.web.beantable.provider.IProviderSelector;
import com.antilia.web.beantable.provider.impl.InMemoryPageableProvider;
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
public class EditPanel<B extends Serializable> extends Panel implements ICRUDModeReporter {

	private static final long serialVersionUID = 1L;

	private Class<B> beanClass;
	
	private BeanProxy<B> beanProxy;
	

	private InMemoryPageableProvider<B> pageableProvider;
	
	private CrudStyler<B> styler;
	
	/**
	 * 
	 * @param id
	 * @param beanClass
	 * @param filterQuery
	 */
	public EditPanel(String id, CrudStyler<B> styler) {
		super(id);		
		this.beanClass = styler.getBeanClass();
		this.styler = styler;
		setOutputMarkupId(true);		
	}
	
	@SuppressWarnings("unchecked")
	@Override
	protected void onBeforeRender() {
		if(pageableProvider == null) {
			ArrayList< B> beans = new ArrayList<B>();
			IProviderSelector<B> selector = getCRUDPanel().getSelected();		
			Iterator<B> it = selector.getSelected().iterator();
			while(it.hasNext() ) {
				B bean = it.next();
				try {
					beans.add((B)BeanUtils.cloneBean(bean));
				} catch (Exception e) {
					beans.add(bean);
				}
			}
			pageableProvider = new InMemoryPageableProvider<B>(beans, beanClass);
		}		
		
		this.beanProxy = new BeanProxy<B>(pageableProvider.current());
		
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
	
	public B getCurrentBean() {
		return this.beanProxy.getBean();
	}
	
	protected void configureFieldModel(IAutoFieldModel<B> autoFieldModel) {
		List<String> searchFields = this.styler.getEditFields();
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


	/**
	 * @return the beanProxy
	 */
	public BeanProxy<B> getBeanProxy() {
		return beanProxy;
	}

	/**
	 * @return the pageableProvider
	 */
	public InMemoryPageableProvider<B> getPageableProvider() {
		return pageableProvider;
	}

	/**
	 * @param pageableProvider the pageableProvider to set
	 */
	public void clearPageableProvider() {
		this.pageableProvider = null;
	}
	
	@Override
	public CRUDMode getCrudMode() {
		return CRUDMode.EDIT;
	}
}
