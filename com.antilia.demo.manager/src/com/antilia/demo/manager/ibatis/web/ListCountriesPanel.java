/**
 * 
 */
package com.antilia.demo.manager.ibatis.web;

import org.apache.wicket.Component;
import org.apache.wicket.spring.injection.annot.SpringBean;

import com.antilia.demo.manager.ibatis.IBCountriesDao;
import com.antilia.demo.manager.ibatis.IBCountry;
import com.antilia.web.button.IMenuItemHolder;
import com.antilia.web.layout.BackToHomeTopMenuPanel;



/**
 * @author Ernesto Reinaldo Barreiro (reirn70@gmail.com)
 *
 */
public class ListCountriesPanel extends BackToHomeTopMenuPanel<IBCountry> {

	private static final long serialVersionUID = 1L;

	@SpringBean
	private IBCountriesDao dao;
		
	public ListCountriesPanel(String id) {
		super(id);
	}
	
	public void populateMenuItems(String menuId, IMenuItemHolder itemHolder) {
		super.populateMenuItems(menuId, itemHolder);
	}	
	
	@Override
	protected Component createBody(String id) {
		return new ListBeanPanel<IBCountry>(id, dao, IBCountry.class, "codigo","nif", "direccion","poblacion", "consultorSenior");
	}
	
}
