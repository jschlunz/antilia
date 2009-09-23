/**
 * 
 */
package com.antilia.web.layout;

import org.apache.wicket.Component;
import org.apache.wicket.markup.html.panel.EmptyPanel;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.IModel;

/**
 * @author Ernesto Reinaldo Barreiro (reirn70@gmail.com)
 *
 */
public class ScopedPanel extends Panel implements IScopedPanel {

	private static final long serialVersionUID = 1L;

	public static final String BODY_ID = "body";
	
	private Component body;
	
	private boolean bodyChanged = false;
	
	/**
	 * @param id
	 */
	public ScopedPanel(String id) {
		super(id);
		setOutputMarkupId(true);
	}
	
	/**
	 * @param id
	 * @param model
	 */
	public ScopedPanel(String id, IModel<?> model) {
		super(id, model);
		setOutputMarkupId(true);
	}
	
	@Override
	protected void onBeforeRender() {
		super.onBeforeRender();
		if(body == null) {
			this.body = createBody(BODY_ID);
			this.bodyChanged = true;
		}
		if(bodyChanged) {
			addOrReplace(body);
			this.bodyChanged = false;
		}
	}
	
	/**
	 * Overrride this method to create your panel.
	 * @param id
	 * @return
	 */
	protected Component createBody(String id) {		
		return new EmptyPanel(id);
	}
	
	public void replaceBody(Component body) {
		this.body = body;
		this.bodyChanged = true;
	}

}
