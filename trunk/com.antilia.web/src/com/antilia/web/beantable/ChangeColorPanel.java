/**
 * 
 */
package com.antilia.web.beantable;

import org.apache.wicket.AttributeModifier;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.AbstractReadOnlyModel;

/**
 * @author EReinaldoB
 *
 */
public class ChangeColorPanel extends Panel {

	private static final long serialVersionUID = 1L;

	
	private WebMarkupContainer colorPanel;
	/**
	 * @param id
	 */
	public ChangeColorPanel(String id) {
		super(id);
		
		colorPanel = new WebMarkupContainer("colorPanel");
		colorPanel.setOutputMarkupId(true);
		add(colorPanel);
		
		WebMarkupContainer red = new WebMarkupContainer("red");
		red.add(new AttributeModifier("onclick", new AbstractReadOnlyModel<String>() {
			
			private static final long serialVersionUID = 1L;

			@Override
			public String getObject() {
				String id = colorPanel.getMarkupId();
				return "document.getElementById('" + id + "').style.backgroundColor='red'";
			}
		}));	
		add(red);
		
		WebMarkupContainer blue = new WebMarkupContainer("blue");
		blue.add(new AttributeModifier("onclick", new AbstractReadOnlyModel<String>() {
			
			private static final long serialVersionUID = 1L;

			@Override
			public String getObject() {
				String id = colorPanel.getMarkupId();
				return "document.getElementById('" + id + "').style.backgroundColor='blue'";
			}
		}));	
		add(blue);
		
		WebMarkupContainer green = new WebMarkupContainer("green");
		green.add(new AttributeModifier("onclick", new AbstractReadOnlyModel<String>() {
			
			private static final long serialVersionUID = 1L;

			@Override
			public String getObject() {
				String id = colorPanel.getMarkupId();
				return "document.getElementById('" + id + "').style.backgroundColor='green'";
			}
		}));	
		add(green);
	}

}
