package com.antilia.demo.manager.ibatis.web;

import org.apache.wicket.Component;

import com.antilia.demo.manager.Index;
import com.antilia.web.layout.ScopedPanel;
import com.antilia.web.toolbar.Toolbar;

/**
 * 
 *
 * @author Ernesto Reinaldo Barreiro (reiern70@gmail.com)
 *
 */
public class InitialPanelIBatis extends ScopedPanel {

	private static final long serialVersionUID = 1L;

	private Index page;
	
	/**
	 * @param id
	 */
	public InitialPanelIBatis(String id, Index page) {
		super(id);
		this.page = page;
	}
	
	@Override
	protected Component createBody(String id) {
		return 	Toolbar.createToolbar(id, new MainMenuFactoryIBatis(page, getId()));
	}
}
