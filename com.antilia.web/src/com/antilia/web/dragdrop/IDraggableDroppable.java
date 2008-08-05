/**
 * This software is provided as IS by Antilia-Soft SL.
 * Copyright 2006-2007.
 */
package com.antilia.web.dragdrop;

import org.apache.wicket.Component;
import org.apache.wicket.ajax.AjaxRequestTarget;

/**
 * 
 *
 * @author Ernesto Reinaldo Barreiro (reiern70@gmail.com)
 *
 */
public interface IDraggableDroppable {
	

		/**
		 *  extension point for defining functionality when a {@link DraggableImage} is dropped.
		 * @param input
		 * @param target
		 */
		void onDrop(String sourceId, String targetId, AjaxRequestTarget target);
		
		/**
		 * Call back method 
		 * 
		 * @param component
		 */
		public void accepts(Component component);

}
