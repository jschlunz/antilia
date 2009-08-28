/**
 * 
 */
package com.antilia.jsp.component.table;

import java.io.Serializable;

import javax.servlet.http.HttpServletRequest;

import com.antilia.jsp.component.AjaxLink;
import com.antilia.jsp.component.RequestContext;
import com.antilia.jsp.resources.Resources;

/**
 * @author Ernesto Reinaldo Barreiro (reirn70@gmail.com)
 *
 */
public class FirstPageLink<E extends Serializable> extends AjaxLink {

	private TableComponent<E> tableComponent;
	
	/**
	 * @param id
	 */
	public FirstPageLink(String id, TableComponent<E> tableComponent) {
		super(id, null, "First Page", Resources.IMG_FIRST_ENABLED_PNG);
		this.tableComponent = tableComponent;
	}


	public void onLinkClicked(HttpServletRequest request) {
		tableComponent.getPageableNavigator().firstPage();
		if(RequestContext.get().isAjax()) {
			RequestContext.get().setAjaxTarget(this.tableComponent);
		}
	}
}
