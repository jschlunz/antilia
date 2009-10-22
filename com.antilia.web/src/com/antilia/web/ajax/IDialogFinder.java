/**
 * 
 */
package com.antilia.web.ajax;

import com.antilia.web.dialog.IDialogScope;

/**
 * @author Ernesto Reinaldo Barreiro (reirn70@gmail.com)
 *
 */
public interface IDialogFinder extends IVeilFinder {

	IDialogScope findParentDialog();
				
}
