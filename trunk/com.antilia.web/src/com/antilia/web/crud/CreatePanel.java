/**
 * This software is provided as IS by Antilia-Soft SL.
 * Copyright 2006-2007.
 */
package com.antilia.web.crud;

import java.io.Serializable;
import java.util.List;

import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.markup.html.panel.Panel;

import com.antilia.hibernate.query.IQuery;
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
public class CreatePanel<B extends Serializable> extends Panel {

	private static final long serialVersionUID = 1L;

	private Class<B> beanClass;
	
	private BeanProxy<B> beanProxy;
	
	private CrudStyler<B> styler;
	
	private FeedbackPanel messages;
	
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
		messages  = new FeedbackPanel("messages");
		messages.setOutputMarkupId(true);
		add(messages);
	}
	
	@SuppressWarnings("unchecked")
	@Override
	protected void onBeforeRender() {
						
		try {
			this.beanProxy =  new BeanProxy<B>(beanClass.newInstance());
		} catch (Exception e) {
		}
		
		IAutoFieldModel<B> autoFieldModel = newAutoFieldModel(null, this.beanProxy);
		configureFieldModel(autoFieldModel);
		
		BeanForm<B> beanForm = newForm("form", this.beanProxy);
		addOrReplace(beanForm);				
				
		 Menu menu = Menu.createMenu("topMenu", CreatePanelPanelButtonsFactory.getInstance());
		 
		beanForm.addOrReplace(menu);
		 
		AutoFieldPanel<B> autoFieldPanel = newAutoFieldPanel("autofield",autoFieldModel);
		
		beanForm.addOrReplace(autoFieldPanel);
		

		
		super.onBeforeRender();
	}
	
	@SuppressWarnings("unchecked")
	public CRUDPanel<B> getCRUDPanel() {
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
	 * @return the messages
	 */
	public FeedbackPanel getMessages() {
		return messages;
	}
}
