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
	
	public Class<E> beanClass;
	
	public CrudStyler(Class<E> beanClass) {
		this.beanClass = beanClass;
	}

	/**
	 * @return the editFields
	 */
	public java.util.List<String> getEditFields() {
		return editFields;
	}
	
	/**
	 * @param editFields the editFields to set
	 */
	public void addEditFields(String[] editFields) {
		for(String field: editFields) {
			this.editFields.add(field);
		}
	}
	
	/**
	 * @param editFields the editFields to set
	 */
	public void setEditFields(java.util.List<String> editFields) {
		this.editFields = editFields;
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
	public void setTableColumns(java.util.List<String> tableColumns) {
		this.tableColumns = tableColumns;
	}

	/**
	 * @param editFields the editFields to set
	 */
	public void addTableColumns(String[] tableColums) {
		for(String field: tableColums) {
			this.tableColumns.add(field);
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
	public void setSearchFields(java.util.List<String> searchFields) {
		this.searchFields = searchFields;
	}
	
	/**
	 * @param editFields the editFields to set
	 */
	public void addSearchFields(String[] tableColums) {
		for(String field: tableColums) {
			this.tableColumns.add(field);
		}
	}

}
