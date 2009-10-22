package com.antilia.web.ajax;

import java.io.Serializable;

import org.apache.wicket.Component;

import com.antilia.web.dialog.IVeilScope;

/**
 * @author  Ernesto Reinaldo Barreiro (reiern70@gmail.com)
 *
 */
public interface IVeilFinder extends Serializable {

	IVeilScope findVeilScope();
	
	Component getDefiningComponent();
}
