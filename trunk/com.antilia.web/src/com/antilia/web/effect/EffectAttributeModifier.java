/**
 * This software is provided as IS by Antilia-Soft SL.
 * Copyright 2006-2007.
 */
package com.antilia.web.effect;

import org.apache.wicket.AttributeModifier;
import org.apache.wicket.Component;
import org.apache.wicket.behavior.HeaderContributor;
import org.apache.wicket.markup.html.WebMarkupContainer;

import com.antilia.web.resources.DefaultStyle;

/**
 * 
 * @author Ernesto Reinaldo Barreiro (reiern70@gmail.com)
 */
public abstract class EffectAttributeModifier extends AttributeModifier {

	private WebMarkupContainer target;
	
	private static final long serialVersionUID = 1L;

	/**
	 * @param attribute
	 * @param replaceModel
	 */
	public EffectAttributeModifier(WebMarkupContainer target, Component trigger, String attribute) {
		this(target, trigger, attribute, false);
	}	

	
	/**
	 * @param attribute
	 * @param addAttributeIfNotPresent
	 * @param replaceModel
	 */
	public EffectAttributeModifier(WebMarkupContainer target, Component trigger, String attribute, boolean addAttributeIfNotPresent) {
		super(attribute, addAttributeIfNotPresent, null);
		this.target = target;
		trigger.add(this);
		target.setOutputMarkupId(true);
		target.add(HeaderContributor.forJavaScript(DefaultStyle.JS_PROTOTYPE));
		target.add(HeaderContributor.forJavaScript(DefaultStyle.JS_EFFECT));
	}

	@Override
	protected String newValue(String currentValue, String replacementValue) {
		return getEffect().toJavascript(getTargetId());
	}
	/**
	 * @return the container
	 */
	public WebMarkupContainer getTarget() {
		return target;
	}

	protected String getTargetId() {
		return getTarget().getMarkupId();
	}
	
	/**
	 * @param container the container to set
	 */
	public void setTarget(WebMarkupContainer container) {
		this.target = container;
	}


	/**
	 * @return the effect
	 */
	protected abstract Effect getEffect();

}
