package com.antilia.letsplay;

import org.apache.wicket.Component;
import org.apache.wicket.markup.html.panel.EmptyPanel;

import com.antilia.web.dialog.DefaultDialog;
import com.antilia.web.dialog.IDialogLink;

/**
 * @author Ernesto Reinaldo Barreiro (reiern70@gmail.com)
 */
public class AboutDialog extends DefaultDialog {

	private static final long serialVersionUID = 1L;

	/**
	 * @param id
	 * @param button
	 * @param dialogStyle
	 */
	public AboutDialog(String id, IDialogLink button) {
		super(id, button, new LightGreenDialogStyle());
		setModal(true);
		setTopLevel(true);
		setCentered(true);
		setTitle("About");
	}

	/* (non-Javadoc)
	 * @see com.antilia.web.dialog.DefaultDialog#createBody(java.lang.String)
	 */
	@Override
	protected Component createBody(String id) {
		return new EmptyPanel(id);
	}

}
