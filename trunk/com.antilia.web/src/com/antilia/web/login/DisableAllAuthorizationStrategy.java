/**
 * 
 */
package com.antilia.web.login;

import org.apache.wicket.Component;
import org.apache.wicket.RestartResponseAtInterceptPageException;
import org.apache.wicket.authorization.Action;
import org.apache.wicket.authorization.IAuthorizationStrategy;
import org.apache.wicket.authorization.IUnauthorizedComponentInstantiationListener;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.protocol.http.WebSession;

import com.antilia.web.IAuthenticableSession;

/**
 * @author EReinaldoB
 *
 */
public abstract class DisableAllAuthorizationStrategy implements
		IAuthorizationStrategy, IUnauthorizedComponentInstantiationListener {

	/**
	 * 
	 */
	public DisableAllAuthorizationStrategy() {
	}

	/* (non-Javadoc)
	 * @see org.apache.wicket.authorization.IAuthorizationStrategy#isActionAuthorized(org.apache.wicket.Component, org.apache.wicket.authorization.Action)
	 */
	public boolean isActionAuthorized(Component component, Action action) {
		return true;
	}

	/* (non-Javadoc)
	 * @see org.apache.wicket.authorization.IAuthorizationStrategy#isInstantiationAuthorized(java.lang.Class)
	 */
	public <T extends Component> boolean isInstantiationAuthorized(Class<T> componentClass) {
		if(IProtectedPage.class.isAssignableFrom(componentClass)) {
			if(WebSession.get() instanceof IAuthenticableSession) {
				return ((IAuthenticableSession)WebSession.get()).isAuthenticated();
			}
		}
		return true;
	}
	
	public void onUnauthorizedInstantiation(Component component) {
		throw new RestartResponseAtInterceptPageException(getSignInPage());
	}
	
	public abstract Class<? extends WebPage> getSignInPage(); 
}
