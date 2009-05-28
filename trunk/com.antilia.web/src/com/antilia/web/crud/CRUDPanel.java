/**
 * This software is provided as IS by Antilia-Soft SL.
 * Copyright 2006-2007.
 */
package com.antilia.web.crud;

import java.io.Serializable;

import org.apache.wicket.Component;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.panel.Panel;

import com.antilia.hibernate.dao.IDaoLocator;
import com.antilia.hibernate.dao.IQuerableUpdatableDao;
import com.antilia.hibernate.query.IQuery;
import com.antilia.web.beantable.model.IColumnModel;
import com.antilia.web.navigator.INavigatorSelector;
import com.antilia.web.provider.IQuerableDataProvider;
import com.antilia.web.provider.IQuerableUpdatebleDataProvider;
import com.antilia.web.provider.impl.DaoQuerableUpdatebleDataProvider;
import com.antilia.web.utils.DaoUtils;
import com.google.inject.Inject;

/**
 * Panel that automates the creation of CRUD pages.
 * 
 * @author Ernesto Reinaldo Barreiro (reiern70@gmail.com)
 */
public class CRUDPanel<B extends Serializable> extends Panel implements ICRUDModeReporter {

	private static final long serialVersionUID = 1L;
	
	private SearchPanel<B> searchPanel;
	
	private EditPanel<B> editPanel;
		
	private CreatePanel<B> createPanel;
	
	private Panel currentPanel;
	
	private CrudStyler<B> styler;
	
	private CRUDPanel<Serializable> parentCrud;
	
	public static final String BEFORE_BODY_ID = "beforeBody";
	
	public static final String AFTER_BODY_ID = "afterBody";
	
	public static final String BODY_ID = "body";
	
	public static final String EXTRA_ID_CRUD = "CRUDPanel";
	
	@Inject(optional=true)
	private transient IDaoLocator daoLocator;
	
	/**
	 * Constructs a default CRUD panel.
	 * 
	 * @param id
	 * @param beanClass
	 */
	public CRUDPanel(String id, Class<B> beanClass) {
		this(id, new AutoCrudStyler<B>(beanClass));
	}
	
	public CRUDPanel(String id, Class<B> beanClass,  CRUDPanel<Serializable> parentCrud) {
		this(id, new AutoCrudStyler<B>(beanClass), parentCrud);
	}
	
	public CRUDPanel(String id, CrudStyler<B> styler, CRUDPanel<Serializable> parentCrud) {
		super(id, null);
		this.parentCrud = parentCrud;
		setOutputMarkupId(true);		 		
		
		this.styler = styler;
		
		configureStyler(this.styler);
		
		this.searchPanel = newSearchPanel(BODY_ID, styler);				
		currentPanel = searchPanel;
		// edit panel
		editPanel = newEditPanel(BODY_ID, styler);				
		// create panel
		createPanel = newCreatePanel(BODY_ID, styler);		
	
		add(newAfterBody(AFTER_BODY_ID, styler));
	}
	
	/**
	 * 
	 * @param id
	 * @param styler
	 */
	public CRUDPanel(String id, CrudStyler<B> styler) {
		this(id,  styler, (CRUDPanel<Serializable>)null);			
	}
	
	
	
	/**
	 * Override this method on supper classes in case you want to tune the style before 
	 * components are created (e.g. to add/remove table columns, etc).
	 */
	protected void configureStyler(CrudStyler<B> styler) {
		
	}
	
	
	@Override
	protected void onBeforeRender() {
		addOrReplace(newBeforeBody(BEFORE_BODY_ID, styler));
		
		addOrReplace(currentPanel);
		super.onBeforeRender();
	}
	
	 public INavigatorSelector<B> getSelected() {
		 return getSearchPanel().getSelected();
	 }
	
	 /**
		 * Override this method to create your custom component before the search panel.
		 * 
		 * @param id
		 * @param styler
		 * @return
		 */
		protected Component newBeforeBody(String id, CrudStyler<B> styler) {
			Label label = new Label(id, "");
			label.setRenderBodyOnly(true);
			return label;
		}
		
