/**
 * 
 */
package com.antilia.web.field.impl;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.form.OnChangeAjaxBehavior;
import org.apache.wicket.model.IModel;

/**
 * @author Ernesto Reinaldo Barreiro (reirn70@gmail.com)
 *
 */
public abstract class AjaxBooleanDropDownChoice extends BooleanDropDownChoice {

	private static final long serialVersionUID = 1L;

	/**
	 * @param id
	 * @param model
	 */
	public AjaxBooleanDropDownChoice(String id, IModel<Boolean> model) {
		super(id, model);
		
		add(new OnChangeAjaxBehavior() {
			private static final long serialVersionUID = 1L;

			@Override
			protected void onUpdate(AjaxRequestTarget target) {
				AjaxBooleanDropDownChoice.this.onUpdate(target);
			}
		});
	}

	protected abstract void onUpdate(AjaxRequestTarget target);

}
