/**
 * This software is provided as IS by Antilia-Soft SL.
 * Copyright 2006-2007.
 */
package com.antilia.demo;

import org.apache.wicket.behavior.HeaderContributor;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.form.Form;

import com.antilia.demo.buttons.MainButtonsFactory;
import com.antilia.demo.style.Style;
import com.antilia.web.menu.Menu;
import com.antilia.web.resources.DefaultStyle;

/**
 * 
 * @author Ernesto Reinaldo Barreiro (reiern70@gmail.com)
 */
public class Index extends WebPage {

	private static final long serialVersionUID = 1L;

	private WebMarkupContainer body;
	private WebMarkupContainer contents;
	/**
	 * 
	 */
	public Index() {
		Form form = new Form("form");
		add(form);
		add(HeaderContributor.forCss(DefaultStyle.CSS_MAIN));
		add(HeaderContributor.forCss(Style.TEST_CSS));
		add(HeaderContributor.forJavaScript(DefaultStyle.JS_COMMON));
		add(HeaderContributor.forJavaScript(DefaultStyle.JS_TABLE));
				
		contents = new WebMarkupContainer("contents") {
			private static final long serialVersionUID = 1L;

			@Override
			protected void onBeforeRender() {
				addOrReplace(body);
				super.onBeforeRender();
			}
		};				
		form.add(contents);
		form.add(Menu.createMenu("menu",  new MainButtonsFactory(this)));
		body = createBody("body");
		contents.setOutputMarkupId(true);
		contents.setVisible(true);
	}
	
	protected WebMarkupContainer createBody(String id) {
		return new WebMarkupContainer(id);
	}

	/**
	 * @return the body
	 */
	protected WebMarkupContainer getBody() {
		return body;
	}

	/**
	 * @param body the body to set
	 */
	public void setBody(WebMarkupContainer body) {
		this.body = body;		
	}

	/**
	 * @return the contents
	 */
	public WebMarkupContainer getContents() {
		return contents;
	}

	/**
	 * @param contents the contents to set
	 */
	protected void setContents(WebMarkupContainer contents) {
		this.contents = contents;
	}

		
}
