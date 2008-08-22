package com.antilia.demo.manager.crud;

import org.apache.wicket.markup.html.WebMarkupContainer;

import com.antilia.demo.manager.BasePage;
import com.antilia.demo.manager.IContainer;
import com.antilia.web.dialog.ModalContainer;


public  abstract  class CRUDPage extends BasePage implements IContainer {

	private static final long serialVersionUID = 1L;	
	
	private WebMarkupContainer content;
	
	public CRUDPage() {		
		super();			
		
		content = newModalContainer("content");
		content.setOutputMarkupId(true);
		
		add(content);
	}
	
	protected abstract ModalContainer newModalContainer(String id);

	
	/**
	 * @return the content
	 */
	public WebMarkupContainer getContent() {
		return content;
	}

	/**
	 * @param content the content to set
	 */
	public void setContent(WebMarkupContainer content) {
		this.content = content;
	}
	
}
