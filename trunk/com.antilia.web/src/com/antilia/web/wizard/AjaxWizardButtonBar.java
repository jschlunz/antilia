/**
 * 
 */
package com.antilia.web.wizard;

import org.apache.wicket.extensions.wizard.IDefaultButtonProvider;
import org.apache.wicket.extensions.wizard.IWizardModel;
import org.apache.wicket.markup.html.form.IFormSubmittingComponent;
import org.apache.wicket.markup.html.panel.Panel;

import com.antilia.web.button.AbstractButton;

/**
 * @author Ernesto Reinaldo Barreiro (reirn70@gmail.com)
 *
 */
public class AjaxWizardButtonBar extends Panel implements IDefaultButtonProvider {

	private static final long serialVersionUID = 1L;

	/**
	 * @param id
	 * @param wizard
	 */
	public AjaxWizardButtonBar(String id, AjaxWizard wizard) {
		super(id);		
		setOutputMarkupId(true);
		add(new AjaxPreviousButton("previous", wizard));
		add(new AjaxNextButton("next", wizard));
		add(new AjaxLastButton("last", wizard));
		add(new AjaxCancelButton("cancel", wizard));
		add(new AjaxFinishButton("finish", wizard));
	}

	/**
	 * @see org.apache.wicket.extensions.wizard.IDefaultButtonProvider#getDefaultButton(org.apache.wicket.extensions.wizard.IWizardModel)
	 */
	public IFormSubmittingComponent getDefaultButton(IWizardModel model)
	{
		if (model.isNextAvailable())
		{
			return ((AbstractButton)get("next")).getLink();
		}
		else if (model.isLastAvailable())
		{
			return ((AbstractButton)get("last")).getLink();
		}
		else if (model.isLastStep(model.getActiveStep()))
		{
			return ((AbstractButton)get("finish")).getLink();
		}
		return null;
	}
}
