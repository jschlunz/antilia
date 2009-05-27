/**
 * 
 */
package com.antilia.web.wizard;

import org.apache.wicket.extensions.wizard.IWizard;
import org.apache.wicket.extensions.wizard.IWizardModel;
import org.apache.wicket.markup.html.form.Form;

import com.antilia.web.dialog.DialogButton;

/**
 * @author Ernesto Reinaldo Barreiro (reirn70@gmail.com)
 *
 */
public abstract class AjaxWizardDialogButton extends DialogButton {

    private static final long serialVersionUID = 1L;
    private final IWizard wizard;

    private String labelResourceKey;
    
	public AjaxWizardDialogButton(String id, IAjaxWizard wizard, final Form<?> form, String labelResourceKey){
	    super(id);
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

	public AjaxWizardDialogButton(String id, IAjaxWizard wizard, String labelResourceKey)
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
    
} 
