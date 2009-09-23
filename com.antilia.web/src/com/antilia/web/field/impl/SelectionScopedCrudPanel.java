/**
 * 
 */
package com.antilia.web.field.impl;

import java.io.Serializable;

import org.apache.wicket.Component;

import com.antilia.web.field.BeanProxy;
import com.antilia.web.field.IFieldModel;
import com.antilia.web.field.IFieldPanel;
import com.antilia.web.layout.ScopedPanel;

/**
 * @author  Ernesto Reinaldo Barreiro (reiern70@gmail.com)
 *
 */
public class SelectionScopedCrudPanel<B extends Serializable> extends ScopedPanel {

	private static final long serialVersionUID = 1L;

	private Class<B> beanClass;
	private LargeSelectionDialog<B> selectionDialog;
	private BeanProxy<B> beanProxy; 
	private IFieldModel<B> fieldModel;
	private IFieldPanel fieldPanel;
	
	
	/**
	 * @param id
	 */
	public SelectionScopedCrudPanel(String id,  Class<B> beanClass, LargeSelectionDialog<B> selectionDialog, BeanProxy<B> beanProxy, IFieldModel<B> fieldModel, IFieldPanel fieldPanel) {
		super(id);	
		this.beanClass = beanClass;
		this.beanProxy = beanProxy;
		this.fieldModel = fieldModel;
		this.fieldPanel= fieldPanel;
		this.selectionDialog = selectionDialog;
	}

	
	@Override
	protected Component createBody(String id) {
		return new DialogSelectionCRUDPanel<B>(id, beanClass, selectionDialog, this.beanProxy, this.fieldModel, fieldPanel);
	}

}
