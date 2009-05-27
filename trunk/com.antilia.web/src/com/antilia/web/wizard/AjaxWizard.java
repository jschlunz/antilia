/**
 * 
 */
package com.antilia.web.wizard;

import org.apache.wicket.Component;
import org.apache.wicket.ResourceReference;
import org.apache.wicket.extensions.wizard.IWizardModel;
import org.apache.wicket.extensions.wizard.Wizard;
import org.apache.wicket.feedback.ContainerFeedbackMessageFilter;
import org.apache.wicket.feedback.IFeedback;
import org.apache.wicket.markup.html.CSSPackageResource;

import com.antilia.web.crud.IFeedBackAware;
import com.antilia.web.feedback.AntiliaFeedBackPanel;
import com.antilia.web.resources.DefaultStyle;

/**
 * @author Ernesto Reinaldo Barreiro (reirn70@gmail.com)
 *
 */
public class AjaxWizard extends Wizard implements IAjaxWizard, IFeedBackAware {

	private static final long serialVersionUID = 1L;

	private AntiliaFeedBackPanel feedbackPanel;
	
	private AjaxWizardButtonBar ajaxWizardButtonBar;
	
	/**
	 * @param id
	 */
	public AjaxWizard(String id) {
		super(id, false);
		setOutputMarkupId(true);
		add(CSSPackageResource.getHeaderContribution(getCssResource()));
	}

	/**
	 * Override this method to create your own cancel button;
	 */
	public Component createCacelButton(String id, IAjaxWizard wizard) {
		return new AjaxCancelButton(id, wizard);
	}

	/**
	 * @param id
	 * @param wizardModel
	 */
	public AjaxWizard(String id, IWizardModel wizardModel) {
		super(id, wizardModel, false);
		setOutputMarkupId(true);
		add(CSSPackageResource.getHeaderContribution(getCssResource()));
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
		return (feedbackPanel=new AntiliaFeedBackPanel(id, new ContainerFeedbackMessageFilter(this)));
	}
	
	
	protected void init(IWizardModel wizardModel)
	{
		super.init(wizardModel);
		
		if (feedbackPanel == null)
			getForm().addOrReplace(newAntiliaFeedBackPanel(FEEDBACK_ID));
		
	}
	

	/**
	 * @return the ajaxWizardButtonBar
	 */
	public AjaxWizardButtonBar getAjaxWizardButtonBar() {
		return ajaxWizardButtonBar;
	}


	public IFeedback getFeedback() {
		return feedbackPanel;
	}

}
