/**
 * This software is provided as IS by Antilia-Soft SL.
 * Copyright 2006-2007.
 */
package com.antilia.web.dialog;

import org.apache.wicket.AttributeModifier;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.image.Image;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.Model;

import com.antilia.web.menu.Menu;
import com.antilia.web.roundpane.DiscriminatedImage;
import com.antilia.web.roundpane.TopLeftImg;
import com.antilia.web.roundpane.TopRightImg;

/**
 * 
 * @author Ernesto Reinaldo Barreiro (reiern70@gmail.com)
 */
public class DefaultHeaderSquare extends Panel  {
		
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private DefaultDialog dialog;
	/**
	 * @param id
	 */
	public DefaultHeaderSquare(String id, DefaultDialog dialog) {
		super(id);
		this.dialog = dialog;
		
		add(new AttributeModifier("id", new Model() {

			private static final long serialVersionUID = 1L;

			@Override
			public Object getObject() {
				return DefaultHeaderSquare.this.dialog.getDialogId()+"Header";
			}
		}));
		
		add(new AttributeModifier("onclick", true, new Model() {
			private static final long serialVersionUID = 1L;

			@Override
			public Object getObject() {
				return "Antilia_dragPanels.orderPanels('"+DefaultHeaderSquare.this.dialog.getDialogId()+"');";
			}
		}));
		
		/*
		Image tr = new DiscriminatedImage("tr", new TopRightImg(
				DefaultHeader1.this.getDialog().getDialogStyle().getBackgroundColor(), 
				DefaultHeader1.this.getDialog().isBrowserIExplorer6())) {
			private static final long serialVersionUID = 1L;

			@Override
			protected String getDiscriminator() {
				return DefaultHeader1.this.getDialog().getDialogStyle().getBackgroundColor();
			}
		};
		
		add(tr);
		Image tl = new DiscriminatedImage("tl", 
				new TopLeftImg(DefaultHeader1.this.getDialog().getDialogStyle().getBackgroundColor(), 
						DefaultHeader1.this.getDialog().isBrowserIExplorer6())) {
			
			private static final long serialVersionUID = 1L;

			protected String getDiscriminator() {
				return DefaultHeader1.this.getDialog().getDialogStyle().getBackgroundColor();
			}
		};
		add(tl);
		*/
		
		WebMarkupContainer middlePanel = new WebMarkupContainer("middlePanel");		
		middlePanel.add(new AttributeModifier("style", new Model(){
			private static final long serialVersionUID = 1L;

			@Override
			public Object getObject() {
				StringBuffer sb = new StringBuffer();
				sb.append("position: relative; height: 18px;"); 
				sb.append("background-color: ");
				sb.append(getDialog().getDialogStyle().getBackgroundColor());				
				sb.append("; margin:0px; padding: 0px; overflow:hidden;");
				return sb.toString();				
			}
		}));
		add(middlePanel);
		Label title = new Label("title", new Model() {			
			private static final long serialVersionUID = 1L;

			@Override
			public Object getObject() {
				return DefaultHeaderSquare.this.getDialog().getTitle();
			}
			
			@Override
			public void setObject(Object object) {
				super.setObject(object);
				if(object != null)
					DefaultHeaderSquare.this.getDialog().setTitle(object.toString());
			}
			
		});
		/*
		title.setOutputMarkupId(true);
		title.add(new AttributeModifier("id", new Model() {

			private static final long serialVersionUID = 1L;

			@Override
			public Object getObject() {
				return DefaultHeader.this.dialog.getDialogId()+"Title";
			}
		}));
		*/
		title.add(new AttributeModifier("style", new Model() {
			
			private static final long serialVersionUID = 1L;

			@Override
			public Object getObject() {
				return "float: left; text-align: center;"+DefaultHeaderSquare.this.getDialog().getDialogStyle().getTitleStyle();
			}
		}));		
		middlePanel.add(title);
		Menu menu = Menu.createMenu("menu", 
				new DialogHeaderMenuItemsFactory(DefaultHeaderSquare.this.getDialog()));		
		menu.setMenuStyle("width: auto; background: transparent; right: 0px; position: relative; float: right;");
		menu.setOutputMarkupId(false);
		menu.setRenderBodyOnly(true);		
		middlePanel.add(menu);
	}
	/**
	 * @return the dialog
	 */
	public DefaultDialog getDialog() {
		return dialog;
	}
	/**
	 * @param dialog the dialog to set
	 */
	public void setDialog(DefaultDialog dialog) {
		this.dialog = dialog;
	}
}
