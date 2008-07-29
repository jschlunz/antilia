/**
 * This software is provided as IS by Antilia-Soft SL.
 * Copyright 2006-2007.
 */
package com.antilia.web.crud;

import java.io.Serializable;

import org.apache.wicket.markup.html.panel.Panel;

import com.antilia.web.beantable.provider.IProviderSelector;

/**
 * 
 * @author Ernesto Reinaldo Barreiro (reiern70@gmail.com)
 */
public abstract class CRUDPanel<B extends Serializable> extends Panel {

	private static final long serialVersionUID = 1L;
	
	private SearchPanel<B> searchPanel;
	
	private EditPanel<B> editPanel;
		
	private CreatePanel<B> createPanel;
	
	private Panel currentPanel;
	
	private CrudStyler<B> styler;
	
	/**
	 * Constructs a default CRUD panel.
	 * 
	 * @param id
	 * @param beanClass
	 */
	public CRUDPanel(String id, Class<B> beanClass) {
		this(id, new AutoCrudStyler<B>(beanClass));
	}
	
	/**
	 * 
	 * @param id
	 * @param styler
	 */
	public CRUDPanel(String id, CrudStyler<B> styler) {
		super(id,  null);		
		setOutputMarkupId(true);
		
		this.styler = styler;
		
		configureStyler(this.styler);
		
		this.searchPanel = getSearchPanel("body", styler);				
		currentPanel = searchPanel;
		
		editPanel = getEditPanel("body", styler);		
		
		
		createPanel = getCreatePanel("body", styler);
		
	}
	
	/**
	 * Override this method on supper classes in case you want to tune the style before 
	 * components are created (e.g. to add/remove table columns, etc).
	 */
	protected void configureStyler(CrudStyler<B> styler) {
		
	}
	
	
	@Override
	protected void onBeforeRender() {
		addOrReplace(currentPanel);
		super.onBeforeRender();
	}
	
	 public IProviderSelector<B> getSelected() {
		 return getSearchPanel().getSelected();
	 }
	
	/**
	 * Override this method to create your custom SearchPanel.
	 * 
	 * @param id
	 * @param styler
	 * @return
	 */
	protected SearchPanel<B> getSearchPanel(String id, CrudStyler<B> styler) {
		return new SearchPanel<B>(id, styler);
	}
	
	/**
	 * Override this method to create your custom EditPanel.
	 * @param id
	 * @param styler
	 * @return
	 */
	protected EditPanel<B> getEditPanel(String id, CrudStyler<B> styler) {
		return new EditPanel<B>(id, styler);
	}
	
	/**
	 * Override this method to create your custom EditPanel.
	 * @param id
	 * @param styler
	 * @return
	 */
	protected CreatePanel<B> getCreatePanel(String id, CrudStyler<B> styler) {
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
	
}
