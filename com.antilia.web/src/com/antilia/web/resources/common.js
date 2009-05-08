if (typeof(Antilia) == "undefined")
	Antilia = { };

Antilia.emptyFunction = function() { };

/**
 * Prevent event from bubbling up in the element hierarchy.
 */
Antilia.stopEvent = function(e) {
	e=Antilia.fixEvent(e);		
	e.cancelBubble = true;
	if (e.stopPropagation)
		e.stopPropagation();
}

/**
 * If no event is given as argument (IE), window.event is returned. 
 */
Antilia.fixEvent = function(e) {
	if (typeof e == 'undefined') 
		e = window.event;
	return e;		
}

Antilia.loadingVisible = false;

/**
 * Flexible dragging support.
 */
Antilia.Drag = {
	
	/**
	 * Initializes the dragging on the specified element.
	 * Element's onmousedown will be replaced by generated handler.
	 *
	 * @param {Element} element - element clicking on which the drag should begin 
	 * @param {Function} onDragBegin - handler called at the begin on dragging - passed element as first parameter
	 * @param {Function} onDragEnd - handler called at the end of dragging - passed element as first parameter
	 * @param {Function} onDrag - handler called during dragging - passed element and mouse deltas	 
	 */
	init: function(element, onDragBegin, onDragEnd, onDrag) {		
		
		if (typeof(onDragBegin) == "undefined")
			onDragBegin = Antilia.emptyFunction;
		if (typeof(onDragEnd) == "undefined")
			onDragEnd = Antilia.emptyFunction;
		if (typeof(onDrag) == "undefined")
			onDrag = Antilia.emptyFunction;
		
		// set the mousedown handler 
		element.onmousedown = function(e) {			
			
			e = Antilia.fixEvent(e);
	
			// HACK - for safari stopPropagation doesn't work well because
			// it also prevents scrollbars and form components getting the
			// event. Therefore for safari the 'ignore' flag is set on event. 
			if (typeof(e.ignore) == "undefined") {
				
				Antilia.stopEvent(e);
	
				onDragBegin(element);
			
				element.onDrag = onDrag;
				element.onDragEnd = onDragEnd;
				
				element.lastMouseX = e.clientX;
				element.lastMouseY = e.clientY;
				
				element.old_onmousemove = document.onmousemove;
				element.old_onmouseup = document.onmouseup;
				element.old_onselectstart = document.onselectstart;			
				
				document.onselectstart = function() { return false; }
				document.onmousemove = Antilia.Drag.mouseMove;
				document.onmouseup = Antilia.Drag.mouseUp;
							
				Antilia.Drag.current = element;
							
				return false;
			} 			
		};		
	},
	
	/**
	 * Deinitializes the dragging support on given element. 
	 */
	clean: function(element) {
		element.onmousedown = null;
	},

	/**
	 * Called when mouse is moved. This method fires the onDrag event
	 * with element instance, deltaX and deltaY (the distance
	 * between this call and the previous one).
	 
	 * The onDrag handler can optionally return an array of two integers 
	 * - the delta correction. This is used, for example, if there is
	 * element being resized and the size limit has been reached (but the
	 * mouse can still move).
	 * 
	 * @param {Event} e
	 */	
	mouseMove: function(e) {
		e = Antilia.fixEvent(e);
		var o = Antilia.Drag.current;

		// this happens sometimes in Safari 
		if (e.clientX < 0 || e.clientY < 0) {
			return;
		}

		if (o != null) {		
			var deltaX = e.clientX - o.lastMouseX;
			var deltaY = e.clientY - o.lastMouseY;
				
			var res = o.onDrag(o, deltaX, deltaY);
			
			if (res == null)
				res = [0, 0];
			
			o.lastMouseX = e.clientX + res[0];
			o.lastMouseY = e.clientY + res[1];
		}
		
		return false;
	},

	/**
	 * Called when the mouse button is released.
	 * Cleans all temporary variables and callback methods.
	 * 
	 * @param {Event} e
	 */	
	mouseUp: function(e) {		
		e = Antilia.fixEvent(e);
		var o = Antilia.Drag.current;
		
		o.onDragEnd(o);		
		
		o.onDrag = null;
		o.onDragEnd = null;
		o.lastMouseX = null;
		o.lastMouseY = null;
		
		document.onmousemove = o.old_onmousemove;
		document.onmouseup = o.old_onmouseup;		
		document.onselectstart = o.old_onselectstart;
		
		o.old_mousemove = null;		
		o.old_mouseup = null;
		o.old_onselectstart = null;
		
		Antilia.Drag.current = null;
	}
};

Antilia.getElementsByClass = function getElementsByClass(node,searchClass,tag) {
    var classElements = new Array();
    var els = node.getElementsByTagName(tag); // use "*" for all elements
    var elsLen = els.length;
    for (i = 0, j = 0; i < elsLen; i++) {
         if (els[i].className == searchClass) {
             classElements[j] = els[i];
             j++;
         }
    }
    return classElements;
}
 
Antilia.replaceStyle = function(nodeid,searchClass,tag, styleClass) { 	
	var node = document.getElementById(nodeid);
	var eles = Antilia.getElementsByClass(node,searchClass,tag);	
	
	for(var i=0; i < eles.length; i++) {
		 var ele = eles[i];		 
		 if(ele) {
			ele.className = styleClass;
		 }
	 }	 
};

Antilia.setStyle = function(fields, styleClass) { 	
	 for(var i=0; i < fields.length; i++) {
		 var id = fields[i];		 
		 var ele = document.getElementById(id);		 
		 if(ele) {
			ele.className = styleClass;
		 }
	 }	 
 };
 

