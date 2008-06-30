/**
 * 
 */
package com.antilia.wicketstarter;

import java.util.Map;

import org.apache.wicket.protocol.http.WebApplication;
import org.apache.wicket.protocol.http.WicketServlet;
import org.osgi.framework.BundleContext;
import org.osgi.service.http.HttpContext;

/**
 * @author Ernesto Reinaldo Barreiro (reiern70@gmail.com)
 *
 */
public abstract class WebApplicationActivator extends AbstractActivator {
	
	private static BundleContext context;	
	
	/**
	 * 
	 */
	public WebApplicationActivator() {
		super();

		addServiceActivator( new WicketServletServiceActivator() {

			private static final long serialVersionUID = 1L;

			@Override
			protected String getServletAlias() {
				return WebApplicationActivator.this.getServletAlias();
			}

			@Override
			protected Class<? extends WebApplication> getApplicationClass() {
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
	 * You should override this method to provide the WebApplicationContext context of Wep application.
	 * 
	 * @return
	 */
	protected abstract Class <? extends WebApplication> getApplicationClass();
	
	
	/**
	 * Returns the context root of your WEB application (e.g. helloworld etc).
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

	/**
	 * @return the context
	 */
	public static BundleContext getContext() {
		return context;
	}
}
