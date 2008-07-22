package com.antilia.web.grid;

/**
 * 
 * @author Ernesto Reinaldo Barreiro (reiern70@gmail.com)
 */
public interface IGridWidget {
		
		int getHeight();
		
		int getWidth();
		
		String getId();
		
		WidgetModel getWidgetModel();
		
		void setWidgetModel(WidgetModel widgetModel);

}
