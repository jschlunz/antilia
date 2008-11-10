/**
 * 
 */
package com.antilia.web.login;

import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.PasswordTextField;
import org.apache.wicket.markup.html.form.StatelessForm;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.PropertyModel;

/**
 * @author EReinaldoB
 *
 */
public abstract class LogInPanel extends Panel {

	private static final long serialVersionUID = 1L;
	
	private String userName;
	
	private String password;
	
	/**
	 * @param id
	 */
	public LogInPanel(String id) {
		super(id);		
		Form<String> sigInForm = new StatelessForm<String>("sigInForm") {

			private static final long serialVersionUID = 1L;
			
			@Override
			protected void onSubmit() {
				if (signIn(LogInPanel.this.userName, LogInPanel.this.password)) {
					if (!continueToOriginalDestination()) {
						setResponsePage(getApplication().getHomePage());
					}
				} else {
						error("Unknown username/ password");
				}
			}			
		};
		sigInForm.add(new TextField<String>("userName", new PropertyModel<String>(this,"userName")));
		sigInForm.add(new PasswordTextField("password", new PropertyModel<String>(this,"password")));	
		add(sigInForm);
		
	}
	
	
	protected abstract boolean signIn(String userName, String passWord);
	
	
	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
