/**
 * 
 */
package com.antilia.web.wizard;

import org.apache.wicket.extensions.wizard.IWizard;
import org.apache.wicket.markup.html.panel.FeedbackPanel;

/**
 * @author Ernesto Reinaldo Barreiro (reirn70@gmail.com)
 *
 */
public interface IAjaxWizard extends IWizard {

	public FeedbackPanel getFeedbackPanel();
	
}
