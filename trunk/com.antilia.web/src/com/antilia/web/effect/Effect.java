/**
 * This software is provided as IS by Antilia-Soft SL.
 * Copyright 2006-2007.
 */
package com.antilia.web.effect;



import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.wicket.Component;



/**
 * This class is based on a similar call of project wicket.scriptaculous (maintained by Ryan Sonneck).
 * It adds additional methods as well as enumerations helping in remembering scriptaculous syntax..
 * 
 * @author Ernesto Reinaldo Barreiro (reiern70@gmail.com)
 */
public interface Effect extends Serializable
{

	String toJavascript(String id);
	
	String toJavascript(Component component);

	/**
	 * Sets a function that modifies the current point of the animation, which is between 0 and 1. 
	 * Following transitions are supplied: Effect.Transitions.sinoidal (default), Effect.Transitions.linear, 
	 * Effect.Transitions.reverse, Effect.Transitions.wobble and Effect.Transitions.flicker.
	 */
	public static enum Transition {
		flicker,
		slowstop,
		sinoidal,
		linear,
		wobble,
		reverse,
		pulse,
		;
		
		@Override
		public String toString() {
			return "Effect.Transitions."+name().toLowerCase();
		}
	};
	
	public class Queue implements Serializable  {
		
		private static final long serialVersionUID = 1L;

		public static enum Position {
			front,
			end;
			
			@Override
			public String toString() {
				return "'"+super.toString()+"'";
			}
		}
		
		private Position position;
		
		private String scope;
		
		private Integer limit;
		
		public Queue(Position position) {
			this.position = position;
		}
		
		/**
		 * @return the position
		 */
		protected Position getPosition() {
			return position;
		}
		/**
		 * @param position the position to set
		 */
		protected void setPosition(Position position) {
			if(position != null)
				this.position = position;
		}

		/**
		 * @return the scope
		 */
		protected String getScope() {
			return scope;
		}

		/**
		 * @param scope the scope to set
		 */
		protected void setScope(String scope) {
			this.scope = scope;
		}

		/**
		 * @return the limit
		 */
		protected Integer getLimit() {
			return limit;
		}

		/**
		 * @param limit the limit to set
		 */
		protected void setLimit(Integer limit) {
			this.limit = limit;
		}
		
		@Override
		public String toString() {
			StringBuffer sb = new StringBuffer();
			sb.append("{");
			sb.append("position: ");
			sb.append(getPosition().toString());
			if(getScope() != null) {
				sb.append(", scope: ");
				sb.append("'");
				sb.append(getScope());
				sb.append("'");
			}
			
			if(getLimit() != null) {
				sb.append(", limit: ");
				sb.append(getLimit());				
			}
			sb.append("}");
			return super.toString();
		}
		
	}
	
	/**
	 * Helper Base Effect class for effects.
	 */
	public abstract class AbstractEffect implements Effect
	{
		private final Map<String, Object> options;

		private final Map<String, Object> params;
		
		public AbstractEffect() {
			this.options = new HashMap<String, Object>();
			this.params = new HashMap<String, Object>();
		}

		public String toJavascript(Component component) {
			if(component != null)
				return toJavascript(component.getMarkupId());
			return null;
		}
				

		public String toJavascript(String id) {
			JavascriptHelper builder = new JavascriptHelper();
			builder
					.addLine("new Effect." + getEffectName() + "('" + id
							+ "', ");
			if(params.size() > 0) {
				for(String key: params.keySet()) {
					builder.addLine(params.get(key)+",");
				}
			}
			builder.addOptions(options);
			builder.addLine(");");

			return builder.toJavascript();
		}

		public String toString() {
			return toJavascript((String)null);
		}
		
		protected void addOption(String key, Object value) {
			options.put(key, value);
		}
		
		protected void addParam(String key, Object value) {
			params.put(key, value);
		}
		
		/**
		 * Duration of the effect in seconds, given as a float. Defaults to 1.0.
		 * @param duration
		 */
		public void setDuration(float duration) {
			if(duration <= 0)
				throw new IllegalArgumentException("duration should be bigger than 0 ("+duration+")." );
			options.put("duration", new Float(duration));
		}

		/**
		 * Sets whether the effect should render new frames automatically (which it does by default). 
		 * If true, you can render frames manually by calling the render() instance method of an effect. 
		 * This is used by Effect.Parallel()
		 * @param sync
		 */
		public void setSync(boolean sync) {
			options.put("sync", new Boolean(sync));
		}
		
