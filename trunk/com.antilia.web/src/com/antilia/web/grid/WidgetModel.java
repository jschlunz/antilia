package com.antilia.web.grid;

import org.apache.wicket.Component;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.model.Model;

/**
 * @author Ernesto Reinaldo Barreiro (reiern70@gmail.com)
 */
public abstract class WidgetModel extends Model {

	private static final long serialVersionUID = 1L;

	private int height;	
	
	private int width;
	
	private int col;
	
	private int row;
	
	private class GridLabel extends Label implements IGridWidget {
		
		private static final long serialVersionUID = 1L;	
		
		private WidgetModel widgetModel;
		
		public GridLabel(String id, WidgetModel widgetModel) {
			super(id, "&nbsp;");
			this.widgetModel = widgetModel;
			setEscapeModelStrings(false);
		}
		
		public int getHeight() {
			return widgetModel.getHeight();
		}
		
		public int getWidth() {
			return widgetModel.getWidth();
		}

		public WidgetModel getWidgetModel() {
			return widgetModel;
		}

		public void setWidgetModel(WidgetModel widgetModel) {
			this.widgetModel = widgetModel;
		}
	}
	
	public static class DummyWidgetModel extends WidgetModel {
		
		private static final long serialVersionUID = 1L;

		public DummyWidgetModel(int width, int height) {
			super(width, height);
		}

		@Override
		public Component newDashBoardWidget(String id) {
			GridLabel label = new GridLabel(id, this);
			return label;
		}
	}

	/**
	 * 
	 */
	public WidgetModel(int width, int height) {
		super();
	}

	public int getHeight() {
		return height;
	}
	
	public int getWidth() {
		return width;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public void setWidth(int width) {
		this.width = width;
	}
	
	public abstract Component newDashBoardWidget(String id);

	public int getCol() {
		return col;
	}

	public void setCol(int col) {
		this.col = col;
	}

	public int getRow() {
		return row;
	}

	public void setRow(int row) {
		this.row = row;
	}
	
}
