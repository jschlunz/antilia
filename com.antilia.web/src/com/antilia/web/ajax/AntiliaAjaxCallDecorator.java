/**
 * 
 */
package com.antilia.web.ajax;

import org.apache.wicket.ajax.IAjaxCallDecorator;
import org.wicketstuff.minis.veil.VeilResources;

import com.antilia.web.dialog.IVeilScope;

/**
 * An IAjaxCallDecorator that will display a loading icon on AJAX
 * requests.
 * 
 * @author Ernesto Reinaldo Barreiro (reirn70@gmail.com)
 */
public class AntiliaAjaxCallDecorator implements IAjaxCallDecorator {

	private static final long serialVersionUID = 1L;

	private IVeilFinder veilFinder;
	
	private transient IVeilScope veilScope;
	
	public AntiliaAjaxCallDecorator(IVeilFinder veilFinder) {
		this.veilFinder = veilFinder;
	}
	
	public CharSequence decorateOnFailureScript(CharSequence script) {
		IVeilScope dialogScope = getDialogScope();
		String errorMessage = ";alert('"+this.veilFinder.getDefiningComponent().getString("ServerDown", null, "Server Down!")+"');";
		if(dialogScope != null) {
			return script + ";if(Wicket.Veil){" + VeilResources.Javascript.Generic.toggle(dialogScope.getVeilId()) + ";};"+ errorMessage ;
		} 
		return script + ";if(Wicket.Veil){" + VeilResources.Javascript.Generic.toggle("AT_body") + ";};"+errorMessage;
	}
	
	public CharSequence decorateOnSuccessScript(CharSequence script) {
		IVeilScope dialogScope = getDialogScope();
		if(dialogScope != null) {
			return script + ";if(Wicket.Veil){" + VeilResources.Javascript.Generic.toggle(dialogScope.getVeilId()) + ";};" ;
		}
		return script + ";if(Wicket.Veil){" + VeilResources.Javascript.Generic.toggle("AT_body") + ";};";
	}
	
	public CharSequence decorateScript(CharSequence script) {
		IVeilScope dialogScope = getDialogScope();
		if(dialogScope != null) {
			return "if(Wicket.Veil){"+VeilResources.Javascript.Generic.show(dialogScope.getVeilId()) + ";};" + script;
		}
		return "if(Wicket.Veil){"+VeilResources.Javascript.Generic.show("AT_body") + ";};" + script;
	}
	
	private IVeilScope getDialogScope() {
		if(veilScope == null) {
			veilScope = this.veilFinder.findVeilScope();
		}
		return veilScope;
	}

}
