/**
 * This software is provided as IS by Antilia-Soft SL.
 * Copyright 2006-2007.
 */
package com.antilia.web.export;

import org.apache.wicket.ajax.AjaxSelfUpdatingTimerBehavior;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.util.time.Duration;

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
	}
	
	@Override
	protected void onBeforeRender() {				
		if(!this.button.getExportTask().isFinished()) {			
			for(Object behavior : getBehaviors()) {
				if(behavior instanceof AjaxSelfUpdatingTimerBehavior) {
					remove((AjaxSelfUpdatingTimerBehavior)behavior);
				}
			}
			add(new AjaxSelfUpdatingTimerBehavior(Duration.seconds(2)));
			progress = new ProgressReportPanel("progress", DefaultExportPanel.this.button.getExportTask().getProgressReporter());
			addOrReplace(progress);
			
		} else {
			for(Object behavior : getBehaviors()) {
				if(behavior instanceof AjaxSelfUpdatingTimerBehavior) {
					remove((AjaxSelfUpdatingTimerBehavior)behavior);
				}
			}
			add(new AjaxSelfUpdatingTimerBehavior(Duration.seconds(100)));
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
			progress.setOutputMarkupId(true);
			addOrReplace(progress);
		}
		super.onBeforeRender();
	}
	
	public abstract String getContentType();
	
	
	public abstract String getDowloadMessage();
	
}