		/**
		 * Override this method to create your custom component after the search panel.
		 * 
		 * @param id
		 * @param styler
		 * @return
		 */
		protected Component newAfterBody(String id, CrudStyler<B> styler) {
			Label label = new Label(id, "");
			label.setRenderBodyOnly(true);
			return label;
		}
		
	/**
	 * Override this method to create your custom SearchPanel.
	 * 
	 * @param id
	 * @param styler
	 * @return
	 */
	protected SearchPanel<B> newSearchPanel(String id, CrudStyler<B> styler) {
		return new SearchPanel<B>(id, styler) {
			 private static final long serialVersionUID = 1L;

			@Override
			protected void configureColumnModel(IColumnModel<B> model) {
				CRUDPanel.this.configureColumnModel(model);
			}
			
			@Override
			protected IQuerableDataProvider<B> createPageableProvider( IQuery<B> filterQuery) {
				return CRUDPanel.this.createPageableProvider(filterQuery);
			}
			
		};
	}
	
	
	/**
	 * Give the chance to sub-classes to return 
	 * 
	 * @param filterQuery
	 * @return
	 */
	protected IQuerableUpdatebleDataProvider<B> createPageableProvider(IQuery<B> filterQuery) {
		return new DaoQuerableUpdatebleDataProvider<B>(filterQuery, createQuerableUpdatableDao(filterQuery));
	}
	
	/**
	 * 
	 * @return
	 */
	protected IQuerableUpdatableDao<B> createQuerableUpdatableDao(IQuery<B> filterQuery) {
		return DaoUtils.findQuerableUpdatableDao(daoLocator, filterQuery.getEntityClass(), getExtraId());
	}
	
	protected String getExtraId() {
		return EXTRA_ID_CRUD;
	}
	
	
	/**
	 * This method is called for each column model to give the user a chance to configure the 
	 * columns...
	 * @param model
	 */
	protected void configureColumnModel(IColumnModel<B> model) {
		
	}
	
	/**
	 * Override this method to create your custom EditPanel.
	 * @param id
	 * @param styler
	 * @return
	 */
	protected EditPanel<B> newEditPanel(String id, CrudStyler<B> styler) {
		return new EditPanel<B>(id, styler);
	}
	
	/**
	 * Override this method to create your custom EditPanel.
	 * @param id
	 * @param styler
	 * @return
	 */
	protected CreatePanel<B> newCreatePanel(String id, CrudStyler<B> styler) {
		return new CreatePanel<B>(id, styler);
	}


	/**
	 * @return the editPanel
	 */
	public EditPanel<B> getEditPanel() {
		return editPanel;
	}


	/**
	 * @param editPanel the editPanel to set
	 */
	public void setEditPanel(EditPanel<B> editPanel) {
		this.editPanel = editPanel;
	}


	/**
	 * @return the searchPanel
	 */
	public SearchPanel<B> getSearchPanel() {
		return searchPanel;
	}


	/**
	 * @param searchPanel the searchPanel to set
	 */
	public void setSearchPanel(SearchPanel<B> searchPanel) {
		this.searchPanel = searchPanel;
	}

	/**
	 * @return the currentPanel
	 */
	public Panel getCurrentPanel() {
		return currentPanel;
	}

	/**
	 * @param currentPanel the currentPanel to set
	 */
	public void setCurrentPanel(Panel currentPanel) {
		this.currentPanel = currentPanel;
	}

	/**
	 * @return the styler
	 */
	public CrudStyler<B> getStyler() {
		return styler;
	}

	/**
	 * @param styler the styler to set
	 */
	public void setStyler(CrudStyler<B> styler) {
		this.styler = styler;
	}

	/**
	 * @return the createPanel
	 */
	public CreatePanel<B> getCreatePanel() {
		return createPanel;
	}

	public CRUDPanel<Serializable> getParentCrud() {
		return parentCrud;
	}

	public void setParentCrud(CRUDPanel<Serializable> parentCrud) {
		this.parentCrud = parentCrud;
	}
	
	public CRUDMode getCrudMode() {
		if(currentPanel != null && currentPanel instanceof ICRUDModeReporter)
			return ((ICRUDModeReporter)currentPanel).getCrudMode();
		return CRUDMode.SEARCH;
	}
	
}
