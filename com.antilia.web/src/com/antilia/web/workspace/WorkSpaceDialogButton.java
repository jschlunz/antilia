/**
 * 
 */
package com.antilia.web.workspace;

import org.apache.wicket.Component;
import org.apache.wicket.ResourceReference;
import org.apache.wicket.markup.html.panel.EmptyPanel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.ResourceModel;

import com.antilia.web.dialog.DefaultDialog;
import com.antilia.web.dialog.DialogButton;
import com.antilia.web.resources.DefaultStyle;

/**
 * @author Ernesto Reinaldo Barreiro (reirn70@gmail.com)
 *
 */
public abstract class WorkSpaceDialogButton extends DialogButton {

	private static final long serialVersionUID = 1L;

	/**
	 * @param id
	 */
	public WorkSpaceDialogButton(String id) {
		super(id);
	}

	/* (non-Javadoc)
	 * @see com.antilia.web.dialog.DialogButton#getImage()
	 */
	@Override
	protected ResourceReference getImage() {
		return DefaultStyle.IMG_VIEW;
	}

	@Override
	public DefaultDialog newDialog(String id) {
		return new WorkSpaceDialog(id, WorkSpaceDialogButton.this) {
			
			private static final long serialVersionUID = 1L;

			@Override
			protected Component createBody(String id) {
				return WorkSpaceDialogButton.this.createBody(id);
			}
			
			@Override
			public IModel<String> getTitle() {
				return new ResourceModel(WorkSpaceDialogButton.this.getDialogTitleKey(), WorkSpaceDialogButton.this.getDialogTitleKey());
			}
		};
	}
			
	
	public String getDialogTitleKey() {
		return WorkSpaceDialogButton.this.getClass().getSimpleName();
	}
	
	@Override
	protected String getLabel() {
		return WorkSpaceDialogButton.this.getClass().getSimpleName();
	}
	

	/**
	 * Override this method to create your panel!
	 * 
	 * @param id
	 * @return
	 */
	protected Component createBody(String id) {
		return new EmptyPanel(id);
	}
}
