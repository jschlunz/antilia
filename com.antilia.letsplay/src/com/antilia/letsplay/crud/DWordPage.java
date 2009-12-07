/**
 * 
 */
package com.antilia.letsplay.crud;

import org.apache.wicket.markup.html.panel.Panel;

import com.antilia.letsplay.RoundBasePage;
import com.antilia.letsplay.domain.DWord;
import com.antilia.web.crud.CRUDPanel;
import com.antilia.web.crud.TitledCRUDPanel;
import com.antilia.web.layout.ScopedCrudPanel;
import com.antilia.web.login.IProtectedPage;

/**
 * @author Ernesto Reinaldo Barreiro (reiern70@gmail.com)
 *
 */
public class DWordPage extends RoundBasePage implements IProtectedPage  {

	public DWordPage() {
		super();
	}

	@Override
	protected Panel newContentPanel(String id) {
		return new ScopedCrudPanel<DWord>(id, DWord.class) {
			
			private static final long serialVersionUID = 1L;

			protected CRUDPanel<DWord> createCrudPanel(String id) {
				return new TitledCRUDPanel<DWord>(id, DWord.class);
			}
		};
	}
}