		/**
		 * @see Transition
		 * @param transition
		 */
		public void setTransition(Transition transition) {
			addOption("transition", transition);			
		}
		
		/**
		 * Target this many frames per second. Default to 25. Can’t be higher than 100.
		 * @param fps
		 */
		public void setFps(int fps) {
			if(fps >100)
				fps = 100;
			if(fps <= 0)
				fps = 25;
			addOption("fps", fps);
		}
		
		public void setDelay(int delay) {
			addOption("delay", delay);
		}
		
		/**
		 * Sets the starting point of the transition, a float between 0.0 and 1.0. Defaults to 0.0.
		 * @param from
		 */
		public void setFrom(float from) {
			from = checkFromTo(from);
			addOption("from", new Float(from));
		}

		/**
		 * Sets queuing options. When used with a string, can be ‘front’ or ‘end’ to queue the effect in the 
		 * global effects queue at the beginning or end, or a queue parameter object that can have 
		 * {position:’front/end’, scope:’scope’, limit:1}. For more info on this, see Effect Queues.
		 * @param queue
		 */
		public void setQueue(Queue queue) {			
			addOption("queue", queue);
		}
		
		/**
		 * Sets the end point of the transition, a float between 0.0 and 1.0. Defaults to 1.0.
		 * @param to
		 */
		public void setTo(float to) {
			to = checkFromTo(to);
			addOption("to", new Float(to));
		}
		
		public float checkFromTo(float value) {
			if(value<0)
				return 0;
			if(value>1)
				return 1;
			return value;
		}
		
		/**
		 * Called before the main effects rendering loop is started.
		 * 
		 * @param beforeStart
		 */
		public void setBeforeStart(String beforeStart) {
			if(beforeStart != null)
				addOption("beforeStart", beforeStart);
		}
		
		/**
		 * Called on each iteration of the effects rendering loop, before the redraw takes places.
		 * @param beforeUpdate
		 */
		public void setBeforeUpdate(String beforeUpdate) {
			if(beforeUpdate != null)
				addOption("beforeUpdate", beforeUpdate);
		}
		
		/**
		 * Called on each iteration of the effects rendering loop, after the redraw takes places.
		 * @param afterUpdate
		 */
		public void setAfterUpdate(String afterUpdate) {
			if(afterUpdate != null)
				addOption("afterUpdate", afterUpdate);
		}
		
		/**
		 * Called after the last redraw of the effect was made.
		 * 
		 * @param afterFinish
		 */
		public void setAfterFinish(String afterFinish) {
			if(afterFinish != null)
				addOption("afterFinish", afterFinish);
		}
		
		protected abstract String getEffectName();

		/**
		 * @return the options
		 */
		protected Map<String, Object> getOptions() {
			return options;
		}
	}

	/**
	 * <p>
	 * Make an element appear. If the element was previously set to display:none; 
	 * inside the style attribute of the element, the effect will automatically show 
	 * the element. This means that display must be set within the style attribute of an 
	 * object, and not in the CSS in the head of the document or a linked file. In other 
	 * words, this Effect will not work if display:none; is set within style tag or 
	 * linked CSS file.
	 * </p>
	 * 
	 * @see http://wiki.script.aculo.us/scriptaculous/show/Effect.Appear
	 */
	public class Appear extends Opacity {
		private static final long serialVersionUID = 1L;

		public Appear() {
		}
		
		@Override
		protected String getEffectName() {
			return "Appear";
		}
	}
	
	/**
	 * <p>
	 * 	Makes an element fade away and takes it out of the document flow at 
	 * 	the end of the effect by setting the CSS display property to none. 
	 * 	Opposite of Effect.Appear
	 * </p>
	 * 
	 * @see http://wiki.script.aculo.us/scriptaculous/show/Effect.Fade
	 */
	public class Fade extends Opacity {
		private static final long serialVersionUID = 1L;

		public Fade() {
		}
		
		@Override
		protected String getEffectName() {
			return "Fade";
		}
	}
	
	/**
	 * <p>
	 * 	Makes the element drop and fade out at the same time.	 
	 * </p>
	 * 
	 * @see http://wiki.script.aculo.us/scriptaculous/show/Effect.DropOut
	 */
	public class DropOut extends AbstractEffect {
		
		private static final long serialVersionUID = 1L;

		public DropOut() {
			super();
		}
		
		@Override
		protected String getEffectName() {
			return "DropOut";
		}
	}
	
