/**
 * 
 */
package com.antilia.web.osgi;

import java.util.Map;

import org.apache.wicket.protocol.http.WicketServlet;
import org.osgi.framework.BundleContext;
import org.osgi.service.http.HttpContext;

import com.antilia.common.osgi.AggregatedActivator;

/**
 * 
 * @author Ernesto Reinaldo Barreiro (reiern70@gmail.com)
 */
public abstract class WebApplicationActivator extends AggregatedActivator {
	
	private static BundleContext context;	
	
	/**
	 * 
	 */
	public WebApplicationActivator() {
		super();
		addServiceActivator(new LoggingServiceActivator());
		addServiceActivator( new WicketServletServiceActivator() {

			private static final long serialVersionUID = 1L;

			@Override
			protected String getServletAlias() {
				return WebApplicationActivator.this.getServletAlias();
			}

			@Override
			protected Class<? extends org.apache.wicket.protocol.http.WebApplication> getApplicationClass() {
				return WebApplicationActivator.this.getApplicationClass();
			}
			
			@Override
			protected HttpContext getHttpContext() {
				return WebApplicationActivator.this.getHttpContext();
			}
			
			@Override
			protected Map<String, String> getInitparams() {
				return WebApplicationActivator.this.getInitparams();
			}
			
			@Override
			protected WicketServlet getWicketServlet() {
				return WebApplicationActivator.this.getWicketServlet();
			}
			
			public boolean isMandatory() {
				return true;
			}
			
		}				
		);
	}
	
	@Override
	public final void start(BundleContext context) throws Exception {
		WebApplicationActivator.context = context;
		super.start(context);
	}
	
	@Override
	public final void stop(BundleContext context) throws Exception {
		super.stop(context);
	}
	
	/**
	 * You should override this method to provide the WebApplication class of your Web application.
	 * 
	 * @return
	 */
	protected abstract Class <? extends org.apache.wicket.protocol.http.WebApplication> getApplicationClass();
	
	
	/**
	 * Returns the alias used to register the wicket servlet at the HttService.
	 * 
	 * @return
	 */
	protected abstract String getServletAlias();		
	
	/**
	 * Return an HttpContext by default we return null.
	 * @return
	 */
	protected HttpContext getHttpContext() {
		return null;
	}
	
	/**
	 * You should override this method if you want to pass additional initialization parameters to the WicketServlet.
	 * @return Additional parameters..
	 */
	protected Map<String, String> getInitparams() {
		return null;
	}
	
	
	/**
	 * Returns an instance of the WicketServlet to be registered at OSGi HttpService. In  
	 * case you need to override WicketServlet this method allows you to do so...
	 * 
	 * @return An instance of WicketServlet...
	 */
	protected WicketServlet getWicketServlet() {
		return new WicketServlet();
	}

	/**
	 * @return the context
	 */
	public static BundleContext getContext() {
		return context;
	}
}
