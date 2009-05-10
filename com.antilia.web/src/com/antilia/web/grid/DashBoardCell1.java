package com.antilia.web.grid;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import org.apache.wicket.Component;
import org.apache.wicket.Page;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.behavior.HeaderContributor;
import org.apache.wicket.behavior.IBehavior;
import org.apache.wicket.markup.MarkupStream;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.IFormSubmittingComponent;
import org.apache.wicket.util.string.AppendingStringBuffer;
import org.apache.wicket.util.string.PrependingStringBuffer;

import com.antilia.web.dragdrop.DraggableBehavior;
import com.antilia.web.dragdrop.ScriptaculousAjaxBehavior;
import com.antilia.web.dragdrop.SortableListView;
import com.antilia.web.effect.JavascriptHelper;
import com.antilia.web.resources.DefaultStyle;




/**
 * Target for drag/drop operations.
 * user can drop a Draggable item onto this component to perform ajax operation.
 *
 * @see http://wiki.script.aculo.us/scriptaculous/show/Droppables.add
 */
public abstract  class DashBoardCell1 extends WebMarkupContainer implements IFormSubmittingComponent
{
	private static final long serialVersionUID = 1L;
	private final ScriptaculousAjaxBehavior onDropBehavior;
	private final Map<String, Object> dropOptions = new HashMap<String, Object>();
	
	private int row;
	
	private int col;
	
	private IGridWidget widget;
	
	private Form<?> form;
	
	private GridPane dashBoardPane;
	
	private boolean defaultFormProcessing = true;

	public DashBoardCell1(GridPane dashBoardPane, String id, int row, int col)
	{
		super(id);
		
		this.dashBoardPane = dashBoardPane;
		
		onDropBehavior = new DraggableTargetBehavior(dashBoardPane);
		
		this.row = row;
		this.col = col;

		add(HeaderContributor.forJavaScript(DefaultStyle.JS_PROTOTYPE));
		add(HeaderContributor.forJavaScript(DefaultStyle.JS_BUILDER));
		add(HeaderContributor.forJavaScript(DefaultStyle.JS_EFFECT));
		add(HeaderContributor.forJavaScript(DefaultStyle.JS_DRAGDROP));
		add(HeaderContributor.forJavaScript(DefaultStyle.JS_CONTROL));				
		add(HeaderContributor.forJavaScript(DefaultStyle.JS_SLIDER));
		
		setOutputMarkupId(true);
		add(onDropBehavior);
	}

	public void addWidget(Component widget) {
		add(widget);
		if(widget instanceof IGridWidget) {
			setWidget((IGridWidget)widget);
		}
	}
	
	/**
	 * extension point for defining functionality when a {@link DraggableImage} is dropped.
	 * @param input the id attribute of the dropped component
	 */
	protected abstract void onDrop(String input, AjaxRequestTarget target);

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


		//dropOptions.put("onDrop", new JavascriptBuilder.JavascriptFunction("function(draggable, droppable, event) { wicketAjaxGet('" + onDropBehavior.getCallbackUrl()
		//		+ "&id=' + draggable.id); }"));
		
		final String formId = getForm().getMarkupId();
		final CharSequence url = onDropBehavior.getCallbackUrl();

		AppendingStringBuffer call = new AppendingStringBuffer("wicketSubmitFormById('").append(
				formId).append("', '").append(url).append("&id=' + draggable.id, ");
		call.append("'").append(this.getInputName()).append("');");
		
		dropOptions.put("onDrop", new JavascriptHelper.JavascriptFunction("function(draggable, droppable, event) { Droppables.removeAll();" 
				+call.toString()+ "}"));
				
		
		JavascriptHelper builder = new JavascriptHelper();
		builder.addLine("Droppables.add('" + getMarkupId() + "', ");
		builder.addOptions(dropOptions);
		builder.addLine(");");

		String script = builder.buildScript();
		getResponse().write(script);
	}

	private class DraggableTargetBehavior extends ScriptaculousAjaxBehavior
	{
		private static final long serialVersionUID = 1L;

		//private Form form;
		
		private GridPane dashBoardPane;
		
		private DraggableTargetBehavior(GridPane dashBoardPane) {
			this.dashBoardPane = dashBoardPane;
		}
		
		@Override
		protected void respond(AjaxRequestTarget target) {
			String input = getRequest().getParameter("id");
			//target.addComponent(DraggableTarget.this);
			//target.appendJavascript(new Effect.Highlight().toJavascript(DraggableTarget.this));

			Set<String> params = getRequest().getParameterMap().keySet();			
			for(String param: params) {		
				String value = getRequest().getParameter(param);
				if(param.indexOf("rows")>0 && param.indexOf("cells") > 0 && param.indexOf("roundbox:width")>0) {
					int row = getRow(param);
					int col = getCol(param);
					WidgetModel model = this.dashBoardPane.getWidgetModel(row, col);
					if(model != null) {
						model.setWidth(new Integer(value));
					}
				} else if(param.indexOf("rows")>0 && param.indexOf("cells") > 0 && param.indexOf("roundbox:height")>0) {
					int row = getRow(param);
					int col = getCol(param);
					WidgetModel model = this.dashBoardPane.getWidgetModel(row, col);
					if(model != null) {
						model.setHeight(new Integer(value));
					}
				}
			}
			
			onDrop(input, target);
		}
		
		private int getRow(String param) {
			String value = param.substring(param.indexOf("rows:")+5);
			value = value.substring(0, value.indexOf(":"));
			try {
				return new Integer(value).intValue()-1;
			} catch (Exception e) {
				return 0;
			}
		}
		
		private int getCol(String param) {
			String value = param.substring(param.indexOf("cells:")+6);
			value = value.substring(0, value.indexOf(":"));
			try {
				return new Integer(value).intValue()-1;
			} catch (Exception e) {
				return 0;
			}
		}
		
		/*
		private Form getForm()
		{
			if (form == null)
			{
				// try to find form in the hierarchy of owning component
				Component cursor = getComponent();
				while (cursor != null && !(cursor instanceof Form))
				{
					cursor = cursor.getParent();
				}
				if (cursor == null)
				{
					throw new IllegalStateException(
							"form was not specified in the constructor and cannot "
									+ "be found in the hierarchy of the component this behavior "
									+ "is attached to");
				}
				else
				{
					form = (Form)cursor;
				}
			}
			return form;
		}
		*/
	}

	public int getRow() {
		return row;
	}

	public void setRow(int row) {
		this.row = row;
	}

	public int getCol() {
		return col;
	}

	public void setCol(int col) {
		this.col = col;
	}

	public IGridWidget getWidget() {
		return widget;
	}

	public void setWidget(IGridWidget widget) {
		this.widget = widget;
	}

	public boolean getDefaultFormProcessing() {
		return defaultFormProcessing;
	}

	public Form getForm() {
		if (this.form != null)
		{
			return this.form;
		}
		else
		{
			return (Form)findParent(Form.class);
		}
	}

	public String getInputName() {
		// TODO: This is a copy & paste from the FormComponent class. 
		String id = getId();
		final PrependingStringBuffer inputName = new PrependingStringBuffer(id.length());
		Component c = this;
		while (true)
		{
			inputName.prepend(id);
			c = c.getParent();
			if (c == null || (c instanceof Form && ((Form)c).isRootForm()) || c instanceof Page)
			{
				break;
			}
			inputName.prepend(Component.PATH_SEPARATOR);
			id = c.getId();
		}
		return inputName.toString();
	}

	public final void onSubmit() {
	}

	public GridPane getDashBoardPane() {
		return dashBoardPane;
	}
}
