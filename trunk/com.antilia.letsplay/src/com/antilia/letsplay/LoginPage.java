package com.antilia.letsplay;

import org.apache.wicket.Component;
import org.apache.wicket.markup.html.panel.Panel;

import com.antilia.web.login.LogInRoundPanel;


public class LoginPage extends RoundBasePage {

	private static final long serialVersionUID = 1L;	
		
	public LoginPage() {		
		super();			
	}

	@Override
	protected Panel newContentPanel(String id) {
		return new LogInRoundPanel("content",  new GreenStyle()) {
			
			private static final long serialVersionUID = 1L;

			@Override
			protected boolean signIn(String userName, String passWord) {
				return PlaySession.getSession().authenticated(userName, passWord);
			}
			
			@Override
			protected Component newBeforeFields(String id) {
				return new LanguagePanel(id) {
	
					private static final long serialVersionUID = 1L;

					@Override
					protected Component getReloadComponent() {
						LogInRoundPanel inRoundPanel = (LogInRoundPanel)findParent(LogInRoundPanel.class);
						return inRoundPanel.getRoundpane();
					}
				};
			}			
		};	
	}
		
}