	/**
	 * Effect for highlighting a component using the famous "yellow fade".
	 * 
	 * @see http://wiki.script.aculo.us/scriptaculous/show/Effect.Highlight
	 */
	public class Highlight extends AbstractEffect {
		private static final long serialVersionUID = 1L;

		public Highlight()
		{
			super();
		}

		protected String getEffectName()
		{
			return "Highlight";
		}

		public void setStartColor(String rgb)
		{
			addOption("startcolor", rgb);
		}

		public void setEndColor(String rgb)
		{
			addOption("endcolor", rgb);
		}

		public void setRestoreColor(String rgb)
		{
			addOption("restorecolor", rgb);
		}
	}
	
	/**
	 * This effect changes an element’s opacity (transparency).
	 * 
	 * @see http://wiki.script.aculo.us/scriptaculous/show/Effect.Opacity
	 */
	public class Opacity extends AbstractEffect {
		private static final long serialVersionUID = 1L;

		public Opacity() {
			super();
		}

		protected String getEffectName() {
			return "Opacity";
		}		
	}
	
	
	/**
	 * This effect changes the CSS properties of an element.
	 * 
	 * @see http://wiki.script.aculo.us/scriptaculous/show/Effect.Morph
	 */
	public class Morph extends AbstractEffect {
		private static final long serialVersionUID = 1L;

		public Morph() {
			super();
		}

		protected String getEffectName() {
			return "Morph";
		}

		public void setStyle(String style) {
			addOption("style", style);
		}
	}
	
	
	
	/**
	 * This effect changes the CSS properties of an element.
	 * 
	 * @see http://wiki.script.aculo.us/scriptaculous/show/Effect.Morph
	 */
	public class Move extends AbstractEffect {
		private static final long serialVersionUID = 1L;

		public enum Mode {
			ABSOLUTE,
			RELATIVE;
			
			@Override
			public String toString() {
				return "'"+name().toLowerCase()+"'";
			}
		};
		
		public Move() {
			super();
		}

		protected String getEffectName() {
			return "Move";
		}

		public void setX(float x) {
			addOption("x", new Float(x));			
		}
		
		public void setY(float y) {
			addOption("y", new Float(y));			
		}
		
		public void setMode(Mode mode) {
			addOption("mode", mode);			
		}		
	}
	
	/**
	 * Gives the illusion of the element puffing away (like a in a cloud of smoke).
	 * 
	 * @see http://wiki.script.aculo.us/scriptaculous/show/Effect.Morph
	 */
	public class Puff extends AbstractEffect {
		private static final long serialVersionUID = 1L;
		
		public Puff() {
			super();
		}

		protected String getEffectName() {
			return "Puff";
		}

		public void setFrom(float from) {
			addOption("from", new Float(from));			
		}
		
		public void setTo(float to) {
			addOption("to", new Float(to));			
		}

	}
	
	public class toggle extends AbstractEffect {
		private static final long serialVersionUID = 1L;
		
		public static enum Type {
			slide,
		    blind,
		    appear;
			
			@Override
			public String toString() {
				return "'"+super.toString()+"'";
			}
			
		};
		
		public toggle(Type type) {
			super();
			setType(type);
		}

		protected String getEffectName() {
			return "toggle";
		}
		
		public void setType(Type type) {
			addParam("type", type.toString());
		}
	}

	public interface ScaleMode extends Serializable {
		 public String toString();
		 
		 public static final ScaleMode BOX = new ScaleMode() {
			private static final long serialVersionUID = 1L;

			public String toString() {
				return "'box'";
			}
		 };
		 
		 public static final ScaleMode CONTENTS = new ScaleMode() {
			 private static final long serialVersionUID = 1L;

			 public String toString() {
				 return "'contents'";
			 }
		 };
		 
		 public class Custom implements ScaleMode {

			private static final long serialVersionUID = 1L;
			 
			private int originalWidth;
			private int originalHeight;
			
			public Custom(int originalWidth, int originalHeight) {
				this.originalWidth = originalWidth;
				this.originalHeight = originalHeight;
			}
			/**
			 * @return the originalHeight
			 */
			public int getOriginalHeight() {
				return originalHeight;
			}
			/**
			 * @param originalHeight the originalHeight to set
			 */
			public void setOriginalHeight(int originalHeight) {
				this.originalHeight = originalHeight;
			}
			/**
			 * @return the originalWidth
			 */
			public int getOriginalWidth() {
				return originalWidth;
			}
			/**
			 * @param originalWidth the originalWidth to set
			 */
			public void setOriginalWidth(int originalWidth) {
				this.originalWidth = originalWidth;
			}
			
