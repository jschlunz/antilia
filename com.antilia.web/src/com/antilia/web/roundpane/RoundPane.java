/**
 * This software is provided as IS by Antilia-Soft SL.
 * Copyright 2006-2007.
 */
package com.antilia.web.roundpane;

import org.apache.wicket.AttributeModifier;
import org.apache.wicket.Component;
import org.apache.wicket.ResourceReference;
import org.apache.wicket.behavior.HeaderContributor;
import org.apache.wicket.markup.ComponentTag;
import org.apache.wicket.markup.MarkupStream;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.HiddenField;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.Model;
import org.apache.wicket.protocol.http.ClientProperties;
import org.apache.wicket.protocol.http.request.WebClientInfo;

import com.antilia.web.button.IMenuItemHolder;
import com.antilia.web.button.IMenuItemsFactory;
import com.antilia.web.menu.Menu;
import com.antilia.web.resources.DefaultStyle;

/**
 * @author Ernesto Reinaldo Barreiro (reiern70@gmail.com)
 *
 */
public class RoundPane extends Panel implements IMenuItemsFactory {

	private static final long serialVersionUID = 1L;

	public static final int DEFAULT_MIN_WIDTH=200;
	
	public static final int DEFAULT_MIN_HEIGHT=200;
	
	public static final int DEFAULT_WIDTH=500;
	
	public static final int DEFAULT_HEIGHT=300;
	
	private String title;
	
	public static final ResourceReference RBOX_CSS = new ResourceReference(RoundPane.class, "roundpane.css");
	
	public static final ResourceReference RBOX_JS = new ResourceReference(RoundPane.class, "roundpane.js");
	
	private boolean resizable = false;
	
	private boolean foldable = true;
	
	private boolean browserIExplorer;
	
	private boolean browserIExplorer6 = false;
	
	private RoundPaneStyle boxStyle;
	
	private WebMarkupContainer body;
	
	private WebMarkupContainer roundpane;
	
	private int width = 500; 
	
	private int height = 300;
	
	private int minWidth = 200; 
	
	private int minHeight = 200;
	
	private boolean centered = false;
	
	private Integer top = null; 
	
	private Integer left = null;
		
	private IMenuItemsFactory topMenuItemFactory;
	
	/**
	 * @param id
	 */
	public RoundPane(String id, String title, final RoundPaneStyle boxStyle) {
		super(id);
		this.boxStyle = boxStyle;
		this.title = title;
		setRenderBodyOnly(true);	
		roundpane = new WebMarkupContainer("roundbox") {
			
			private static final long serialVersionUID = 1L;

			@Override
			protected void onBeforeRender() {
				Label script = new Label("script", new Model<String>()) {
					private static final long serialVersionUID = 1L;

					@Override
					protected void onComponentTagBody(MarkupStream markupStream, ComponentTag openTag) {
						boolean ie = RoundPane.this.isBrowserIExplorer();
						StringBuffer sb = new StringBuffer();
						sb.append(RoundPane.this.getMarkupId()+"Box =");
						sb.append("new RoundPane('");
						sb.append(RoundPane.this.getMarkupId());
						sb.append("',");
						sb.append(ie);
						sb.append(",");
						sb.append(RoundPane.this.getMinWidth());
						sb.append(",");
						sb.append(RoundPane.this.getMinHeight());
						sb.append(",");
						sb.append(RoundPane.this.isCentered());
						sb.append(");");			
						
						replaceComponentTagBody(markupStream, openTag, sb.toString());
					}
				};
				addOrReplace(script);
				
				super.onBeforeRender();
			}
		};
		roundpane.setOutputMarkupId(true);
		roundpane.add(new AttributeModifier("style", true, new Model<String>() {
			private static final long serialVersionUID = 1L;

			@Override
			public String getObject() {
				StringBuffer sb = new StringBuffer();				
				sb.append("width: ");
				sb.append(RoundPane.this.getWidth());
				sb.append("px; height: ");
				sb.append(RoundPane.this.getHeight());
				sb.append("px;");
				if(RoundPane.this.getTop() != null) {
					sb.append("position: absolute;");
					sb.append("top: ");
					sb.append(RoundPane.this.getTop());
					sb.append("px;");
				}
				if(RoundPane.this.getLeft() != null) {
					sb.append("left: ");
					sb.append(RoundPane.this.getLeft());
					sb.append("px;");
				}
				return sb.toString();
			}
		}));
		add(roundpane);
		
		HiddenField<Integer> widthField = new HiddenField<Integer>("width", new Model<Integer>() {
			
			private static final long serialVersionUID = 1L;

			@Override
			public Integer getObject() {
				return new Integer(width);
			}
			
			@Override
			public void setObject(Integer object) {
				super.setObject(object);
				try {
					setWidth(new Integer(object.toString()));
				} catch (Exception e) {					
				}
			}
		});
		
		widthField.add(new AttributeModifier("id", new Model<String>() {
			private static final long serialVersionUID = 1L;

			@Override
			public String getObject() {
				return RoundPane.this.getMarkupId() + "Width";
			}
			
		}));
		
		roundpane.add(widthField);
		
		HiddenField<Integer> heightField = new HiddenField<Integer>("height", new Model<Integer>() {
			
			private static final long serialVersionUID = 1L;

			@Override
			public Integer getObject() {
				return new Integer(height);
			}
			
			@Override
			public void setObject(Integer object) {
				super.setObject(object);
				try {
					setHeight(new Integer(object.toString()));
				} catch (Exception e) {					
				}
			}
		});
		
		heightField.add(new AttributeModifier("id", new Model<String>() {
			private static final long serialVersionUID = 1L;

			@Override
			public String getObject() {
				return RoundPane.this.getMarkupId() + "Height";
			}
			
		}));
		roundpane.add(heightField);			
		
		ClientProperties properties = ((WebClientInfo)getRequestCycle().getClientInfo()).getProperties();
		setBrowserIExplorer(properties.isBrowserInternetExplorer());
		if(isBrowserIExplorer()) {
			if(properties.getBrowserVersionMajor() <=6) {
				setBrowserIExplorer6(true);
			}
		}
		add(HeaderContributor.forCss(RBOX_CSS));
		add(HeaderContributor.forJavaScript(DefaultStyle.JS_COMMON));
		add(HeaderContributor.forJavaScript(DefaultStyle.JS_PROTOTYPE));
		add(HeaderContributor.forJavaScript(DefaultStyle.JS_EFFECT));
		
		add(HeaderContributor.forJavaScript(DefaultStyle.JS_YUI_DOM_EVENT));
		add(HeaderContributor.forJavaScript(DefaultStyle.JS_YUI_DOM_MIN));
		add(HeaderContributor.forJavaScript(DefaultStyle.JS_YUI_ANIMATION));
		
		add(HeaderContributor.forJavaScript(RBOX_JS));
		
		
		// creating the header panel
		roundpane.add(new RoundPaneHeader("roundboxHeader", this));
		
		WebMarkupContainer middlePanel = new WebMarkupContainer("middlePanel");
		middlePanel.add(new AttributeModifier("style", new Model<String>("background-color: "+getBoxStyle().getBackgroundColor())));
		roundpane.add(middlePanel);
		
		// creating the body
		body = new WebMarkupContainer("body");
		if(getBoxStyle().getBodyStyle() != null)
			body.add(new AttributeModifier("style", new Model<String>(getBoxStyle().getBodyStyle())));
		body.add(new AttributeModifier("id", new Model<String>() {
			private static final long serialVersionUID = 1L;

			@Override
			public String getObject() {
				return RoundPane.this.getMarkupId() + "Body";
			}
			
		}));
		body.add(new AttributeModifier("style", true, new Model<String>() {
			private static final long serialVersionUID = 1L;

			@Override
			public String getObject() {
				StringBuffer sb = new StringBuffer();
				if(getBoxStyle().getBodyStyle() != null)
					sb.append(getBoxStyle().getBodyStyle());
				sb.append("width: ");
				if(RoundPane.this.isBrowserIExplorer()) {
					sb.append(RoundPane.this.getWidth()-10);
				} else {
					sb.append(RoundPane.this.getWidth()-12);
				}
				sb.append("px; height: ");
				sb.append(RoundPane.this.getHeight()-30);
				sb.append("px;");
				return sb.toString();
			}
		}));
		middlePanel.add(body);
		
		// adding footer
		roundpane.add(new RoundPaneFooter("roundboxFooter", this));
	}
	
	
	protected Menu newTopMenu(String id) {
		Menu menu = null;
		if(getTopMenuItemFactory() != null)
			 menu = Menu.createMenu(id, getTopMenuItemFactory());
		else 
			menu = Menu.createMenu(id, this);		
		return menu;
	}
	
