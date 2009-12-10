/**
 * This software is provided as IS by Antilia-Soft SL.
 * Copyright 2006-2007.
 */
package com.antilia.letsplay;

import org.apache.wicket.RequestCycle;
import org.apache.wicket.ResourceReference;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.markup.html.WebPage;

import com.antilia.letsplay.crud.DWordPage;
import com.antilia.letsplay.crud.ImagesPage;
import com.antilia.letsplay.crud.UsersPage;
import com.antilia.web.button.AbstractLink;
import com.antilia.web.resources.DefaultStyle;
import com.antilia.web.toolbar.IToolbar;
import com.antilia.web.toolbar.IToolbarItemsFactory;
import com.antilia.web.toolbar.SubToolbar;

/**
 * 
 *
 * @author Ernesto Reinaldo Barreiro (reiern70@gmail.com)
 *
 */
public class MainMenuFactory implements IToolbarItemsFactory {

	private static final long serialVersionUID = 1L;

	private Index page;
		
	
	private abstract class PageLink extends AbstractLink {
		
		private static final long serialVersionUID = 1L;
		
		private Class<? extends WebPage> pageClass;
		
		public PageLink(String id, Class<? extends WebPage> pageClass) {
			super(id);
			this.pageClass = pageClass;
		}
		
		@Override
		protected ResourceReference getImage() {
			return DefaultStyle.IMG_TRANSPARENT;
		}
		
		
		@Override
		protected void onClick(AjaxRequestTarget target) {
			RequestCycle.get().setResponsePage(pageClass);
		}
		
	}
	
	/**
	 * 
	 */
	public MainMenuFactory(Index page) {
		this.page = page;
	}

	/* (non-Javadoc)
	 * @see com.antilia.web.toolbar.IToolbarItemsFactory#populateMenuItems(java.lang.String, com.antilia.web.toolbar.IToolbar)
	 */
	public void populateMenuItems(String menuId, IToolbar toolbar) {
			SubToolbar subToolbar = new SubToolbar("Games", toolbar) {
				private static final long serialVersionUID = 1L;

				@Override
				protected String getTitle() {
					return "Games";
				}
			};
			toolbar.addItem(subToolbar);
			
			subToolbar.addItem(new PageLink("letters", PlayPage.class) {
				private static final long serialVersionUID = 1L;

				@Override
				protected String getLabel() {
					return "Letters";
				}
			});
			
			subToolbar = new SubToolbar("Other", toolbar) {
				private static final long serialVersionUID = 1L;

				@Override
				protected String getTitle() {
					return "Other";
				}
			};
			subToolbar.addItem(new PageLink("words", DWordPage.class) {
				private static final long serialVersionUID = 1L;

				@Override
				protected String getLabel() {
					return "Words";
				}
			});
			
			subToolbar.addItem(new PageLink("images", ImagesPage.class) {
				private static final long serialVersionUID = 1L;

				@Override
				protected String getLabel() {
					return "Images";
				}
			});
			
			subToolbar.addItem(new PageLink("users", UsersPage.class) {
				private static final long serialVersionUID = 1L;

				@Override
				protected String getLabel() {
					return "Users";
				}
			});
			
			
			
			toolbar.addItem(subToolbar);
	}

	public Index getPage() {
		return page;
	}

}
