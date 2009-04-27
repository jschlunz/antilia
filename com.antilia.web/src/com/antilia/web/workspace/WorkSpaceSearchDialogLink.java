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
public class WorkSpaceSearchDialogLink<T extends Serializable> extends WorkSpaceDialogLink {

	private static final long serialVersionUID = 1L;

	private Class<T> beanClass;
	
	/**
	 * @param id
	 */
	public WorkSpaceSearchDialogLink(String id, Class<T> beanClass) {
		super(id);
		this.beanClass = beanClass;
	}

	@Override
	public DefaultDialog newDialog(String id) {
		DefaultDialog defaultDialog = new WorkSpaceSearchDialog<T>(id, this, beanClass);
		configureDialog(defaultDialog);
		return defaultDialog;
	}
	
	@Override
	protected String getLabel() {
		return beanClass.getSimpleName() + ".crudDialog.label";
	}
	
	
	@Override
	protected String getTitleKey() {
		return beanClass.getSimpleName()+".crudDialog.title";
	}
	
	
	@Override
	protected String getLabelKey() {
		return beanClass.getSimpleName() + ".crudDialog.label";
	}
}
