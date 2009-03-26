/**
 * This software is provided as IS by Antilia-Soft SL.
 * Copyright 2006-2007.
 */
package com.antilia.web.button;

import org.apache.wicket.Component;

/**
 * 
 *
 * @author Ernesto Reinaldo Barreiro (reiern70@gmail.com)
 *
 */
public abstract class AbstractAction implements IAction {

	private static final long serialVersionUID = 1L;
	
	private Component trigger;
	
	public AbstractAction(Component trigger) {
		this.trigger = trigger;
	}
	
	/* (non-Javadoc)
	 * @see com.antilia.web.button.IAJAXAction#getTrigger()
	 */
	public Component getTrigger() {
		return trigger;
	}
	
	
	public void setTrigger(Component component) {
		this.trigger = component;
	}
}
