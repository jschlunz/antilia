package com.antilia.demo.manager;

import java.util.Locale;

import org.apache.wicket.Request;
import org.apache.wicket.Session;

import com.antilia.common.util.StringUtils;
import com.antilia.web.AntiliaSession;

/**
 * 
 *
 * @author Ernesto Reinaldo Barreiro (EReinaldoB@fcc.es)
 *
 */
public class ManagerSession extends AntiliaSession {

	private String userName;
	
	private String passWord;
	
	private static final long serialVersionUID = 1L;

	/**
	 * @param request
	 */
	public ManagerSession(Request request) {
		super(request);
		setLocale(new Locale("es", "ES"));
	}
	
	public static ManagerSession getSession() {
		return (ManagerSession)Session.get();
	}

	@Override
	public boolean isAuthenticated() {
		if(!StringUtils.isEmpty(userName))
			return true;
		return false;
	}

	public boolean authenticated(String userName, String passWord) {
		if(!StringUtils.isEmpty(userName) && passWord.equals(userName)) {
			this.userName = userName;
			this.passWord = passWord;
			return true;
		}
		return false;
	}
	
	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassWord() {
		return passWord;
	}

	public void setPassWord(String passWord) {
		this.passWord = passWord;
	}	
}
