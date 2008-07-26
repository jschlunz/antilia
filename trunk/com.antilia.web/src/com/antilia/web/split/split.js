
	function HSplitPane(id) {
		this.id = id;
		this.splitDrag = document.getElementById(id);
		Antilia.Drag.init(this.splitDrag, function() {} , function() { }, this.onResize);
	}

	HSplitPane.prototype.onResize = function(element, deltaX, deltaY) {
		var td = element.parentNode.parentNode.parentNode.parentNode;	
		
		var res = [0, 0];
			
		if ((parseInt(td.offsetWidth)+ deltaX) >0)
	    {
	    	td.style.width =  parseInt(td.offsetWidth)+ deltaX +"px";	
	    }
	    else
	   		this.style.width=1;
		
		return res;
	}
	
	function VSplitPane(id) {
		this.id = id;
		this.splitDrag = document.getElementById(id);
		Antilia.Drag.init(this.splitDrag, function() {} , function() { }, this.onResize);
	}

	VSplitPane.prototype.onResize = function(element, deltaX, deltaY) {
		var td = element.parentNode.parentNode;	
		
		var res = [0, 0];
			
		if ((parseInt(td.offsetHeight)+ deltaY) >0)
	    {
	    	td.style.height =  parseInt(td.offsetHeight)+ deltaY +"px";	
	    }
	    else
	   		this.style.height=1;
		
		return res;
	}