/**
 * 
 */
package com.antilia.demo.manager;

import java.io.PrintWriter;

import org.apache.derby.drda.NetworkServerControl;
import org.osgi.framework.BundleContext;

import com.antilia.common.osgi.IServiceActivator;

/**
 * Iservcie Activator used to start derby.
 * 
 * @author Ernesto Reinaldo Barreiro (reiern70@gmail.com)
 *
 */
public class StartDerbyActivator implements IServiceActivator {

	NetworkServerControl derby = null;
	
	public StartDerbyActivator() {
		try {
			derby = new NetworkServerControl("reiern","reiern");
		} catch (Exception e) {			
			e.printStackTrace();
		}
	}
	/* (non-Javadoc)
	 * @see com.antilia.common.osgi.IServiceActivator#isMandatory()
	 */
	public boolean isMandatory() throws Exception {
		return true;
	}

	/* (non-Javadoc)
	 * @see com.antilia.common.osgi.IServiceActivator#start(org.osgi.framework.BundleContext)
	 */
	public void start(BundleContext context) throws Exception {
		derby.start(new PrintWriter(System.out));
	}

	/* (non-Javadoc)
	 * @see com.antilia.common.osgi.IServiceActivator#stop(org.osgi.framework.BundleContext)
	 */
	public void stop(BundleContext context) throws Exception {
		derby.shutdown();
	}

}
