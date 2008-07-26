/**
 * This software is provided as IS by Antilia-Soft SL.
 * Copyright 2006-2007.
 */
package com.antilia.demo.roundpane;

import com.antilia.web.roundpane.RoundPaneStyle;

/**
 * @author Ernesto Reinaldo Barreiro (reiern70@gmail.com)
 *
 */
public class BlueStyle extends RoundPaneStyle {

	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	public BlueStyle() {
		setBackgroundColor("#0000ff");
		setHandleColor("#ffffff");
		setTitleStyle("color: #ffffff;");
		setBodyStyle("background-color: #00ccff; color: black; border: 1px solid #0000ff;");		
	}

}
