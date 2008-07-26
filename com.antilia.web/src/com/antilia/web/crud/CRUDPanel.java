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
	
	private Panel currentPanel;
	
	public CRUDPanel(String id) {
		super(id,  null);		
		setOutputMarkupId(true);
		this.searchPanel = getSearchPanel("body");		
		
		
		currentPanel = searchPanel;
		
		editPanel = getEditPanel("body");		
	}
	
	@Override
	protected void onBeforeRender() {
		addOrReplace(currentPanel);
		super.onBeforeRender();
	}
	
	 public IProviderSelector<B> getSelected() {
		 return getSearchPanel().getSelected();
	 }
	
	protected abstract SearchPanel<B> getSearchPanel(String id); 
	
	protected abstract EditPanel<B> getEditPanel(String id);


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
	
}
