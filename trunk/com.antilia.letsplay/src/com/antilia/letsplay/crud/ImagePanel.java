package com.antilia.letsplay.crud;

import org.apache.wicket.markup.html.image.NonCachingImage;
import org.apache.wicket.markup.html.image.resource.DynamicImageResource;
import org.apache.wicket.markup.html.panel.Panel;

import com.antilia.letsplay.domain.DImage;

/**
 * @author Ernesto Reinaldo Barreiro (reiern70@gmail.com)
 *
 */
public class ImagePanel extends Panel {

	private static final long serialVersionUID = 1L;
	
	/**
	 * @param id
	 */
	public ImagePanel(String id, final DImage img) {
		super(id);
		NonCachingImage image = new NonCachingImage("image", new DynamicImageResource("jpeg") {
			private static final long serialVersionUID = 1L;

			@Override
			protected byte[] getImageData() {
				return img.getBytes();
			}
		});
		add(image);
	}
}
