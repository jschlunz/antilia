/**
 * 
 */
package com.antilia.web.wizard;

import org.apache.wicket.Component;
import org.apache.wicket.ResourceReference;
import org.apache.wicket.behavior.HeaderContributor;
import org.apache.wicket.extensions.wizard.IWizardModel;
import org.apache.wicket.extensions.wizard.Wizard;
import org.apache.wicket.feedback.ContainerFeedbackMessageFilter;
import org.apache.wicket.markup.html.panel.FeedbackPanel;

import com.antilia.web.feedback.AntiliaFeedBackPanel;
import com.antilia.web.resources.DefaultStyle;

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
		super(id, false);
		setOutputMarkupId(true);
		add(HeaderContributor.forCss(getCssResource()));
	}


	/**
	 * @param id
	 * @param wizardModel
	 */
	public AjaxWizard(String id, IWizardModel wizardModel) {
		super(id, wizardModel, false);
		setOutputMarkupId(true);
		add(HeaderContributor.forCss(getCssResource()));
	}


	protected ResourceReference getCssResource() {
		return DefaultStyle.CSS_AJAX_WIZARD;
	}
	
	
	@Override
	protected Component newButtonBar(String id)
	{
		return (ajaxWizardButtonBar = new AjaxWizardButtonBar(id, this));
	}

	
	protected AntiliaFeedBackPanel newAntiliaFeedBackPanel(String id) {
		return new AntiliaFeedBackPanel(id, new ContainerFeedbackMessageFilter(this));
	}
	
	
	protected void init(IWizardModel wizardModel)
	{
		super.init(wizardModel);
		
		if (wizardModel == null)
			getForm().addOrReplace(newFeedbackPanel(FEEDBACK_ID));
		
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
