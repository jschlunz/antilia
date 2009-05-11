/**
 * This software is provided as IS by Antilia-Soft SL.
 * Copyright 2006-2007.
 */
package com.antilia.web.beantable.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.model.IComponentInheritedModel;
import org.apache.wicket.model.Model;

import com.antilia.web.beantable.provider.SelectionMode;

/**
 * 
 * @author Ernesto Reinaldo Barreiro (reiern70@gmail.com)
 */
public class TableModel<E extends Serializable> extends Model<ArrayList<IColumnModel<E>>> implements ITableModel<E> {

	private static final long serialVersionUID = 1L;

	private Class<E> beanClass;
	
	private List<IColumnModel<E>> models;
	
	private List<IColumnModel<E>> hiddenModels;
		
	private SelectionMode selectionMode = SelectionMode.MULTIPLE;
	
	/**
	 * @param object
	 */
	public TableModel() {
		super(new ArrayList<IColumnModel<E>>());	
		this.models = (List<IColumnModel<E>>) getObject();
		this.hiddenModels = new ArrayList<IColumnModel<E>>();
	}
	
	public TableModel(Class<E> beanClass, String[] columnNames, String[] hiddenNames) {
		this();
		this.beanClass = beanClass;
		init(beanClass, Arrays.asList(columnNames), Arrays.asList(hiddenNames));
	}
	
	public TableModel(Class<E> beanClass, Iterable<String> columnNames, Iterable<String> hiddenNames) {
		this();
		this.beanClass = beanClass;
		init(beanClass, columnNames, hiddenNames);
	}
	
	public TableModel(Class<E> beanClass, List<String> columnNames, List<String> hiddenNames) {
		this();
		this.beanClass = beanClass;
		init(beanClass, columnNames, hiddenNames);
	}
	
	/**
	 * 
	 * @param beanClass
	 * @param columnNames
	 */
	public TableModel(Class<E> beanClass, String... columnNames) {
		this();
		this.beanClass = beanClass;
		init(beanClass, Arrays.asList(columnNames), null);
	}
	

	private void init(Class<E> beanClass, Iterable<String> expresions, Iterable<String> hidden) {
		this.beanClass = beanClass;		
		this.models = new ArrayList<IColumnModel<E>>();
		if(expresions != null) {
			for(String expresion:expresions) {	
				createColumnModel(expresion);
			}
		}		
		
		if(hidden != null) {
			for(String expresion: hidden) {	
				createHiddenColumnModel(expresion);
			}
		}
	}
	
		
	protected  void createColumnModel(String expresion) {
		IColumnModel<E> model = new ColumnModel<E>(this, ColumnModel.DEFAULT_WIDTH, 1, expresion);
		addColumnModel(model);
 	}

	protected  void createHiddenColumnModel(String expresion) {
		IColumnModel<E> model = new ColumnModel<E>(this, ColumnModel.DEFAULT_WIDTH, 1, expresion);
		addHiddenColumnModel(model);
 	}
	
	public IComponentInheritedModel<E> newModel(E object) {
		return new CompoundPropertyModel<E>(object);
	}
	
	protected void addColumnModel(IColumnModel<E> model) {
		models.add(model);
	}
	
	protected void addHiddenColumnModel(IColumnModel<E> model) {
		hiddenModels.add(model);
	}
	
	public SelectionMode getSelectionMode() {
		return selectionMode;
	}

	public void setSelectionMode(SelectionMode selectionMode) {
		this.selectionMode = selectionMode;
	}

	public Iterator<IColumnModel<E>> getColumnModels() {
		return models.iterator();
	}
	
	public IColumnModel<E> getColumnModel(int index) {
		return models.get(index);
	}
	
	public Iterator<IColumnModel<E>> getHiddenModels() {
		return hiddenModels.iterator();
	}
	
	public boolean hideColumn(int i) {
		if(i>=0 && i < models.size()) {
			IColumnModel<E> model = models.remove(i);
			hiddenModels.add(model);
			return true;
		}
		return false;
	}
	
	public boolean swapColumns(int i, int j) {
		if(i==j) {
			return false;
		}
		if(i>=0 && i< models.size())	{
			IColumnModel<E> tempi = models.get(i);
			if(j>=0 && j< models.size())	{
				IColumnModel<E> tempj = models.get(j);
				models.set(i, tempj);
				models.set(j, tempi);
			}
		}
		return false;
	}
	
	public boolean moveColumnBefore(int toMove, int before) {		
		if(toMove==before || toMove==before-1 ) {
			return false;		
		}
		if(toMove>=0 && toMove< models.size())	{
			if(before>=0 && before< models.size())	{
				int pos = before;
				if(toMove < before) 
					pos = before -1;
				IColumnModel<E> tempi = models.remove(toMove);			
				models.add(before>0?pos:0, tempi);
			} else {
				IColumnModel<E> tempi = models.remove(toMove);			
				models.add(tempi);			
			}
			return true;
		}
		return false;
	}

	public void setColumnModels(List<IColumnModel<E>> models) {
		this.models = models;
	}
	
	public int getColumns() {
		return models.size();
	}
	

	public Class<E> getBeanClass() {
		return beanClass;
	}

	/**
	 * @param beanClass the beanClass to set
	 */
	public void setBeanClass(Class<E> beanClass) {
		this.beanClass = beanClass;
	}

	/**
	 * @param hiddenModels the hiddenModels to set
	 */
	public void setHiddenModels(List<IColumnModel<E>> hiddenModels) {
		this.hiddenModels = hiddenModels;
	}

}
