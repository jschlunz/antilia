package com.antilia.demo.manager;

import org.apache.wicket.Component;
import org.apache.wicket.markup.html.WebMarkupContainer;

import com.antilia.web.layout.FullPage;
import com.antilia.web.login.BlueStyle;
import com.antilia.web.login.LogInRoundPanel;


public class LoginPage extends FullPage {

	private static final long serialVersionUID = 1L;	
	
	private LogInRoundPanel content;
		
	public LoginPage() {		
		super();			
		
		content = new LogInRoundPanel("content",  new BlueStyle()) {
			
			private static final long serialVersionUID = 1L;

			@Override
			protected boolean signIn(String userName, String passWord) {
				return ManagerSession.getSession().authenticated(userName, passWord);
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
