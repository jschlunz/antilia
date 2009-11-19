/**
 * This software is provided as IS by Antilia-Soft SL.
 * Copyright 2006-2007.
 */
package com.antilia.web.beantable.navigation;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.wicket.markup.html.form.IChoiceRenderer;
import org.apache.wicket.model.Model;

import com.antilia.web.beantable.Table;
import com.antilia.web.beantable.model.IColumnModel;
import com.antilia.web.beantable.model.ITableModel;
import com.antilia.web.palette.AntiliaBasePalette;

/**
 * 
 *
 * @author Ernesto Reinaldo Barreiro (reiern70@gmail.com)
 *
 */
public class ColumnModelPalette<E extends Serializable> extends AntiliaBasePalette<IColumnModel<E>> {

	private static final long serialVersionUID = 1L;

	private abstract static class ColumnModelChoiceRenderer<E extends Serializable> implements IChoiceRenderer<IColumnModel<E>> {
		
		private static final long serialVersionUID = 1L;
		
		public String getIdValue(IColumnModel<E> object, int index) {
			return object.getPropertyPath();
		}		
		
	}
	
	private static class AvailableList<E extends Serializable> extends ArrayList<IColumnModel<E>> {
		
		private static final long serialVersionUID = 1L;

		public AvailableList(ITableModel<E> tableModel) {
			super();
			Iterator<IColumnModel<E>> it = tableModel.getColumnModels();
			while (it.hasNext()) {
				IColumnModel<E> columnModel = it.next();
				add(columnModel);
			}
			it = tableModel.getHiddenModels();
			while (it.hasNext()) {
				IColumnModel<E> columnModel = it.next();
				add(columnModel);
			}
		}
	}
	
	private static class SelectedList<E extends Serializable> extends ArrayList<IColumnModel<E>> {
		
		private static final long serialVersionUID = 1L;

		public SelectedList(ITableModel<E> tableModel) {
			super();
			Iterator<IColumnModel<E>> it = tableModel.getColumnModels();
			while (it.hasNext()) {
				IColumnModel<E> columnModel = it.next();
				add(columnModel);
			}
		}
	}

	//private Table<E> table;
	/**
	 * @param id
	 * @param selected
	 * @param available
	 * @param renderer
	 */
	@SuppressWarnings("unchecked")
	public ColumnModelPalette(String id, final Table<E> table) {
		super(id, 
				new Model(new SelectedList<E>(table.getTableModel())),  
				new Model(new AvailableList<E>(table.getTableModel())), 
				new ColumnModelChoiceRenderer<E>() {
			
					private static final long serialVersionUID = 1L;

					public Object getDisplayValue(IColumnModel<E> object) {
						
						return object.getTitleModel(table).getObject();
					}
				
				}, 10, true);
		//this.table = table;
	}
	
	public List<IColumnModel<E>> getSelected() {
		Iterator<IColumnModel<E>> it  = getSelectedChoices();
		List<IColumnModel<E>> list = new ArrayList<IColumnModel<E>>();
		while(it .hasNext()) {
			list.add(it.next());
		}
		return list;
	}
	
	
	public List<IColumnModel<E>> getAvailable() {
		Iterator<IColumnModel<E>> it  = getUnselectedChoices();
		List<IColumnModel<E>> list = new ArrayList<IColumnModel<E>>();
		while(it .hasNext()) {
			list.add(it.next());
		}
		return list;
	}
}
