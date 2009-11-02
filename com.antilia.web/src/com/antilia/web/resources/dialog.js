	Wicket.Window.Mask.zIndex = 1;
	
	//Class for draggable panel.	
	function Panel(id, parentId, ie, minWidth, minHeight, modal, panelClass, onDragClass, selectedClass, centered, isContainer,draggable) {
		this.id = id;
		this.parentId = parentId;
		this.ie = ie;
		this.centered = centered;
		this.draggable = draggable;	
		this.modal = modal;
		this.panelClass = panelClass;
		this.onDragClass =  onDragClass;
		this.selectedClass = selectedClass;
		
		if(minWidth)
			this.minWidth = minWidth;
		else 
			this.minWidth = 100;
		if(minHeight)	
			this.minHeight = minHeight;
		else
			this.minHeight = 100;
		
		this.panel = document.getElementById(id);
		
		this.parentPanel = document.getElementById(parentId);		
				
		if(this.parentPanel == null) {		
			var element = document.createElement("div");			
			element.id='mask-'+this.id;
			this.panel.parentNode.appendChild(element);	
		}
				
		
		this.panel = document.getElementById(id);
		
		if(Antilia.Browser.ie6 == true) {
			this.panel.innerHTML =  '<iframe id="'+id+'-iframe" src="" scrolling="no" frameborder="0" style="position: absolute; top: 0px; left: 0px; z-index: -1; width: 100px; height: 100px; display: block; filter:alpha(opacity=0);"></iframe>' + this.panel.innerHTML;
			this.iframe = document.getElementById(id+'-iframe');
			this.resizeIFrame(this.iframe, this.panel);
		}
		
		this.parentPanel = document.getElementById(parentId);
		
		this.height = parseInt(this.panel.style.height, 10);	
		this.panelBody = document.getElementById(id+"Body");		
		this.panelHeader = document.getElementById(id+"Header");
		this.panelResize = document.getElementById(id+"Resize");
		
		if(this.panelHeader && draggable== true)
			Antilia.Drag.init(this.panelHeader, this.onBeginDrag , this.onEndDrag, this.onDrag);
		if(this.panelResize) {	
			Antilia.Drag.init(this.panelResize, function() {} , function() { }, this.onResize);
		}
		this.folded = false;
		this.overlayVisible = false;
		this.loadingVisible = false;
		this.addModalLayer();
		if(this.parentId) {	
			this.parent = Antilia_dragPanels.getPanel(this.parentId);			
		}							

		this.toggleModal();
				
		if(this.centered) {			
			if(this.parent) {
				var pwidth = parseInt(this.parent.panel.style.width, 10);
				var pheight = parseInt(this.parent.panel.style.height, 10);				
				var cor = 0;
				if(pwidth == 100 || isNaN(pwidth)) {
					pwidth = YAHOO.util.Dom.getViewportWidth();
				}					
				if(pheight == 100 || isNaN(pheight)) {
					pheight = YAHOO.util.Dom.getViewportHeight();
					cor = 0;
				}				
				var width = parseInt(this.panel.style.width, 10);
				var height = parseInt(this.panel.style.height, 10);
				YAHOO.util.Dom.setStyle(this.id, "top", (((pheight-height)/2)-cor)+ "px"); 
	            YAHOO.util.Dom.setStyle(this.id, "left", (((pwidth-width)/2)) + "px");
			} else {		
				var fbody = document.getElementById('frameBody');
				if(fbody) {
					var width = parseInt(this.panel.style.width, 10);
					var height = parseInt(this.panel.style.height, 10);		
					
					var fwidth = YAHOO.util.Dom.getViewportWidth();
					var fheight = parseInt(fbody.style.height, 10);
					
					YAHOO.util.Dom.setStyle(this.id, "top", ((fheight/2)-height/2)+ "px"); 
					YAHOO.util.Dom.setStyle(this.id, "left", ((fwidth/2)-width/2) + "px");
				} else {
					var width = parseInt(this.panel.style.width, 10);
					var height = parseInt(this.panel.style.height, 10);		
					YAHOO.util.Dom.setStyle(this.id, "top", ((YAHOO.util.Dom.getViewportHeight()/2)-height/2)+ "px"); 
					YAHOO.util.Dom.setStyle(this.id, "left", ((YAHOO.util.Dom.getViewportWidth()/2)-width/2) + "px");
				}
			}
		}
	}
	
	Panel.prototype.resizeIFrame = function(iframe, panel) { 
		if(iframe) {
			iframe.style.width = panel.offsetWidth;
			iframe.style.height = panel.offsetHeight;
			iframe.style.top = -parseInt(panel.currentStyle.borderTopWidth);
			iframe.style.left = -parseInt(panel.currentStyle.borderLeftWidth);
		}
	}
	
	Panel.prototype.resizeIFrameHeight = function(iframe, height) { 
		if(iframe) {
			iframe.style.height = height + 'px';
		}
	}
	
	
	Panel.prototype.addModalLayer = function() {
		this.overlay = $(document.createElement('div'));
		this.overlay.id = this.id + 'modal_overlay';
		this.overlay.className = 'dark-mask-dark';
		this.panelBody.appendChild(this.overlay);
		this.overlay.style.display = 'none';
		//this.overlay.style.width = "1900px";
		this.overlay.style.left = "0px";
		this.overlay.style.top = "0px";
		//this.overlay.style.height = "1800px";
		this.overlay.style.right = "0px";
		this.overlay.style.bottom = "0px";
	}
		
	Panel.prototype.toggleModal = function() {		
		if(this.modal == false)
			return;		
		
		if(this.parent != null) {
			if(!this.overlayVisible) {			
				this.overlayVisible = true;	
				if(Antilia.Browser.ie6==true) {
					Antilia.disableSelects();
				}
				this.parent.overlay.style.display = 'block'; 
			} else {
				this.overlayVisible = false;
				if(Antilia.Browser.ie6==true) {
					Antilia.enableSelects();
				}
				this.parent.overlay.style.display = 'none'; 
			}			
		} else {
			if(!this.overlayVisible) {			
				this.overlayVisible = true;	
				var element = document.getElementById("mask-"+this.id);
				if(element)
					element.className  = "wicket-mask-dark"; 
			} else {
				this.overlayVisible = false;
				var element = document.getElementById("mask-"+this.id);
				if(element)
					element.style.display = 'none'; 
			}
		}		
	}	
		
    
	Panel.prototype.toggleFold = function() {
		if(!this.folded) {			
			this.folded = true;
			this.panelBody.folded = true;	
			this.panelBody.beforeFoldHeight = parseInt(this.panel.style.height, 10);
			this.resizeIFrameHeight(this.iframe, 30);
		} else {
			this.folded = false;
			this.panelBody.folded = false;
			this.resizeIFrameHeight(this.iframe, parseInt(this.panel.style.height, 10));
		}
		new Effect.toggle(this.panelBody, 
		'blind',
			{
 				afterFinish: this.afterFold,
 				beforeStart: this.beforeFold, 
 				duration: 0.5
			});
		
	}
	
	Panel.prototype.afterFold = function(body) {
		if(body.element.folded == true) {	
			body.element.parentNode.parentNode.style.height = 28 + "px";	
		} else {
			body.element.style.overflow = 'auto';
		}
	}
	
	Panel.prototype.beforeFold = function(body) {
		if(body.element.folded == false) {	
			body.element.parentNode.parentNode.style.height = body.element.beforeFoldHeight + "px";
		}
	}
		
	Panel.prototype.toggleMinimized = function() {
		//this.minimized = !this.minimized;
		this.resetPanel();
	}
	
	Panel.prototype.resetPanel = function() {
		this.panel = document.getElementById(this.id);
	}
	
	Panel.prototype.onDrag = function(element, deltaX, deltaY) {
		var w = element.parentNode;	
		if(this.drag != true) {
		    this.drag = true;
		    var panel = Antilia_dragPanels.getPanel(w.id);
		    if(panel.parentPanel) {
		    	this.pWidth = parseInt(panel.parentPanel.style.width, 10);
		    	this.pHeight = parseInt(panel.parentPanel.style.height, 10);		    	
		    }
            var Dom = YAHOO.util.Dom;
            
            if(Antilia.Browser.ie6 == false && Antilia.Browser.ie7 == false) {
            	Dom.setStyle(panel.panel, "opacity", 0.6);                         
            }
         }					
		var x = parseInt(w.style.left, 10) + deltaX;
		var y = parseInt(w.style.top, 10) + deltaY;
		
		
		var width = parseInt(w.style.width, 10);
		var height = parseInt(w.style.height, 10);
		
		
		var res = [0, 0];
		
		if (x < 0) {
			res[0] = 0;
			x = 0;
		} else if(this.pWidth != null && ((width + x) > this.pWidth)) {
			res[0] = -deltaX;
			x = this.pWidth-width-8;
		}
		
		if (y < 0) {
			res[1] = -deltaY;
			y = 0;	
		} else if(this.pHeight != null && ((height + y) > this.pHeight)) {
			res[0] = 0;
			y = this.pHeight-height-24;
		}
				
			
		w.style.left = x + "px";
		w.style.top = y + "px";		
		
		return res;
	}
	
	Panel.prototype.onBeginDrag = function(element) {
		var panel = Antilia_dragPanels.getPanel(element.parentNode.id);
		//panel.panelBody.style.display = 'none';
		this.drag = true;			
		Antilia_dragPanels.orderPanels(panel.id);
		panel.panel.className=panel.onDragClass;					
	}
	
	Panel.prototype.onEndDrag = function(element) {
		var panel = Antilia_dragPanels.getPanel(element.parentNode.id);
		if(this.drag == true) {
		    this.drag = false;
		    var Dom = YAHOO.util.Dom;
		    if(Antilia.Browser.ie6 == false && Antilia.Browser.ie7 == false ) {
		    	Dom.setStyle(panel.panel, "opacity", 1);
		    }		      
		} 
		panel.panel.className=panel.panelClass;		
	}
	
	Panel.prototype.onResize = function(element, deltaX, deltaY) {		
		var id = element.parentNode.parentNode.id;
		var window = element.parentNode.parentNode;
		var bodyId = id +"Body";
		var frameId = id +"Frame";
		var iframeId = id +"-iframe";
		
			
		var panel = Antilia_dragPanels.getPanel(element.parentNode.parentNode.id);		
						
		var res = [0, 0];
		
		if(panel.folded || panel.overlay.style.display == 'block') {
			return res;
		}
		
		
		var body = document.getElementById(bodyId);
		var frame = document.getElementById(frameId);
		var iframe = document.getElementById(iframeId);
							
		var width = parseInt(window.style.width, 10) + deltaX;
		var height = parseInt(window.style.height, 10) + deltaY;
		
		var bodywidth = parseInt(body.style.width, 10) + deltaX;
		var bodyheight = parseInt(body.style.height, 10) + deltaY;
			
		
		if (width < panel.minWidth) {
			res[0] = -deltaX;
			width = panel.minWidth;
			if(window.ie == true) {
			   bodywidth = width;
			} else
				bodywidth = width -4;
		}
		
		if (height < panel.minHeight) {
			res[1] = -deltaY;
			height = panel.minHeight;
			bodyheight = height-28;	
		}
								
			
		window.style.width = width + "px";
		window.style.height = height + "px";
		
		panel.resizeIFrame(iframe, window);
		
		if(frame) {
			frame.style.width = bodywidth + "px";
		}		
		body.style.width = bodywidth + "px";
		body.style.height = bodyheight + "px";		
		
		panel.height = window.style.height;
		
		return res;
	}	
	
	function Coordinate(id, e) {
	       this.id = id;
	       this.x = e.clientX;
	       this.y = e.clientY;
	}
	
	//prototypes for draggable panels.	
	function Panels() {
		this.panels = new Array();
			
		this.addPanel = function(id, parentId, ie, minWidth, minHeight, modal, panelClass, onDragClass, selectedClass, centered, isContainer, draggable) {
			var panel = this.getPanel(id)
			if(panel == null) {
				panel = new Panel(id, parentId, ie, minWidth, minHeight, modal, panelClass, onDragClass, selectedClass, centered, isContainer, draggable);
				this.panels[this.panels.length]= panel;
				this.panels.id = id;
				if(panel.parentPanel) {
					panel.panel.zIndex = panel.parentPanel.zIndex;
					this.orderPanels(id);
				} else  {
					this.orderPanels(id);
				}
			} else {
				panel.ie = ie;
				panel.parentId = parentId;
				panel.panel.zIndex=5002;
			}			
		}
		
		this.addAndRemovePanel = function(id, parentId, ie, minWidth, minHeight, modal, panelClass, onDragClass, selectedClass, centered,isContainer, draggable) {
            var panel = this.getPanel(id)
           if(panel != null) {
                this.deletePanel(id);
           }
            panel = new Panel(id, parentId, ie, minWidth, minHeight, modal, panelClass, onDragClass, selectedClass, centered,isContainer, draggable);
            this.panels[this.panels.length]= panel;
            this.panels.id = id;
            if(panel.parentPanel) {
                panel.panel.zIndex = panel.parentPanel.zIndex;
                this.orderPanels(id);
            } else  {
                this.orderPanels(id);
            }                       
        }
		
		
		// shows the panel at the same position of the button the was clicket.		
		this.showPanel = function(id, buttonId) {
		      var panel = this.getPanel(id);
		      var pos = YAHOO.util.Dom.getXY(buttonId);    	      	      	      
		      if(panel != null) {                    
		            YAHOO.util.Dom.setXY(panel.panel, pos);
               }
		}
		        
		// retrieves a panel based in its ID.
		this.getPanel = function(id) {
			for(var i=this.panels.length-1; i>=0 ; i--) {
				var panel = this.panels[i];
				if(panel.id==id) {
					return panel;
				}				
			}
			return null;
		}	
				
		// folds/unfiolds a given panel		
		this.foldPanel = function(id) {
			var panel = this.getPanel(id);
			if(panel) {
				panel.toggleFold();
			}			
		}
		
		// deletes a panel
		this.deletePanel = function(id) {
			var ele = document.getElementById(id+"C");
			if(ele) {
				ele.innerHTML = '';
			}
			var newpanels = new Array();
			if(this.panels != null && this.panels.length > 0) {
				for(var i= 0; i <this.panels.length; i++) {
					var panel = this.panels[i];
					if(panel.id==id || panel.parentId==id) {
						if(panel.id==id) {
							panel.toggleModal();
						}				
					} else {
						newpanels[newpanels.length] = panel;
					}
				}
			}
			this.panels = newpanels;
		}
        
		// bring forward (z-index) a panel
		this.orderPanels = function(current) {
			var currentPanel = this.getPanel(current);
			if(currentPanel == null) {
				return;
			}
			if(this.panels != null && this.panels.length > 0) {												
				for(var i=this.panels.length-1; i>=0 ; i--) {
					var panel = this.panels[i];
					if(!panel.minimized) {
						if(panel.id==current || panel.id == currentPanel.parentId || panel.parentId == current) {
							panel.panel.style.zIndex = 5002; 
							panel.panel.className=panel.selectedClass;									
						} else {
						    panel.panel.className=panel.panelClass;
							panel.panel.style.zIndex = 5001;							
						}
					}						
				}
				this.panels[0];
			}			
		}										
	}	
	
	var Antilia_dragPanels = new Panels();
	
	
	function ModalContainer(id) {
	   this.id = id;
	   this.overlayVisible = false;
	   this.addModalLayer();
	}
	
	ModalContainer.prototype.addModalLayer = function() {
        this.overlay = $(document.createElement('div'));
        this.overlay.id = this.id + 'modal_overlay';
        this.overlay.className = 'dark-mask-dark';
        this.panelBody.appendChild(this.overlay);
        this.overlay.style.display = 'none';
        this.overlay.style.width = "1900px";
        this.overlay.style.height = "1800px";
    }
	
	
	ModalContainer.prototype.toggleModal = function() {
        if(!this.overlayVisible) {          
            this.overlayVisible = true;
            this.parent.overlay.style.display = 'block';       
        } else {
            this.overlayVisible = false;            
            this.parent.overlay.style.display  = 'none';        
        }       
    }