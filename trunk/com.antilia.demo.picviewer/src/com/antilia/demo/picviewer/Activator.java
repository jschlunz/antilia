package com.antilia.demo.picviewer;

import org.osgi.framework.ServiceReference;

import com.antilia.demo.picviewer.osgi.IPicturesService;
import com.antilia.demo.picviewer.osgi.PicturesServiceActivator;
import com.antilia.web.WebApplication;
import com.antilia.web.osgi.WebApplicationActivator;

public class Activator extends WebApplicationActivator {

	public Activator(){
		addServiceActivator(new PicturesServiceActivator());
	}
	
	@Override
	protected Class<? extends WebApplication> getApplicationClass() {
		return PicViewerApplication.class;
	}
	
	@Override
	protected String getServletAlias() {
		return "picviewer";
	}

	public static final IPicturesService getPicturesService() {
		ServiceReference reference = getContext().getServiceReference(IPicturesService.class.getName());
		if(reference != null) {
			return (IPicturesService)getContext().getService(reference);
		}
		return null;
	}
}
