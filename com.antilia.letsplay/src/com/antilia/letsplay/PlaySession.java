package com.antilia.letsplay;

import org.apache.wicket.Request;
import org.apache.wicket.Session;

import com.antilia.letsplay.dao.IUserDao;
import com.antilia.letsplay.dao.UserDao;
import com.antilia.letsplay.domain.User;
import com.antilia.web.AntiliaSession;

/**
 * 
 *
 * @author Ernesto Reinaldo Barreiro (EReinaldoB@fcc.es)
 *
 */
public class PlaySession extends AntiliaSession {

	private User user;
		
	private static final long serialVersionUID = 1L;

	private Language language;
	
	/**
	 * @param request
	 */
	public PlaySession(Request request) {
		super(request);
		setLanguage(Language.SPANISH);		
	}
	
	public static PlaySession getSession() {
		return (PlaySession)Session.get();
	}

	public boolean isAuthenticated() {
		if(user != null)
			return true;
		return false;
	}

	public boolean authenticated(String userName, String passWord) {
		if(this.user != null) {
			return true;
		}
		IUserDao dao =  new UserDao();
		try {
			User user = dao.findByLogname(userName);
			if(user.getPassword().trim().equals(passWord.trim())) {
				this.user = user;
				return true;
			}				
		} catch (Exception e) {
			return false;
		}
		return false;
	}
	

	public Language getLanguage() {
		return language;
	}

	public void setLanguage(Language language) {
		this.language = language;
		if(language != null) {
			setLocale(language.getLocale());
		}
	}	
}
