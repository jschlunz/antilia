/**
 * This software is provided as IS by Antilia-Soft SL.
 * Copyright 2006-2007.
 */
package com.antilia.export.pdf;

import org.apache.wicket.Component;
import org.apache.wicket.ResourceReference;

import com.antilia.web.dialog.DefaultDialog;
import com.antilia.web.dialog.DialogButton;
import com.antilia.web.resources.DefaultStyle;

/**
 * 
 *
 * @author Ernesto Reinaldo Barreiro (reiern70@gmail.com)
 *
 */
public class ExportPdfButton extends DialogButton {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * @param id
	 */
	public ExportPdfButton(String id) {
		super(id);
	}

	/* (non-Javadoc)
	 * @see com.antilia.web.dialog.DialogButton#getImage()
	 */
	@Override
	protected ResourceReference getImage() {
		return DefaultStyle.IMG_VIEW;
	}

	/* (non-Javadoc)
	 * @see com.antilia.web.dialog.DialogButton#getLabel()
	 */
	@Override
	protected String getLabel() {
		return null;
	}

	/* (non-Javadoc)
	 * @see com.antilia.web.dialog.DialogButton#newDialog(java.lang.String)
	 */
	@Override
	public DefaultDialog newDialog(String id) {
		return new DefaultDialog(id, this) {
			private static final long serialVersionUID = 1L;

			@Override
			protected Component createBody(String id) {
				return new ExportPanel(id);
			}
		};
	}

}
