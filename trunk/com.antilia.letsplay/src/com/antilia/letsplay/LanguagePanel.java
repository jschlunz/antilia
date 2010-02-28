/**
 * 
 */
package com.antilia.letsplay;

import org.apache.wicket.Component;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.form.OnChangeAjaxBehavior;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.Model;

import com.antilia.web.field.impl.EnumDropDownChoice;

/**
 * @author Ernesto Reinaldo Barreiro (reiern70@gmail.com)
 *
 */
public abstract class LanguagePanel extends Panel {

	private static final long serialVersionUID = 1L;

	
	/**
	 * @param id
	 * @param model
	 */
	public LanguagePanel(String id) {
		super(id);
		
		EnumDropDownChoice<Language> language = new EnumDropDownChoice<Language>("language", Language.class, new Model<Language>() {
			
			private static final long serialVersionUID = 1L;

			@Override
			public Language getObject() {
				return PlaySession.getSession().getLanguage();
			}
			
			@Override
			public void setObject(Language language) {
				PlaySession.getSession().setLanguage(language);
			}
			
		});
		language.setRequired(true);
		language.setNullValid(false);
		language.add(new OnChangeAjaxBehavior() {
			private static final long serialVersionUID = 1L;

			@Override
			protected void onUpdate(AjaxRequestTarget target) {
				if(target != null) {
					target.addComponent(getReloadComponent());
				}
			}
		});
		
		add(language);
	}
	
	protected abstract Component getReloadComponent();
	
	/*
	private LogInRoundPanel getPanel() {
		return (LogInRoundPanel)findParent(LogInRoundPanel.class);
	}
	*/

}
