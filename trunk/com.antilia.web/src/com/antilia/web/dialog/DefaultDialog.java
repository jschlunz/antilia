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
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.extensions.ajax.markup.html.modal.ModalWindow;
import org.apache.wicket.markup.ComponentTag;
import org.apache.wicket.markup.MarkupStream;
import org.apache.wicket.markup.html.CSSPackageResource;
import org.apache.wicket.markup.html.JavascriptPackageResource;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.markup.html.resources.CompressedResourceReference;
import org.apache.wicket.markup.html.resources.JavascriptResourceReference;
import org.apache.wicket.model.IModel;
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
public abstract class DefaultDialog extends Panel implements IDialogScope, IMenuItemsFactory {

	private static final long serialVersionUID = 1L;

	public static final int DEFAULT_MIN_WIDTH=200;
	
	public static final int DEFAULT_MIN_HEIGHT=200;
	
	public static final int DEFAULT_WIDTH=500;
	
	public static final int DEFAULT_HEIGHT=300;
	
	private boolean browserIExplorer;	
	
	private boolean browserIExplorer6 = false;
	
	private static ResourceReference JAVASCRIPT = new JavascriptResourceReference(
			ModalWindow.class, "res/modal.js");

	private static ResourceReference CSS = new CompressedResourceReference(ModalWindow.class,
			"res/modal.css");
		
	private int posX = 100;
	
	private int posY = 100;
	
	private int width = DEFAULT_WIDTH;
	
	private int height = DEFAULT_HEIGHT;
	
	private boolean modal = false;
	
	private boolean centered = false;
	
	private int minimumWidth = DEFAULT_MIN_WIDTH;
	
	private int minimumHeight = DEFAULT_MIN_HEIGHT;
	
	private IModel<String> title  = new Model<String>("Title");
	
	private IDialogScope parent;
	
	private Panel header;
	
	/**
	 * Flag to set the dialog as a top level one.
	 */
	private boolean isTopLevel = false;
	
	
	public boolean isTopLevel() {
		return isTopLevel;
	}

	public void setTopLevel(boolean isTopLevel) {
		this.isTopLevel = isTopLevel;
	}

	/**
	 * If the dialog is re-sizable or not
	 */
	private boolean resizable = true;
	
	/**
	 * If the dialog is draggable or not
	 */
	private boolean draggable = true;
	
	
	/**
	 * Flag that determines if the dialog is foldable.
	 */
	private boolean foldable = true;
	
	/**
	 * Flag that sets if the dialog has a close button.
	 */
	private boolean closeable = true;
	
	/**
	 * The button opening a dialog. It may be null;
	 */
	private IDialogLink dialogButton;
	
	private WebMarkupContainer innerPanel;
	
	private List<IDialogScope> dialogs = new ArrayList<IDialogScope>();
	
	private DialogStyle dialogStyle;
	
	private IMenuItemsFactory topMenuItemFactory;
	
	private String panelClass = "panel";
	
	private String onDragClass = "panelDrag";
	
	private String panelSelectedClass = "panelSelected";
	
	private WebMarkupContainer dialogBody;
	
	private Component body;
	
	/**
	 * Constructor.
	 * 
	 * @param id
	 */
	public DefaultDialog(String id) {
		this(id, null);
	}
	
	/**
	 * Constructor.
	 * 
	 * @param id
	 * @param button
	 */
	public DefaultDialog(String id, IDialogLink button) {
		this(id, button, new DefaultDialogStyle());
	}
	
	/**
	 * Constructor.
	 * 
	 * @param id
	 * @param button
	 */
	public DefaultDialog(String id, IDialogLink button, final DialogStyle dialogStyle) {
		super(id);
		this.dialogButton = button;
		this.dialogStyle = dialogStyle;
		setOutputMarkupId(true);
		
		ClientProperties properties = ((WebClientInfo)getRequestCycle().getClientInfo()).getProperties();
		setBrowserIExplorer(properties.isBrowserInternetExplorer());
		if(isBrowserIExplorer()) {
			if(properties.getBrowserVersionMajor() <=6) {
				setBrowserIExplorer6(true);
			}
		}
		add(new AntiliaVeilResource());
		
		add(CSSPackageResource.getHeaderContribution(CSS));
		add(JavascriptPackageResource.getHeaderContribution(JAVASCRIPT));
		
		// add the CSS style of the dialog.	
		add(CSSPackageResource.getHeaderContribution(getCSSStyle()));
		
		add(JavascriptPackageResource.getHeaderContribution(DefaultStyle.JS_PROTOTYPE));
		add(JavascriptPackageResource.getHeaderContribution(DefaultStyle.JS_EFFECT));
		
		add(JavascriptPackageResource.getHeaderContribution(DefaultStyle.JS_YUI_DOM_EVENT));
		add(JavascriptPackageResource.getHeaderContribution(DefaultStyle.JS_YUI_DOM_MIN));
		add(JavascriptPackageResource.getHeaderContribution(DefaultStyle.JS_YUI_ANIMATION));
		
		add(JavascriptPackageResource.getHeaderContribution(DefaultStyle.JS_COMMON));
		add(JavascriptPackageResource.getHeaderContribution(DefaultStyle.JS_DIALOG));			
		
		
				
				
	}
	
