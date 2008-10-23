/**
 * 
 */
package com.antilia.web.export;

import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.panel.Panel;

import com.antilia.hibernate.context.IProgressReporter;
import com.antilia.web.progress.HtmlProgressBar;

/**
 * @author EReinaldoB
 *
 */
public class ProgressReportPanel extends Panel {

	private static final long serialVersionUID = 1L;

	private IProgressReporter progresReporter;
	
	
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
	}
}
