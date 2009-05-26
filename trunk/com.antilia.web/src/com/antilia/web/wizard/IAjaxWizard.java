/**
 * 
 */
package com.antilia.web.wizard;

import org.apache.wicket.extensions.wizard.IWizard;

import com.antilia.web.crud.IFeedBackAware;

/**
 * @author Ernesto Reinaldo Barreiro (reirn70@gmail.com)
 *
 */
public interface IAjaxWizard extends IWizard, IFeedBackAware {
	
	public AjaxWizardButtonBar getAjaxWizardButtonBar();
}
