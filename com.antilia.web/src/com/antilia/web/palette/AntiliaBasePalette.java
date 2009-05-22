/**
 * This software is provided as IS by Antilia-Soft SL.
 * Copyright 2006-2007.
 */
package com.antilia.web.palette;

import java.util.Collection;
import java.util.List;

import org.apache.wicket.Component;
import org.apache.wicket.ResourceReference;
import org.apache.wicket.extensions.markup.html.form.palette.Palette;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.form.IChoiceRenderer;
import org.apache.wicket.markup.html.image.Image;
import org.apache.wicket.model.IModel;

import com.antilia.web.resources.DefaultStyle;
import com.antilia.web.utils.RequestUtils;

/**
 * Our extension of palette priving a custom look and feel.
 *
 * @author Ernesto Reinaldo Barreiro (reiern70@gmail.com)
 *
 */
public class AntiliaBasePalette<T> extends Palette<T> {

	private static final long serialVersionUID = 1L;

	/**
	 * @param id
	 * @param choicesModel
	 * @param choiceRenderer
	 * @param rows
	 * @param allowOrder
	 */
	public AntiliaBasePalette(String id, IModel<Collection<T>> choicesModel,
			IChoiceRenderer<T> choiceRenderer, int rows, boolean allowOrder) {
		super(id, choicesModel, choiceRenderer, rows, allowOrder);
	}

	/**
	 * @param id
	 * @param model
	 * @param choicesModel
	 * @param choiceRenderer
	 * @param rows
	 * @param allowOrder
	 */
	public AntiliaBasePalette(String id, IModel<List<T>> model, IModel<Collection<T>> choicesModel,
			IChoiceRenderer<T> choiceRenderer, int rows, boolean allowOrder) {
		super(id, model, choicesModel, choiceRenderer, rows, allowOrder);
	}
	
	@Override
	protected ResourceReference getCSS() {
		return DefaultStyle.CSS_PALETTE;
	}
	
	@Override
	protected Component newAddComponent() {
		WebMarkupContainer add =  (WebMarkupContainer)super.newAddComponent();
		if(RequestUtils.isBrowserIeExplorer6())
			add.addOrReplace(new Image("image", DefaultStyle.IMG_NEXT_ENABLED));
		else 
			add.addOrReplace(new Image("image", DefaultStyle.IMG_NEXT_ENABLED_PNG));
		return add;
	}
	
	@Override
	protected Component newRemoveComponent() {
		WebMarkupContainer add =  (WebMarkupContainer)super.newRemoveComponent();
		if(RequestUtils.isBrowserIeExplorer6())
			add.addOrReplace(new Image("image", DefaultStyle.IMG_PREVIOUS_ENABLED));
		else
			add.addOrReplace(new Image("image", DefaultStyle.IMG_PREVIOUS_ENABLED_PNG));
		return add;
	}
	
	@Override
	protected Component newDownComponent() {
		WebMarkupContainer add =  (WebMarkupContainer)super.newDownComponent();
		if(RequestUtils.isBrowserIeExplorer6())
			add.addOrReplace(new Image("image", DefaultStyle.IMG_DOWN_ENABLED));
		else
			add.addOrReplace(new Image("image", DefaultStyle.IMG_DOWN_ENABLED_PNG));
		return add;
	}

	@Override
	protected Component newUpComponent() {
		WebMarkupContainer add =  (WebMarkupContainer)super.newUpComponent();
		if(RequestUtils.isBrowserIeExplorer6())
			add.addOrReplace(new Image("image", DefaultStyle.IMG_UP_ENABLED));
		else
			add.addOrReplace(new Image("image", DefaultStyle.IMG_UP_ENABLED_PNG));
		return add;
	}
}
