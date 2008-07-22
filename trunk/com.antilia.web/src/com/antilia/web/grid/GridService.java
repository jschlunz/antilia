package com.antilia.web.grid;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Ernesto Reinaldo Barreiro (reiern70@gmail.com)
 *
 */
public class GridService implements IGridService {

	private static GridService instance = new GridService();
	
	private List<WidgetModel> models = new ArrayList<WidgetModel>();
	
	/**
	 * 
	 */
	private GridService() {
	}

	/* (non-Javadoc)
	 * @see com.isencia.sherpa.dashboard.osgi.IDashBoardService#deregisterWidgetModel(com.isencia.sherpa.dashboard.model.WidgetModel)
	 */
	public IGridService deregisterWidgetModel(WidgetModel model) {
		models.remove(model);
		return this;
	}

	/* (non-Javadoc)
	 * @see com.isencia.sherpa.dashboard.osgi.IDashBoardService#getWidgetModels()
	 */
	public Iterable<WidgetModel> getWidgetModels() {
		return models;
	}

	/* (non-Javadoc)
	 * @see com.isencia.sherpa.dashboard.osgi.IDashBoardService#registerWidgetModel(com.isencia.sherpa.dashboard.model.WidgetModel)
	 */
	public IGridService registerWidgetModel(WidgetModel model) {
		models.add(model);
		return this;
	}

	public static GridService getInstance() {
		return instance;
	}

}
