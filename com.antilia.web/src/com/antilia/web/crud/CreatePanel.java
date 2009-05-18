/**
 * This software is provided as IS by Antilia-Soft SL.
 * Copyright 2006-2007.
 */
package com.antilia.web.crud;

import java.io.Serializable;
import java.util.List;

import org.apache.wicket.markup.html.panel.Panel;

import com.antilia.hibernate.query.IQuery;
import com.antilia.web.feedback.AntiliaFeedBackPanel;
import com.antilia.web.field.AutoFieldCreator;
import com.antilia.web.field.AutoFieldPanel;
import com.antilia.web.field.BeanForm;
import com.antilia.web.field.BeanProxy;
import com.antilia.web.field.IAutoFieldCreator;
import com.antilia.web.field.IFieldModel;
import com.antilia.web.field.factory.FieldMode;
import com.antilia.web.menu.Menu;

/**
 * 
 * @author Ernesto Reinaldo Barreiro (reiern70@gmail.com)
 */
public class CreatePanel<B extends Serializable> extends Panel implements ICRUDModeReporter, IFeedBackAware {

	private static final long serialVersionUID = 1L;

	private Class<B> beanClass;
	
	private BeanProxy<B> beanProxy;
	
	private CrudStyler<B> styler;
	
	private AntiliaFeedBackPanel feedback;
	
	/**
	 * 
	 * @param id
	 * @param beanClass
	 * @param filterQuery
	 */
	public CreatePanel(String id, CrudStyler<B> styler) {
		super(id);		
		this.beanClass = styler.getBeanClass();
		this.styler = styler;
		
		setOutputMarkupId(true);		
		feedback  = new AntiliaFeedBackPanel("messages");
		feedback.setOutputMarkupId(true);
		add(feedback);
	}
	
	@Override
	protected void onBeforeRender() {
				
		if(this.beanProxy == null) {
			try {
				this.beanProxy =  new BeanProxy<B>(beanClass.newInstance());
			} catch (Exception e) {
			}
		}
		
		IAutoFieldCreator<B> autoFieldCreator = newAutoFieldCreator(null, this.beanProxy);
		configureAutoFieldCreator(autoFieldCreator);
		
		BeanForm<B> beanForm = newForm("form", this.beanProxy);
		addOrReplace(beanForm);				
				
		Menu menu = Menu.createMenu("topMenu", 
				null,CreatePanelPanelButtonsFactory.getInstance());
		 
		beanForm.addOrReplace(menu);
		 
		AutoFieldPanel<B> autoFieldPanel = newAutoFieldPanel("autofield",autoFieldCreator);
		
		beanForm.addOrReplace(autoFieldPanel);
		

		
		super.onBeforeRender();
	}
	
	@SuppressWarnings("unchecked")
	public CRUDPanel<B> getCRUDPanel() {
		return (CRUDPanel<B>) findParent(CRUDPanel.class);
		
	}
	
	public CreatePanel<B> reset() {
		this.beanProxy = null;
		return this;
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
	 * @return the messages
	 */
	public AntiliaFeedBackPanel getFeedback() {
		return feedback;
	}
	
	public CRUDMode getCrudMode() {
		return CRUDMode.CREATE;
	}
}