			@Override
			public String toString() {
				return "{ originalHeight"+":"+ getOriginalHeight()+", originalWidth:"+ getOriginalWidth()+"}";
			}
			
		 }
		 
	}
	
	/**
	 * This effect changes an elements width and height dimensions and the base for em units. 
	 * This allows for smooth, automatic relative scaling of elements contained 
	 * within the scaled element.

	 * @see http://wiki.script.aculo.us/scriptaculous/show/Effect.Scale
	 */
	public abstract class Scale extends AbstractEffect {
		
		private static final long serialVersionUID = 1L;

		public Scale() {
			super();
			checkPercent(getPercent());
			addParam("percent", new Float(getPercent()));
		}

		protected String getEffectName() {
			return "Scale";
		}

		public void setScaleFrom(float from) {
			addOption("scaleFrom", new Float(from));
		}

		public abstract float getPercent();
		
		/**
		 * Sets whether content scaling should be enabled, defaults to true.
		 * 
		 * @param scaleContent
		 */
		public void setScaleContent(boolean scaleContent) {
			addOption("scaleContent", new Boolean(scaleContent));
		}
		
		public void setScaleMode(ScaleMode scaleMode) {
			addOption("scaleMode", scaleMode);
		}
		
		/**
		 * Sets whether the element should be scaled horizontally, defaults to true.
		 * 
		 * @param scaleX
		 */
		public void setScaleX(boolean scaleX) {
			addOption("scaleX", new Boolean(scaleX));
		}
		
		/**
		 * Sets whether the element should be scaled vertically, defaults to true.
		 * @param scaleY
		 */
		public void setScaleY(boolean scaleY) {
			addOption("scaleY", new Boolean(scaleY));
		}
		
		/**
		 * If true, scale the element in a way that the center of the element stays on the same 
		 * position on the screen, defaults to false.
		 * 
		 * @param scaleFromCenter
		 */
		public void setScaleFromCenter(boolean scaleFromCenter) {
			addOption("scaleFromCenter", new Boolean(scaleFromCenter));
		}
		
		public void checkPercent(float percent) {
			if(percent<0 || percent >10000)
				throw new IllegalArgumentException("Opacity should between 0 an 10000");
		}
	}
	
	public class Delayed extends AbstractEffect {
		
		private static final long serialVersionUID = 1L;

		private Effect effect;
		
		private int delay = 1000;
		
		public Delayed(Effect effect, int delay) {
			this.effect = effect;
		}
		
		@Override
		protected String getEffectName() {
			return null;
		}
		
		@Override
		public String toJavascript(String id) {
			String eStr = effect.toJavascript(id);
			StringBuffer sb = new StringBuffer();
			sb.append("window.setTimeout('");
			sb.append(eStr.replace("'","\\'").replace("\n", " ").replace(";", ""));
			sb.append("',");
			sb.append(delay);
			sb.append(");");
			return sb.toString();
		}
		
		
	}
	
	/**
	 * <p>
	 * 	Reduce the element to its top then to left to make it disappear.
	 * </p>
	 * 
	 * @see http://wiki.script.aculo.us/scriptaculous/show/Effect.Fold
	 */
	public class Parallel extends AbstractEffect {
		
		private static final long serialVersionUID = 1L;

		private static Object NO_COMPONENT = new Object();
		List<Effect> effects = new ArrayList<Effect>();		
		List<Object> targets = new ArrayList<Object>();
		
		public Parallel() {
			super();
		}
		
		@Override
		protected String getEffectName() {
			return "Parallel";
		}
		
		public void addEffect(Effect effect, Component target) {
			effects.add(effect);
			if(target != null) {
				targets.add(target);
				target.setOutputMarkupId(true);
			} else
				targets.add(NO_COMPONENT);
				
		}		
		
		@Override
		public String toJavascript(String id) {			
			StringBuffer sb = new StringBuffer();
			sb.append("new Effect." + getEffectName() + "(\n"); 
			Iterator<Effect> it = effects.iterator();
			sb.append("[");
			int count = 0;
			while(it.hasNext()) {
				Effect effect = it.next();
				String eid = id; 
				Object obj = targets.get(count);
				if(obj instanceof Component)
					eid = ((Component)obj).getMarkupId();
				String temp = effect.toJavascript(eid);
				temp = temp.substring(0, temp.length()-2);
				sb.append(temp);
				if(it.hasNext())
					sb.append(",\n");
				count++;
			}
			sb.append("]");	
			if(getOptions() != null) {
				sb.append(",");
				sb.append(JavascriptHelper.toJavascriptHash(getOptions()));
			}
			sb.append(");");
			return sb.toString();
			
			
		}
	}
	
