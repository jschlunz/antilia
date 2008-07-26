package com.antilia.web.grid;


/**
 * @author Ernesto Reinaldo Barreiro (reiern70@gmail.com)
 *
 */
public interface IGridPane {

	int getColumns();
	
	IGridPane setColumns(int columns);
	
	Iterable<WidgetModel> getWidgetModels();
		
}
