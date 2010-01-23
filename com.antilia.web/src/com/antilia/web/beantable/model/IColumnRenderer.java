package com.antilia.web.beantable.model;

import java.io.Serializable;

import org.apache.wicket.Component;

/**
 * @author Ernesto Reinaldo Barreiro (reiern70@gmail.com)
 *
 */
public interface IColumnRenderer<E extends Serializable> extends Serializable {

	/**
	 * The property path of the component.
	 * 
	 * @return
	 */
	String getPropertyPath();
	
	/**
	 * 
	 * @param rowModel
	 * @return
	 */
	Component newTableCell(String id, IColumnModel<E> columnModel, E object);
}
