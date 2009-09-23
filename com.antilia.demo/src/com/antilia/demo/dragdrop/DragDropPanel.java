package com.antilia.demo.dragdrop;

import org.apache.wicket.ResourceReference;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.image.Image;
import org.apache.wicket.markup.html.panel.Panel;

import com.antilia.web.dragdrop.DraggableBehavior;
import com.antilia.web.scriptaculous.drag.DraggableTarget;
import com.antilia.web.scriptaculous.drag.Indicator;



/**
 * @author Ernesto Reinaldo Barreiro (reiern70@gmail.com)
 */
public class DragDropPanel extends Panel {

	private static final long serialVersionUID = 1L;

	private static ResourceReference PRODUCT =   new ResourceReference(DragDropPanel.class, "product.png");
	private static ResourceReference PRODUCT1 =   new ResourceReference(DragDropPanel.class, "product1.png");
	
	
	/**
	 * @param parent
	 * @param id
	 * @param model
	 */
	public DragDropPanel(String id) {
		super(id);		
		
		Indicator indicator = new Indicator();
		final DraggableTarget cart = new DraggableTarget("cart") {
			public void onDrop(String input, AjaxRequestTarget target)
			{
				System.out.println("Input: " + input + " was dropped on the DraggableTarget");
			}
		};

		WebMarkupContainer product1 = new WebMarkupContainer("product1");		
		Image image =  new Image("product1Img", PRODUCT);
		image.add(new DraggableBehavior() {
			private static final long serialVersionUID = 1L;

			public String getDraggableClassName()
			{
				return "draggable";
			}
		});
		product1.add(image);

		WebMarkupContainer product2 = new WebMarkupContainer("product2");				
		Image image2 = new Image("product2Img", PRODUCT1);
		image2.add(new DraggableBehavior() {
			private static final long serialVersionUID = 1L;

			public String getDraggableClassName()
			{
				return "draggable";
			}
		});
		product2.add(image2);

		cart.accepts(product1);
		cart.accepts(product2);

		add(cart);
		add(product1);
		add(product2);
		add(indicator);
	}
}
