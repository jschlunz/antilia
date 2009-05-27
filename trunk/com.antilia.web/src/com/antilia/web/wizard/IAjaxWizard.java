/**
 * 
 */
package com.antilia.web.wizard;

import org.apache.wicket.Component;
import org.apache.wicket.extensions.wizard.IWizard;

import com.antilia.web.crud.IFeedBackAware;

/**
 * @author Ernesto Reinaldo Barreiro (reirn70@gmail.com)
 *
 */
public interface IAjaxWizard extends IWizard, IFeedBackAware {
	
	public AjaxWizardButtonBar getAjaxWizardButtonBar();
	
	
	/**
	 * Give a chance to the wizard to override the creation of the cancel button.
	 * @param id
	 * @return
	 */
	public Component createCacelButton(String id, IAjaxWizard wizard);
}
