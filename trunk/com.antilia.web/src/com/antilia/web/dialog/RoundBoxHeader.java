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
 * @author Ernesto Reinaldo Barreiro (reiern70@gmail.com)
 *
 */
public class RoundBoxHeader extends Panel {

	private static final long serialVersionUID = 1L;
	
	private DefaultDialog defaultDialog;
	
	public RoundBoxHeader(String id, DefaultDialog defaultDialog) {
		super(id);
		this.defaultDialog = defaultDialog;
		setRenderBodyOnly(true);
		
		Image tr = new DiscriminatedImage("tr", new TopRightImg(
					getDefaultDialog().getDialogStyle().getBackgroundColor()
					, getDefaultDialog().isBrowserIExplorer6())) {
			private static final long serialVersionUID = 1L;

			@Override
			protected String getDiscriminator() {
				return getDefaultDialog().getDialogStyle().getBackgroundColor();
			}
		};
		
		add(tr);
		Image tl = new DiscriminatedImage("tl", new TopLeftImg(
					getDefaultDialog().getDialogStyle().getBackgroundColor(), 
					getDefaultDialog().isBrowserIExplorer6())) {
			
			private static final long serialVersionUID = 1L;

			protected String getDiscriminator() {
				return getDefaultDialog().getDialogStyle().getBackgroundColor();
			}
		};
		add(tl);
		tl.add(new AttributeModifier("id", true, new Model<String>() {
			private static final long serialVersionUID = 1L;

			@Override
			public String getObject() {
				return getDefaultDialog().getMarkupId() + "TopLeft";
			}
		}));
		
		WebMarkupContainer titleColumn = new WebMarkupContainer("titleColumn");
		titleColumn.add(new AttributeModifier("style", new Model<String>() {
			private static final long serialVersionUID = 1L;

			@Override
			public String getObject() {
				StringBuffer sb = new StringBuffer();
				sb.append("background-color: ");
				sb.append(getDefaultDialog().getDialogStyle().getBackgroundColor());				
				return sb.toString();
			}
		}));
		
		titleColumn.add(new AttributeModifier("ondblclick", new Model<String>() {
			private static final long serialVersionUID = 1L;

			@Override
			public String getObject() {
				if(getDefaultDialog().isFoldable())
					return getDefaultDialog().getMarkupId()+"Box.toggleFold();";
				else 
					return "";
			}
		}));
		
		add(titleColumn);
		
		Label ltitle = new Label("title", getDefaultDialog().getTitle()) {
			private static final long serialVersionUID = 1L;

			@Override
			public boolean isVisible() {
				return getDefaultDialog().getTitle() != null;
			}
		};
		
		ltitle.add(new AttributeModifier("style", new Model<String>() {
			private static final long serialVersionUID = 1L;

			@Override
			public String getObject() {
				return getDefaultDialog().getDialogStyle().getTitleStyle();
			}
		}));
		titleColumn.add(ltitle);
		
		
		Menu menu = getDefaultDialog().newTopMenu("boxTopMenu");		
		menu.setMenuStyle("width: auto; right: 0px; position: relative; height: 15px; float: right; background: transparent;");
		menu.setOutputMarkupId(false);
		menu.setRenderBodyOnly(true);
		
		titleColumn.add(menu);
	}

	public DefaultDialog getDefaultDialog() {
		return defaultDialog;
	}

	public void setDefaultDialog(DefaultDialog defaultDialog) {
		this.defaultDialog = defaultDialog;
	}
}
