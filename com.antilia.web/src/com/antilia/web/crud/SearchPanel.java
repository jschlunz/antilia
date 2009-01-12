/**
 * This software is provided as IS by Antilia-Soft SL.
 * Copyright 2006-2007.
 */
package com.antilia.web.crud;

import java.io.Serializable;
import java.util.Iterator;
import java.util.List;

import org.apache.wicket.markup.html.panel.Panel;

import com.antilia.hibernate.query.IQuery;
import com.antilia.hibernate.query.Query;
import com.antilia.web.beantable.Table;
import com.antilia.web.beantable.model.IColumnModel;
import com.antilia.web.beantable.model.ITableModel;
import com.antilia.web.beantable.model.TableModel;
import com.antilia.web.beantable.provider.ILoadable;
import com.antilia.web.beantable.provider.IPageableProvider;
import com.antilia.web.beantable.provider.IProviderSelector;
import com.antilia.web.beantable.provider.impl.DataProviderPageableProvider;
import com.antilia.web.beantable.provider.impl.HibernateQueryDataProvider;
import com.antilia.web.field.AutoFieldCreator;
import com.antilia.web.field.AutoFieldPanel;
import com.antilia.web.field.BeanForm;
import com.antilia.web.field.BeanProxy;
import com.antilia.web.field.IAutoFieldCreator;
import com.antilia.web.field.factory.FieldMode;
import com.antilia.web.menu.Menu;

/**
 * 
 * @author Ernesto Reinaldo Barreiro (reiern70@gmail.com)
 */
public class SearchPanel<B extends Serializable> extends Panel implements ILoadablePanel<B>, ICRUDModeReporter {

	private static final long serialVersionUID = 1L;

	private Query<B> filterQuery;
	
	private BeanProxy<B> beanProxy;
	
	private IPageableProvider<B> pageableProvider;
	
	private Table<B> table;
	
	private CrudStyler<B> styler;
	
	public SearchPanel(String id, CrudStyler<B> styler) {
		this(id,  null, null, styler);
	}
	
	/**
	 * 
	 * @param id
	 * @param beanClass
	 * @param filterQuery
	 */
	public SearchPanel(String id,  Query<B> filterQuery, IPageableProvider<B> pageableProvider, CrudStyler<B> styler) {
		super(id);
		
		setOutputMarkupId(true);
		
		if(filterQuery != null)			
			this.filterQuery = filterQuery;
		else 
			this.filterQuery =  new Query<B>(styler.getBeanClass());
		// create the form
		
		this.beanProxy = new BeanProxy<B>(this.filterQuery.getEntityClass());
		if(pageableProvider != null) 
			this.pageableProvider =  pageableProvider;
		else {
			this.pageableProvider = new DataProviderPageableProvider<B>(new HibernateQueryDataProvider<B>(this.filterQuery), this.filterQuery);
		}
		
		this.styler = styler;
		
		IAutoFieldCreator<B> autoFieldModel = newAutoFieldModel(this.filterQuery, this.beanProxy);
		configureFieldModel(autoFieldModel);
		
		BeanForm<B> beanForm = newForm("form", this.beanProxy);
		add(beanForm);
				
				
		 Menu menu = newTopMenuMenu("topMenu");
		 
		beanForm.add(menu);
		 
		AutoFieldPanel<B> autoFieldPanel = newAutoFieldPanel("autofield",autoFieldModel);
		beanForm.add(autoFieldPanel);
		
		// allow users to configure the column models.
		ITableModel<B> tableModel = newTableModel(styler.getBeanClass());		
		Iterator<IColumnModel<B>> it = tableModel.getColumnModels();		
		while(it.hasNext() ) {
			IColumnModel<B> columnModel = it.next();
			configureColumnModel(columnModel);
		}
		
		// allow users to configure hidden column models.
		it = tableModel.getHiddenModels();		
		while(it.hasNext() ) {
			IColumnModel<B> columnModel = it.next();
			configureColumnModel(columnModel);
		}
		
		table  = newTable("table",tableModel, this.pageableProvider);
		beanForm.add(table);
	}
	
	protected Menu newTopMenuMenu(String id) {
		return Menu.createMenu(id, SearchPanelButtonsFactory.getInstance());
	}
	
	 public IProviderSelector<B> getSelected() {
		 return table.getSourceSelector();
	 }
	
	protected void configureFieldModel(IAutoFieldCreator<B> autoFieldModel) {
		List<String> searchFields = styler.getSearchFields();
		if(searchFields != null) {
			for(String propertyPath: searchFields) {
				autoFieldModel.newFieldModel(propertyPath);
			}
		}
	}
	
	protected void populateTopMenu(Menu topMenu) {
		
	}
	
	protected AutoFieldPanel<B> newAutoFieldPanel(String id, IAutoFieldCreator<B> autoFieldModel) {
		return new AutoFieldPanel<B>(id,autoFieldModel, FieldMode.SEARCH);
	}
	
	protected BeanForm<B> newForm(String id, BeanProxy<B> beanProxy) {
		return new BeanForm<B>(id, beanProxy);
	}
	
	protected Table<B> newTable(String id, ITableModel<B> tableModel, IPageableProvider<B> pageableProvider) {
		return new Table<B>(id,tableModel, pageableProvider);
	}
	
	protected ITableModel<B> newTableModel(Class<B> beanClass) {
		return new TableModel<B>(beanClass, styler.getTableColumns(), styler.getHiddenTableColumns());
	}
	
	/**
	 * This method is called for each column model to give the user a chance to configure the 
	 * columns...
	 * @param model
	 */
	protected void configureColumnModel(IColumnModel<B> model) {
		
	}
	
	protected IAutoFieldCreator<B> newAutoFieldModel(IQuery<B> query, BeanProxy<B> beanProxy) {
		return new AutoFieldCreator<B>(query, beanProxy);
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public void reload() {
		getBeanProxy().updateFilterQuery(getFilterQuery());
		if(this.pageableProvider instanceof ILoadable) {
			((ILoadable)this.pageableProvider).load(getFilterQuery());
		}
	}

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

	/**
	 * @return the pageableProvider
	 */
	public IPageableProvider<B> getPageableProvider() {
		return pageableProvider;
	}
	
	@Override
	public CRUDMode getCrudMode() {
		return CRUDMode.SEARCH;
	}
}
