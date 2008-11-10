/**
 * 
 */
package com.antilia.web.login;

import com.antilia.web.roundpane.RoundPane;
import com.antilia.web.roundpane.RoundPaneStyle;

/**
 * @author EReinaldoB
 *
 */
public abstract class LogInRoundPanel extends RoundPane {

	private static final long serialVersionUID = 1L;

	/**
	 * @param id
	 * @param title
	 * @param boxStyle
	 */
	public LogInRoundPanel(String id, String title, RoundPaneStyle boxStyle) {
		super(id, title, boxStyle);
		addToBody(new LogInPanel("logIn") {
			
			private static final long serialVersionUID = 1L;

			@Override
			protected boolean signIn(String userName, String passWord) {
				return LogInRoundPanel.this.signIn(userName, passWord);
			}
		});
		setWidth(300);
		setHeight(200);
		setTop(100);
		setCentered(true);
		setLeft(200);
	}

	protected abstract boolean signIn(String userName, String passWord);
}
