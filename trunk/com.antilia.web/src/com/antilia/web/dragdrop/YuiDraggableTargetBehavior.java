/**
 * This software is provided as IS by Antilia-Soft SL.
 * Copyright 2006-2007.
 */
package com.antilia.web.dragdrop;

import java.util.HashMap;
import java.util.Map;

import org.apache.wicket.Component;
import org.apache.wicket.ajax.AjaxRequestTarget;

import com.antilia.common.FrameworkException;

/**
 * 
 *
 * @author Ernesto Reinaldo Barreiro (reiern70@gmail.com)
 *
 */
public class YuiDraggableTargetBehavior extends ScriptaculousAjaxBehavior {

	private static final long serialVersionUID = 1L;

	private IDraggableDroppable dragableDroppable;
	
	private final Map<String, Object> dropOptions = new HashMap<String, Object>();
	
	public YuiDraggableTargetBehavior(IDraggableDroppable dragableDroppable) {
		this.dragableDroppable = dragableDroppable;
		if(!(dragableDroppable instanceof Component))
			throw new FrameworkException("", "IDraggableDroppable target has to be a component");
			
	}
	
	@Override
	protected void respond(AjaxRequestTarget target) {
		String sourceId = ((Component)dragableDroppable). getRequest().getParameter("sourceId");
		String targetId = ((Component)dragableDroppable). getRequest().getParameter("targetId");
		//target.addComponent(DraggableTarget.this);
		//target.appendJavascript(new Effect.Highlight().toJavascript(DraggableTarget.this));

		dragableDroppable.onDrop(sourceId, targetId, target);
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
