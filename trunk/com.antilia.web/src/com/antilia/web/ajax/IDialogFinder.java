/**
 * 
 */
package com.antilia.web.ajax;

import java.io.Serializable;

import org.apache.wicket.Component;

import com.antilia.web.dialog.IDialogScope;

/**
 * @author Ernesto Reinaldo Barreiro (reirn70@gmail.com)
 *
 */
public interface IDialogFinder extends Serializable {

	IDialogScope findParentDialog();
	
	Component getDefiningComponent();
		
}
