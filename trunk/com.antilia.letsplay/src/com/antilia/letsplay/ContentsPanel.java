/**
 * 
 */
package com.antilia.letsplay;

import org.apache.wicket.Component;
import org.apache.wicket.ResourceReference;
import org.apache.wicket.markup.html.panel.EmptyPanel;
import org.apache.wicket.markup.html.panel.Panel;

import com.antilia.web.button.IMenuItemHolder;
import com.antilia.web.button.SeparatorButton;
import com.antilia.web.dialog.DefaultDialog;
import com.antilia.web.dialog.DialogLink;
import com.antilia.web.roundpane.RoundPane;
import com.antilia.web.roundpane.RoundPaneStyle;

/**
 * @author reiern70@gmail.com
 *
 */
public  class ContentsPanel extends RoundPane {

	private static final long serialVersionUID = 1L;

	public ContentsPanel(String id, RoundPaneStyle boxStyle) {
		this(id, "", boxStyle);
	}
	/**
	 * @param id
	 * @param title
	 * @param boxStyle
	 */
	public ContentsPanel(String id, String title, RoundPaneStyle boxStyle) {
		super(id, title, boxStyle);
		addToBody(newContentPanel("content"));
		setWidth(890);
		setHeight(800);
		setCentered(false);
	}
	
	protected Panel newContentPanel(String id) {
		return new EmptyPanel(id);
	}
	
	public void populateMenuItems(String menuId, IMenuItemHolder itemHolder) {
		LanguagePanel languagePanel = new LanguagePanel("lan") {
			
			private static final long serialVersionUID = 1L;

			@Override
			protected Component getReloadComponent() {
				return ContentsPanel.this.getRoundpane();
			}
		};
		
		itemHolder.addMenuItem(languagePanel);
		
		itemHolder.addMenuItem(new SeparatorButton());
		
		DialogLink dialogLink = new DialogLink("about") {			
			
			private static final long serialVersionUID = 1L;

			@Override
			protected ResourceReference getImage() {
				// TODO Auto-generated method stub
				return null;
			}
			
			@Override
			protected String getLabel() {
				return "About";
			}
			
			@Override
			protected String getLabelKey() {
				return "About";
			}
			
			@Override
			public String getLinkClass() {
				return "smallbutton whitebutton";
			}
			
			@Override
			public DefaultDialog newDialog(String id) {
				return new AboutDialog(id, this);
			}
		};
		itemHolder.addMenuItem(dialogLink);
		
		
		
	}
	
}
