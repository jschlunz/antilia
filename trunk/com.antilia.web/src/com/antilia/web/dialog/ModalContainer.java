/**
 * This software is provided as IS by Antilia-Soft SL.
 * Copyright 2006-2007.
 */
package com.antilia.web.dialog;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.wicket.AttributeModifier;
import org.apache.wicket.Component;
import org.apache.wicket.ResourceReference;
import org.apache.wicket.behavior.HeaderContributor;
import org.apache.wicket.markup.ComponentTag;
import org.apache.wicket.markup.MarkupStream;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.Model;
import org.apache.wicket.protocol.http.ClientProperties;
import org.apache.wicket.protocol.http.request.WebClientInfo;

import com.antilia.web.button.IMenuItemHolder;
import com.antilia.web.button.IMenuItemsFactory;
import com.antilia.web.menu.Menu;
import com.antilia.web.resources.DefaultStyle;
import com.antilia.web.veil.AntiliaVeilResource;

/**
 * 
 * @author Ernesto Reinaldo Barreiro (reiern70@gmail.com)
 */
public abstract class ModalContainer extends Panel implements IDialogScope, IMenuItemsFactory {

	private static final long serialVersionUID = 1L;

	public static final int DEFAULT_MIN_WIDTH=200;
	
	public static final int DEFAULT_MIN_HEIGHT=200;
	
	public static final int DEFAULT_WIDTH=500;
	
	public static final int DEFAULT_HEIGHT=300;
	
	private boolean browserIExplorer;	
	
	private boolean browserIExplorer6 = false;
	
	private int posX = 100;
	
	private int posY = 100;
	
	private int width = DEFAULT_WIDTH;
	
	private int height = DEFAULT_HEIGHT;
		
	private Integer widthPercentage = null;
	
	private Integer heightPercentage = null;
	
	private int minimumWidth = DEFAULT_MIN_WIDTH;
	
	private int minimumHeight = DEFAULT_MIN_HEIGHT;
	
	private String title  = "Title";
	
	private ModalContainer parent;
	
	/**
	 * If the dialog is re-sizable or not
	 */
	private boolean resizable = true;
	
	/**
	 * Flag that determines if the dialog is foldable.
	 */
	private boolean foldable = true;

	
	private WebMarkupContainer dialogBody;
	
	private WebMarkupContainer innerPanel;
	
	private List<IDialogScope> dialogs = new ArrayList<IDialogScope>();
	
	private DialogStyle dialogStyle;
	
	private IMenuItemsFactory topMenuItemFactory;
	
	private String panelClass = "modalContainerBody";
	
	private String onDragClass = "panelDrag";
	
	private String panelSelectedClass = "modalContainerBody";
	
	
	private Component body;
	
	/**
	 * Constructor.
	 * 
	 * @param id
	 */
	public ModalContainer(String id) {
		this(id, new DefaultDialogStyle());
	}
	
