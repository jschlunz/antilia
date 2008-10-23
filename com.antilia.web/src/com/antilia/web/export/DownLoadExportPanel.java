/**
 * This software is provided as IS by Antilia-Soft SL.
 * Copyright 2006-2007.
 */
package com.antilia.web.export;

import java.io.File;
import java.io.FileInputStream;

import org.apache.wicket.markup.html.DynamicWebResource;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.link.ResourceLink;
import org.apache.wicket.markup.html.panel.Panel;

import com.antilia.common.util.FileUtils;

/**
 * 
 *
 * @author Ernesto Reinaldo Barreiro (reiern70@gmail.com)
 *
 */
public abstract class DownLoadExportPanel extends Panel {

	private static final long serialVersionUID = 1L;

	/**
	 * @param id
	 */
	public DownLoadExportPanel(String id, final File file) {
		super(id);
		
		ResourceLink fileLink  = new ResourceLink("file", new DynamicWebResource(file.getName()) {
			
			private static final long serialVersionUID = 1L;

			@Override
			protected ResourceState getResourceState() {
				return new DynamicWebResource.ResourceState() {
					@Override
					public String getContentType() {
						return DownLoadExportPanel.this.getContentType();
					}
					 
					@Override
					public byte[] getData() {
						try {
							return FileUtils.bytes(new FileInputStream(file));
						} catch (Exception e) {
							return null;
						}
					}
				};
			}
		}); 
		
		add(fileLink);
		
		String message = getMessage();		
		fileLink.add(new Label("message", message!= null?message: "file"));
	}	
	
	public abstract String getContentType();
	
	public abstract String getMessage();
}
