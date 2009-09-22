package com.antilia.web.scriptaculous.drag;

import org.apache.wicket.ResourceReference;
import org.apache.wicket.ajax.AbstractDefaultAjaxBehavior;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.markup.html.IHeaderResponse;

import com.antilia.web.resources.DefaultStyle;

/**
 * Handles event requests using 'script.aculo.us'.
 * <p>
 * This class is mainly here to automatically add the javascript files you need.
 * As header contributions are done once per class, you can have multiple
 * instances/ subclasses without having duplicate header contributions.
 * </p>
 *
 * @see <a href="http://script.aculo.us/">script.aculo.us</a>
 * @author <a href="mailto:wireframe6464@users.sourceforge.net">Ryan Sonnek</a>
 */
public abstract class ScriptaculousAjaxBehavior extends AbstractDefaultAjaxBehavior {

	private static final long serialVersionUID = 1L;

	public static ScriptaculousAjaxBehavior newJavascriptBindingBehavior() {
		return new ScriptaculousAjaxBehavior() {

			private static final long serialVersionUID = 1L;

			@Override
			protected void respond(AjaxRequestTarget target) {
				// do nothing
			}
		};
	}

	public void renderHead(IHeaderResponse response) {
		super.renderHead(response);

		addJavascriptReference(response, DefaultStyle.JS_PROTOTYPE);
		addJavascriptReference(response, DefaultStyle.JS_BUILDER);
		addJavascriptReference(response, DefaultStyle.JS_EFFECT);
		addJavascriptReference(response, DefaultStyle.JS_DRAGDROP);
		addJavascriptReference(response, DefaultStyle.JS_CONTROL);
		addJavascriptReference(response, DefaultStyle.JS_SLIDER);
	}

	private void addJavascriptReference(IHeaderResponse response, ResourceReference resource) {
		response.renderJavascriptReference(resource);
	}
}
