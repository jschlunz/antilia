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
public class LastPageLink<E extends Serializable> extends AjaxLink {

	private TableComponent<E> tableComponent;
	
	/**
	 * @param id
	 */
	public LastPageLink(String id, TableComponent<E> tableComponent) {
		super(id, null, "Last Page", Resources.IMG_LAST_ENABLED_PNG);
		this.tableComponent = tableComponent;
	}


	public void onLinkClicked(HttpServletRequest request) {
		tableComponent.getPageableNavigator().lastPage();
		if(RequestContext.get().isAjax()) {
			RequestContext.get().setAjaxTarget(this.tableComponent);
		}
	}
}
