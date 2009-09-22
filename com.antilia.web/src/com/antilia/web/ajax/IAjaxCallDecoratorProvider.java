package com.antilia.web.ajax;

import org.apache.wicket.ajax.IAjaxCallDecorator;

/**
 * Itreface to mark a component as IAjaxCallDecorator provider.
 * 
 * @author  Ernesto Reinaldo Barreiro (reiern70@gmail.com)
 */
public interface IAjaxCallDecoratorProvider {

	/**
	 * @return Returns an IAjaxCallDecorator.
	 */
	IAjaxCallDecorator getAjaxCallDecorator();
	
}
