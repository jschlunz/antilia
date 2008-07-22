package com.antilia.web.grid;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.wicket.Component;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.markup.repeater.Item;
import org.apache.wicket.markup.repeater.RefreshingView;
import org.apache.wicket.markup.repeater.RepeatingView;

import com.antilia.web.dragdrop.DraggableBehavior;


/**
 * @author Ernesto Reinaldo Barreiro (reiern70@gmail.com)
 */
public class GridPane extends Panel implements IGridPane {

	private static class DashBoardRows extends RefreshingView {

		private static final long serialVersionUID = 1L;
		
		private GridPane dashBoardPane;
		
		public DashBoardRows(GridPane dashBoardPane, String id) {
			super(id);			
			this.dashBoardPane  = dashBoardPane;
			dashBoardPane.addOrReplace(this);
		}

		@Override
		protected Iterator<?> getItemModels() {
			int columns = getColumns();
			int size = getModels().size();
			int rows = (size/columns) + ((size%columns==0)?0:1);  
			List<GridPaneRowModel> models = new ArrayList<GridPaneRowModel>();
			for (int row=0; row < rows;row++) {							
				int start = row*columns;
				int end = ((row+1)*columns);						
				GridPaneRowModel model = new GridPaneRowModel();				
				model.setRow(row);
				for(int i =start; i < end; i++) {
					WidgetModel widgetModel = null;
					if(i<size) {
						widgetModel = DashBoardRows.this.getModels().get(i);
					} else {
						widgetModel = new WidgetModel.DummyWidgetModel(10,10);						
					}
					widgetModel.setCol(i-start);
					model.add(widgetModel);
				}
				models.add(model);
			}
			return models.iterator();
		}

		@SuppressWarnings("unchecked")
		@Override
		protected void populateItem(Item item) {
			GridPaneRowModel dashboardPaneRowModel = (GridPaneRowModel)item.getModel();
			RepeatingView cells = new RepeatingView("cells");
			for(WidgetModel widgetModel: dashboardPaneRowModel.getWidgetModels()) {				  
				String id = cells.newChildId();
				GridCell target = new GridCell(id, dashboardPaneRowModel.getRow(), widgetModel.getCol(), widgetModel) {
					private static final long serialVersionUID = 1L;

					@Override
					public void onDrop(String input, AjaxRequestTarget target) {
						if(target != null) {
							target.addComponent(dashBoardPane);
						}
					}
				};
				cells.add(target);
				Component component = widgetModel.newDashBoardWidget("cell"); 
				if(component instanceof IGridWidget) {
					component.add(new DraggableBehavior() {
						
						private static final long serialVersionUID = 1L;

						@Override
						public String getDraggableClassName() {
							return "dashboardPane";
						}
					});
				}
				target.add(component);				
			}
			item.add(cells);
		}

		public int getColumns() {
			return dashBoardPane.getColumns();
		}

		public List<WidgetModel> getModels() {
			return dashBoardPane.getWidgetModels();
		}
		
	}
	
	private static final long serialVersionUID = 1L;

	private int columns;
	
	List<WidgetModel> widgetModels = new ArrayList<WidgetModel>();
		
	private int height;
	
	private int width;
	
	/**
	 * @param parent
	 * @param id
	 */
	public GridPane(String id, int columns, List<WidgetModel> widgetModels) {
		super(id);
		this.columns = columns;
		this.widgetModels = widgetModels;		
		setOutputMarkupId(true);
	}

	public WidgetModel getWidgetModel(int row, int col) {
		return this.widgetModels.get(row);
	}
	/**
	 * @param parent
	 * @param id
	 */
	public GridPane(String id, List<WidgetModel> widgetModels) {
		this(id, 3, widgetModels);
	}
	
	@Override
	protected void onBeforeRender() {
		new DashBoardRows(this, "rows");
		super.onBeforeRender();
	}
	
	public int getColumns() {
		return columns;
	}

	public List<WidgetModel> getWidgetModels() {
		return widgetModels;
	}

	public IGridPane setColumns(int columns) {
		this.columns=columns;
		return this;
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

}
