/**
 * This software is provided as IS by Antilia-Soft SL.
 * Copyright 2006-2007.
 */
package com.antilia.web.layout;

import org.apache.wicket.markup.html.WebMarkupContainer;

import com.antilia.web.toolbar.Toolbar;


/**
 * 
 * @author Ernesto Reinaldo Barreiro (reiern70@gmail.com)
 *
 */
public abstract class ToolBarFullPage extends FullPage implements IContainer {

	private static final long serialVersionUID = 1L;	
	
	private WebMarkupContainer content;
	
	private WebMarkupContainer body;
	
	public ToolBarFullPage() {		
		super();			
		
		body = new WebMarkupContainer(IContainer.BODY_ID);
		body.setOutputMarkupId(true);
		
		add(body);
		
		content = new InitialPanel(IContainer.BODY_CONTENT_ID, this) {
			
			private static final long serialVersionUID = 1L;

			@Override
			protected Toolbar createToolbar(String id, ToolBarFullPage page) {
				return ToolBarFullPage.this.createToolbar(id, page);
			}
			
		};				
	}

	protected abstract Toolbar createToolbar(String id, ToolBarFullPage page);
	
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
