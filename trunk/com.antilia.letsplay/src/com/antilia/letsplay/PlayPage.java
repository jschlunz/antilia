/**
 * 
 */
package com.antilia.letsplay;

import com.antilia.letsplay.components.ScrambledWordPanel;
import com.antilia.web.login.IProtectedPage;

/**
 * @author Ernesto Reinaldo Barreiro (reiern70@gmail.com)
 *
 */
public class PlayPage extends BasePage implements IProtectedPage  {

	public PlayPage() {
		super();
		ScrambledWordPanel scrambled  = new ScrambledWordPanel("scrambled");
		add(scrambled);
	}
}
