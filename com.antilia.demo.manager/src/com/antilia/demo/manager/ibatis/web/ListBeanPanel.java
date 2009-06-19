/**
 * 
 */
package com.antilia.demo.manager.ibatis.web;

import java.io.Serializable;

import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.panel.Panel;

import com.antilia.hibernate.dao.IQuerableUpdatableDao;
import com.antilia.hibernate.query.IQuery;
import com.antilia.hibernate.query.Query;
import com.antilia.web.beantable.Table;
import com.antilia.web.beantable.model.TableModel;

/**
 * @author Ernesto Reinaldo Barreiro (reirn70@gmail.com)
 *
 */
public class ListBeanPanel<B extends Serializable> extends Panel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private IQuery<B> query;
	
	private IQuerableUpdatableDao<B> dao;
	
	/**
	 * 
	 */
	public ListBeanPanel(String id, IQuerableUpdatableDao<B> dao, Class<B> beanClass, String... columns) {
		super(id);
		this.dao = dao;
		this.query = new Query<B>(beanClass);
		
		Label clazz = new Label("class", beanClass.getName());
		add(clazz);
		
		TableModel<B> tableModel = new TableModel<B>(beanClass, columns);		
		Table<B> table = new Table<B>("table", tableModel, dao, query);
		add(table);
	}

	/**
	 * @return the dao
	 */
	public IQuerableUpdatableDao<B> getDao() {
		return dao;
	}

	/**
	 * @param dao the dao to set
	 */
	public void setDao(IQuerableUpdatableDao<B> dao) {
		this.dao = dao;
	}

}
