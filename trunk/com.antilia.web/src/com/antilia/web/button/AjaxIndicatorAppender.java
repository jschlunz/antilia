package com.antilia.web.button;

import org.apache.wicket.Component;
import org.apache.wicket.RequestCycle;
import org.apache.wicket.Response;
import org.apache.wicket.ajax.AbstractDefaultAjaxBehavior;
import org.apache.wicket.behavior.AbstractBehavior;

public class AjaxIndicatorAppender extends AbstractBehavior
{
	/**
	 * Component instance this behavior is bound to
	 */
	private Component component;

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Construct.
	 */
	public AjaxIndicatorAppender()
	{

	}

	/**
	 * @see org.apache.wicket.behavior.AbstractBehavior#onRendered(org.apache.wicket.Component)
	 */
	public void onRendered(Component component)
	{
		final Response r = component.getResponse();
		r.write("<div style=\"display:none;\" class=\"");
		r.write(getSpanClass());
		r.write("\" ");
		r.write("id=\"");
		r.write(getMarkupId());
		r.write("\">");
		r.write("<img src=\"");
		r.write(getIndicatorUrl());
		r.write("\"/></div>");
	}

	/**
	 * @return url of the animated indicator image
	 */
	protected CharSequence getIndicatorUrl()
	{
		return RequestCycle.get().urlFor(AbstractDefaultAjaxBehavior.INDICATOR);
	}

	/**
	 * @return css class name of the generated outer span
	 */
	protected String getSpanClass()
	{
		return "antilia-ajax-indicator";
	}

	/**
	 * Returns the markup id attribute of the outer most span of this indicator.
	 * This is the id of the span that should be hidden or show to hide or show
	 * the indicator.
	 * 
	 * @return markup id of outer most span
	 */
	public String getMarkupId()
	{
		return component.getMarkupId() + "--ajax-indicator";
	}

	/**
	 * @see org.apache.wicket.behavior.AbstractBehavior#bind(org.apache.wicket.Component)
	 */
	public final void bind(Component component)
	{
		this.component = component;
	}

}