	public final WebMarkupContainer addToBody(final Component child) {
		body.add(child);
		return body;
	}
	
	@Override
	public String getMarkupId() {
		if(roundpane != null) {
			return roundpane.getMarkupId();
		}
		return super.getMarkupId();
	}
	

	public String getTitle() {
		return title;
	}


	public void setTitle(String title) {
		this.title = title;
	}


	public boolean isResizable() {
		return resizable;
	}


	public void setResizable(boolean resizable) {
		this.resizable = resizable;
	}


	public boolean isBrowserIExplorer() {
		return browserIExplorer;
	}


	public void setBrowserIExplorer(boolean browserIExplorer) {
		this.browserIExplorer = browserIExplorer;
	}



	public boolean isBrowserIExplorer6() {
		return browserIExplorer6;
	}



	public void setBrowserIExplorer6(boolean browserIExplorer6) {
		this.browserIExplorer6 = browserIExplorer6;
	}



	public RoundPaneStyle getBoxStyle() {
		return boxStyle;
	}



	public void setBoxStyle(RoundPaneStyle boxStyle) {
		this.boxStyle = boxStyle;
	}

	public boolean isFoldable() {
		return foldable;
	}

	public void setFoldable(boolean foldable) {
		this.foldable = foldable;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public int getMinWidth() {
		return minWidth;
	}

	public void setMinWidth(int minWidth) {
		this.minWidth = minWidth;
	}

	public int getMinHeight() {
		return minHeight;
	}

	public void setMinHeight(int minHeight) {
		this.minHeight = minHeight;
	}

	public WebMarkupContainer getRoundpane() {
		return roundpane;
	}

	public void setRoundpane(WebMarkupContainer roundbox) {
		this.roundpane = roundbox;
	}

	public void populateMenuItems(String menuId, IMenuItemHolder itemHolder) {
		
	}

	public IMenuItemsFactory getTopMenuItemFactory() {
		return topMenuItemFactory;
	}

	public void setTopMenuItemFactory(IMenuItemsFactory menuItemsFactory) {
		topMenuItemFactory = menuItemsFactory;
	}


	public Integer getTop() {
		return top;
	}


	public void setTop(Integer top) {
		this.top = top;
	}


	public Integer getLeft() {
		return left;
	}


	public void setLeft(Integer left) {
		this.left = left;
	}


	public boolean isCentered() {
		return centered;
	}


	public void setCentered(boolean centered) {
		this.centered = centered;
	}	
}
