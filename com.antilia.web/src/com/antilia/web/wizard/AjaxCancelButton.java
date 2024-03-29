package com.antilia.web.wizard;

import org.apache.wicket.ResourceReference;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.markup.html.form.Form;

import com.antilia.web.resources.DefaultStyle;

/**
 * 
 * @author Ernesto Reinaldo Barreiro (reirn70@gmail.com)
 *
 */
public class AjaxCancelButton extends AjaxWizardButton
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
	public AjaxCancelButton(String id, IAjaxWizard wizard)
	{
		super(id, wizard, "org.apache.wicket.extensions.wizard.cancel");
		getLink().setDefaultFormProcessing(false);
	}

	/**
	 * @see org.apache.wicket.Component#isEnabled()
	 */
	@Override
	public final boolean isEnabled()
	{
		return true;
	}

	/**
	 * @see org.apache.wicket.Component#isVisible()
	 */
	@Override
	public final boolean isVisible()
	{
		return getWizardModel().isCancelVisible();
	}

	
	@Override
	protected void onClick(AjaxRequestTarget target, Form<?> form) {
		getWizardModel().cancel();	
	}
	
	@Override
	protected ResourceReference getImage() {
		return DefaultStyle.IMG_CANCEL;
	}
}