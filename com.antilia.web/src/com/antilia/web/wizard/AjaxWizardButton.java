/**
 * 
 */
package com.antilia.web.wizard;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.form.AjaxButton;
import org.apache.wicket.extensions.wizard.IWizard;
import org.apache.wicket.extensions.wizard.IWizardModel;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.model.ResourceModel;

/**
 * @author Ernesto Reinaldo Barreiro (reirn70@gmail.com)
 *
 */
public abstract class AjaxWizardButton extends AjaxButton {

    private static final long serialVersionUID = 1L;
    private final IWizard wizard;

	public AjaxWizardButton(String id, IWizard wizard, final Form<?> form, String labelResourceKey){
	    super(id, form);
	    this.setLabel(new ResourceModel(labelResourceKey));
	    this.wizard = wizard;
	}

	public AjaxWizardButton(String id, IWizard wizard, String labelResourceKey)
	{
	    this(id, wizard, null, labelResourceKey);
	}

	protected final IWizard getWizard()
	{
	    return wizard;
	}

	protected final IWizardModel getWizardModel()
	{
	    return getWizard().getWizardModel();
	}

	protected final void onSubmit(AjaxRequestTarget target, Form<?> form){
		onClick(target, form);
		if(target != null) {
			AjaxWizard ajaxWizard = findParent(AjaxWizard.class);
			target.addComponent(ajaxWizard);
		}
	}
	
	

	protected abstract void onClick(AjaxRequestTarget target, Form<?> form);    
} 
