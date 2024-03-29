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

import com.antilia.common.query.IQuery;
import com.antilia.web.feedback.AntiliaFeedBackPanel;
import com.antilia.web.field.AutoFieldCreator;
import com.antilia.web.field.AutoFieldPanel;
import com.antilia.web.field.BeanForm;
import com.antilia.web.field.BeanProxy;
import com.antilia.web.field.IAutoFieldCreator;
import com.antilia.web.field.IFieldModel;
import com.antilia.web.field.factory.FieldMode;
import com.antilia.web.menu.Menu;
import com.antilia.web.navigator.INavigatorSelector;
import com.antilia.web.navigator.impl.InMemoryPageableNavigator;

/**
 * 
 * @author Ernesto Reinaldo Barreiro (reiern70@gmail.com)
 */
public class EditPanel<B extends Serializable> extends Panel implements ICRUDModeReporter, IFeedBackAware {

	private static final long serialVersionUID = 1L;

	private Class<B> beanClass;
	
	private BeanProxy<B> beanProxy;
	

	private InMemoryPageableNavigator<B> pageableProvider;
	
	private CrudStyler<B> styler;
	
	private AntiliaFeedBackPanel feedback;
	
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
		feedback = new AntiliaFeedBackPanel("feedBack");
		feedback.setOutputMarkupId(true);
		add(feedback);
		
	}
	
	@SuppressWarnings("unchecked")
	@Override
	protected void onBeforeRender() {		
		if(pageableProvider == null) {
			ArrayList< B> beans = new ArrayList<B>();
			INavigatorSelector<B> selector = getCRUDPanel().getSelected();		
			Iterator<B> it = selector.getSelected().iterator();
			while(it.hasNext() ) {
				B bean = it.next();
				try {
					beans.add((B)BeanUtils.cloneBean(bean));
				} catch (Exception e) {
					beans.add(bean);
				}
			}
			pageableProvider = new InMemoryPageableNavigator<B>(beans, beanClass);
		}				
		
		this.beanProxy = new BeanProxy<B>(pageableProvider.current());
		
		IAutoFieldCreator<B> autoFieldCreator = newAutoFieldCreator(null, this.beanProxy);
		configureAutoFieldCreator(autoFieldCreator);
		
		BeanForm<B> beanForm = newForm("form", this.beanProxy);
		addOrReplace(beanForm);
				
				
		Menu menu = Menu.createMenu("topMenu", null, EditPanelButtonsFactory.getInstance());
		 
		beanForm.addOrReplace(menu);
		 
		AutoFieldPanel<B> autoFieldPanel = newAutoFieldPanel("autofield",autoFieldCreator);
		
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
	
	protected void configureAutoFieldCreator(IAutoFieldCreator<B> autoFieldModel) {
		List<String> searchFields = this.styler.getEditFields();
		if(searchFields != null) {
			for(String propertyPath: searchFields) {
				IFieldModel<B> fieldModel = autoFieldModel.newFieldModel(propertyPath);
				configureFieldModel(propertyPath, fieldModel);
			}
		}
	}
	
	protected void configureFieldModel(String propertyPath, IFieldModel<B> fieldModel) {
		
	}
	
	public AntiliaFeedBackPanel getFeedback() {
		return feedback;
	}

	public void setFeedback(AntiliaFeedBackPanel feedBack) {
		this.feedback = feedBack;
	}

	protected void populateTopMenu(Menu topMenu) {
		
	}
	
	protected AutoFieldPanel<B> newAutoFieldPanel(String id, IAutoFieldCreator<B> autoFieldModel) {
		return new AutoFieldPanel<B>(id,autoFieldModel, FieldMode.EDIT, 1);
	}
	
	protected BeanForm<B> newForm(String id, BeanProxy<B> beanProxy) {
		return new BeanForm<B>(id, beanProxy);			
	}
	
	
	
	protected IAutoFieldCreator<B> newAutoFieldCreator(IQuery<B> query, BeanProxy<B> beanProxy) {
		return new AutoFieldCreator<B>(query, beanProxy);
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
	public InMemoryPageableNavigator<B> getPageableProvider() {
		return pageableProvider;
	}

	/**
	 * @param pageableProvider the pageableProvider to set
	 */
	public void clearPageableProvider() {
		this.pageableProvider = null;
	}
	
	public CRUDMode getCrudMode() {
		return CRUDMode.EDIT;
	}
}
