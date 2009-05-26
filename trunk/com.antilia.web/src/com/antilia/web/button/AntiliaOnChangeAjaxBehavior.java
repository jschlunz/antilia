/**
 * 
 */
package com.antilia.web.button;

import org.apache.wicket.Component;
import org.apache.wicket.ajax.IAjaxCallDecorator;
import org.apache.wicket.ajax.form.OnChangeAjaxBehavior;

import com.antilia.web.ajax.AntiliaAjaxCallDecorator;
import com.antilia.web.ajax.IDialogFinder;
import com.antilia.web.dialog.IDialogScope;

/**
 * @author Ernesto Reinaldo Barreiro (reirn70@gmail.com)
 *
 */
public abstract class AntiliaOnChangeAjaxBehavior extends OnChangeAjaxBehavior implements IDialogFinder {

	private static final long serialVersionUID = 1L;
	
	
	/**
	 * 
	 */
	public AntiliaOnChangeAjaxBehavior() {
	}

	
	public IDialogScope findParentDialog() {
		return (IDialogScope)getComponent().findParent(IDialogScope.class);
	}

	public Component getDefiningComponent() {
		return getComponent();
	}
	
	protected IAjaxCallDecorator getAjaxCallDecorator() {
		return new AntiliaAjaxCallDecorator(this);
	}

}
