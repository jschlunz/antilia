/**
 * This software is provided as IS by Antilia-Soft SL.
 * Copyright 2006-2007.
 */
package com.antilia.web.export;

import org.apache.wicket.ajax.AjaxSelfUpdatingTimerBehavior;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.util.time.Duration;

import com.antilia.hibernate.context.IProgressReporter;
import com.antilia.web.export.ProgressReportPanel;


/**
 * 
 *
 * @author Ernesto Reinaldo Barreiro (reiern70@gmail.com)
 *
 */
public abstract class DefaultExportPanel extends Panel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private AbstractExportDialogButton button;
	
	private WebMarkupContainer progress;
	
	/**
	 * @param id
	 */
	public DefaultExportPanel(String id, AbstractExportDialogButton button) {
		super(id);		
		this.button = button;		
		setOutputMarkupId(true);		
		add(new AjaxSelfUpdatingTimerBehavior(Duration.seconds(5)));
	}
	
	@Override
	protected void onBeforeRender() {				
		if(this.button == null || this.button.getExportTask() == null || !this.button.getExportTask().isFinished()) {						
			progress = new ProgressReportPanel("progress") {
				
				private static final long serialVersionUID = 1L;

				@Override
				protected IProgressReporter getProgressReporter() {
					if(DefaultExportPanel.this.button.getExportTask() != null)
						return DefaultExportPanel.this.button.getExportTask().getProgressReporter();
					return null;
				}
			};		
		} else {
			for(Object behavior : getBehaviors()) {
				if(behavior instanceof AjaxSelfUpdatingTimerBehavior) {
					((AjaxSelfUpdatingTimerBehavior)behavior).stop();
				}
			}
			//add(new AjaxSelfUpdatingTimerBehavior(Duration.seconds(1000)));
			progress = new DownLoadExportPanel("progress", this.button.getExportTask().getFile()) {
				
				private static final long serialVersionUID = 1L;

				@Override
				public String getContentType() {
					return DefaultExportPanel.this.getContentType();
				}
				
				@Override
				public String getMessage() {
					return DefaultExportPanel.this.getDowloadMessage();
				}
			};
			
		}
		progress.setOutputMarkupId(true);
		addOrReplace(progress);
		super.onBeforeRender();
	}
	
	public abstract String getContentType();
	
	
	public abstract String getDowloadMessage();
	
}
