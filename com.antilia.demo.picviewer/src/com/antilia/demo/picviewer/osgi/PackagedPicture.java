/**
 * This software is provided as IS by Antilia-Soft SL.
 * Copyright 2006-2007.
 */
package com.antilia.demo.picviewer.osgi;

import org.apache.wicket.ResourceReference;

/**
 * 
 *
 * @author Ernesto Reinaldo Barreiro (reiern70@gmail.com)
 *
 */
public class PackagedPicture implements IPicture, Comparable<PackagedPicture> {

	private static final long serialVersionUID = 1L;
	
	private String id;
	private String title;
	private String image;
	
	private Class<? extends PackagedPicturesSource> sourceClass;
		
	
	public PackagedPicture(PackagedPicturesSource source, String fileName) {
		this.sourceClass= source.getClass();
		this.id = fileName;
		this.title = fileName;
	}
	
	public ResourceReference getContent() {
		return new ResourceReference(sourceClass, getId());
	}
	
	public String getId() {
		return id;
	}
	
	public String getTitle() {
		return title;
	}

	public Class<? extends PackagedPicturesSource> getSourceClass() {
		return sourceClass;
	}

	public void setSourceClass(Class<? extends PackagedPicturesSource> sourceClass) {
		this.sourceClass = sourceClass;
	}

	public void setId(String id) {
		this.id = id;
	}

	public void setTitle(String title) {
		this.title = title;
	}
	
	@Override
	public boolean equals(Object obj) {
		if(obj instanceof PackagedPicture){
			return ((PackagedPicture)obj).getId().equals(getId());
		}
		return false;
	}
	
	public int compareTo(PackagedPicture o) {
		return id.compareTo(o.getId());
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

}
