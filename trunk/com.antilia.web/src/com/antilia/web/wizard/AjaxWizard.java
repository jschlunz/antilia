/**
 * 
 */
package com.antilia.web.wizard;

import org.apache.wicket.extensions.wizard.IWizardModel;
import org.apache.wicket.extensions.wizard.Wizard;

/**
 * @author Ernesto Reinaldo Barreiro (reirn70@gmail.com)
 *
 */
public class AjaxWizard extends Wizard {

	private static final long serialVersionUID = 1L;

	/**
	 * @param id
	 */
	public AjaxWizard(String id) {
		super(id);
		setOutputMarkupId(true);
	}

	/**
	 * @param id
	 * @param addDefaultCssStyle
	 */
	public AjaxWizard(String id, boolean addDefaultCssStyle) {
		super(id, addDefaultCssStyle);
		setOutputMarkupId(true);
	}

	/**
	 * @param id
	 * @param wizardModel
	 */
	public AjaxWizard(String id, IWizardModel wizardModel) {
		super(id, wizardModel);
		setOutputMarkupId(true);
	}

	/**
	 * @param id
	 * @param wizardModel
	 * @param addDefaultCssStyle
	 */
	public AjaxWizard(String id, IWizardModel wizardModel, boolean addDefaultCssStyle) {
		super(id, wizardModel, addDefaultCssStyle);
		setOutputMarkupId(true);
	}

}
