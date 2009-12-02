function Letter(source, url) {
	this.source = source;
	this.url = url;
	this.initialize();
}

Letter.prototype.initialize = function() { 
    var el = YAHOO.util.Dom.get(this.source);     
    
    this.dd = new YAHOO.util.DD(this.source, 'letter');    
    
    this.startPos = YAHOO.util.Dom.getXY(el);
    this.dd.url = this.url;
    this.dd.onDragDrop = function(e, id) { 
    	wicketAjaxGet(this.url+ '&sourceId=' + this.getEl().id + '&targetId=' + id);
    }
	    
    this.dd.onInvalidDrop = function(e) {         
    	// Animating the move is more intesting 
       var Dom = YAHOO.util.Dom;               
       Dom.setStyle(this.getEl(), "border", "1px solid");
       new YAHOO.util.Motion(  
        this.getEl().id, {  
                      points: {  
                          to: this.startPos 
                      } 
                  },  
                  0.3,  
                  YAHOO.util.Easing.easeOut  
              ).animate();                       
     }
     
    this.dd.onMouseDown  = function(e) {  
        this.startPos  = YAHOO.util.Dom.getXY(this.getEl());        
     }
     
    this.dd.startDrag = function(x, y) { 
        var Dom = YAHOO.util.Dom;
        //Dom.setStyle(this.getEl(), "opacity", 0.8);
        Dom.setStyle(this.getEl(), "border", "1px dashed");
    }
    
    this.dd.onDragOver =  function(e, id) { 
        var Dom = YAHOO.util.Dom;
        var el = YAHOO.util.Dom.get(id);
        //Dom.setStyle(el, "opacity", 0.8);
        Dom.setStyle(el, "border", "1px dashed");   
    }
   
    this.dd.onDragOut =  function(e, id) { 
        var Dom = YAHOO.util.Dom;
        var el = YAHOO.util.Dom.get(id);
        el.parentNode.className='';        
        //Dom.setStyle(el, "opacity", 1);          
        Dom.setStyle(el, "border", "1px solid");
    }	    	
}

function Target(target) {
	this.target = target;
	this.dt = new YAHOO.util.DDTarget (this.target, 'letter');
}
