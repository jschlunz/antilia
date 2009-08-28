package com.antilia.jsp.resources;


import com.antilia.jsp.component.ResourceReference;
import com.antilia.web.resources.DefaultStyle;
import com.antilia.web.resources.images.ImgDummy;

public class Resources {

	public static ResourceReference JS_ANTILIA_AJAX = new ResourceReference(Resources.class, "ant-ajax.js");
	
	public static ResourceReference JS_TABLE = new ResourceReference(Resources.class, "table.js");
	public static ResourceReference CSS_TABLE = new ResourceReference(DefaultStyle.class, "table.css");
	
	public static ResourceReference JS_PROTOTYPE = new ResourceReference(DefaultStyle.class, "prototype.js");
	public static ResourceReference JS_BUILDER = new ResourceReference(DefaultStyle.class, "builder.js");
	public static ResourceReference JS_EFFECT = new ResourceReference(DefaultStyle.class, "effects.js");
	public static ResourceReference JS_DRAGDROP = new ResourceReference(DefaultStyle.class, "dragdrop.js");
	public static ResourceReference JS_CONTROL = new ResourceReference(DefaultStyle.class, "controls.js");
	public static ResourceReference JS_SLIDER = new ResourceReference(DefaultStyle.class, "slider.js");
	
	public static ResourceReference CSS_MAIN = new ResourceReference(DefaultStyle.class, "main.css");
	
	public static ResourceReference JS_YUI_DOM_EVENT = new ResourceReference(DefaultStyle.class, "yui270/yahoo-dom-event.js");
	public static ResourceReference JS_YUI_DOM_MIN = new ResourceReference(DefaultStyle.class, "yui270/dom-min.js");	
	public static ResourceReference JS_YUI_EVENT = new ResourceReference(DefaultStyle.class, "yui270/event-min.js");
	public static ResourceReference JS_YUI_ANIMATION = new ResourceReference(DefaultStyle.class, "yui270/animation-min.js");
	public static ResourceReference JS_YUI_DRAG_DROP = new ResourceReference(DefaultStyle.class, "yui270/dragdrop-min.js");
	
	public static ResourceReference JS_COMMON = new ResourceReference(DefaultStyle.class, "common.js");
	
	public static ResourceReference IMG_NEXT_ENABLED  = new ResourceReference(ImgDummy.class, "next_enabled.gif");
	public static ResourceReference IMG_NEXT_ENABLED_PNG  = new ResourceReference(ImgDummy.class, "next_enabled.png");
	
	public static ResourceReference IMG_PREVIOUS_ENABLED = new ResourceReference(ImgDummy.class, "previous_enabled.gif");	
	public static ResourceReference IMG_PREVIOUS_ENABLED_PNG = new ResourceReference(ImgDummy.class, "previous_enabled.png");
	
	public static ResourceReference IMG_LAST_ENABLED  = new ResourceReference(ImgDummy.class, "last_enabled.gif");
	public static ResourceReference IMG_LAST_ENABLED_PNG  = new ResourceReference(ImgDummy.class, "last_enabled.png");
	
	public static ResourceReference IMG_FIRST_ENABLED  = new ResourceReference(ImgDummy.class, "first_enabled.gif");
	public static ResourceReference IMG_FIRST_ENABLED_PNG  = new ResourceReference(ImgDummy.class, "first_enabled.png");
}
