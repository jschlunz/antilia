/**
 * This software is provided as IS by Antilia-Soft SL.
 * Copyright 2008-2009.
 */
package com.antilia.web.layout;

import org.apache.wicket.Component;
import org.apache.wicket.IPageMap;
import org.apache.wicket.PageParameters;
import org.apache.wicket.ResourceReference;
import org.apache.wicket.markup.html.CSSPackageResource;
import org.apache.wicket.markup.html.JavascriptPackageResource;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.model.IModel;

import com.antilia.web.resources.DefaultStyle;
import com.antilia.web.veil.AntiliaVeilResource;

/**
 * A page that occupies the whole view port. 
 * 
 * @author Ernesto Reinaldo Barreiro (reiern70@gmail.com)
 *
 */
public class FullPage extends WebPage {

	public static ResourceReference FullPageCSS = new ResourceReference(FullPage.class, "FullPage.css");
	
	public static String HEADER_ID = "header";
	
	/**
	 * 
	 */
	public FullPage() {		
		init();
	}

	/**
	 * @param model
	 */
	public FullPage(IModel<?> model) {
		super(model);
		init();
	}

	/**
	 * @param pageMap
	 */
	public FullPage(IPageMap pageMap) {
		super(pageMap);
		init();
	}

	/**
	 * @param parameters
	 */
	public FullPage(PageParameters parameters) {
		super(parameters);
		init();
	}

	/**
	 * @param pageMap
	 * @param model
	 */
	public FullPage(IPageMap pageMap, IModel<?> model) {
		super(pageMap, model);
		init();
	}

	/**
	 * @param pageMap
	 * @param parameters
	 */
	public FullPage(IPageMap pageMap, PageParameters parameters) {
		super(pageMap, parameters);
		init();
	}
	
	private void init() {
		add(new AntiliaVeilResource());
		add(JavascriptPackageResource.getHeaderContribution(DefaultStyle.JS_YUI_DOM_EVENT));
		add(JavascriptPackageResource.getHeaderContribution(DefaultStyle.JS_YUI_DOM_MIN));
		add(JavascriptPackageResource.getHeaderContribution(DefaultStyle.JS_YUI_ANIMATION));
		
		add(JavascriptPackageResource.getHeaderContribution(DefaultStyle.JS_COMMON));
		add(JavascriptPackageResource.getHeaderContribution(DefaultStyle.JS_TABLE));		
		add(CSSPackageResource.getHeaderContribution(DefaultStyle.CSS_MAIN));

		
		add(CSSPackageResource.getHeaderContribution(getFullPageCSS()));
		add(CSSPackageResource.getHeaderContribution(DefaultStyle.CSS_DIALOG));
		
		add(newHeader(HEADER_ID));
	}

	protected ResourceReference getFullPageCSS() {
		return FullPageCSS;
	}
	
	protected Component newHeader(String id) {
		DefaultFullPageHeader header = new DefaultFullPageHeader(id);
		header.setRenderBodyOnly(true);
		return header;
	}
}
