/**
 * 
 */
package com.antilia.jsp.component;

import java.io.Serializable;

import javax.servlet.http.HttpServletRequest;

import com.antilia.jsp.resources.Resources;

/**
 * @author Ernesto Reinaldo Barreiro (reirn70@gmail.com)
 *
 */
public class PreviousPageLink<E extends Serializable> extends AjaxLink {

	private TableComponent<E> tableComponent;
	
	/**
	 * @param id
	 */
	public PreviousPageLink(String id, TableComponent<E> tableComponent) {
		super(id, null, "Previous page", Resources.IMG_PREVIOUS_ENABLED_PNG);
		this.tableComponent = tableComponent;
	}

	public void onLinkClicked(HttpServletRequest request) {
		tableComponent.getPageableNavigator().previousPage();
		if(RequestContext.get().isAjax()) {
			RequestContext.get().setAjaxTarget(this.tableComponent);
		}
	}
}
