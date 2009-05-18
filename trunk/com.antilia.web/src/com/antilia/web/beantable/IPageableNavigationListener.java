package com.antilia.web.beantable;

import org.apache.wicket.ajax.AjaxRequestTarget;

/**
 * Defines a listener for a pageable component navigation.
 * 
 * @author Ernesto Reinaldo Barreiro (reirn70@gmail.com)
 */
public interface IPageableNavigationListener {
			
	/**
	 * Called when navigating to next page.
	 * 
	 * @param target
	 */
	void onFirstPage(AjaxRequestTarget target);	
	
	
	/**
	 * Called when navigating to previous page.
	 * 
	 * @param target
	 */
	void onPreviousPage(AjaxRequestTarget target);
	
	
	/**
	 * Called when navigating to next page.
	 * 
	 * @param target
	 */
	void onNextPage(AjaxRequestTarget target);
	
	/**
	 * Called when navigating to last page.
	 * 
	 * @param target
	 */
	void onLastPage(AjaxRequestTarget target);
}
