package com.antilia.demo.manager;

import org.apache.wicket.markup.html.WebMarkupContainer;


public class Index extends BasePage implements IContainer {

	private static final long serialVersionUID = 1L;	
	
	private WebMarkupContainer content;
	
	private WebMarkupContainer body;
	
	public Index() {		
		super();			
		
		body = new WebMarkupContainer("body");
		body.setOutputMarkupId(true);
		
		add(body);
		
		content = new InitialPanel("content", this);				
	}

	@Override
	protected void onBeforeRender() {
		super.onBeforeRender();
		
		body.addOrReplace(content);
	
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


	/**
	 * @return the body
	 */
	public WebMarkupContainer getBody() {
		return body;
	}
	
}
