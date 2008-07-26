/**
 * This software is provided as IS by Antilia-Soft SL.
 * Copyright 2006-2007.
 */
package com.antilia.web.roundpane;

import java.io.Serializable;

/**
 * @author Ernesto Reinaldo Barreiro (reiern70@gmail.com)
 *
 */
public class RoundPaneStyle implements Serializable {

	private static final long serialVersionUID = 1L;

	private String backgroundColor;
	
	private String handleColor;
	
	private String titleStyle;
	
	private String bodyStyle;
	
	/**
	 * 
	 */
	public RoundPaneStyle() {
		
	}

	public String getBackgroundColor() {
		return backgroundColor;
	}

	public void setBackgroundColor(String backgroundColor) {
		this.backgroundColor = backgroundColor;
	}

	public String getHandleColor() {
		return handleColor;
	}

	public void setHandleColor(String handleColor) {
		this.handleColor = handleColor;
	}

	public String getTitleStyle() {
		return titleStyle;
	}

	public void setTitleStyle(String titleColor) {
		this.titleStyle = titleColor;
	}

	public String getBodyStyle() {
		return bodyStyle;
	}

	public void setBodyStyle(String bodyStyle) {
		this.bodyStyle = bodyStyle;
	}

}