	/**
	 * <p>
	 * 	Reduce the element to its top then to left to make it disappear.
	 * </p>
	 * 
	 * @see http://wiki.script.aculo.us/scriptaculous/show/Effect.Fold
	 */
	public class Sequential extends AbstractEffect {
		
		private static final long serialVersionUID = 1L;

		List<Effect> effects = new ArrayList<Effect>();		
		
		public Sequential() {
			super();
		}
		
		@Override
		protected String getEffectName() {
			return "Sequential";
		}
		
		public void addEffect(Effect effect) {
			effects.add(effect);				
		}
		
		@Override
		public String toJavascript(String id) {			
			StringBuffer sb = new StringBuffer();
			Iterator<Effect> it = effects.iterator();
			while(it.hasNext()) {
				Effect effect = it.next();
				String temp = effect.toJavascript(id);			
				sb.append(temp);
			}				
			return sb.toString().replace('\n', ' ');
		}
	}
	
	/**
	 * <p>
	 * 		Moves the element slightly to the left, then to the right, repeatedly.
	 * </p>
	 * 
	 * @see http://wiki.script.aculo.us/scriptaculous/show/Effect.Shake
	 */
	public class Shake extends AbstractEffect {
		
		private static final long serialVersionUID = 1L;

		public Shake() {
			super();
		}
		
		@Override
		protected String getEffectName() {
			return "Shake";
		}
	}
	
	/**
	 * <p>
	 * 	Pulsates the element, loops over five times over fading out and in.
	 * </p>
	 * 
	 * @see http://wiki.script.aculo.us/scriptaculous/show/Effect.Pulsate
	 */
	public class Pulsate extends AbstractEffect {
		
		private static final long serialVersionUID = 1L;

		public Pulsate() {
			super();
		}
		
		@Override
		protected String getEffectName() {
			return "Pulsate";
		}
	}
	
	/**
	 * <p>
	 * 	Reduce the element to its top-left corner.
	 * </p>
	 * 
	 * @see http://wiki.script.aculo.us/scriptaculous/show/Effect.Squish
	 */
	public class Squish extends AbstractEffect {
		
		private static final long serialVersionUID = 1L;

		public Squish() {
			super();
		}
		
		@Override
		protected String getEffectName() {
			return "Squish";
		}
	}
	
	/**
	 * <p>
	 * 	Reduce the element to its top then to left to make it disappear.
	 * </p>
	 * 
	 * @see http://wiki.script.aculo.us/scriptaculous/show/Effect.Fold
	 */
	public class Fold extends AbstractEffect {
		
		private static final long serialVersionUID = 1L;

		public Fold() {
			super();
		}
		
		@Override
		protected String getEffectName() {
			return "Fold";
		}
	}
	
	/**
	 * Direction for Grow and Shrink.
	 * 
	 * @author Ernesto Reinaldo Barreiro (ereinald@antilia.com)
	 */
	public enum Direction {
		top_left,
		top_right,
		bottom_left,
		bottom_right,
		center;
		
		@Override
		public String toString() {
			return "'"+name().toLowerCase().replace('_', '-')+"'";
		}
	};
	
	/**
	 * <p>
	 * 	Reduce the element to its top-left corner.
	 * </p>
	 * 
	 * @see http://wiki.script.aculo.us/scriptaculous/show/Effect.Shrink
	 */
	public class Shrink extends AbstractEffect {
		
		private static final long serialVersionUID = 1L;

		public Shrink() {
			super();
		}
		
		@Override
		protected String getEffectName() {
			return "Shrink";
		}
		
		public void setDirection(Direction direction) {
			addOption("direction", direction);
		}
	}	
	
	/**
	 * 
	 * @see http://wiki.script.aculo.us/scriptaculous/show/Effect.Grow
	 */
	public class Grow extends AbstractEffect {		
		
		private static final long serialVersionUID = 1L;

		public Grow() {
			super();
		}
		
		@Override
		protected String getEffectName() {
			return "Grow";
		}
		
		public void setDirection(Direction direction) {
			addOption("direction", direction);
		}
	}
	
