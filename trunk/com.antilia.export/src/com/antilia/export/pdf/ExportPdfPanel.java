/**
 * This software is provided as IS by Antilia-Soft SL.
 * Copyright 2006-2007.
 */
package com.antilia.export.pdf;

import java.io.Serializable;

import org.apache.wicket.ajax.AjaxSelfUpdatingTimerBehavior;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.util.time.Duration;

import com.antilia.web.progress.HtmlProgressBar;
import com.antilia.web.progress.ProgressPanel;

/**
 * 
 *
 * @author Ernesto Reinaldo Barreiro (reiern70@gmail.com)
 *
 */
public class ExportPdfPanel<B extends Serializable> extends Panel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private ExportPdfButton<B> button;
	
	private WebMarkupContainer progress;
	
	/**
	 * @param id
	 */
	public ExportPdfPanel(String id, ExportPdfButton<B> button) {
		super(id);		
		this.button = button;		
		setOutputMarkupId(true);		
	}
	
	@Override
	protected void onBeforeRender() {				
		if(!this.button.getExportPdfTask().isFinished()) {			
			for(Object behavior : getBehaviors()) {
				if(behavior instanceof AjaxSelfUpdatingTimerBehavior) {
					remove((AjaxSelfUpdatingTimerBehavior)behavior);
				}
			}
			add(new AjaxSelfUpdatingTimerBehavior(Duration.seconds(2)));
			progress = new HtmlProgressBar("progress") {
				
				private static final long serialVersionUID = 1L;
	
				@Override
				public int getProgress() {
					return ExportPdfPanel.this.button.getExportPdfTask().getProgress();
				}
			};	
			addOrReplace(progress);
			
		} else {
			for(Object behavior : getBehaviors()) {
				if(behavior instanceof AjaxSelfUpdatingTimerBehavior) {
					remove((AjaxSelfUpdatingTimerBehavior)behavior);
				}
			}
			add(new AjaxSelfUpdatingTimerBehavior(Duration.seconds(100)));
			progress = new DownLoadExportPanel("progress", this.button.getExportPdfTask().getFile());
			progress.setOutputMarkupId(true);
			addOrReplace(progress);
		}
		super.onBeforeRender();
	}
}
