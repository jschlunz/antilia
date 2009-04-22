/**
 * 
 */
package com.antilia.web.wizard;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.extensions.wizard.IWizard;
import org.apache.wicket.extensions.wizard.IWizardModel;
import org.apache.wicket.markup.html.form.Form;

import com.antilia.web.button.AbstractButton;

/**
 * @author Ernesto Reinaldo Barreiro (reirn70@gmail.com)
 *
 */
public abstract class AjaxWizardButton extends AbstractButton {

    private static final long serialVersionUID = 1L;
    private final IWizard wizard;

    private String labelResourceKey;
    
	public AjaxWizardButton(String id, IAjaxWizard wizard, final Form<?> form, String labelResourceKey){
	    super(id, true);
	    this.labelResourceKey  = labelResourceKey;
	    this.wizard = wizard;
	}
	
	@Override
	protected String getLabelKey() {
		return labelResourceKey;
	}
	
	@Override
	protected String getTitleKey() {
		return labelResourceKey;
	}
	
	@Override
	protected String getLabel() {
		return labelResourceKey;
	}

	public AjaxWizardButton(String id, IAjaxWizard wizard, String labelResourceKey)
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
	
	@Override
	protected void onError(AjaxRequestTarget target, Form<?> form) {
		if(target != null) {
			AjaxWizard ajaxWizard = findParent(AjaxWizard.class);
			target.addComponent(ajaxWizard);
		}
	}

	protected abstract void onClick(AjaxRequestTarget target, Form<?> form);    
} 
