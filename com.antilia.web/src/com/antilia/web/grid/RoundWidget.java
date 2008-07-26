package com.antilia.web.grid;

import com.antilia.web.roundpane.RoundPane;
import com.antilia.web.roundpane.RoundPaneStyle;



/**
 * @author Ernesto Reinaldo Barreiro (reiern70@gmail.com)
 *
 */
public class RoundWidget extends RoundPane implements IGridWidget {

	private static final long serialVersionUID = 1L;

	private WidgetModel widgetModel;
	
	/**
	 * @param id
	 * @param title
	 * @param boxStyle
	 */
	public RoundWidget(String id, String title, WidgetModel widgetModel, RoundPaneStyle boxStyle) {
		super(id, title, boxStyle);
		this.widgetModel = widgetModel;
	}

	public WidgetModel getWidgetModel() {
		return widgetModel;
	}

	public void setWidgetModel(WidgetModel widgetModel) {
		this.widgetModel = widgetModel;
	}

}
