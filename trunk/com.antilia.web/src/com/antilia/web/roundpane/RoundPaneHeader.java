/**
 * This software is provided as IS by Antilia-Soft SL.
 * Copyright 2006-2007.
 */
package com.antilia.web.roundpane;

import org.apache.wicket.AttributeModifier;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.image.Image;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.Model;
import org.apache.wicket.model.PropertyModel;

import com.antilia.web.menu.Menu;

/**
 * @author Ernesto Reinaldo Barreiro (reiern70@gmail.com)
 *
 */
public class RoundPaneHeader extends Panel {

	private static final long serialVersionUID = 1L;
	
	private RoundPane roundPane;
	
	private WebMarkupContainer titleColumn;
	
	public RoundPaneHeader(String id, RoundPane roundPane) {
		super(id);
		this.roundPane = roundPane;
		setRenderBodyOnly(true);
		
		Image tr = new DiscriminatedImage("tr", new TopRightImg(getRoundBox().getBoxStyle().getBackgroundColor(), getRoundBox().isBrowserIExplorer6())) {
			private static final long serialVersionUID = 1L;

			@Override
			protected String getDiscriminator() {
				return getRoundBox().getBoxStyle().getBackgroundColor();
			}
		};
		
		add(tr);
		Image tl = new DiscriminatedImage("tl", new TopLeftImg(getRoundBox().getBoxStyle().getBackgroundColor(), getRoundBox().isBrowserIExplorer6())) {
			
			private static final long serialVersionUID = 1L;

			protected String getDiscriminator() {
				return getRoundBox().getBoxStyle().getBackgroundColor();
			}
		};
		add(tl);
		tl.add(new AttributeModifier("id", true, new Model<String>() {
			private static final long serialVersionUID = 1L;

			@Override
			public String getObject() {
				return getRoundBox().getMarkupId() + "TopLeft";
			}
		}));
		
		titleColumn = new WebMarkupContainer("titleColumn");
		titleColumn.add(new AttributeModifier("style", new Model<String>() {
			private static final long serialVersionUID = 1L;

			@Override
			public String getObject() {
				StringBuffer sb = new StringBuffer();
				sb.append("background-color: ");
				sb.append(getRoundBox().getBoxStyle().getBackgroundColor());				
				return sb.toString();
			}
		}));
		
		titleColumn.add(new AttributeModifier("ondblclick", new Model<String>() {
			private static final long serialVersionUID = 1L;

			@Override
			public String getObject() {
				if(getRoundBox().isFoldable())
					return getRoundBox().getMarkupId()+"Box.toggleFold();";
				else 
					return "";
			}
		}));
		
		add(titleColumn);
		
		Label ltitle = new Label("title", new PropertyModel(getRoundBox(), "title")) {
			private static final long serialVersionUID = 1L;

			@Override
			public boolean isVisible() {
				return getRoundBox().getTitle() != null;
			}
		};
		
		ltitle.add(new AttributeModifier("style", new Model<String>() {
			private static final long serialVersionUID = 1L;

			@Override
			public String getObject() {
				return getRoundBox().getBoxStyle().getTitleStyle();
			}
		}));
		titleColumn.add(ltitle);
			
	}

	@Override
	protected void onBeforeRender() {
		Menu menu = getRoundBox().newTopMenu("boxTopMenu");		
		menu.setMenuStyle("width: auto; right: 0px; position: relative; height: 15px; float: right; background: transparent;");
		menu.setOutputMarkupId(false);
		menu.setRenderBodyOnly(true);
		
		titleColumn.addOrReplace(menu);
		super.onBeforeRender();
	}
	
	public RoundPane getRoundBox() {
		return roundPane;
	}

	public void setRoundBox(RoundPane roundBox) {
		this.roundPane = roundBox;
	}
}
