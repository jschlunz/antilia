/**
 * 
 */
package com.antilia.ibatis;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.antilia.common.util.AnnotationUtils;
import com.antilia.common.util.StringUtils;

/**
 * @author Ernesto Reinaldo Barreiro (reirn70@gmail.com)
 *
 */
public class IBatisQuery<B extends Serializable> implements Serializable {
	
	
	public static enum Order {
		ASC,
		DESC;
		
		public Order revert() {
			return (this.equals(ASC))?DESC:ASC;
		}
	}
	
	public static class SortInfo implements Serializable {
		private static final long serialVersionUID = 1L;
		
		private String columnName;		
		private Order order;
		
		public SortInfo(String columnName, Order order) {
			this.columnName = columnName;
			this.order = order;
		}
		
		public SortInfo revert() {
			return new SortInfo(getColumnName(), getOrder().revert());
		}
		
		@Override
		public String toString() {
			return  getColumnName() + " " + getOrder().name();
		}

		/**
		 * @return the columnName
		 */
		public String getColumnName() {
			return columnName;
		}

		/**
		 * @param columnName the columnName to set
		 */
		public void setColumnName(String columnName) {
			this.columnName = columnName;
		}

		/**
		 * @return the order
		 */
		public Order getOrder() {
			return order;
		}

		/**
		 * @param order the order to set
		 */
		public void setOrder(Order order) {
			this.order = order;
		}
	}

	private class ColumnInfo implements Serializable {
		private static final long serialVersionUID = 1L;
		
		/**
		 * The name of the column on the DATABASE table.
		 */
		private String columnName;

		public ColumnInfo(String columnName) {			
			this.columnName = columnName;
		}

		/**
		 * @return the columnName
		 */
		public String getColumnName() {
			return columnName;
		}

		/**
		 * @param columnName the columnName to set
		 */
		public void setColumnName(String columnName) {
			this.columnName = columnName;
		}				
	}
	
	private static final long serialVersionUID = 1L;
	
	private int firstResult;
	
	private int maxResults;
	
	private int totalSize;
		
	private String whereClause = null;
	
	private Class<B> beanClass;
	
	private List<SortInfo> sortInfo = new ArrayList<SortInfo>();
	
	private SortInfo defaultSort;
	
	private String tableName;
	
	private Map<String, ColumnInfo> tableColumns = new HashMap<String, ColumnInfo>();
	
	private IBatisDialect dialect;
	
	public String getListQuery() {
		return dialect.buildListQuery(this);
	}
	
	public String getCountQuery() {
		return dialect.buildCountQuery(this);
	}
	
	public IBatisQuery(Class<B> beanClass) {		
		this(beanClass, 0, 10);
	}
	
	/**
	 * 
	 */
	public IBatisQuery(Class<B> beanClass, int firstResult, int maxResults) {		
		this.beanClass = beanClass;
		this.firstResult = firstResult;
		this.maxResults = maxResults;		
		init();
	}
	
	private void init() {
		Table table = this.beanClass.getAnnotation(Table.class);
		if(table == null) {
			throw new IllegalArgumentException("Table name should not be null!");
		}
		this.tableName = table.name();
		Field[] fields =  AnnotationUtils.findAnnotatedFields(beanClass, Column.class);
		for(Field field: fields) {
			Column column = field.getAnnotation(Column.class);		
			if(StringUtils.isEmpty(column.name()))
				throw new IllegalArgumentException("Column name should not be null!");
			ColumnInfo info = new ColumnInfo(column.name());
			tableColumns.put(field.getName(),info);
			if(column.defaultorder() == true) {
				defaultSort = new SortInfo(column.name(), Order.ASC);
			}
		}
		updateTotal();
	}

	public String getSort(String aliasName) {		
		return getPrivateSort(false, aliasName);
	}
	
	public void addSort(String propertyName, Order order) {
		ColumnInfo info = tableColumns.get(propertyName);
		sortInfo.add(new SortInfo(info.getColumnName(), order));
	}
	
	public String getReverseSort(String aliasName) {		
		return getPrivateSort(true, aliasName);
	}
	
	public String getPrivateSort(boolean revert, String aliasName) {
		if(sortInfo.size() > 0) {
			StringBuffer sb = new StringBuffer();
			Iterator<SortInfo> it = sortInfo.iterator();
			while(it.hasNext()) {
				SortInfo si = it.next();
				if(revert)
					si = si.revert();
				if(!StringUtils.isEmpty(aliasName)) {
					sb.append(aliasName);
					sb.append(".");
				}
				sb.append(si.toString());				
				if(it.hasNext())
					sb.append(", ");
			}
			return sb.toString();
		} else if(defaultSort != null) {
			StringBuffer sb = new StringBuffer();
			if(!StringUtils.isEmpty(aliasName)) {
				sb.append(aliasName);
				sb.append(".");
			}
			if(revert) {				
				sb.append(defaultSort.revert().toString());
			} else 
				sb.append(defaultSort.toString());
			return sb.toString();
		}
		return "";
	}
	
	private void updateTotal() {
		this.totalSize = firstResult + maxResults;
	}


	/**
	 * @return the end
	 */
	public int getMaxResults() {
		return maxResults;
	}

	/**
	 * @return the totalSize
	 */
	public int getTotalSize() {
		return totalSize;
	}



	/**
	 * @return the whereClause
	 */
	public String getWhereClause() {
		if(whereClause == null)
			return "";
		return whereClause;
	}

	/**
	 * @param whereClause the whereClause to set
	 */
	public void setWhereClause(String whereClause) {
		this.whereClause = whereClause;
	}

	/**
	 * @param size the size to set
	 */
	public void setMaxResults(int size) {
		this.maxResults = size;
		updateTotal();
	}

	/**
	 * @return the firstResult
	 */
	public int getFirstResult() {
		return firstResult;
	}

	/**
	 * @param firstResult the firstResult to set
	 */
	public void setFirstResult(int firstResult) {
		this.firstResult = firstResult;
		updateTotal();
	}

	/**
	 * @return the tableName
	 */
	public String getTableName() {
		return tableName;
	}

	/**
	 * @param tableName the tableName to set
	 */
	public void setTableName(String tableName) {
		this.tableName = tableName;
	}

	/**
	 * @return the dialect
	 */
	public IBatisDialect getDialect() {
		return dialect;
	}

	/**
	 * @param dialect the dialect to set
	 */
	public void setDialect(IBatisDialect dialect) {
		this.dialect = dialect;
	}

	/**
	 * @return the beanClass
	 */
	public Class<B> getBeanClass() {
		return beanClass;
	}

}
