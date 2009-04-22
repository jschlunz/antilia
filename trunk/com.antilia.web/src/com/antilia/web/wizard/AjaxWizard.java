/**
 * 
 */
package com.antilia.web.wizard;

import org.apache.wicket.Component;
import org.apache.wicket.extensions.wizard.IWizardModel;
import org.apache.wicket.extensions.wizard.Wizard;
import org.apache.wicket.markup.html.panel.FeedbackPanel;

/**
 * @author Ernesto Reinaldo Barreiro (reirn70@gmail.com)
 *
 */
public class AjaxWizard extends Wizard implements IAjaxWizard {

	private static final long serialVersionUID = 1L;

	private FeedbackPanel feedbackPanel;
	
	private AjaxWizardButtonBar ajaxWizardButtonBar;
	
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

	@Override
	protected FeedbackPanel newFeedbackPanel(String id) {
		feedbackPanel =  super.newFeedbackPanel(id);
		return feedbackPanel;
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
	
	@Override
	protected Component newButtonBar(String id)
	{
		return (ajaxWizardButtonBar = new AjaxWizardButtonBar(id, this));
	}

	/**
	 * @return the feedbackPanel
	 */
	public FeedbackPanel getFeedbackPanel() {
		return feedbackPanel;
	}

	/**
	 * @return the ajaxWizardButtonBar
	 */
	public AjaxWizardButtonBar getAjaxWizardButtonBar() {
		return ajaxWizardButtonBar;
	}

}
