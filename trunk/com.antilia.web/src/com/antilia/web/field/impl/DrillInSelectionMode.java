/**
 * This software is provided as IS by Antilia-Soft SL.
 * Copyright 2006-2007.
 */
package com.antilia.web.field.impl;

/**
 * 
 *
 * @author Ernesto Reinaldo Barreiro (reiern70@gmail.com)
 *
 */
public enum DrillInSelectionMode {
	DROPDOWN,
	LARGE_IN_MODAL_DIALOG,
	LARGE_ON_NEXT_PAGE,
	// represents a nested selection: e.g. to select a City we display a Country combo followed by a city combo.
	COMBO_RELOAD
}
