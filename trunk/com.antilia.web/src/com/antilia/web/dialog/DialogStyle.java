/**
 * This software is provided as IS by Antilia-Soft SL.
 * Copyright 2006-2007.
 */
package com.antilia.web.dialog;

import java.io.Serializable;

/**
 * @author Ernesto Reinaldo Barreiro (reiern70@gmail.com)
 *
 */
public class DialogStyle implements Serializable {

	private static final long serialVersionUID = 1L;

	private String backgroundColor;
	
	private String handleColor;
	
	private String titleStyle;
	
	private String bodyColor;
	
	/**
	 * 
	 */
	public DialogStyle() {
		
	}

	public String getBackgroundColor() {
		return backgroundColor;
	}

	public DialogStyle setBackgroundColor(String backgroundColor) {
		this.backgroundColor = backgroundColor;
		return this;
	}

	public String getHandleColor() {
		return handleColor;
	}

	public DialogStyle setHandleColor(String handleColor) {
		this.handleColor = handleColor;
		return this;
	}

	public String getTitleStyle() {
		return titleStyle;
	}

	public DialogStyle setTitleStyle(String titleColor) {
		this.titleStyle = titleColor;
		return this;
	}

	public String getBodyColor() {
		return bodyColor;
	}

	public DialogStyle setBodyColor(String bodyStyle) {
		this.bodyColor = bodyStyle;
		return this;
	}

}
