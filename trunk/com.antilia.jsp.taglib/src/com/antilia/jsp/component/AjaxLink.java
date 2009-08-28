/**
 * 
 */
package com.antilia.jsp.component;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Ernesto Reinaldo Barreiro (reirn70@gmail.com)
 *
 */
public abstract class AjaxLink extends AbstractComponent implements ImenuItem, ILinkListener {

	private String label;
	
	private ResourceReference image;
	
	private String title;
	/**
	 * @param id
	 */
	public AjaxLink(String id, String label, String title, ResourceReference image) {
		super(id);
		this.label = label;
		this.image = image;
		this.title = title;
	}

	/* (non-Javadoc)
	 * @see com.antilia.jsp.component.AbstractComponent#onRender(java.io.PrintWriter, javax.servlet.http.HttpServletRequest)
	 */
	@Override
	protected void onRender(PrintWriter writer, HttpServletRequest request) throws Exception {		
		writer.print("<a href=\"javascript:void(0);\" class=\"smallbutton\"");
		writer.print(" onclick=\"Antilia.Ajax.doAjax(\'");		
		writer.print(RequestContext.get().getUrlGenerator().generateUrlFor(this));		
		writer.println("\');\"");
		writer.println("title=\"");
		writer.println(getTitle());
		writer.println("\"");
		writer.println(">");
		writer.println("<img src=\""+RequestContext.get().urlFor(image)+"\"/>");
		writer.print("<span>");
		writer.println(getLabel()!=null?getLabel():"&nbsp;");
		writer.print("</span>");
		writer.print("</a>");
	}

	/**
	 * @return the label
	 */
	public String getLabel() {
		return label;
	}

	/**
	 * @param label the label to set
	 */
	public void setLabel(String label) {
		this.label = label;
	}

	/**
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * @param title the title to set
	 */
	public void setTitle(String title) {
		this.title = title;
	}

}