	@Override
	protected void onBeforeRender() {		
		
		if(innerPanel == null) {
			innerPanel = new WebMarkupContainer("dialog");
			innerPanel.setOutputMarkupId(true);
			innerPanel.add(new AntiliaVeilResource());
			
			
					
			add(innerPanel);
			
			innerPanel.add(new AttributeModifier("onmousedown", new Model<String>() {
				private static final long serialVersionUID = 1L;

				@Override
				public String getObject() {
					return "Antilia_dragPanels.orderPanels('"+DefaultDialog.this.getDialogId()+"');";
				}
			}));					
			innerPanel.add(new AttributeModifier("style", new Model<String>() {
				private static final long serialVersionUID = 1L;

				@Override
				public String getObject() {
					StringBuffer sb = new StringBuffer();
					sb.append("left: ");
					sb.append(DefaultDialog.this.getPosX());
					sb.append("px; top:");
					sb.append(DefaultDialog.this.getPosY());
					sb.append("px;");
					sb.append(" width:");
					sb.append(DefaultDialog.this.getWidth());
					sb.append("px;");
					sb.append(" height:");
					sb.append(DefaultDialog.this.getHeight());
					sb.append("px;");
					return sb.toString();
				}
			}));
							
			
			WebMarkupContainer dialogBackground = new WebMarkupContainer("dialogBackground");
			dialogBackground.add(new AttributeModifier("style", new Model<String>() {
				private static final long serialVersionUID = 1L;

				@Override
				public String getObject() {
					return "background-color: " + dialogStyle.getBackgroundColor();
				}
			}));	
			
			innerPanel.add(dialogBackground);
			
			dialogBody = new WebMarkupContainer("dialogBody");
			dialogBody.add(new AttributeModifier("id", new Model<String>() {

				private static final long serialVersionUID = 1L;

				@Override
				public String getObject() {
					return DefaultDialog.this.getDialogId()+"Body";
				}
			}));		
			
			
			dialogBody.add(new AttributeModifier("style", new Model<String>() {
				private static final long serialVersionUID = 1L;

				@Override
				public String getObject() {
					StringBuffer sb = new StringBuffer();
					sb.append("position: relative; left: 0px;");
					sb.append("; top: 0px;");
					sb.append(" width:");
					int width = DefaultDialog.this.getWidth();
					sb.append(width-4);
					sb.append("px;");
					sb.append(" height:");
					sb.append(DefaultDialog.this.getHeight()-28);
					sb.append("px;");
					sb.append("overflow: auto;");
					if(dialogStyle.getBodyColor() != null) {
						sb.append("background-color: ");				
						sb.append(dialogStyle.getBodyColor());
					}
					return sb.toString();
				}
			}));
			dialogBackground.add(dialogBody);
					
			WebMarkupContainer veil = new WebMarkupContainer("veil");
			veil.add(new AttributeModifier("id", new Model<String>() {

				private static final long serialVersionUID = 1L;

				@Override
				public String getObject() {
					return DefaultDialog.this.getDialogId()+"modal_overlay";
				}
			}));
			
			dialogBody.add(veil);
			
			
			WebMarkupContainer footer = new WebMarkupContainer("footer") ;
			footer.add(new AttributeModifier("style", new Model<String>() {
				
				private static final long serialVersionUID = 1L;

				@Override
				public String getObject() {
					return "background-color: " + dialogStyle.getBackgroundColor();
				}
			}));
			
			innerPanel.add(footer);
				
			WebMarkupContainer resizeHandle = new WebMarkupContainer("resizeHandle") {
				private static final long serialVersionUID = 1L;

				@Override
				public boolean isVisible() {
					return DefaultDialog.this.isResizable();
				}
			};
			resizeHandle.add(new AttributeModifier("id", new Model<String>() {

				private static final long serialVersionUID = 1L;

				@Override
				public String getObject() {
					return DefaultDialog.this.getDialogId()+"Resize";
				}
			}));
			
			footer.add(resizeHandle);
		}
		
		
		Label script = new Label("script", new Model<String>()) {
			private static final long serialVersionUID = 1L;

			@Override
			protected void onComponentTagBody(MarkupStream markupStream, ComponentTag openTag) {
				boolean ie = DefaultDialog.this.isBrowserIExplorer();
				String parentId =  DefaultDialog.this.getParentDialog()!=null?DefaultDialog.this.getParentDialog().getDialogId():"";
				StringBuffer sb = new StringBuffer();
				sb.append("Antilia_dragPanels.addAndRemovePanel('");
				sb.append(DefaultDialog.this.getDialogId());
				sb.append("','");
				sb.append(parentId);
				sb.append("',");
				sb.append(ie);
				sb.append(",");
				sb.append(DefaultDialog.this.getMinimumWidth());
				sb.append(",");
				sb.append(DefaultDialog.this.getMinimumHeight());
				sb.append(",");
				sb.append(DefaultDialog.this.isModal());
				sb.append(",'");
				sb.append(DefaultDialog.this.getPanelClass());
				sb.append("','");
				sb.append(DefaultDialog.this.getOnDragClass());
				sb.append("','");
				sb.append(DefaultDialog.this.getPanelSelectedClass());
				sb.append("',");
				sb.append(DefaultDialog.this.isCentered());
				sb.append(", false,");
				sb.append(DefaultDialog.this.isDraggable());
				sb.append(",");
				sb.append(DefaultDialog.this.isTopLevel());
				sb.append(");");
				replaceComponentTagBody(markupStream, openTag, sb.toString());
			}
		};
		addOrReplace(script);
		
		if(!dialogStyle.isRoundedHeader()) {
			panelClass = "panelSquare";
			panelSelectedClass = "panelSquareSelected";
		} else {
			//panelClass = "panelRound";
			//panelSelectedClass = "panelRoundSelected";
		}
		
		innerPanel.add(new AttributeModifier("class", new Model<String>() {
			private static final long serialVersionUID = 1L;

			@Override
			public String getObject() {
				return panelClass;
			}
		}));
		
		if(header == null) {
			header = newDialogHeader("header");						
			if(header != null)
				innerPanel.add(header);
			else {
				innerPanel.add(new Label("header", ""));
			}
		}
		
		if(body == null) {
			// call createBody to retrieve user defined body.
			body = createBody("body");
			if(body != null) {
				//body.setRenderBodyOnly(true);
				dialogBody.add(body);
			}
			else 
				dialogBody.add(new Label("body", ""));
		}
		super.onBeforeRender();
	}
	
	
	/**
	 * This method is called when the dialog is closed.
	 * 
	 * @param requestTarget
	 * @param actionKey
	 */
	protected void onClose(AjaxRequestTarget requestTarget, String actionKey) {
		
	}	
	
