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
	
	private java.util.List<String> hiddenTableColumns = new ArrayList<String>();
	
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
	
	public CrudStyler<E> addEditFields(String... editFields) {
		if(editFields != null) {
			for(String field: editFields) {
				this.editFields.add(field);
			}
		}
		return this;
	}
	
	/**
	 * @param editFields the editFields to set
	 */
	public CrudStyler<E> removeEditFields(String[] editFields) {
		for(String field: editFields) {
			this.editFields.remove(field);
		}
		return this;
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
	public CrudStyler<E> addTableColumns(java.util.List<String> tableColumns) {
		if(tableColumns != null) {
			for(String field: tableColumns) {
				this.tableColumns.add(field);
			}
		}
		return this;
	}

	/**
	 * @param editFields the editFields to set
	 */
	public CrudStyler<E> addTableColumns(String...  tableColumns) {
		if(tableColumns != null) {
			for(String field: tableColumns) {
				this.tableColumns.add(field);
			}
		}
		return this;
	}
	
	/**
	 * @param editFields the editFields to set
	 */
	public CrudStyler<E> removeTableColumns(String[] tableColums) {
		for(String field: tableColums) {
			this.tableColumns.remove(field);
		}
		return this;
	}
	
	/**
	 * @param editFields the editFields to set
	 */
	public CrudStyler<E> addHiddenTableColumns(String...  tableColumns) {
		if(tableColumns != null) {
			for(String field: tableColumns) {
				this.hiddenTableColumns.add(field);
			}
		}
		return this;
	}
	
	/**
	 * @param editFields the editFields to set
	 */
	public CrudStyler<E> removeHiddenTableColumns(String[] tableColums) {
		for(String field: tableColums) {
			this.hiddenTableColumns.remove(field);
		}
		return this;
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
	public CrudStyler<E> addSearchFields(java.util.List<String> searchFields) {
		if(searchFields != null) {
			for(String field: searchFields) {
				this.searchFields.add(field);
			}
		}
		return this;
	}
	
	/**
	 * @param editFields the editFields to set
	 */
	public CrudStyler<E> addSearchFields(String... searchFields) {
		if(searchFields != null) {
			for(String field: searchFields) {
				this.searchFields.add(field);
			}
		}
		return this;
	}

	/**
	 * @param editFields the editFields to set
	 */
	public CrudStyler<E> removeSearchFields(String[] searchFields) {
		for(String field: searchFields) {
			this.searchFields.remove(field);
		}
		return this;
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

	public java.util.List<String> getHiddenTableColumns() {
		return hiddenTableColumns;
	}
}
