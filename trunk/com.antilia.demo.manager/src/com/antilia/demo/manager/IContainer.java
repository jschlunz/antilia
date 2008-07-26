/**
 * This software is private property of FCC (www.fcc.es).
 *
 */
package com.antilia.demo.manager;

import org.apache.wicket.markup.html.WebMarkupContainer;

/**
 * 
 *
 * @author Ernesto Reinaldo Barreiro (reiern70@gmail.com)
 *
 */
public interface IContainer {

	WebMarkupContainer getContent();
	
	String getBodyId();
	
}