	protected ResourceReference getCSSStyle() {
		return DefaultStyle.CSS_DIALOG;
	}
	
	protected Menu newTopMenu(String id) {
		Menu menu = null;
		if(getTopMenuItemFactory() != null)
			 menu = Menu.createMenu(id, null,getTopMenuItemFactory());
		else 
			menu = Menu.createMenu(id, null, this);		
		return menu;
	}
	
	private IDialogScope findParentDialog() {
		return (IDialogScope)findParent(IDialogScope.class);
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
	
	/**
	 * Creates a new Dialog Header. Override it of you dare to define your 
	 * own header.
	 * 
	 * @param id
	 * @return
	 */
	protected Panel newDialogHeader(String id) {
		if(getDialogStyle().isRoundedHeader())
			return new DefaultHeaderRounded(id, this);
		return new DefaultHeaderSquare(id, this);
	}
	
	public void populateMenuItems(String menuId, IMenuItemHolder itemHolder) {
		
	}
	
	public String getDialogId() {
		if(this.innerPanel != null)
			return innerPanel.getMarkupId();
		return "dialog";
	}
	
	public String getVeilId() {
		return getDialogId();
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
	public IModel<String> getTitle() {
		return title;
	}

	/**
	 * @param title the title to set
	 */
	public void setTitle(IModel<String> title) {
		this.title = title;
	}
	
	/**
	 * @param title the title to set
	 */
	public void setTitle(String title) {
		this.title = new Model<String>(title);
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

	public IDialogScope getParentDialog() {
		return parent;
	}

	public void setParentDialog(DefaultDialog parent) {
		this.parent = parent;
	}
	
	public void addDialog(IDialogScope dialog) {
		dialogs.add(dialog);
	}
	
	//TODO: see if I can come up with an interface for Dialogs.
	public Iterator<IDialogScope> getDialogs(){
		return dialogs.iterator();
	}

	public IDialogLink getDialogButton() {
		return dialogButton;
	}

	public void setDialogButton(IDialogLink button) {
		this.dialogButton = button;
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

	public boolean isModal() {
		return modal;
	}

	public void setModal(boolean modal) {
		this.modal = modal;
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
	
	public void replaceBody(Component body) {
		this.body = body;
		this.dialogBody.addOrReplace(body);
	}

	public boolean isCentered() {
		return centered;
	}

	public void setCentered(boolean centered) {
		this.centered = centered;
	}

	/**
	 * @return the draggable
	 */
	public boolean isDraggable() {
		return draggable;
	}

	/**
	 * @param draggable the draggable to set
	 */
	public void setDraggable(boolean draggable) {
		this.draggable = draggable;
	}

	/**
	 * @return the closeable
	 */
	public boolean isCloseable() {
		return closeable;
	}

	/**
	 * @param closeable the closeable to set
	 */
	public void setCloseable(boolean closeable) {
		this.closeable = closeable;
	}
 
}
