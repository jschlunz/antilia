package com.antilia.wicketstarter;

import java.util.Map;
import java.util.Properties;

import org.apache.wicket.protocol.http.WebApplication;
import org.apache.wicket.protocol.http.WicketServlet;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;
import org.osgi.service.http.HttpContext;
import org.osgi.service.http.HttpService;
import org.osgi.util.tracker.ServiceTracker;

/**
 *  WicketServletServiceActivator
 *  
 * @author Ernesto Reinaldo Barreiro (reiern70@gmail.com)
 */
public abstract class WicketServletServiceActivator implements IServiceActivator {

	private static final long serialVersionUID = 1L;

	private ServiceTracker httpServiceTracker;
	
	public static final String APPLICATION_CLASSNAME = "applicationClassName";
	
	
	/* (non-Javadoc)
	 * @see com.isencia.sherpa.dynamic.common.IServiceActivator#start(org.osgi.framework.BundleContext)
	 */
	public void start(BundleContext context) throws Exception {
		httpServiceTracker = new HttpServiceTracker(context);
		httpServiceTracker.open();
	}

	/* (non-Javadoc)
	 * @see com.isencia.sherpa.dynamic.common.IServiceActivator#stop(org.osgi.framework.BundleContext)
	 */
	public void stop(BundleContext context) throws Exception {
		httpServiceTracker.close();
		httpServiceTracker = null;
	}
	
	private class HttpServiceTracker extends ServiceTracker {

		public HttpServiceTracker(BundleContext context) {
			super(context, HttpService.class.getName(), null);
		}

		public Object addingService(ServiceReference reference) {
			HttpService httpService = (HttpService) context.getService(reference);
			try {			
				Properties initparams = new Properties();
				// add APPLICATION_CLASSNAME parameter
				initparams.put(APPLICATION_CLASSNAME, getApplicationClass().getName());
				// add additional parameters				
				Map<String, String> params = getInitparams();
				if(params != null) {
					for(String key: params.keySet()) {
						initparams.put(key, params.get(key));
					}
				}
				httpService.registerServlet("/" + getServletAlias(), getWicketServlet(), initparams, getHttpContext()); //$NON-NLS-1$
			} catch (Throwable e) {
				e.printStackTrace();
				throw new OsgiException(OsgiException.SERVICE_ACTIVATION_FAILURE, e);
			}
			return httpService;
		}		
		
		public void removedService(ServiceReference reference, Object service) {
			HttpService httpService = (HttpService) service;
			httpService.unregister("/" + getServletAlias()); 
			super.removedService(reference, service);
		}
	}
	
	/**
	 * You should override this method to provide the WebApplicationContext context of Wep application.
	 * 
	 * @return
	 */
	protected abstract Class <? extends WebApplication> getApplicationClass();
	
	
	/**
	 * Returns the context root of your WEB application (e.g. helloworld, compas, etc).
	 * 
	 * @return
	 */
	protected abstract String getServletAlias();		
	
	/**
	 * Return an HttpContext by default we return null (as the framework) will built a default one.
	 * @return
	 */
	protected HttpContext getHttpContext() {
		return null;
	}
	
	/**
	 * You should override this method if you want to pass additional init parameters to the WicketServlet.
	 * By default the parameter "applicationClassName" is passed to the servlet.
	 * @return
	 */
	protected Map<String, String> getInitparams() {
		return null;
	}
	
	
	/**
	 * Returns an instance of the WicketServlet to be added on top of OSGi HttpService. In  
	 * case you need to override WicketServlet this method allows you to do so.
	 * 
	 * @return
	 */
	protected WicketServlet getWicketServlet() {
		return new WicketServlet();
	}
}
