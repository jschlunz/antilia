/**
 * 
 */
package com.antilia.letsplay;

import org.apache.wicket.markup.html.CSSPackageResource;
import org.apache.wicket.markup.html.JavascriptPackageResource;
import org.apache.wicket.markup.html.WebPage;

import com.antilia.letsplay.resources.AppStyle;
import com.antilia.web.resources.DefaultStyle;
import com.antilia.web.veil.AntiliaVeilResource;

/**
 * @author Ernesto Reinaldo Barreiro (reiern70@gmail.com)
 *
 */
public abstract class BasePage extends WebPage  {

	public BasePage() {
		add(new AntiliaVeilResource());
		add(JavascriptPackageResource.getHeaderContribution(DefaultStyle.JS_YUI_DOM_EVENT));
		add(JavascriptPackageResource.getHeaderContribution(DefaultStyle.JS_YUI_DOM_MIN));
		add(JavascriptPackageResource.getHeaderContribution(DefaultStyle.JS_YUI_ANIMATION));
		
		add(JavascriptPackageResource.getHeaderContribution(DefaultStyle.JS_COMMON));
		add(JavascriptPackageResource.getHeaderContribution(DefaultStyle.JS_TABLE));		
		add(CSSPackageResource.getHeaderContribution(DefaultStyle.CSS_MAIN));		
		add(CSSPackageResource.getHeaderContribution(DefaultStyle.CSS_DIALOG));
		
		add(CSSPackageResource.getHeaderContribution(AppStyle.CSS));
			

	}
}
