/**
 * 
 */
package com.antilia.jsp.provider;

import java.util.Iterator;

/**
 * @author Ernesto Reinaldo Barreiro (reirn70@gmail.com)
 *
 */
public interface IDataProvider<B> {

	/**
	 * 
	 * @param start
	 * @param size
	 * @return
	 */
	Iterator<B> getData(long start, long size);
	
	/**
	 * 
	 * @return
	 */
	Long getCount();
}
