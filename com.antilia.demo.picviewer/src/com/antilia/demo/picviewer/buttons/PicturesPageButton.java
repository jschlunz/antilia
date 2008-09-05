/**
 * This software is provided as IS by Antilia-Soft SL.
 * Copyright 2006-2007.
 */
package com.antilia.demo.picviewer.buttons;

import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.model.Model;

import com.antilia.demo.picviewer.Index;
import com.antilia.demo.picviewer.osgi.IPicturesSource;
import com.antilia.demo.picviewer.view.PicturesPanel;


/**
 * 
 * @author Ernesto Reinaldo Barreiro (reiern70@gmail.com)
 */
public class PicturesPageButton extends AppNavigationButton {

	private static final long serialVersionUID = 1L;

	private IPicturesSource source;
	/**
	 * @param page
	 */
	public PicturesPageButton(Index page, IPicturesSource source) {
		super(source.getId(),page);
		this.source = source;
	}

	/* (non-Javadoc)
	 * @see com.antilia.test.AppNavigationButton#getBodyPanel(java.lang.String)
	 */
	@Override
	public WebMarkupContainer getBodyPanel(String id) {
		return new PicturesPanel(id, getSource());
	}
	
	protected Label newLabel(String id) {
		return new Label(id, new Model<String>() {
			
			private static final long serialVersionUID = 1L;

			@Override
			public String getObject() {
				return PicturesPageButton.this.getLabel();
			}
		});
	}
	
	@Override
	protected String getLabel() {
		return getSource().getTitle();
	}

	public IPicturesSource getSource() {
		return source;
	}

	public void setSource(IPicturesSource source) {
		this.source = source;
	}

}
