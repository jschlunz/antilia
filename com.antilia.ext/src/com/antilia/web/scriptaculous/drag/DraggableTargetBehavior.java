/**
 * This software is provided as IS by Antilia-Soft SL.
 * Copyright 2006-2007.
 */
package com.antilia.web.scriptaculous.drag;

import java.util.HashMap;
import java.util.Map;

import org.apache.wicket.Component;
import org.apache.wicket.ajax.AjaxRequestTarget;

import com.antilia.common.FrameworkException;
import com.antilia.web.dragdrop.IDraggableTarget;

/**
 * 
 *
 * @author Ernesto Reinaldo Barreiro (reiern70@gmail.com)
 *
 */
public class DraggableTargetBehavior extends ScriptaculousAjaxBehavior {

	private static final long serialVersionUID = 1L;

	private IDraggableTarget dragableTarget;
	
	private final Map<String, Object> dropOptions = new HashMap<String, Object>();
	
	public DraggableTargetBehavior(IDraggableTarget dragableTarget) {
		this.dragableTarget = dragableTarget;
		if(!(dragableTarget instanceof Component))
			throw new FrameworkException("", "Draggable target has to be a component");
			
	}
	
	@Override
	protected void respond(AjaxRequestTarget target) {
		String input = ((Component)dragableTarget). getRequest().getParameter("id");
		dragableTarget.onDrop(input, target);
	}
	
	
	/**
	 * TODO: this should build a string array of classes so that one target
	 * can accept multiple classes.
	 * @param className
	 */
	public void addAcceptClass(String className) {
		dropOptions.put("accept", className);
	}

	/**
	 * set an additional CSS class for when an accepted Draggable is hovered over it.
	 * default is none
	 * @param className
	 */
	public void setHoverClass(String className) {
		dropOptions.put("hoverclass", className);
	}

	/**
	 * @return the dropOptions
	 */
	public Map<String, Object> getDropOptions() {
		return dropOptions;
	}

}
