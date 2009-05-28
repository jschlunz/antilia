/**
 * 
 */
package com.antilia.web.export;

import org.apache.wicket.ResourceReference;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.panel.Panel;

import com.antilia.hibernate.context.IProgressReporter;
import com.antilia.web.dialog.CloseDialogButton;
import com.antilia.web.dialog.DefaultDialog;
import com.antilia.web.progress.HtmlProgressBar;
import com.antilia.web.resources.DefaultStyle;

/**
 * @author EReinaldoB
 *
 */
public abstract class ProgressReportPanel extends Panel {

	private static final long serialVersionUID = 1L;

	private CloseDialogButton cancelButton;
	
	public ProgressReportPanel(String id) {
		super(id);		
	}
	
	
	@Override
	protected void onBeforeRender() {
		super.onBeforeRender();		
		String messageStr = getProgressReporter() != null?  getProgressReporter().getMessage(): "";
		Label message = new Label("message", messageStr);		
		addOrReplace(message);
		
		HtmlProgressBar progress = new HtmlProgressBar("progress") {
			
			private static final long serialVersionUID = 1L;

			@Override
			public int getProgress() {
				IProgressReporter reporter = ProgressReportPanel.this.getProgressReporter();
				return reporter != null?(int)reporter.getProgress(): 0;
			}
		};	
		addOrReplace(progress);
		
		if(cancelButton == null){
			cancelButton = new CloseDialogButton("cancelButton", findDefaultDialog()) {

				private static final long serialVersionUID = 1L;
				
				@Override
				protected void onSubmit(AjaxRequestTarget target, Form<?> form) {
					super.onSubmit(target, form);
					IProgressReporter reporter = ProgressReportPanel.this.getProgressReporter();
					if(reporter != null)
						reporter.cancelJob();
				}
				
				@Override
				protected ResourceReference getImage() {
					return DefaultStyle.IMG_CANCEL;
				}
				
				@Override
				protected String getLabel() {
					return "Cancel";
				}
				
				@Override
				protected String getLabelKey() {
					return "ProgressReportPanel.cancel";
				}
			};
			add(cancelButton);
		}
	}
	
	private DefaultDialog findDefaultDialog() {
		return findParent(DefaultDialog.class);
	}
	
	protected abstract IProgressReporter getProgressReporter();
}
