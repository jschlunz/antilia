
package com.antilia.web.wizard;

import org.apache.wicket.ResourceReference;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.markup.html.form.Form;

import com.antilia.web.resources.DefaultStyle;


public class AjaxPreviousButton extends AjaxWizardButton
{
	private static final long serialVersionUID = 1L;

	/**
	 * Construct.
	 * 
	 * @param id
	 *            The component id
	 * @param wizard
	 *            The wizard
	 */
	public AjaxPreviousButton(String id, IAjaxWizard wizard)
	{
		super(id, wizard, "org.apache.wicket.extensions.wizard.previous");
		getLink().setDefaultFormProcessing(false);
	}

	/**
	 * @see org.apache.wicket.Component#isEnabled()
	 */
	@Override
	public final boolean isEnabled()
	{
		return getWizardModel().isPreviousAvailable();
	}
	
	@Override
	protected void onClick(AjaxRequestTarget target, Form<?> form) {
		getWizardModel().previous();
	}
	
	@Override
	protected ResourceReference getImage() {
		if(isEnabled())
			return DefaultStyle.IMG_PREVIOUS_ENABLED_PNG;
		return DefaultStyle.IMG_PREVIOUS_DISABLED_PNG;
	}
}