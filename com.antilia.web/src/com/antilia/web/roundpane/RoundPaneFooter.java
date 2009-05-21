/**
 * This software is provided as IS by Antilia-Soft SL.
 * Copyright 2006-2007.
 */
package com.antilia.web.roundpane;

import org.apache.wicket.AttributeModifier;
import org.apache.wicket.Resource;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.image.Image;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.Model;

/**
 * @author Ernesto Reinaldo Barreiro (reiern70@gmail.com)
 *
 */
public class RoundPaneFooter extends Panel {

	private static final long serialVersionUID = 1L;
	
	private RoundPane roundBox;
	
	public RoundPaneFooter(String id, RoundPane roundBox) {
		super(id);
		this.roundBox = roundBox;
		setRenderBodyOnly(true);
		
		Image br = new DiscriminatedImage("br") {
			private static final long serialVersionUID = 1L;

			@Override
			protected String getDiscriminator() {
				return getRoundBox().getBoxStyle().getBackgroundColor();
			}
			
			@Override
			protected Resource getImageResource() {
				if(getRoundBox().isResizable()) {
					return new BottomRightDragImg(getRoundBox().getBoxStyle().getBackgroundColor(), getRoundBox().getBoxStyle().getHandleColor(), 
							getRoundBox().isBrowserIExplorer6());
				} else {
					return new BottomRightImg(getRoundBox().getBoxStyle().getBackgroundColor(), getRoundBox().isBrowserIExplorer6());
				}
			}
		}; 
		add(br);
		br.add(new AttributeModifier("id", new Model<String>() {
			private static final long serialVersionUID = 1L;

			@Override
			public String getObject() {
				if(getRoundBox().isResizable())
					return getRoundBox().getMarkupId() + "Handle";
				else 
					return getRoundBox().getMarkupId() + "NotHandle";
			}
			
		}));
		
		br.add(new AttributeModifier("style", new Model<String>() {
			private static final long serialVersionUID = 1L;

			@Override
			public String getObject() {
				if(getRoundBox().isResizable())
					return "display: none; cursor: nw-resize;";
				else 
					return "display: none;";
			}
			
		}));
			
		
		Image bl = new DiscriminatedImage("bl", new BottomLeftImg(getRoundBox().getBoxStyle().getBackgroundColor(), getRoundBox().isBrowserIExplorer6())) {
			private static final long serialVersionUID = 1L;

			@Override
			protected String getDiscriminator() {
				return getRoundBox().getBoxStyle().getBackgroundColor();
			}
		};
		add(bl);
		
		WebMarkupContainer titleColumnBottom = new WebMarkupContainer("titleColumnBottom");
		titleColumnBottom.add(new AttributeModifier("style", new Model<String>("background-color: "+getRoundBox().getBoxStyle().getBackgroundColor())));
		add(titleColumnBottom);
	}

	public RoundPane getRoundBox() {
		return roundBox;
	}

	public void setRoundBox(RoundPane roundBox) {
		this.roundBox = roundBox;
	}
}
