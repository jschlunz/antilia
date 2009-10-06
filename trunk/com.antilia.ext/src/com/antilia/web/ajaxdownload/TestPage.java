package com.antilia.web.ajaxdownload;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.AjaxLink;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.model.AbstractReadOnlyModel;


/**
 * @author  Ernesto Reinaldo Barreiro (reiern70@gmail.com)
 *
 */
public class TestPage extends WebPage{

	private Label text;
	
	private String labelText = "Hi!";
	
	private DocumentResourceListener documentResourceListener;

	
	/**
	 * 
	 */
	public TestPage() {
		
		AjaxLink<Void> link = new AjaxLink<Void>("link") {
			
			private static final long serialVersionUID = 1L;

			@Override
			public void onClick(AjaxRequestTarget target) {
				TestPage.this.labelText = "Hi! and donwload a file!";
				if(target!= null) {
					target.addComponent(TestPage.this.text);
					String url = documentResourceListener.getURL().toString();
					target.appendJavascript(";window.location.href='"+url+"';");
				}
			}
		};
		
		add(link);
		text = new Label("text", new AbstractReadOnlyModel<String>() {
			
			private static final long serialVersionUID = 1L;

			@Override
			public String getObject() {
				return TestPage.this.labelText;
			}
		});
		text.setOutputMarkupId(true);
		add(text);
		
		documentResourceListener = new DocumentResourceListener("listener", new MyPdfResource());
		
		add(documentResourceListener);
	}

}
