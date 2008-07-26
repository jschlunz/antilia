/**
 * This software is provided as IS by Antilia-Soft SL.
 * Copyright 2006-2007.
 */
package com.antilia.demo.picviewer.viewer;

import org.apache.wicket.Component;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.image.NonCachingImage;

import com.antilia.demo.picviewer.osgi.IPicture;
import com.antilia.demo.picviewer.osgi.IPicturesSource;
import com.antilia.demo.picviewer.viewer.nav.NavigationItemsFactory;
import com.antilia.web.beantable.IPageableComponent;
import com.antilia.web.beantable.provider.IPageableProvider;
import com.antilia.web.beantable.provider.impl.InMemoryPageableProvider;
import com.antilia.web.button.IMenuItemHolder;
import com.antilia.web.resources.DefaultStyle;
import com.antilia.web.roundpane.RoundPane;
import com.antilia.web.roundpane.RoundPaneStyle;

/**
 * @author Ernesto Reinaldo Barreiro (reiern70@gmail.com)
 *
 */
public class PicturesNavigator extends RoundPane implements IPageableComponent<IPicture> {

	private static final long serialVersionUID = 1L;

	private WebMarkupContainer  imageContainer;
	
	private IPageableProvider<IPicture> pageableProvider;
	
	/**
	 * @param id
	 * @param title
	 * @param boxStyle
	 */
	public PicturesNavigator(String id, RoundPaneStyle boxStyle, IPicturesSource source) {
		super(id, source.getTitle(), boxStyle);
		setOutputMarkupId(true);
		setResizable(true);
		setFoldable(true);
		setWidth(800);
		setHeight(400);
		setMinWidth(500);
		setMinHeight(300);
		
		pageableProvider = new InMemoryPageableProvider<IPicture>(source.getPictures(),IPicture.class) ;
		
		imageContainer = new WebMarkupContainer("imageContainer") {
			
			private static final long serialVersionUID = 1L;

			@Override
			protected void onBeforeRender() {
				if(getPageableProvider() != null && !getPageableProvider().isEmpty())
					imageContainer.addOrReplace(new NonCachingImage("image",  getPageableProvider().current().getContent()));
				else 
					imageContainer.addOrReplace(new NonCachingImage("image",  DefaultStyle.IMG_CANCEL));
				super.onBeforeRender();
			}
		};		
		addToBody(imageContainer);		
	}


	@Override
	public String getTitle() {
		if(getPageableProvider() != null && !getPageableProvider().isEmpty())
			return super.getTitle() + " - " +getPageableProvider().current().getTitle();
		return super.getTitle();
	}
	
	public IPageableProvider<IPicture> getPageableProvider() {
		return pageableProvider;
	}
	
	@Override
	public void populateMenuItems(String menuId, IMenuItemHolder itemHolder) {
		super.populateMenuItems(menuId, itemHolder);
		NavigationItemsFactory.getInstance().populateMenuItems(menuId, itemHolder);
	}
	
	public Component getUpdatableComponent() {
		return getRoundpane();
	}

}
