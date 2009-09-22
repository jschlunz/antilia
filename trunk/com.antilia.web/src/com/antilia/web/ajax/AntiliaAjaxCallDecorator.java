/**
 * 
 */
package com.antilia.web.ajax;

import org.apache.wicket.ajax.IAjaxCallDecorator;
import org.wicketstuff.minis.veil.VeilResources;

import com.antilia.web.dialog.IDialogScope;

/**
 * An IAjaxCallDecorator that will display a loading icon on AJAX
 * requests.
 * 
 * @author Ernesto Reinaldo Barreiro (reirn70@gmail.com)
 */
public class AntiliaAjaxCallDecorator implements IAjaxCallDecorator {

	private static final long serialVersionUID = 1L;

	private IDialogFinder dialogFinder;
	
	private transient IDialogScope dialogScope;
	
	public AntiliaAjaxCallDecorator(IDialogFinder dialogFinder) {
		this.dialogFinder = dialogFinder;
	}
	
	public CharSequence decorateOnFailureScript(CharSequence script) {
		IDialogScope dialogScope = getDialogScope();
		String errorMessage = ";alert('"+this.dialogFinder.getDefiningComponent().getString("ServerDown", null, "Server Down!")+"');";
		if(dialogScope != null) {
			return script + ";if(Wicket.Veil){" + VeilResources.Javascript.Generic.toggle(dialogScope.getDialogId()) + ";};"+ errorMessage ;
		} 
		return script + ";if(Wicket.Veil){" + VeilResources.Javascript.Generic.toggle("AT_body") + ";};"+errorMessage;
	}
	
	public CharSequence decorateOnSuccessScript(CharSequence script) {
		IDialogScope dialogScope = getDialogScope();
		if(dialogScope != null) {
			return script + ";if(Wicket.Veil){" + VeilResources.Javascript.Generic.toggle(dialogScope.getDialogId()) + ";};" ;
		}
		return script + ";if(Wicket.Veil){" + VeilResources.Javascript.Generic.toggle("AT_body") + ";};";
	}
	
	public CharSequence decorateScript(CharSequence script) {
		IDialogScope dialogScope = getDialogScope();
		if(dialogScope != null) {
			return "if(Wicket.Veil){"+VeilResources.Javascript.Generic.show(dialogScope.getDialogId()) + ";};" + script;
		}
		return "if(Wicket.Veil){"+VeilResources.Javascript.Generic.show("AT_body") + ";};" + script;
	}
	
	private IDialogScope getDialogScope() {
		if(dialogScope == null) {
			dialogScope = this.dialogFinder.findParentDialog();
		}
		return dialogScope;
	}

}
