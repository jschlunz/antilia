/**
 * This software is provided as IS by Antilia-Soft SL.
 * Copyright 2006-2007.
 */
package com.antilia.web.beantable.model;

import java.io.Serializable;
import java.util.ArrayList;
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
public class TableModel<E extends Serializable> extends Model implements ITableModel<E> {

	private static final long serialVersionUID = 1L;

	private List<String> expresions;
	
	private Class<E> beanClass;
	
	private List<IColumnModel<E>> models;
	
	private SelectionMode selectionModel = SelectionMode.MULTIPLE;
	
	/**
	 * @param object
	 */
	@SuppressWarnings("unchecked")
	public TableModel() {
		super(new ArrayList<IColumnModel<E>>());	
		this.models = (List<IColumnModel<E>>) getObject();
		this.expresions = new ArrayList<String>();
	}
	
	/**
	 * 
	 * @param beanClass
	 * @param columnNames
	 */
	public TableModel(Class<E> beanClass, String... columnNames) {
		this();
		this.beanClass = beanClass;
		init(beanClass, columnNames);
	}
	

	private void init(Class<E> beanClass, String... expresions) {
		this.beanClass = beanClass;		
		this.models = new ArrayList<IColumnModel<E>>();
		if(expresions != null) {
			for(String expresion:expresions) {	
				createColumnModel(expresion);
				// add a dummy column model for drag columns
				//addColumnModel(new DummyColumnModel<E>(this));
			}
		}		
	}
	
	protected void addExpresion(String expresion) {
		expresions.add(expresion);
	}
	
	protected  void createColumnModel(String expresion) {
		addExpresion(expresion);
		IColumnModel<E> model = new ColumnModel<E>(this, ColumnModel.DEFAULT_WIDTH, 1, expresion);
		addColumnModel(model);
 	}
	
	public IComponentInheritedModel newModel(E object) {
		return new CompoundPropertyModel(object);
	}
	
	protected void addColumnModel(IColumnModel<E> model) {
		models.add(model);
	}
	
	public SelectionMode getSelectionModel() {
		return selectionModel;
	}

	public void setSelectionModel(SelectionMode selectionModel) {
		this.selectionModel = selectionModel;
	}

	public Iterator<IColumnModel<E>> getColumnModels() {
		return models.iterator();
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

}
