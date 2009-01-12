/**
 * 
 */
package com.antilia.demo.manager;

import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.Model;

import com.antilia.web.field.impl.EnumDropDownChoice;

/**
 * @author Ernesto Reinaldo Barreiro (reiern70@gmail.com)
 *
 */
public class LanguagePanel extends Panel {

	private static final long serialVersionUID = 1L;


	/**
	 * @param id
	 * @param model
	 */
	public LanguagePanel(String id) {
		super(id);
		add(new EnumDropDownChoice<Language>("language", Language.class, new Model<Language>() {
			
			private static final long serialVersionUID = 1L;

			@Override
			public Language getObject() {
				return ManagerSession.getSession().getLanguage();
			}
			
			@Override
			public void setObject(Language language) {
				ManagerSession.getSession().setLanguage(language);
			}
			
		}));
	}

}