	/**
	 * <p>
	 * 		Gives the illusion of a TV-style switch off.
	 * </p>
	 * 
	 * @see http://wiki.script.aculo.us/scriptaculous/show/Effect.SwitchOff
	 */
	public class SwitchOff extends AbstractEffect {
		
		private static final long serialVersionUID = 1L;

		public SwitchOff() {
			super();
		}
		
		@Override
		protected String getEffectName() {
			return "SwitchOff";
		}
	}
	
	/**
	 * This effect changes an elements width and height dimensions and the base for em units. 
	 * This allows for smooth, automatic relative scaling of elements contained 
	 * within the scaled element.

	 * @see http://wiki.script.aculo.us/scriptaculous/show/Effect.Scale
	 */
	public abstract class CommonBlind extends AbstractEffect {
		
		private static final long serialVersionUID = 1L;

		public CommonBlind() {
			super();
		}

		public void setScaleFrom(float from) {
			addOption("scaleFrom", new Float(from));
		}
		
		/**
		 * Sets whether content scaling should be enabled, defaults to true.
		 * 
		 * @param scaleContent
		 */
		public void setScaleContent(boolean scaleContent) {
			addOption("scaleContent", new Boolean(scaleContent));
		}
		
		public void setScaleMode(ScaleMode scaleMode) {
			addOption("scaleMode", scaleMode);
		}
		
		/**
		 * Sets whether the element should be scaled horizontally, defaults to true.
		 * 
		 * @param scaleX
		 */
		public void setScaleX(boolean scaleX) {
			addOption("scaleX", new Boolean(scaleX));
		}
		
		/**
		 * Sets whether the element should be scaled vertically, defaults to true.
		 * @param scaleY
		 */
		public void setScaleY(boolean scaleY) {
			addOption("scaleY", new Boolean(scaleY));
		}
		
		/**
		 * If true, scale the element in a way that the center of the element stays on the same 
		 * position on the screen, defaults to false.
		 * 
		 * @param scaleFromCenter
		 */
		public void setScaleFromCenter(boolean scaleFromCenter) {
			addOption("scaleFromCenter", new Boolean(scaleFromCenter));
		}
	}
	
	/**	 
	 * 
	 * @see http://wiki.script.aculo.us/scriptaculous/show/Effect.BlindDown
	 */
	public class BlindDown extends CommonBlind {
		
		private static final long serialVersionUID = 1L;

		public BlindDown() {
			super();
		}
		
		@Override
		protected String getEffectName() {
			return "BlindDown";
		}
	}
	
	/**
	 * @see http://wiki.script.aculo.us/scriptaculous/show/Effect.BlindUp
	 */
	public class BlindUp extends CommonBlind {
		
		private static final long serialVersionUID = 1L;

		public BlindUp() {
			super();
		}
		
		@Override
		protected String getEffectName() {
			return "BlindUp";
		}
	}
	
	/**
	 * This pair of effects simulates a window blind, 
	 * where the contents of the affected elements scroll 
	 * up and down accordingly. 
	 * @see http://wiki.script.aculo.us/scriptaculous/show/Effect.SlideUp
	 */
	public class SlideUp extends CommonBlind {
		
		private static final long serialVersionUID = 1L;

		public SlideUp() {
			super();
		}
		
		@Override
		protected String getEffectName() {
			return "SlideUp";
		}
	}

	/**
	 * This pair of effects simulates a window blind, 
	 * where the contents of the affected elements scroll 
	 * up and down accordingly.
	 * 
	 * @see http://wiki.script.aculo.us/scriptaculous/show/Effect.SlideDown
	 */
	public class SlideDown extends CommonBlind {
		
		private static final long serialVersionUID = 1L;

		public SlideDown() {
			super();
		}
		
		@Override
		protected String getEffectName() {
			return "SlideDown";
		}
	}
	
	public static class Delay {
		
		/**
		 * Creates a delayed effect. 
		 * 
		 * @param effect The effect to be delayed.
		 * @param id the component id;
		 * @param delay in milliseconds 
		 * @return
		 */
		public static String delayedEffect(Effect effect, String id, int delay) {
			String eStr = effect.toJavascript(id);
			StringBuffer sb = new StringBuffer();
			sb.append("window.setTimeout('");
			sb.append(eStr.replace("'","\\'").replace("\n", " ").replace(";", ""));
			sb.append("',");
			sb.append(delay);
			sb.append(");");
			return sb.toString();
			///window.setTimeout('Effect.Appear(\'demo-effect-appear\', {duration:.3})',2500);		
		}
	}
}

