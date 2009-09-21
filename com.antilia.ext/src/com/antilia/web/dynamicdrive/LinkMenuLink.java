/**
 * 
 */
package com.antilia.web.dynamicdrive;

import org.apache.wicket.markup.html.link.AbstractLink;

/**
 * @author  Ernesto Reinaldo Barreiro (reiern70@gmail.com)
 *
 */
public class LinkMenuLink extends AbstractMenuLink {

	private static final long serialVersionUID = 1L;

	private AbstractLink link;
	/**
	 * @param id
	 */
	public LinkMenuLink(String id, AbstractLink link) {
		super(id);
		this.link = link;
	}

	/* (non-Javadoc)
	 * @see com.antilia.web.dynamicdrive.AbstractMenuLink#newLink(java.lang.String)
	 */
	@Override
	protected AbstractLink newLink(String id) {
		return this.link;
	}

}
