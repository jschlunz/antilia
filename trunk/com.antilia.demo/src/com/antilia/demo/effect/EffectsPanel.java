/**
 * This software is provided as IS by Antilia-Soft SL.
 * Copyright 2006-2007.
 */
package com.antilia.demo.effect;

import org.apache.wicket.ResourceReference;
import org.apache.wicket.behavior.HeaderContributor;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.panel.Panel;

import com.antilia.demo.beans.Person;
import com.antilia.demo.tables.TablesPanel;
import com.antilia.web.beantable.Table;
import com.antilia.web.beantable.model.TableModel;
import com.antilia.web.effect.Effect;
import com.antilia.web.effect.EffectAttributeModifier;

/**
 * 
 * @author Ernesto Reinaldo Barreiro (reiern70@gmail.com)
 */
public class EffectsPanel extends Panel {
	private static final long serialVersionUID = 1L;

	private static final ResourceReference CSS = new ResourceReference(EffectsPanel.class, "effectspage.css");
	
	EffectPanel panel1;
	
	EffectPanel panel2;
	/**
	 * 
	 */
	public EffectsPanel(String id) {
		super(id);
		add(HeaderContributor.forCss(CSS));
		
		add(new EffectPanel("Effect.Fade", Effect.Fade.class, true));

		add(new EffectPanel("Effect.Puff",Effect.Puff.class, true));
		
		add(new EffectPanel("Effect.BlindDown", Effect.BlindDown.class, false));
		
		add(new EffectPanel("Effect.BlindUp",Effect.BlindUp.class,true));
		
		add(new EffectPanel("Effect.SwitchOff",Effect.SwitchOff.class,true));

		add(new EffectPanel("Effect.DropOut",Effect.DropOut.class,true));

		add(new EffectPanel("Effect.SlideDown",Effect.SlideDown.class,true));
		
		add(new EffectPanel("Effect.SlideUp",Effect.SlideUp.class,true));
		
		add(new EffectPanel("Effect.Shake",Effect.Shake.class,true));
		
		add(new EffectPanel("Effect.Pulsate",Effect.Pulsate.class,true));
		
		add(new EffectPanel("Effect.Squish",Effect.Squish.class,true));
		
		add(new EffectPanel("Effect.Fold",Effect.Fold.class,true));
		
		add(new EffectPanel("Effect.Grow.center",Effect.Grow.class,true) {
			private static final long serialVersionUID = 1L;

			@Override
			protected String getLabel() {
				return super.getLabel()+"\n{direction:center}";				
			}
		});
		
		add(new EffectPanel("Effect.Grow.bottom_left",Effect.Grow.class,true) {
			private static final long serialVersionUID = 1L;

			@Override
			protected String getLabel() {
				return super.getLabel()+"\n{direction:"+Effect.Grow.Direction.bottom_left+"}";				
			}
			
			@Override
			protected Effect getEffect() {
				Effect.Grow grow = (Effect.Grow)super.getEffect();
				grow.setDirection(Effect.Grow.Direction.bottom_left);
				return grow;
			}
		});
		
		add(new EffectPanel("Effect.Grow.bottom_right",Effect.Grow.class,true) {
			private static final long serialVersionUID = 1L;

			@Override
			protected String getLabel() {
				return super.getLabel()+"\n{direction:"+Effect.Grow.Direction.bottom_right+"}";				
			}
			
			@Override
			protected Effect getEffect() {
				Effect.Grow grow = (Effect.Grow)super.getEffect();
				grow.setDirection(Effect.Grow.Direction.bottom_right);
				return grow;
			}
		});
		
		add(new EffectPanel("Effect.Grow.top_right",Effect.Grow.class,true) {
			private static final long serialVersionUID = 1L;

			@Override
			protected String getLabel() {
				return super.getLabel()+"\n{direction:"+Effect.Grow.Direction.top_right+"}";				
			}
			
			@Override
			protected Effect getEffect() {
				Effect.Grow grow = (Effect.Grow)super.getEffect();
				grow.setDirection(Effect.Grow.Direction.top_right);
				return grow;
			}
		});
		
		add(new EffectPanel("Effect.Grow.top_left",Effect.Grow.class,true) {
			private static final long serialVersionUID = 1L;

			@Override
			protected String getLabel() {
				return super.getLabel()+"\n{direction:"+Effect.Grow.Direction.top_left+"}";				
			}
			
			@Override
			protected Effect getEffect() {
				Effect.Grow grow = (Effect.Grow)super.getEffect();
				grow.setDirection(Effect.Grow.Direction.top_left);
				return grow;
			}
		});
		
		add(new EffectPanel("Effect.Shrink",Effect.Shrink.class,true));
		
		add(new EffectPanel("Effect.Parallel.same",Effect.Parallel.class,true) {
			
			private static final long serialVersionUID = 1L;

			@Override
			protected Effect getEffect() {				
				Effect.Parallel parallel = (Effect.Parallel)super.getEffect();
				parallel.addEffect(new Effect.Shake(), null);
				parallel.addEffect(new Effect.Puff(), null);
				return parallel;
			}
		});

		panel1 = new EffectPanel("Effect.Parallel.one",Effect.Shake.class,true);
		add(panel1);
		
		panel2 = new EffectPanel("Effect.Parallel.two",Effect.Puff.class,true);
		add(panel2);
		
		Label link = new Label("clickme", "click me");
		
		new EffectAttributeModifier(panel1, link, "onclick") {
			
			private static final long serialVersionUID = 1L;

			@Override
			protected Effect getEffect() {
				Effect.Parallel parallel = new Effect.Parallel();
				parallel.addEffect(new Effect.SlideDown(), panel1);
				parallel.addEffect(new Effect.Grow(), panel2);
				return parallel;
			}
			
		};				
		add(link);
		
		Form form = new Form("form");
		add(form);
		
		TableModel<Person> tableModel = new TableModel<Person>(Person.class, "id", "name", "lastName1");
		Table<Person> table = new Table<Person>("table",tableModel, TablesPanel.createPersons());
		
		Label link2 = new Label("clickme", "show/hide");
		form.add(link2);
		new EffectAttributeModifier(table, link2, "onclick") {
			
			private static final long serialVersionUID = 1L;

			@Override
			protected Effect getEffect() {
				return new Effect.toggle(Effect.toggle.Type.slide) ;
			}
		};
		form.add(table);
	}
}
