/**
 * 
 */
package com.antilia.demo.manager.img;

import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.image.Image;
import org.apache.wicket.util.file.Folder;

/**
 * @author  Ernesto Reinaldo Barreiro (reiern70@gmail.com)
 *
 */
public class TestPage extends WebPage {

	private static final MountedImageFactory IMAGE_FACTORY = new MountedImageFactory() {
		
		@Override
		protected Folder getFolder() {
			return new Folder("C:/temp/images");
		}
		
		@Override
		protected String getMountPoint() {
			return "test";
		}
		
	};
	
	/**
	 * 
	 */
	public TestPage() {
		Image img = IMAGE_FACTORY.createImage("img", "test.png");
		add(img);
	}
		
}
