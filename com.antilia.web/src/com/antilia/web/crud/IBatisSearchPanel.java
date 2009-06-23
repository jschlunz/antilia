/**
 * 
 */
package com.antilia.web.crud;

import java.io.Serializable;

import com.antilia.common.dao.IQuerableDao;

/**
 * @author Ernesto Reinaldo Barreiro (reirn70@gmail.com)
 *
 */
public class IBatisSearchPanel<B extends Serializable> extends SearchPanel<B> {

	private static final long serialVersionUID = 1L;

	public IBatisSearchPanel(String id, IQuerableDao<B> dao, CrudStyler<B> styler) {
		super(id, dao, styler);
	}
}
