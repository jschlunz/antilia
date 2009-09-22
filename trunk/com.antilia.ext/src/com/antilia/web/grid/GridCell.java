/**
 * This software is provided as IS by Antilia-Soft SL.
 * Copyright 2006-2007.
 */
package com.antilia.web.grid;

import com.antilia.web.scriptaculous.drag.DraggableTarget;

/**
 * @author Ernesto Reinaldo Barreiro (reiern70@gmail.com)
 *
 */
public abstract class GridCell extends DraggableTarget {

	private static final long serialVersionUID = 1L;
	
	int row;
	
	int col;	
	
	private WidgetModel widgetModel;
	
	public GridCell(String id, int row, int col, WidgetModel widgetModel) {		
		super(id); 	
		this.row = row;
		this.col = col;
		this.widgetModel = widgetModel;
	}
	
	

	public int getRow() {
		return row;
	}

	public void setRow(int row) {
		this.row = row;
	}

	public int getCol() {
		return col;
	}

	public void setCol(int col) {
		this.col = col;
	}



	public WidgetModel getWidgetModel() {
		return widgetModel;
	}



	public void setWidgetModel(WidgetModel widgetModel) {
		this.widgetModel = widgetModel;
	}
}
