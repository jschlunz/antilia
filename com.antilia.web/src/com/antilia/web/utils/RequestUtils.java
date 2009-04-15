/**
 * 
 */
package com.antilia.web.utils;

import org.apache.wicket.RequestCycle;
import org.apache.wicket.protocol.http.ClientProperties;
import org.apache.wicket.protocol.http.request.WebClientInfo;

/**
 * @author Ernesto Reinaldo Barreiro (reirn70@gmail.com)
 *
 */
public class RequestUtils {
	
	public static Boolean isBrowserIeExplorer6() {
		ClientProperties properties = ((WebClientInfo)RequestCycle.get().getClientInfo()).getProperties();
		if(properties.isBrowserInternetExplorer()) {
			if(properties.getBrowserVersionMajor() <=6) {
				return true;
			}
		}
		return false;
	}

}
