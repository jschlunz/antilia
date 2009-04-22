/**
 * 
 */
package com.antilia.web.wizard;

import org.apache.wicket.extensions.wizard.IDefaultButtonProvider;
import org.apache.wicket.extensions.wizard.IWizardModel;
import org.apache.wicket.markup.html.form.Button;
import org.apache.wicket.markup.html.form.IFormSubmittingComponent;
import org.apache.wicket.markup.html.panel.Panel;

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
		add(new PreviousButton("previous", wizard));
		add(new NextButton("next", wizard));
		add(new LastButton("last", wizard));
		add(new CancelButton("cancel", wizard));
		add(new FinishButton("finish", wizard));
	}

	/**
	 * @see org.apache.wicket.extensions.wizard.IDefaultButtonProvider#getDefaultButton(org.apache.wicket.extensions.wizard.IWizardModel)
	 */
	public IFormSubmittingComponent getDefaultButton(IWizardModel model)
	{
		if (model.isNextAvailable())
		{
			return (Button)get("next");
		}
		else if (model.isLastAvailable())
		{
			return (Button)get("last");
		}
		else if (model.isLastStep(model.getActiveStep()))
		{
			return (Button)get("finish");
		}
		return null;
	}
}
