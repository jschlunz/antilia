/**
 * 
 */
package com.antilia.web.workspace;

import java.io.Serializable;

import org.apache.wicket.Component;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.ResourceModel;

import com.antilia.web.crud.CRUDPanel;
import com.antilia.web.dialog.DialogStyle;
import com.antilia.web.dialog.IDialogLink;
import com.antilia.web.layout.ScopedCrudPanel;

/**
 * @author Ernesto Reinaldo Barreiro (reirn70@gmail.com)
 *
 */
public class WorkSpaceCrudDialog<T extends Serializable> extends WorkSpaceDialog {

	private static final long serialVersionUID = 1L;

	private Class<T> beanClass;
	/**
	 * @param id
	 * @param button
	 */
	public WorkSpaceCrudDialog(String id, IDialogLink button, Class<T> beanClass) {
		super(id, button);
		this.beanClass = beanClass;
		setWidth(700);
		setHeight(500);
		setCentered(true);
		getDialogStyle().setRoundedHeader(true);
	}

	/**
	 * @param id
	 * @param button
	 * @param dialogStyle
	 */
	public WorkSpaceCrudDialog(String id, IDialogLink button, DialogStyle dialogStyle, Class<T> beanClass) {
		super(id, button, dialogStyle);
		this.beanClass = beanClass;
		setWidth(700);
		setCentered(true);
		setHeight(500);		
		getDialogStyle().setRoundedHeader(true);
	}

	/* (non-Javadoc)
	 * @see com.antilia.web.dialog.DefaultDialog#createBody(java.lang.String)
	 */
	@Override
	protected Component createBody(String id) {
		//return new CrudDialogPanel<T>(id, beanClass);
		ScopedCrudPanel<T> scopedCrudPanel = new ScopedCrudPanel<T>(id, beanClass) {
			
			private static final long serialVersionUID = 1L;

			@Override
			protected CRUDPanel<T> createCrudPanel(String id) {
				return new CRUDPanel<T>(id, beanClass);
			}
		};
		return scopedCrudPanel;
	}
	
	@Override
	public IModel<String> getTitle() {
		String key = beanClass.getSimpleName()+".crudTitle";
		return new ResourceModel(key, key);
	}

}
