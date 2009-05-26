/**
 * 
 */
package com.antilia.web.tabs;

import org.apache.wicket.extensions.markup.html.tabs.AbstractTab;
import org.apache.wicket.model.IModel;

/**
 * @author Ernesto Reinaldo Barreiro (reirn70@gmail.com)
 *
 */
public abstract class AbstractAntiliaTab extends AbstractTab implements IAntiliaTab {

	private static final long serialVersionUID = 1L;
	
	public AbstractAntiliaTab(IModel<String> title) {
		super(title);
	}
	
	/* (non-Javadoc)
	 * @see com.antilia.web.tabs.IAntiliaTab#isSubmit()
	 */
	public boolean isSubmit() {
		return false;
	}

}
