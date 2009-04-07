	function RoundPane(id, ie, minWidth, minHeight, center) {
		this.id = id;
		this.ie = ie;
		this.minWidth = minWidth;
		this.center = center;
		this.minHeight = minHeight;
		this.folded = false;
		this.panel = document.getElementById(id);
		this.panelBody = document.getElementById(id+"Body");
		this.panelResize = document.getElementById(id+"Handle");
		if(this.panelResize) {
			this.panelResize.wid = id;		
			this.panelResize.ie = ie;
			this.panelResize.minWidth = minWidth;
			this.panelResize.minHeight = minHeight;
			Antilia.Drag.init(this.panelResize, function() {} , function() { }, this.onResize);
		}
		if(this.center) {
			this.panel.style.position = 'absolute';
			var width = parseInt(this.panel.style.width, 10);
			var height = parseInt(this.panel.style.height, 10);			
            YAHOO.util.Dom.setStyle(this.id, "top", ((YAHOO.util.Dom.getViewportHeight()/2)-height)+ "px"); 
            YAHOO.util.Dom.setStyle(this.id, "left", ((YAHOO.util.Dom.getViewportWidth()/2)-width/2) + "px");
            
            
            new Effect.toggle(this.panel, 
        			'appear',
        			{         				 
         				duration: 1,
         				from: 0,
         				to: 1
        			});
        	
		}
	}
	
	RoundPane.prototype.toggleFold = function() {
		if(!this.folded) {			
			this.folded = true;
			this.panelBody.folded = true;	
			this.panelBody.beforeFoldHeight = parseInt(this.panel.style.height, 10);
		} else {
			this.folded = false;
			this.panelBody.folded = false;
		}
		new Effect.toggle(this.panelBody, 
		'blind',
			{
 				duration: 0.5
			});
	}
	
	RoundPane.prototype.onResize = function(element, deltaX, deltaY) {
		var window = document.getElementById(element.wid);		
		var body = document.getElementById(element.wid+"Body");
		var iWidth = document.getElementById(element.wid+"Width");
		var iHeight = document.getElementById(element.wid+"Height");
		var res = [0, 0];			
		
		if(body.folded == true) {
			return res;
		}				
		var width = parseInt(window.style.width, 10) + deltaX;
		var height = parseInt(window.style.height, 10) + deltaY;
				
		var bodywidth = parseInt(body.style.width, 10) + deltaX;
		var bodyheight = parseInt(body.style.height, 10) + deltaY;
		
		if (width < element.minWidth) {
			res[0] = -deltaX;
			width = element.minWidth;
			if(element.ie == true) {
				bodywidth = element.minWidth-10;
			} else {
				bodywidth = element.minWidth-12;
			}			
		}
	
		if (height < element.minHeight) {
			res[1] = -deltaY;
			height = element.minHeight;
			bodyheight = element.minHeight-30;	
		}
										
		window.style.width = width + "px";
		window.style.height = height + "px";
		
		iWidth.value = width;
		iHeight.value = height;
		
		body.style.width = bodywidth + "px";
		body.style.height = bodyheight + "px";
			
		
		return res;
	}