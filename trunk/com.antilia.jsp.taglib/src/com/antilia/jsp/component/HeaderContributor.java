/**
 * 
 */
package com.antilia.jsp.component;

import java.io.PrintWriter;

import org.apache.wicket.util.string.Strings;

/**
 * @author Ernesto Reinaldo Barreiro (reirn70@gmail.com)
 *
 */
public abstract class HeaderContributor {


	public static final String RESOURCES_URI = "resources";
	
	public abstract void renderHead(PrintWriter writer);
	
		
	
	public static HeaderContributor forJavaScript(final ResourceReference reference) {
		return new HeaderContributor() {
			
			@Override
			public void renderHead(PrintWriter writer) {
				renderJavaScript(writer, reference);
			}
		};
	}
	
	public static HeaderContributor forCss(final ResourceReference reference) {
		return new HeaderContributor() {
			
			@Override
			public void renderHead(PrintWriter writer) {
				renderCss(writer, reference);
			}
		};
	}
	
	
	protected void renderCss(PrintWriter writer, ResourceReference reference) {
		renderCSSReference(writer, RequestContext.get().urlFor(reference), null);
	}

	protected void renderJavaScript(PrintWriter writer, ResourceReference reference) {
		writeJavascriptUrl(writer, RequestContext.get().urlFor(reference), null);
	}
	
	protected void renderCSSReference(PrintWriter writer, String url, String media)
	{
		if (Strings.isEmpty(url))
		{
			throw new IllegalArgumentException("url cannot be empty or null");
		}
		writer.write("<link rel=\"stylesheet\" type=\"text/css\" href=\"");
		writer.write(url);
		writer.write("\"");
		if (media != null)
		{
			writer.write(" media=\"");
			writer.write(media);
			writer.write("\"");
		}
		writer.println(" />");			
	}
	
	
	protected void writeJavascriptUrl(PrintWriter writer, final String url, final String id)
	{
		writer.write("<script type=\"text/javascript\" ");
		if (id != null)
		{
			writer.write("id=\"" + id + "\" ");
		}
		writer.write("src=\"");
		writer.write(url);
		writer.println("\"></script>");
	}
}
