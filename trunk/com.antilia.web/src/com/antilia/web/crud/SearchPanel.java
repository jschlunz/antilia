/**
 * This software is provided as IS by Antilia-Soft SL.
 * Copyright 2006-2007.
 */
package com.antilia.web.crud;

import java.io.Serializable;

import org.apache.wicket.markup.html.panel.Panel;

import com.antilia.hibernate.query.IQuery;
import com.antilia.hibernate.query.Query;
import com.antilia.web.beantable.Table;
import com.antilia.web.beantable.model.ITableModel;
import com.antilia.web.beantable.model.TableModel;
import com.antilia.web.beantable.provider.ILoadable;
import com.antilia.web.beantable.provider.IPageableProvider;
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
public abstract class SearchPanel<B extends Serializable> extends Panel implements ILoadablePanel<B> {

	private static final long serialVersionUID = 1L;

	private Query<B> filterQuery;
	
	private BeanProxy<B> beanProxy;
	
	private IPageableProvider<B> pageableProvider;
	
	private Table<B> table;
	
	public SearchPanel(String id, Class<B> beanClass) {
		this(id, beanClass, null);
	}
	
	/**
	 * 
	 * @param id
	 * @param beanClass
	 * @param filterQuery
	 */
	public SearchPanel(String id, Class<B> beanClass, Query<B> filterQuery) {
		super(id);
		
		setOutputMarkupId(true);
		
		if(filterQuery != null)			
			this.filterQuery = filterQuery;
		else 
			this.filterQuery =  new Query<B>(beanClass);
		// create the form
		
		this.beanProxy = new BeanProxy<B>(this.filterQuery.getEntityClass());
		this.pageableProvider =  getTableData();
		
		IAutoFieldModel<B> autoFieldModel = newAutoFieldModel(this.filterQuery, this.beanProxy);
		configureFieldModel(autoFieldModel);
		
		BeanForm<B> beanForm = newForm("form", this.beanProxy);
		add(beanForm);
				
				
		 Menu menu = Menu.createMenu("topMenu", SearchPanelButtonsFactory.getInstance());
		 
		beanForm.add(menu);
		 
		AutoFieldPanel<B> autoFieldPanel = newAutoFieldPanel("autofield",autoFieldModel);
		beanForm.add(autoFieldPanel);
		
		ITableModel<B> tableModel = newTableModel(beanClass);		
		table  = newTable("table",tableModel, this.pageableProvider);
		beanForm.add(table);
	}
	
	 public IProviderSelector<B> getSelected() {
		 return table.getSourceSelector();
	 }
	
	protected void configureFieldModel(IAutoFieldModel<B> autoFieldModel) {
		String[] searchFields = getSearchFields();
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
	
	protected Table<B> newTable(String id, ITableModel<B> tableModel, IPageableProvider<B> pageableProvider) {
		return new Table<B>(id,tableModel, pageableProvider);
	}
	
	protected ITableModel<B> newTableModel(Class<B> beanClass) {
		return new TableModel<B>(beanClass, getTableColumns());
	}
	
	protected IAutoFieldModel<B> newAutoFieldModel(IQuery<B> query, BeanProxy<B> beanProxy) {
		return new AutoFieldModel<B>(query, beanProxy);
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public void reload() {
		getBeanProxy().updateFilterQuery(getFilterQuery());
		if(this.pageableProvider instanceof ILoadable) {
			((ILoadable)this.pageableProvider).load(getFilterQuery());
		}
	}
	
	protected abstract IPageableProvider<B>  getTableData();
	
	protected abstract String[] getTableColumns();
	
	protected abstract String[] getSearchFields();

	/**
	 * @return the filterQuery
	 */
	public Query<B> getFilterQuery() {
		return filterQuery;
	}

	/**
	 * @param filterQuery the filterQuery to set
	 */
	public void setFilterQuery(Query<B> filterQuery) {
		this.filterQuery = filterQuery;
	}

	/**
	 * @return the beanProxy
	 */
	public BeanProxy<B> getBeanProxy() {
		return beanProxy;
	}
}
