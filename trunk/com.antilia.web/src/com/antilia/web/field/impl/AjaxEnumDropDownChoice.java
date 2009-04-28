/**
 * 
 */
package com.antilia.web.field.impl;

import java.util.List;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.form.OnChangeAjaxBehavior;
import org.apache.wicket.model.IModel;

/**
 * @author Ernesto Reinaldo Barreiro (reirn70@gmail.com)
 *
 */
public abstract class AjaxEnumDropDownChoice<T extends Enum<?>> extends EnumDropDownChoice<T> {

	
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 * @param id
	 * @param enumClass
	 * @param model
	 */
	public AjaxEnumDropDownChoice(String id, Class<T> enumClass, IModel<T> model) {
		super(id, enumClass, model);
		add(new OnChangeAjaxBehavior() {
			private static final long serialVersionUID = 1L;

			@Override
			protected void onUpdate(AjaxRequestTarget target) {

			}
		});
	}

	/**
	 * 
	 * @param id
	 * @param choices
	 * @param model
	 */
	public AjaxEnumDropDownChoice(String id, List<T> choices, IModel<T> model) {
		super(id, choices, model);
		
		add(new OnChangeAjaxBehavior() {
			private static final long serialVersionUID = 1L;

			@Override
			protected void onUpdate(AjaxRequestTarget target) {
				AjaxEnumDropDownChoice.this.onUpdate(target);
			}
		});
	}

	/**
	 * 
	 * @param target
	 */
	protected abstract void onUpdate(AjaxRequestTarget target);
	
}
