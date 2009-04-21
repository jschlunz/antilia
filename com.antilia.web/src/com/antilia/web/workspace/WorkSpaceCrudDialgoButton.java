/**
 * 
 */
package com.antilia.web.workspace;

import java.io.Serializable;

import com.antilia.web.dialog.DefaultDialog;

/**
 * @author Ernesto Reinaldo Barreiro (reirn70@gmail.com)
 *
 */
public class WorkSpaceCrudDialgoButton<T extends Serializable> extends WorkSpaceDialogButton {

	private static final long serialVersionUID = 1L;

	private Class<T> beanClass;
	
	/**
	 * @param id
	 */
	public WorkSpaceCrudDialgoButton(String id, Class<T> beanClass) {
		super(id);
		this.beanClass = beanClass;
	}

	@Override
	public DefaultDialog newDialog(String id) {
		return new WorkSpaceCrudDialog<T>(id, this, beanClass);
	}
	
	@Override
	protected String getLabel() {
		return beanClass.getSimpleName();
	}
	
	
	@Override
	protected String getTitleKey() {
		return beanClass.getSimpleName();
	}
	
	
	@Override
	protected String getLabelKey() {
		return beanClass.getSimpleName();
	}
}
