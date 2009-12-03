/**
 * 
 */
package com.antilia.letsplay.components.failure.copy;

import org.apache.wicket.Component;
import org.apache.wicket.markup.html.image.NonCachingImage;
import org.apache.wicket.markup.html.image.resource.DynamicImageResource;

import com.antilia.letsplay.components.IFailureReporter;

/**
 * @author Ernesto Reinaldo Barreiro (reiern70@gmail.com)
 *
 */
public class ImageFailureReporter implements IFailureReporter {

	private static final long serialVersionUID = 1L;
	
	private static final ImageFailureReporter instance = new ImageFailureReporter();
	
	/**
	 * 
	 */
	private ImageFailureReporter() {
	}

	/* (non-Javadoc)
	 * @see com.antilia.letsplay.components.IFailureReporter#createErrorReporter(int)
	 */
	@Override
	public Component createErrorReporter(String id, final int level) {
		return new NonCachingImage(id, new DynamicImageResource() {
			
			private static final long serialVersionUID = 1L;

			@Override
			protected byte[] getImageData() {
				return new HangImage(level<=4?level: 4).getBytes();
			}
		}) ;
	}

	public static ImageFailureReporter getInstance() {
		return instance;
	}

}