	/**
	 * Constructor.
	 * 
	 * @param id
	 * @param button
	 */
	public ModalContainer(String id,  final DialogStyle dialogStyle) {
		super(id);
		setPosX(0);
		setPosY(0);
		setWidth(900);
		setHeight(700);
		setHeightPercentage(100);
		this.dialogStyle = dialogStyle;
		setOutputMarkupId(true);
		ClientProperties properties = ((WebClientInfo)getRequestCycle().getClientInfo()).getProperties();
		setBrowserIExplorer(properties.isBrowserInternetExplorer());
		if(isBrowserIExplorer()) {
			if(properties.getBrowserVersionMajor() <=6) {
				setBrowserIExplorer6(true);
			}
		}
		
		// add the CSS style of the dialog.	
		add(HeaderContributor.forCss(getCSSStyle()));
		
		add(HeaderContributor.forJavaScript(DefaultStyle.JS_PROTOTYPE));
		add(HeaderContributor.forJavaScript(DefaultStyle.JS_EFFECT));
		
		add(HeaderContributor.forJavaScript(DefaultStyle.JS_YUI_DOM_EVENT));
		add(HeaderContributor.forJavaScript(DefaultStyle.JS_YUI_DOM_MIN));
		add(HeaderContributor.forJavaScript(DefaultStyle.JS_YUI_ANIMATION));
		
		add(HeaderContributor.forJavaScript(DefaultStyle.JS_COMMON));
		add(HeaderContributor.forJavaScript(DefaultStyle.JS_DIALOG));			
		
		innerPanel = new WebMarkupContainer("dialog");
		innerPanel.add(new AntiliaVeilResource());
		
		innerPanel.setOutputMarkupId(true);
		
		innerPanel.add(new AttributeModifier("class", new Model<String>() {
			private static final long serialVersionUID = 1L;

			@Override
			public String getObject() {
				return panelClass;
			}
		}));
				
		add(innerPanel);
		
		innerPanel.add(new AttributeModifier("onmousedown", new Model<String>() {
			private static final long serialVersionUID = 1L;

			@Override
			public String getObject() {
				return "Antilia_dragPanels.orderPanels('"+ModalContainer.this.getDialogId()+"');";
			}
		}));					
		innerPanel.add(new AttributeModifier("style", new Model<String>() {
			private static final long serialVersionUID = 1L;

			@Override
			public String getObject() {
				StringBuffer sb = new StringBuffer();
				sb.append("left: ");
				sb.append(ModalContainer.this.getPosX());
				sb.append("; top:");
				sb.append(ModalContainer.this.getPosY());
				sb.append("; position: absolute; overflow: auto;");
				sb.append("right:");
				sb.append("1");
				sb.append("px;");
				sb.append("bottom:");
				sb.append("1");
				sb.append("px;");
				//sb.append("border: solid 1px red;");
				sb.append("height: ");
				if(getHeightPercentage() != null)
					sb.append(getHeightPercentage() + "%");
				else 
					sb.append(ModalContainer.this.getHeight() + "px");
				if(dialogStyle.getBodyColor() != null) {
					sb.append("; background-color: ");				
					sb.append(dialogStyle.getBodyColor());
				}
				return sb.toString();
			}
		}));
				
		dialogBody = new WebMarkupContainer("dialogBody");
		dialogBody.add(new AttributeModifier("id", new Model<String>() {

			private static final long serialVersionUID = 1L;

			@Override
			public String getObject() {
				return ModalContainer.this.getDialogId()+"Body";
			}
		}));		
		dialogBody.add(new AttributeModifier("style", new Model<String>() {
			private static final long serialVersionUID = 1L;

			@Override
			public String getObject() {
				StringBuffer sb = new StringBuffer();
				sb.append("position: relative; left: 0px;");
				sb.append("; top: 0px;");
				sb.append("right:");
				sb.append(0);
				sb.append("px;");
				sb.append("bottom:");
				sb.append(0);
				sb.append("px;");
				sb.append("overflow: visible;");
				sb.append("height: auto;");
				//sb.append(ModalContainer.this.getHeight());
				if(dialogStyle.getBodyColor() != null) {
					sb.append("background-color: ");				
					sb.append(dialogStyle.getBodyColor());
				}
				return sb.toString();
			}
		}));
		
		//dialogBackground.add(dialogBody);
		
		innerPanel.add(dialogBody);
					
	}
	
	
	@Override
	protected void onBeforeRender() {
		super.onBeforeRender();
		
		if(body  == null) {
			// call createBody to retrieve user defined body.
			body = createBody(IDialogScope.BODY_ID);
			
			if(body != null) {
				//body.setRenderBodyOnly(true);
				dialogBody.add(body);
			}
			else 
				dialogBody.add(new Label(IDialogScope.BODY_ID, ""));
		}
		Label script = new Label("script", new Model<String>()) {
			private static final long serialVersionUID = 1L;

			@Override
			protected void onComponentTagBody(MarkupStream markupStream, ComponentTag openTag) {
				boolean ie = ModalContainer.this.isBrowserIExplorer();
				String parentId =  ModalContainer.this.getParentDialog()!=null?ModalContainer.this.getParentDialog().getDialogId():"";
				StringBuffer sb = new StringBuffer();
				sb.append("Antilia_dragPanels.addAndRemovePanel('");
				sb.append(ModalContainer.this.getDialogId());
				sb.append("','");
				sb.append(parentId);
				sb.append("',");
				sb.append(ie);
				sb.append(",");
				sb.append(ModalContainer.this.getMinimumWidth());
				sb.append(",");
				sb.append(ModalContainer.this.getMinimumHeight());
				sb.append(",");
				sb.append("false");
				sb.append(",'");
				sb.append(ModalContainer.this.getPanelClass());
				sb.append("','");
				sb.append(ModalContainer.this.getOnDragClass());
				sb.append("','");
				sb.append(ModalContainer.this.getPanelSelectedClass());
				sb.append("',");
				sb.append(false);
				sb.append(");");				
				replaceComponentTagBody(markupStream, openTag, sb.toString());
			}
		};
		addOrReplace(script);
	}
	
	protected void appendToScript(StringBuffer script) {
		
	}
	
	protected ResourceReference getCSSStyle() {
		return DefaultStyle.CSS_DIALOG;
	}
	
	protected Menu newTopMenu(String id) {
		Menu menu = null;
		if(getTopMenuItemFactory() != null)
			 menu = Menu.createMenu(id, getTopMenuItemFactory());
		else 
			menu = Menu.createMenu(id, this);		
		return menu;
	}
	
	private ModalContainer findParentDialog() {
		return (ModalContainer)findParent(ModalContainer.class);
	}
	
	@Override
	protected void onRender(MarkupStream markupStream) {		
		if(parent == null) {
			parent = findParentDialog();
			if(parent != null)
				parent.addDialog(this);
		}
		super.onRender(markupStream);
	}
	
	
	public void populateMenuItems(String menuId, IMenuItemHolder itemHolder) {
		
	}
	
