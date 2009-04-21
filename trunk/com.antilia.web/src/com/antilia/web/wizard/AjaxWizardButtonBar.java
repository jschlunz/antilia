/**
 * 
 */
package com.antilia.web.wizard;

import org.apache.wicket.extensions.wizard.CancelButton;
import org.apache.wicket.extensions.wizard.FinishButton;
import org.apache.wicket.extensions.wizard.LastButton;
import org.apache.wicket.extensions.wizard.NextButton;
import org.apache.wicket.extensions.wizard.PreviousButton;
import org.apache.wicket.extensions.wizard.Wizard;
import org.apache.wicket.extensions.wizard.WizardButtonBar;

/**
 * @author Ernesto Reinaldo Barreiro (reirn70@gmail.com)
 *
 */
public class AjaxWizardButtonBar extends WizardButtonBar {

	private static final long serialVersionUID = 1L;

	/**
	 * @param id
	 * @param wizard
	 */
	public AjaxWizardButtonBar(String id, Wizard wizard) {
		super(id, wizard);
		
		add(new PreviousButton("previous", wizard));
		add(new NextButton("next", wizard));
		add(new LastButton("last", wizard));
		add(new CancelButton("cancel", wizard));
		add(new FinishButton("finish", wizard));
	}

}
