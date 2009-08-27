/**
 * 
 */
package com.antilia.jsp.component;

import java.io.Serializable;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Ernesto Reinaldo Barreiro (reirn70@gmail.com)
 *
 */
public class NextPageLink<E extends Serializable> extends AjaxLink {

	private TableComponent<E> tableComponent;
	
	/**
	 * @param id
	 */
	public NextPageLink(String id, TableComponent<E> tableComponent) {
		super(id);
		this.tableComponent = tableComponent;
	}


	public void onLinkClicked(HttpServletRequest request) {
		tableComponent.getPageableNavigator().nextPage();
		if(RequestContext.get().isAjax()) {
			RequestContext.get().setAjaxTarget(this.tableComponent);
		}
	}
}