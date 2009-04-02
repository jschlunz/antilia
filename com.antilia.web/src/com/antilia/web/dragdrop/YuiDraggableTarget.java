package com.antilia.web.dragdrop;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.apache.wicket.Component;
import org.apache.wicket.behavior.HeaderContributor;
import org.apache.wicket.behavior.IBehavior;
import org.apache.wicket.markup.MarkupStream;
import org.apache.wicket.markup.html.WebMarkupContainer;

import com.antilia.web.resources.DefaultStyle;


/**
 * 
 * @author Ernesto Reinaldo Barreiro (reiern70@gmail.com)
 *
 */
public abstract class YuiDraggableTarget extends WebMarkupContainer implements IDraggableDroppable
{
	private static final long serialVersionUID = 1L;
	protected final YuiDraggableTargetBehavior onDropBehavior;
	private final Map<String, Object> dropOptions = new HashMap<String, Object>();

	public YuiDraggableTarget(String id)
	{
		super(id);

		add(HeaderContributor.forJavaScript(DefaultStyle.JS_YUI_DOM_EVENT));
		
		setOutputMarkupId(true);
		this.onDropBehavior = new YuiDraggableTargetBehavior(this);
		add(onDropBehavior);
	}

	/**
	 * configure the draggable target to accept a component.
	 * The component must have a {@link DraggableBehavior} attached to it.
	 * @param component
	 */
	@SuppressWarnings("unchecked")
	public void accepts(Component component) {
		for (Iterator iter = component.getBehaviors().iterator(); iter.hasNext();) {
			IBehavior behavior = (IBehavior) iter.next();
			if (behavior instanceof DraggableBehavior) {
				addAcceptClass(((DraggableBehavior)behavior).getDraggableClassName());
			}
		}
	}

	/**
	 * configure the draggable target to accept any draggable item from the {@link SortableListView}
	 * The sortable container needs to override {@link SortableListView#getDraggableClassName()}
	 * in order for the draggable target to know what to accept.
	 * @param container
	 */
	public void acceptAll(SortableListView container) {
		addAcceptClass(container.getDraggableClassName());
	}

	/**
	 * TODO: this should build a string array of classes so that one target
	 * can accept multiple classes.
	 * @param className
	 */
	private void addAcceptClass(String className) {
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

	protected void onRender(MarkupStream markupStream)
	{
		super.onRender(markupStream);
		renderOnDrag(markupStream);
	}
	
	
	protected void renderOnDrag(MarkupStream markupStream) {
		
	}
	
	
	
}
