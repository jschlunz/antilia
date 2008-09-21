/**
 * This software is provided as IS by Antilia-Soft SL.
 * Copyright 2006-2007.
 */
package com.antilia.web.button;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.IAjaxCallDecorator;
import org.apache.wicket.markup.html.form.Form;

import com.antilia.common.osgi.Aggregator;
import com.antilia.common.osgi.IAggregator;

/**
 * An abstract button that is a container of actions.
 *
 * @author Ernesto Reinaldo Barreiro (reiern70@gmail.com)
 *
 */
public abstract class AbstractActionSetButton extends AbstractButton implements IActionsGroup {

	private IAggregator<IAction> actions = new Aggregator<IAction>();
	
	public AbstractActionSetButton(String id) {
		super(id);
	}
	
	
	/**
	 * Constructor.
	 * @param id
	 */
	public AbstractActionSetButton(String id, int order) {
		super(id, order);
	}
	
	/**
	 * Constructor.
	 * @param id
	 */
	public AbstractActionSetButton(String id, boolean ajaxButton) {
		super(id, ajaxButton);
	}

	/**
	 * Constructor.
	 * @param id
	 */
	public AbstractActionSetButton(String id, boolean ajaxButton, int order) {
		super(id, ajaxButton, order);
	}

	@Override
	protected void onAfterRender() {
		for(IAction action: elements()) {
			action.setTrigger(this);
		}		
		super.onAfterRender();
	}
	
	@Override
	public IAggregator<IAction> add(IAction a) {
		actions.add(a);
		return this;
	}

	@Override
	public Iterable<IAction> elements() {
		return actions.elements();
	}

	@Override
	public IAggregator<IAction> delete(IAction a) {
		actions.delete(a);
		return this;
	}

	public IAggregator<IAction> deleteAll() {
		actions.deleteAll();
		return this;
	};
	
	@Override
	protected void onSubmit(AjaxRequestTarget target, Form<?> form) {
		for(IAction action: elements()) {
			action.onSubmit(target, form);
		}
	}
	
	@Override
	public void onSubmit() {
		for(IAction action: elements()) {
			action.onSubmit();
		}
	}
	
	@Override
	protected IAjaxCallDecorator getAjaxCallDecorator() {
		// TODO: should we have a kind of aggregated decorator? instead of returning the first non null?
		for(IAction action: elements()) {
			IAjaxCallDecorator decorator = action.getAjaxCallDecorator();
			if(decorator != null)
				return decorator;
		}
		return null;
	}
	
}
