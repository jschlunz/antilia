package com.antilia.web.wizard;

import org.apache.wicket.ResourceReference;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.ResourceModel;

import com.antilia.web.dialog.DefaultDialog;
import com.antilia.web.dialog.util.ConfirmationDialog;
import com.antilia.web.resources.DefaultStyle;

/**
 * 
 * @author Ernesto Reinaldo Barreiro (reirn70@gmail.com)
 *
 */
public class AjaxConfirmationCancelButton extends AjaxWizardDialogButton
{
	private static final long serialVersionUID = 1L;

	/**
	 * Construct.
	 * 
	 * @param id
	 *            The component id
	 * @param wizard
	 *            The wizard
	 */
	public AjaxConfirmationCancelButton(String id, IAjaxWizard wizard)
	{
		super(id, wizard, "org.apache.wicket.extensions.wizard.cancel");
	}

	/**
	 * @see org.apache.wicket.Component#isEnabled()
	 */
	@Override
	public final boolean isEnabled()
	{
		return true;
	}

	/**
	 * @see org.apache.wicket.Component#isVisible()
	 */
	@Override
	public final boolean isVisible()
	{
		return getWizardModel().isCancelVisible();
	}

	@Override
	protected ResourceReference getImage() {
		return DefaultStyle.IMG_CANCEL;
	}

	@Override
	public DefaultDialog newDialog(String id) {
		ConfirmationDialog confirmationDialog =  new ConfirmationDialog(id, this, getConfirmationMessage()) {
			
			private static final long serialVersionUID = 1L;

			@Override
			protected void onOk(AjaxRequestTarget target, Form<?> form) {
				getWizardModel().cancel();
			}
		};
		confirmationDialog.setModal(false);
		confirmationDialog.getDialogStyle().setRoundedHeader(true);
		return confirmationDialog;
	}
	
	protected IModel<String> getConfirmationMessage(){
		return new ResourceModel("org.apache.wicket.extensions.wizard.cancelConfirm");
	}
	
}