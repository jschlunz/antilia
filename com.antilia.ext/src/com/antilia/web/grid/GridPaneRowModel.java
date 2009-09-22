package com.antilia.web.grid;

import java.util.ArrayList;
import java.util.List;

import org.apache.wicket.model.Model;

/**
 * @author Ernesto Reinaldo Barreiro (reiern70@gmail.com)
 */
public class GridPaneRowModel extends Model<String> {

	private static final long serialVersionUID = 1L;

	List<WidgetModel> models = new ArrayList<WidgetModel>();

	private int row;
	
	/**
	 * @param object
	 */
	public GridPaneRowModel() {
		super();
	}
	
	public void add(WidgetModel model) {
		models.add(model);
	}
	
	public Iterable<WidgetModel> getWidgetModels() {
		return models;
	}

	public int getRow() {
		return row;
	}

	public void setRow(int row) {
		this.row = row;
	}
}
