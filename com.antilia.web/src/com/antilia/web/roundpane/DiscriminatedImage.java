/**
 * This software is provided as IS by Antilia-Soft SL.
 * Copyright 2006-2007.
 */
package com.antilia.web.roundpane;

import org.apache.wicket.Resource;
import org.apache.wicket.ResourceReference;
import org.apache.wicket.markup.ComponentTag;
import org.apache.wicket.markup.html.image.Image;
import org.apache.wicket.model.IModel;
import org.apache.wicket.util.value.ValueMap;

/**
 *  A subclass of {@link Image} that adds noise (a discriminator) to the url every request
 * to prevent the browser from caching the image of different "styles".
 * 
 * @author Ernesto Reinaldo Barreiro (reiern70@gmail.com)
 */
public abstract class DiscriminatedImage extends Image
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Construct.
	 * 
	 * @see Image#Image(String, IModel)
	 * 
	 * @param id
	 * @param model
	 */
	public DiscriminatedImage(String id, IModel<?> model)
	{
		super(id, model);
	}

	/**
	 * Construct.
	 * 
	 * @see Image#Image(String, Resource)
	 * 
	 * @param id
	 * @param imageResource
	 */
	public DiscriminatedImage(String id, Resource imageResource)
	{
		super(id, imageResource);
	}

	/**
	 * Construct.
	 * 
	 * @see Image#Image(String, ResourceReference, ValueMap)
	 * 
	 * @param id
	 * @param resourceReference
	 * @param resourceParameters
	 */
	public DiscriminatedImage(String id, ResourceReference resourceReference,
			ValueMap resourceParameters)
	{
		super(id, resourceReference, resourceParameters);
	}

	/**
	 * Construct.
	 * 
	 * @see Image#Image(String, ResourceReference)
	 * 
	 * @param id
	 * @param resourceReference
	 */
	public DiscriminatedImage(String id, ResourceReference resourceReference)
	{
		super(id, resourceReference);
	}

	/**
	 * Construct.
	 * 
	 * @see Image#Image(String, String)
	 * 
	 * 
	 * @param id
	 * @param string
	 */
	public DiscriminatedImage(String id, String string)
	{
		super(id, string);
	}

	/**
	 * Construct.
	 * 
	 * @see Image#Image(String)
	 * 
	 * @param id
	 */
	public DiscriminatedImage(String id)
	{
		super(id);
	}

	protected abstract String getDiscriminator();
	
	/**
	 * @see org.apache.wicket.markup.html.image.Image#onComponentTag(org.apache.wicket.markup.ComponentTag)
	 */
	protected void onComponentTag(ComponentTag tag)
	{
		super.onComponentTag(tag);

		String url = tag.getAttributes().getString("src");
		url = url + ((url.indexOf("?") >= 0) ? "&" : "?");
		url = url + "wicket:antiCache=" + getDiscriminator();
		
		tag.put("src", url);
	}

}

