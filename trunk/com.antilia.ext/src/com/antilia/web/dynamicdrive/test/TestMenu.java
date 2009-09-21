/**
 * This software is provided as IS by Antilia-Soft SL.
 * Copyright 2006-2007.
 */
package com.antilia.web.dynamicdrive.test;

import org.apache.wicket.markup.html.WebPage;

import com.antilia.web.dynamicdrive.Menubar;
import com.antilia.web.dynamicdrive.SubMenubar;


/**
 * 
 * @author Ernesto Reinaldo Barreiro (reiern70@gmail.com)
 *
 */
public class TestMenu extends WebPage  {

	private static final long serialVersionUID = 1L;	
	
	public TestMenu() {		
		super();			
		//add(new TestPanel("test"));		
		Menubar menubar = new Menubar("test");
		add(menubar);
		
		menubar.addItem(new ExternalLink("google", "http://www.google.com/ncr", "Google Inc"));
		
		SubMenubar subMenubar = new SubMenubar("One-1", menubar) {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
			protected String getTitle() {
				return "One-1";
			}
		};
		
		subMenubar.addItem(new ExternalLink("dynamicdrive", "http://www.dynamicdrive.com/style/csslibrary/item/jquery_multi_level_css_menu_2/","dynamicdrive1"));
		subMenubar.addItem(new ExternalLink("dynamicdrive2", "http://www.dynamicdrive.com/style/csslibrary/item/jquery_multi_level_css_menu_2/","dynamicdrive2"));
		
		subMenubar = new SubMenubar("Two-2", menubar) {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
			protected String getTitle() {
				return "Two-2";
			}
		};
		subMenubar.addItem(new ExternalLink("dynamicdrive", "http://www.dynamicdrive.com/style/csslibrary/item/jquery_multi_level_css_menu_2/", "dynamicdrive"));
		subMenubar.addItem(new ExternalLink("dynamicdrive2", "http://www.dynamicdrive.com/style/csslibrary/item/jquery_multi_level_css_menu_2/", "dynamicdrive2"));
		

		SubMenubar subMenubar2 = new SubMenubar("Two-2-1", subMenubar) {
			
			private static final long serialVersionUID = 1L;

			@Override
			protected String getTitle() {
				return "Two-2-1";
			}
		};
		
		subMenubar2.addItem(new ExternalLink("dynamicdrive", "http://www.dynamicdrive.com/style/csslibrary/item/jquery_multi_level_css_menu_2/", "dynamicdrive"));
		subMenubar2.addItem(new ExternalLink("dynamicdrive2", "http://www.dynamicdrive.com/style/csslibrary/item/jquery_multi_level_css_menu_2/", "dynamicdrive2"));

		SubMenubar subMenubar3 = new SubMenubar("Two-2-1-1", subMenubar2) {
			
			private static final long serialVersionUID = 1L;

			@Override
			protected String getTitle() {
				return "Two-2-1-1";
			}
		};
		
		subMenubar3.addItem(new ExternalLink("dynamicdrive", "http://www.dynamicdrive.com/style/csslibrary/item/jquery_multi_level_css_menu_2/", "dynamicdrive"));
		subMenubar3.addItem(new ExternalLink("dynamicdrive2", "http://www.dynamicdrive.com/style/csslibrary/item/jquery_multi_level_css_menu_2/", "dynamicdrive2"));
		
		menubar.addItem(new ExternalLink("dynamicdrive2", "http://www.dynamicdrive.com/style/csslibrary/item/jquery_multi_level_css_menu_2/", "dynamicdrive2"));
	}

	
}
