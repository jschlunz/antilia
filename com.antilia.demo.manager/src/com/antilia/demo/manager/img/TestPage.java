/**
 * 
 */
package com.antilia.demo.manager.img;

import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.image.Image;

import com.antilia.demo.manager.img.MyImageFactory.MODE;

/**
 * @author  Ernesto Reinaldo Barreiro (reiern70@gmail.com)
 *
 */
public class TestPage extends WebPage {

	
	/**
	 * 
	 */
	public TestPage() {
		Image img = MyImageFactory.createImage("img", "test.png", MODE.FROM_FOLDER);
		add(img);
	}
		
}
