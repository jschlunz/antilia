/**
 * This software is provided as IS by Antilia-Soft SL.
 * Copyright 2006-2007.
 */
package com.antilia.web.crud;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * 
 *
 * @author Ernesto Reinaldo Barreiro (reiern70@gmail.com)
 *
 */
public class CrudStyler<E extends Serializable> implements Serializable {

	
	private static final long serialVersionUID = 1L;
	
	private java.util.List<String> editFields = new ArrayList<String>();
	
	private java.util.List<String> tableColumns = new ArrayList<String>();
	
	private java.util.List<String> searchFields = new ArrayList<String>();
	
	private Class<E> beanClass;
	
	public CrudStyler(Class<E> beanClass) {
		this.beanClass = beanClass;
	}

	/**
	 * @return the editFields
	 */
	public java.util.List<String> getEditFields() {
		return editFields;
	}	
	
	public void addEditFields(String... editFields) {
		if(editFields != null) {
			for(String field: editFields) {
				this.editFields.add(field);
			}
		}
	}
	
	/**
	 * @param editFields the editFields to set
	 */
	public void removeEditFields(String[] editFields) {
		for(String field: editFields) {
			this.editFields.remove(field);
		}
	}
	
	/**
	 * @param editFields the editFields to set
	 */
	public void addEditFields(java.util.List<String> editFields) {
		if(editFields != null) {
			for(String field: editFields) {
				this.editFields.add(field);
			}
		}
	}

	/**
	 * @return the tableColumns
	 */
	public java.util.List<String> getTableColumns() {
		return tableColumns;
	}

	/**
	 * @param tableColumns the tableColumns to set
	 */
	public void addTableColumns(java.util.List<String> tableColumns) {
		if(tableColumns != null) {
			for(String field: tableColumns) {
				this.tableColumns.add(field);
			}
		}
	}

	/**
	 * @param editFields the editFields to set
	 */
	public void addTableColumns(String...  tableColumns) {
		if(tableColumns != null) {
			for(String field: tableColumns) {
				this.tableColumns.add(field);
			}
		}
	}
	
	/**
	 * @param editFields the editFields to set
	 */
	public void removeTableColumns(String[] tableColums) {
		for(String field: tableColums) {
			this.tableColumns.remove(field);
		}
	}
	
	/**
	 * @return the searchFields
	 */
	public java.util.List<String> getSearchFields() {
		return searchFields;
	}

	/**
	 * @param searchFields the searchFields to set
	 */
	public void addSearchFields(java.util.List<String> searchFields) {
		if(searchFields != null) {
			for(String field: searchFields) {
				this.searchFields.add(field);
			}
		}
	}
	
	/**
	 * @param editFields the editFields to set
	 */
	public void addSearchFields(String... searchFields) {
		if(searchFields != null) {
			for(String field: searchFields) {
				this.searchFields.add(field);
			}
		}
	}

	/**
	 * @param editFields the editFields to set
	 */
	public void removeSearchFields(String[] searchFields) {
		for(String field: searchFields) {
			this.searchFields.remove(field);
		}
	}

	/**
	 * @return the beanClass
	 */
	public Class<E> getBeanClass() {
		return beanClass;
	}

	/**
	 * @param beanClass the beanClass to set
	 */
	public void setBeanClass(Class<E> beanClass) {
		this.beanClass = beanClass;
	}
}
