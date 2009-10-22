/**
 * 
 */
package com.antilia.web.field.impl;

import java.util.List;

import org.apache.wicket.Component;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.IAjaxCallDecorator;
import org.apache.wicket.ajax.form.OnChangeAjaxBehavior;
import org.apache.wicket.model.IModel;

import com.antilia.web.ajax.AntiliaAjaxCallDecorator;
import com.antilia.web.ajax.IDialogFinder;
import com.antilia.web.dialog.IDialogScope;
import com.antilia.web.dialog.IVeilScope;

/**
 * @author Ernesto Reinaldo Barreiro (reirn70@gmail.com)
 *
 */
public abstract class AjaxEnumDropDownChoice<T extends Enum<?>> extends EnumDropDownChoice<T> implements IDialogFinder {

	
	private static final long serialVersionUID = 1L;

	public AjaxEnumDropDownChoice(String id, Class<T> enumClass) {
		super(id, enumClass);
		init();
	}
	/**
	 * 
	 * @param id
	 * @param enumClass
	 * @param model
	 */
	public AjaxEnumDropDownChoice(String id, Class<T> enumClass, IModel<T> model) {
		super(id, enumClass, model);		
		init();
	}
	
	private void init() {
		add(new OnChangeAjaxBehavior() {
			private static final long serialVersionUID = 1L;

			@Override
			protected void onUpdate(AjaxRequestTarget target) {
				AjaxEnumDropDownChoice.this.onUpdate(target);
			}
			
			@Override
			protected IAjaxCallDecorator getAjaxCallDecorator() {
				return new AntiliaAjaxCallDecorator(AjaxEnumDropDownChoice.this);
			}
		});
	}
	
	public IDialogScope findParentDialog() {
		return findParent(IDialogScope.class);
	}
	
	public IVeilScope findVeilScope() {
		return findParent(IVeilScope.class);
	}
	
	public Component getDefiningComponent() {
		return this;
	}

	/**
	 * 
	 * @param id
	 * @param choices
	 * @param model
	 */
	public AjaxEnumDropDownChoice(String id, List<T> choices, IModel<T> model) {
		super(id, choices, model);
		init();
	}

	/**
	 * 
	 * @param target
	 */
	protected abstract void onUpdate(AjaxRequestTarget target);
	
}
