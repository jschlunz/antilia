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
public class ProgressReportPanel extends Panel {

	private static final long serialVersionUID = 1L;

	private IProgressReporter progresReporter;
	
	private CloseDialogButton cancelButton;
	
	public ProgressReportPanel(String id, IProgressReporter progresReporter) {
		super(id);
		this.progresReporter = progresReporter;
		
		
	}
	
	@Override
	protected void onBeforeRender() {
		super.onBeforeRender();		
		Label message = new Label("message", progresReporter.getMessage());		
		addOrReplace(message);
		
		HtmlProgressBar progress = new HtmlProgressBar("progress") {
			
			private static final long serialVersionUID = 1L;

			@Override
			public int getProgress() {
				return (int)progresReporter.getProgress();
			}
		};	
		addOrReplace(progress);
		
		if(cancelButton == null){
			cancelButton = new CloseDialogButton("cancelButton", findDefaultDialog()) {

				private static final long serialVersionUID = 1L;
				
				@Override
				protected void onSubmit(AjaxRequestTarget target, Form<?> form) {
					super.onSubmit(target, form);
					progresReporter.cancelJob();
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
}
