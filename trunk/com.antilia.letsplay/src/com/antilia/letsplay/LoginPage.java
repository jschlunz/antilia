package com.antilia.letsplay;

import org.apache.wicket.Component;
import org.apache.wicket.markup.html.WebMarkupContainer;

import com.antilia.web.login.LogInRoundPanel;


public class LoginPage extends BasePage {

	private static final long serialVersionUID = 1L;	
	
	private LogInRoundPanel content;
		
	public LoginPage() {		
		super();			
		
		content = new LogInRoundPanel("content",  new GreenStyle()) {
			
			private static final long serialVersionUID = 1L;

			@Override
			protected boolean signIn(String userName, String passWord) {
				return PlaySession.getSession().authenticated(userName, passWord);
			}
			
			@Override
			protected Component newBeforeFields(String id) {
				return new LanguagePanel(id);
			}			
		};	
	}

	@Override
	protected void onBeforeRender() {
		super.onBeforeRender();		
		addOrReplace(content);	
	}
	
	/**
	 * @return the content
	 */
	public WebMarkupContainer getContent() {
		return content;
	}	
}
