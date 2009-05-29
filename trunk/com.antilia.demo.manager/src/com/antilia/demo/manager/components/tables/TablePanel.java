/**
 * This software is provided as IS by Antilia-Soft SL.
 * Copyright 2008-2009.
 */
package com.antilia.demo.manager.components.tables;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.Model;

import com.antilia.demo.manager.components.beans.Person;
import com.antilia.web.beantable.Table;
import com.antilia.web.beantable.model.IColumnModel;
import com.antilia.web.beantable.model.TableModel;
import com.antilia.web.field.impl.AjaxCheckBox;

/**
 * @author Ernesto Reinaldo Barreiro (reiern70@gmail.com)
 *
 */
public class TablePanel extends Panel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Table<Person> table;	
	private TableModel<Person> tableModel;
	
	/**
	 * @param id
	 */
	public TablePanel(String id) {
		super(id);
		tableModel = new TableModel<Person>(Person.class, "id", "name", "lastName1") {
			
			private static final long serialVersionUID = 1L;

			@Override
			protected IColumnModel<Person> createColumnModel(String expresion) {
				IColumnModel<Person> columnModel =  super.createColumnModel(expresion);
				if(expresion.equals("id")) {
					columnModel.setWidth(80);				
				} 
				return columnModel;
			}
		};
		
		AjaxCheckBox draggableColoumns = new AjaxCheckBox("draggableColoumns", 
				new Model<Boolean>() {

					private static final long serialVersionUID = 1L;
					
					@Override
					public Boolean getObject() {
						return table.isDragableColumns();
					}
					
					@Override
					public void setObject(Boolean object) {
						table.setDragableColumns(object);
					}
			
					}) {
	
			private static final long serialVersionUID = 1L;
		
			@Override
			protected void onUpdate(AjaxRequestTarget target) {
				if(target != null) {
					target.addComponent(table.getUpdatableComponent());
				}
			}
		}; 
		
		add(draggableColoumns);
		
		AjaxCheckBox resizableColumns = new AjaxCheckBox("resizableColumns", 
				new Model<Boolean>() {

					private static final long serialVersionUID = 1L;
					
					@Override
					public Boolean getObject() {
						return table.isColumnsResizable();
					}
					
					@Override
					public void setObject(Boolean object) {
						table.setColumnsResizable(object);
					}
			
					}) {
	
			private static final long serialVersionUID = 1L;
		
			@Override
			protected void onUpdate(AjaxRequestTarget target) {
				if(target != null) {
					target.addComponent(table.getUpdatableComponent());
				}
			}
		}; 
		
		add(resizableColumns);
		
		AjaxCheckBox fresizableColumns = new AjaxCheckBox("fresizableColumns", 
				new Model<Boolean>() {

					private static final long serialVersionUID = 1L;
					
					@Override
					public Boolean getObject() {
						return table.isFirstColumnResizable();
					}
					
					@Override
					public void setObject(Boolean object) {
						table.setFirstColumnResizable(object);
					}
			
					}) {
	
			private static final long serialVersionUID = 1L;
		
			@Override
			protected void onUpdate(AjaxRequestTarget target) {
				if(target != null) {
					target.addComponent(table.getUpdatableComponent());
				}
			}
		}; 
		
		add(fresizableColumns);
		
		
		table = new Table<Person>("table",tableModel, Person.createPersons());
		table.setFirstColumnResizable(true);
		table.setColumnsResizable(true);
		table.setDragableColumns(true);		
		add(table);		
	}
	
}
