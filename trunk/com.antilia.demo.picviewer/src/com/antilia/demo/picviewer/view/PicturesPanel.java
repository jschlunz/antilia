/**
 * This software is provided as IS by Antilia-Soft SL.
 * Copyright 2006-2007.
 */
package com.antilia.demo.picviewer.view;

import org.apache.wicket.markup.html.panel.Panel;

import com.antilia.demo.picviewer.osgi.IPicturesSource;
import com.antilia.demo.picviewer.viewer.DefaultStyle;
import com.antilia.demo.picviewer.viewer.PicturesNavigator;

/**
 * 
 *
 * @author Ernesto Reinaldo Barreiro (reiern70@gmail.com)
 *
 */
public class PicturesPanel extends Panel {

	private static final long serialVersionUID = 1L;

	private IPicturesSource source;
	
	/**
	 * 
	 * @param id
	 */
	public PicturesPanel(String id, IPicturesSource source) {
		super(id);
		/*
		TableModel<PackagedPicture> tableModel = new TableModel<PackagedPicture>(PackagedPicture.class, "id", "title", "image");
		Table<PackagedPicture> table = new Table<PackagedPicture>("table",tableModel, getSources(source)) {
			private static final long serialVersionUID = 1L;

			@Override
			protected WebMarkupContainer newBodyCell(String id, IColumnModel<PackagedPicture> columnModel, PackagedPicture object) {
				if(columnModel.getPropertyPath().equals("image")) {
					return new PicturePanel(id, object);
				} 
				return super.newBodyCell(id, columnModel, object);
			}
		};
		add(table);
		*/
		PicturesNavigator navigator = new PicturesNavigator("navigator",new DefaultStyle(), source);
		add(navigator);
	}
	
	/*
	private List<PackagedPicture> getSources(IPicturesSource source) {
		List<PackagedPicture> pictures = new ArrayList<PackagedPicture>();
		for(IPicture picture: source.getPictures()) {
			pictures.add((PackagedPicture)picture);
		}
		return pictures;
	}
	*/
	
	public IPicturesSource getSource() {
		return source;
	}
	
	public void setSource(IPicturesSource source) {
		this.source = source;
	}

}
