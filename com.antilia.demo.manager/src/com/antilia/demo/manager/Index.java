package com.antilia.demo.manager;

import org.apache.wicket.markup.html.WebMarkupContainer;

import com.antilia.demo.manager.crud.CityCRUDPanel;


public class Index extends BasePage implements IContainer {

	private static final long serialVersionUID = 1L;	
	
	private WebMarkupContainer content;
	
	public Index() {		
		super();			
		
		content = new WebMarkupContainer("content");
		content.setOutputMarkupId(true);		
		
		add(content);
		
		content.add(new CityCRUDPanel(getBodyId()));
		
		//content.add(new Label(getBodyId(), ""));
	}

	@Override
	public String getBodyId() {
		return "body";
	}
	
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
