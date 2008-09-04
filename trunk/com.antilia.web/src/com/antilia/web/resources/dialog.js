	//Class for draggable panel.	
	function Panel(id, parentId, ie, minWidth, minHeight, modal, panelClass, onDragClass, selectedClass) {
		this.id = id;
		this.parentId = parentId;
		this.ie = ie;
			
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
		this.height = parseInt(this.panel.style.height, 10);	
		this.panelBody = document.getElementById(id+"Body");		
		this.panelHeader = document.getElementById(id+"Header");
		this.panelResize = document.getElementById(id+"Resize");	
		
		if(this.panelHeader)
			Antilia.Drag.init(this.panelHeader, this.onBeginDrag , this.onEndDrag, this.onDrag);
		if(this.panelResize) {	
			Antilia.Drag.init(this.panelResize, function() {} , function() { }, this.onResize);
		}
		this.folded = false;
		this.overlayVisible = false;
		this.loadingVisible = false;
		this.addModalLayer();
		this.addLoadingLayer();	
		if(this.parentId) {	
			this.parent = Antilia_dragPanels.getPanel(this.parentId);
			if(this.parent) {
				this.toggleModal();
			}
		}					
	}
	
	Panel.prototype.addModalLayer = function() {
		this.overlay = $(document.createElement('div'));
		this.overlay.id = this.id + 'modal_overlay';
		this.overlay.className = 'dark-mask-dark';
		this.panelBody.appendChild(this.overlay);
		this.overlay.style.display = 'none';
		this.overlay.style.width = "1900px";
		this.overlay.style.height = "1800px";
	}
	
	Panel.prototype.addLoadingLayer = function() {
        this.loading = $(document.createElement('div'));
        this.loading.id = this.id + 'loading_overlay';
        this.loading.className = 'bussy_indicator';
        this.panelBody.appendChild(this.loading);
        this.loading.style.display = 'none';
        this.loading.style.width = "160px";
        this.loading.style.height = "40px";
        this.loading.style.top =   "100px";
        this.loading.style.left =   "100px"
    } 
	
	Panel.prototype.toggleModal = function() {
		if(this.modal == false)
			return;
		if(!this.overlayVisible) {			
			this.overlayVisible = true;	
			if(this.parent) {
			/*
			new Effect.toggle(this.parent.overlay, 
			'appear',
			{
 				afterFinish: this.afterFold,
 				beforeStart: this.beforeFold, 
 				duration: 0.1,
 				from: 0,
 				to: 0.3
			});
			*/
			this.parent.overlay.style.display = 'block'; 
		}
		} else {
			this.overlayVisible = false;
			if(this.parent) {
			/*
			new Effect.toggle(this.parent.overlay, 
			'appear',
			{
 				afterFinish: this.afterFold,
 				beforeStart: this.beforeFold, 
 				duration: 0.1,
 				from: 0.3,
 				to: 0
			});
			*/
			this.parent.overlay.style.display = 'none'; 
		}
		}		
	}	
	
	Panel.prototype.toggleLoadingLayer = function() {        
        if(!this.loadingVisible) {            
            this.loading.style.display='';      
            this.loadingVisible = true;            
            new Effect.toggle(this.overlay, 
            'appear',
            {
                afterFinish: this.afterFold,
                beforeStart: this.beforeFold, 
                duration: 0.1,
                from: 0,
                to: 0.3
            });
        
        } else {            
            this.loading.style.display='none';
            this.loadingVisible = false;            
            new Effect.toggle(this.overlay, 
            'appear',
            {
                afterFinish: this.afterFold,
                beforeStart: this.beforeFold, 
                duration: 0.1,
                from: 0.3,
                to: 0
            });        
        }       
    }
    
	Panel.prototype.toggleFold = function() {
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
		    var panel = Antilia_dragPanels.getPanel(element.parentNode.id);
            var Dom = YAHOO.util.Dom;
            Dom.setStyle(panel.panel, "opacity", 0.6);
             
            /*   
            new Effect.toggle(panel.panel, 
                'appear',
                {
                    duration: 0,
                    from: 1,
                    to: 0.6
                });
                */
         }					
		var x = parseInt(w.style.left, 10) + deltaX;
		var y = parseInt(w.style.top, 10) + deltaY;

		var res = [0, 0];
		
		if (x < 0) {
			res[0] = -deltaX;
			x = 0;
		}
		if (y < 0) {
			res[1] = -deltaY;
			y = 0;	
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
            Dom.setStyle(panel.panel, "opacity", 1);
		    /*
			new Effect.toggle(panel.panel, 
	            'appear',
	            {
	                duration: 0,
	                from: 0.6,
	                to: 1
	            });
	          */
	          
            } 
		panel.panel.className=panel.panelClass;		
	}
	
	Panel.prototype.onResize = function(element, deltaX, deltaY) {
		var window = element.parentNode.parentNode;
		var bodyId = element.parentNode.parentNode.id+"Body";
		var frameId = element.parentNode.parentNode.id+"Frame";
		
		var panel = Antilia_dragPanels.getPanel(element.parentNode.parentNode.id);		
		
		var res = [0, 0];
		
		if(panel.folded) {
			return res;
		}
		
		
		var body = document.getElementById(bodyId);
		var frame = document.getElementById(frameId);
							
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
	
	//prototypes for dragable panels.	
	function Panels() {
		this.panels = new Array();
			
		this.addPanel = function(id, parentId, ie, minWidth, minHeight, modal, panelClass, onDragClass, selectedClass) {
			var panel = this.getPanel(id)
			if(panel == null) {
				panel = new Panel(id, parentId, ie, minWidth, minHeight, modal, panelClass, onDragClass, selectedClass);
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
				panel.panel.zIndex=2;
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
		
		this.showLoading = function(id) {
            if(this.panels != null && this.panels.length > 0) {
                for(var i= 0; i <this.panels.length; i++) {
                    var panel = this.panels[i];
                    if(panel.id==id) {
                        if(panel.id==id) {
                            panel.toggleLoadingLayer();
                        }               
                    } 
                }
            }
        }
        
		// bring forward (z-index) a panel
		this.orderPanels = function(current) {
			var currentPanel = this.getPanel(current);
			if(this.panels != null && this.panels.length > 0) {												
				for(var i=this.panels.length-1; i>=0 ; i--) {
					var panel = this.panels[i];
					if(!panel.minimized) {
						if(panel.id==current || panel.id == currentPanel.parentId || panel.parentId == current) {
							panel.panel.style.zIndex = 2; 
							panel.panel.className=panel.selectedClass;									
						} else {
						    panel.panel.className=panel.panelClass;
							panel.panel.style.zIndex = 1;							
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
            /*
            new Effect.toggle(this.overlay, 
            'appear',
            {
                afterFinish: this.afterFold,
                beforeStart: this.beforeFold, 
                duration: 0.1,
                from: 0,
                to: 0.3
            });
            */ 
            this.parent.overlay.style.display = 'block';       
        } else {
            this.overlayVisible = false;
            /*
            new Effect.toggle(this.overlay, 
            'appear',
            {
                afterFinish: this.afterFold,
                beforeStart: this.beforeFold, 
                duration: 0.1,
                from: 0.3,
                to: 0
            });
            */
            this.parent.overlay.style.display  = 'none';        
        }       
    }