	public String getDialogId() {
		if(this.innerPanel != null)
			return innerPanel.getMarkupId();
		return "dialog";
	}
	
	protected abstract Component createBody(String id);

	/**
	 * @return the posX
	 */
	public int getPosX() {
		return posX;
	}

	/**
	 * @param posX the posX to set
	 */
	public void setPosX(int posX) {
		this.posX = posX;
	}

	/**
	 * @return the posY
	 */
	public int getPosY() {
		return posY;
	}

	/**
	 * @param posY the posY to set
	 */
	public void setPosY(int posY) {
		this.posY = posY;
	}

	/**
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * @param title the title to set
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * @return the width
	 */
	public int getWidth() {
		return width;
	}

	/**
	 * @param width the width to set
	 */
	public void setWidth(int width) {
		this.width = width;
	}

	/**
	 * @return the height
	 */
	public int getHeight() {
		return height;
	}

	/**
	 * @param height the height to set
	 */
	public void setHeight(int height) {
		this.height = height;
	}

	/**
	 * @return the minimizedIcon
	 */
	public ResourceReference getMinimizedIcon() {
		return DefaultStyle.IMG_MINIMIZED;
	}


	/**
	 * @return the innerPanel
	 */
	public WebMarkupContainer getInnerPanel() {
		return innerPanel;
	}

	public boolean isBrowserIExplorer() {
		return browserIExplorer;
	}

	public void setBrowserIExplorer(boolean exporer) {
		this.browserIExplorer = exporer;
	}

	public ModalContainer getParentDialog() {
		return parent;
	}

	public void setParentDialog(ModalContainer parent) {
		this.parent = parent;
	}
	
	@Override
	public void addDialog(IDialogScope dialog) {
		dialogs.add(dialog);
	}
	
	public Iterator<IDialogScope> getDialogs(){
		return dialogs.iterator();
	}

	public int getMinimumWidth() {
		return minimumWidth;
	}

	public void setMinimumWidth(int minimumWidth) {
		this.minimumWidth = minimumWidth;
	}

	public int getMinimumHeight() {
		return minimumHeight;
	}

	public void setMinimumHeight(int minimumHeight) {
		this.minimumHeight = minimumHeight;
	}

	public boolean isResizable() {
		return resizable;
	}

	public void setResizable(boolean rezizable) {
		this.resizable = rezizable;
	}

	public boolean isFoldable() {
		return foldable;
	}

	public void setFoldable(boolean foldable) {
		this.foldable = foldable;
	}

	public DialogStyle getDialogStyle() {
		return dialogStyle;
	}

	public void setDialogStyle(DialogStyle dialogStyle) {
		this.dialogStyle = dialogStyle;
	}

	public boolean isBrowserIExplorer6() {
		return browserIExplorer6;
	}

	public void setBrowserIExplorer6(boolean browserIExplorer6) {
		this.browserIExplorer6 = browserIExplorer6;
	}

	public IMenuItemsFactory getTopMenuItemFactory() {
		return topMenuItemFactory;
	}

	public void setTopMenuItemFactory(IMenuItemsFactory topMenuItemFactory) {
		this.topMenuItemFactory = topMenuItemFactory;
	}

	/**
	 * @return the panelClass
	 */
	public String getPanelClass() {
		return panelClass;
	}

	/**
	 * @param panelClass the panelClass to set
	 */
	public void setPanelClass(String panelClass) {
		this.panelClass = panelClass;
	}

	/**
	 * @return the onDragClass
	 */
	public String getOnDragClass() {
		return onDragClass;
	}

	/**
	 * @param onDragClass the onDragClass to set
	 */
	public void setOnDragClass(String onDragClass) {
		this.onDragClass = onDragClass;
	}

	/**
	 * @return the panelSelectedClass
	 */
	public String getPanelSelectedClass() {
		return panelSelectedClass;
	}

	/**
	 * @param panelSelectedClass the panelSelectedClass to set
	 */
	public void setPanelSelectedClass(String panelSelectedClass) {
		this.panelSelectedClass = panelSelectedClass;
	}

	/**
	 * @return the widthPercentage
	 */
	public int getWidthPercentage() {
		return widthPercentage;
	}

	/**
	 * @param widthPercentage the widthPercentage to set
	 */
	public void setWidthPercentage(Integer widthPercentage) {
		this.widthPercentage = widthPercentage;
	}

	/**
	 * @return the heightPercentage
	 */
	public Integer getHeightPercentage() {
		return heightPercentage;
	}

	/**
	 * @param heightPercentage the heightPercentage to set
	 */
	public void setHeightPercentage(int heightPercentage) {
		this.heightPercentage = heightPercentage;
	}

	public void replaceBody(Component body) {
		this.body = body;
		this.dialogBody.addOrReplace(body);
	}
 
}
