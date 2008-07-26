/**
 * This software is provided as IS by Antilia-Soft SL.
 * Copyright 2006-2007.
 */
package com.antilia.demo.effect;

import org.apache.wicket.ResourceReference;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.image.Image;
import org.apache.wicket.markup.html.panel.Panel;

import com.antilia.web.effect.Effect;
import com.antilia.web.effect.EffectAttributeModifier;

/**
 * 
 * @author Ernesto Reinaldo Barreiro (reiern70@gmail.com)
 */
public class EffectPanel extends Panel {

	private static final long serialVersionUID = 1L;

	private static ResourceReference LOGO = new ResourceReference(EffectPanel.class, "demo-logo.gif");
	
	private static Effect.Appear appear = new Effect.Appear();
	
	static {
		appear.setDuration(3);
	}
	
	private boolean needsAppear;
	
	private Class<? extends Effect> effetClass;
	
	private WebMarkupContainer innerDiv;
	/**
	 * @param id
	 */
	public EffectPanel(String id, Class<? extends Effect> effetClass,boolean needsAppear) {
		super(id);
		this.needsAppear = needsAppear;
		this.effetClass = effetClass;
		innerDiv = new WebMarkupContainer("example");
		new EffectAttributeModifier(innerDiv, innerDiv, "onclick") {

			private static final long serialVersionUID = 1L;

			@Override
			protected String newValue(String currentValue, String replacementValue) {
				StringBuffer sb = new StringBuffer();
				sb.append(super.newValue(currentValue, replacementValue));
				if(needsAppear())
					sb.append(Effect.Delay.delayedEffect(appear, getTarget().getMarkupId(), 2000));
				return sb.toString();
			}
			@Override
			protected Effect getEffect() {
				return EffectPanel.this.getEffect();
			}
									
		};
		add(innerDiv);
		innerDiv.add(new Image("logo", LOGO));		
		innerDiv.add(new Label("label", getLabel()));
	}
	
	protected String getLabel() {
		String name = getEffect().getClass().getCanonicalName();
		return name.substring(name.indexOf(".Effect")+1);
	}
	
	protected Effect getEffect() {
		try {
			return effetClass.newInstance();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	protected boolean needsAppear() {
		return needsAppear;
	}

	/**
	 * @return the innerDiv
	 */
	protected WebMarkupContainer getInnerDiv() {
		return innerDiv;
	}

	/**
	 * @param innerDiv the innerDiv to set
	 */
	protected void setInnerDiv(WebMarkupContainer innerDiv) {
		this.innerDiv = innerDiv;
	}
}


