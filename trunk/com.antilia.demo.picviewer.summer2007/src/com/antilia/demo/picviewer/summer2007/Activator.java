package com.antilia.demo.picviewer.summer2007;

import com.antilia.common.osgi.AggregatedActivator;
import com.antilia.demo.picviewer.osgi.PicturesServiceTracker;
import com.antilia.demo.picviewer.summer2007.italy.ItalyPictures;
import com.antilia.demo.picviewer.summer2007.spain.SpainPictures;

public class Activator extends AggregatedActivator {

	public Activator() {
		PicturesServiceTracker tracker = new PicturesServiceTracker();
		tracker.add(new SpainPictures());
		tracker.add(new ItalyPictures());
		addServiceActivator(tracker);
		
	}
}
