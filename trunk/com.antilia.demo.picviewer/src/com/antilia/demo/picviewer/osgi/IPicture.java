package com.antilia.demo.picviewer.osgi;

import java.io.Serializable;

import org.apache.wicket.ResourceReference;

public interface IPicture extends Serializable {
	
	String getId();
	
	String getTitle();
	
	ResourceReference getContent();
	
}
