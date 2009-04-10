/**
 * This software is provided as IS by Antilia-Soft SL.
 * Copyright 2006-2007.
 */
package com.antilia.web.layout;

import org.apache.wicket.markup.html.WebMarkupContainer;

import com.antilia.web.login.IProtectedPage;
import com.antilia.web.toolbar.Toolbar;


/**
 * 
 * @author Ernesto Reinaldo Barreiro (reiern70@gmail.com)
 *
 */
public abstract class ToolBarFullPage extends FullPage implements IContainer, IProtectedPage {

	private static final long serialVersionUID = 1L;	
	
	private WebMarkupContainer content;
	
	private WebMarkupContainer body;
	
	public ToolBarFullPage() {		
		super();			
		
		body = new WebMarkupContainer("body");
		body.setOutputMarkupId(true);
		
		add(body);
		
		content = new InitialPanel("content", this) {
			
			private static final long serialVersionUID = 1L;

			@Override
			protected Toolbar createToolbar(String id, ToolBarFullPage page, String contentId) {
				return ToolBarFullPage.this.createToolbar(id, page, contentId);
			}
			
		};				
	}

	protected abstract Toolbar createToolbar(String id, ToolBarFullPage page, String contentId);
	
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
