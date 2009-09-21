/**
 * 
 */
package com.antilia.web.dynamicdrive.test;

import org.apache.wicket.markup.html.link.AbstractLink;

import com.antilia.web.dynamicdrive.AbstractMenuLink;

/**
 * @author  Ernesto Reinaldo Barreiro (reiern70@gmail.com)
 *
 */
public class ExternalLink extends AbstractMenuLink {

	private static final long serialVersionUID = 1L;

	private String href;
	
	private String label;
	
	/**
	 * @param id
	 * @param href
	 */
	public ExternalLink(String id, String href, String label) {
		super(id);
		this.href = href;
		this.label = label;
	}	
	
	@Override
	protected AbstractLink newLink(String id) {
		return new org.apache.wicket.markup.html.link.ExternalLink(id,href, label);
	}

}
