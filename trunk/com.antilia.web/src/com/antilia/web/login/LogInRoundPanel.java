/**
 * 
 */
package com.antilia.web.login;

import org.apache.wicket.Component;
import org.apache.wicket.markup.html.panel.EmptyPanel;

import com.antilia.web.roundpane.RoundPane;
import com.antilia.web.roundpane.RoundPaneStyle;

/**
 * @author EReinaldoB
 *
 */
public abstract class LogInRoundPanel extends RoundPane {

	private static final long serialVersionUID = 1L;

	public LogInRoundPanel(String id, RoundPaneStyle boxStyle) {
		this(id, "SignIn", boxStyle);
	}
	/**
	 * @param id
	 * @param title
	 * @param boxStyle
	 */
	public LogInRoundPanel(String id, String title, RoundPaneStyle boxStyle) {
		super(id, title, boxStyle);
		addToBody(newLogInPanel("logIn"));
		setWidth(300);
		setHeight(200);
		setCentered(true);
	}
	
	protected LogInPanel newLogInPanel(String id) {
		return new LogInPanel(id) {
			
			private static final long serialVersionUID = 1L;

			@Override
			protected boolean signIn(String userName, String passWord) {
				return LogInRoundPanel.this.signIn(userName, passWord);
			}
			
			@Override
			protected Component newBeforeFields(String id) {
				return LogInRoundPanel.this.newBeforeFields(id);
			}
		};
	}

	protected abstract boolean signIn(String userName, String passWord);
	
	
	protected Component newBeforeFields(String id) {
		return new EmptyPanel(id);
	}
	
}
