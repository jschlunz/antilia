/**
 * This software is provided as IS by Antilia-Soft SL.
 * Copyright 2006-2007.
 */
package com.antilia.web.veil;

import org.apache.wicket.ResourceReference;
import org.apache.wicket.markup.html.IHeaderResponse;
import org.apache.wicket.markup.html.resources.JavascriptResourceReference;
import org.wicketstuff.minis.veil.VeilResources;

/**
 * 
 *
 * @author Ernesto Reinaldo Barreiro (reiern70@gmail.com)
 *
 */
public class AntiliaVeilResource extends VeilResources {

	private static final long serialVersionUID = 1L;
	
	private static final ResourceReference JS = new JavascriptResourceReference(AntiliaVeilResource.class, "antilia-veil.js");

	private static final ResourceReference CSS = new ResourceReference(AntiliaVeilResource.class, "antilia-veil.css");
	
	public static final String DEFAULT_CSS_CLASS_NAME_MODAL = "antilia-modal";

	public static class Modal {
		/**
		 * Generates javascript to show a veil over a tag
		 * 
		 * @param markupId
		 *            markup id of tag that will be covered by veil
		 * @param className
		 *            css class name for veil
		 * @return javascript
		 */
		public static String show(String markupId, String className)
		{
			return "Wicket.Veil.showModal('" + markupId + "', {className:'" + className + "'});";
		}

		/**
		 * Generates javascript to show a veil over a tag
		 * 
		 * @param markupId
		 *            markup id of tag that will be covered by veil
		 * @return javascript
		 */
		public static String show(String markupId)
		{
			return show(markupId, DEFAULT_CSS_CLASS_NAME_MODAL);
		}

		/**
		 * Generates javascript to toggle a veil over a tag
		 * 
		 * @param markupId
		 *            markup id of tag that will be covered by veil
		 * @param className
		 *            css class name for veil
		 * @return javascript
		 */
		public static String toggle(String markupId, String className)
		{
			return "Wicket.Veil.toggleModal('" + markupId + "', {className:'" + className + "'});";
		}

		/**
		 * Generates javascript to toggle a veil over a tag
		 * 
		 * @param markupId
		 *            markup id of tag that will be covered by veil
		 * @return javascript
		 */

		public static String toggle(String markupId)
		{
			return toggle(markupId, DEFAULT_CSS_CLASS_NAME_MODAL);
		}

		/**
		 * Generates javascript to hide a veil over a tag
		 * 
		 * @param markupId
		 *            markup id of tag that will be covered by veil
		 * @param className
		 *            css class name for veil
		 * @return javascript
		 */
		public static String hide(String markupId)
		{
			return "Wicket.Veil.toggleModal('" + markupId + "');";
		}
	}
	
	/**
	 * 
	 */
	public AntiliaVeilResource() {		
	}

	public void renderHead(IHeaderResponse response)
	{
		response.renderJavascriptReference(JS);
		response.renderCSSReference(CSS);
	}
}
