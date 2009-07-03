package com.antilia.web.osgi;

import java.util.Map;
import java.util.Properties;

import javax.servlet.Servlet;

import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;
import org.osgi.service.http.HttpContext;
import org.osgi.service.http.HttpService;
import org.osgi.util.tracker.ServiceTracker;

import com.antilia.common.osgi.IServiceActivator;

/**
 *  {@link ServletServiceActivator} allows to register an arbitrary Servlet.
 *  
 * @author Ernesto Reinaldo Barreiro (reiern70@gmail.com)
 */
public abstract class ServletServiceActivator<S extends Servlet> implements IServiceActivator {

	private static final long serialVersionUID = 1L;

	private ServiceTracker httpServiceTracker;
	

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
				// add additional parameters				
				Map<String, String> params = getInitparams();
				if(params != null) {
					for(String key: params.keySet()) {
						initparams.put(key, params.get(key));
					}
				}
				httpService.registerServlet("/" + getServletAlias(), getServlet(), initparams, getHttpContext()); //$NON-NLS-1$
			} catch (Throwable e) {
				e.printStackTrace();
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
	 * Returns the context root of your WEB application
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
	protected abstract S getServlet();
}
