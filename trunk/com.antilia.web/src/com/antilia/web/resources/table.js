
function Table(id, rows, ncols, rendringCount, urls) {
	this.id = id;
	// rows is an array of Rows	
	this.rows = rows;
	this.ncols = ncols;	
	this.rendringCount = rendringCount;
	this.urls = urls;
	this.columns = new Array();    
    for(var i = 0; i < this.ncols; i++) {               
        this.addColumn(i);
    }    
    
    //alert('here');
}

Table.prototype.createDraggables = function() { 
    var trashId  = this.id + '_' + 'dropCol';
    new YAHOO.util.DDTarget(trashId , this.id);
    
    for(var j = 1; j < this.ncols; j++) {               
        var titleId = this.id + '_dragger_'  + this.rendringCount+ '_' + j;
        //new Draggable(titleId, { revert: true, ghosting: true, zindex: 100});                
        var url = this.urls[j-1];
        //alert(url);
       new TColumn(this.id, titleId, url);
    }
}

Table.prototype.removeOldDroppables = function() { 
    if(this.rendringCount > 1) {
	    for(var j = 1; j < this.ncols; j++) {               
	        //var titleId = this.id + '_title_' + j;                      
	        var titleId = this.id + '_dragger_' +(this.rendringCount-1) + '_'+ j;
	        try {
	           Droppables.remove(titleId);         
	            /*
	            var element = document.getElementById(titleId);
	            Droppables.remove(element);
	            if(element) {                
	               alert('Here');	                                
	             }
	             */
	          } catch (err) {
	            alert(err);
	          }	        
	    }
	}
}

Table.prototype.addColumn = function(col) {	
	var column = new Column(this.id, col);	
	this.columns[this.columns.length]= column;
}

Table.prototype.toggleSelected = function(row) {	
	var rowObj = this.rows[row];
	if(rowObj)
		rowObj.toggleSelection();
}

Table.prototype.highlight = function(row) {	
	var rowObj = this.rows[row];
	if(rowObj)
		rowObj.highlight();
}

Table.prototype.unhighlight = function(row) {	
	var rowObj = this.rows[row];
	if(rowObj)
		rowObj.unhighlight();
}

function Column(tableId, number) {
	this.tableId = tableId;
	this.number = number;
	this.resizeHandleId = this.tableId + '_c_' + this.number;
	this.resizeHandle = document.getElementById(this.resizeHandleId);	
	if(this.resizeHandle)
		Antilia.Drag.init(this.resizeHandle, function() {} , function() { }, this.onResize);		
}


Column.prototype.onResize = function (obj, deltaX, deltaY) {
	var td = obj.parentNode.parentNode.parentNode.parentNode;		
	if ((parseInt(td.offsetWidth)+(deltaX))>0) {
    	td.style.width =  parseInt(td.offsetWidth)+deltaX+"px";
		td.getElementsByTagName("input")[0].value=parseInt(td.style.width);
    }
    else
  		td.style.width=1;
  	var res = [0, 0];
	return res;
}	
 

function TColumn(tableId, id, url) {
    this.tableId = tableId;
    this.id = id;
    this.url = url;
    this.initialize();
}

TColumn.prototype.initialize = function() { 
    var el = YAHOO.util.Dom.get(this.id);     
    this.dd = new YAHOO.util.DD(this.id, this.tableId);    
    this.startPos = YAHOO.util.Dom.getXY(el);
    this.dd.url = this.url;
      
    this.dd.onDragDrop = function(e, id) { 
       //alert('Column ' + this.getEl().id + ' dopped on column ' +id); 
        wicketAjaxGet(this.url+ '&sourceId=' + this.getEl().id + '&targetId=' + id);
    }
    
    this.dd.onInvalidDrop = function(e) {         
        // Animating the move is more intesting 
               var Dom = YAHOO.util.Dom;
               Dom.setStyle(this.getEl(), "opacity", 1);
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
        Dom.setStyle(this.getEl(), "opacity", 0.5);
    }
    
    this.dd.onDragOver =  function(e, id) { 
        var Dom = YAHOO.util.Dom;
        var el = YAHOO.util.Dom.get(id);
        Dom.setStyle(el, "border", '1px solid white');
    }
    
    this.dd.onDragOut =  function(e, id) { 
        var Dom = YAHOO.util.Dom;
        var el = YAHOO.util.Dom.get(id);
        Dom.setStyle(el, "border", 'none');                 
    }
}
 
function Row(tableId, number, selected) {
	this.tableId = tableId;
	this.number = number;
	this.selected = selected;
	this.rowId = this.tableId + '_r_' + this.number;
	this.row = document.getElementById(this.rowId);		
}

Row.prototype.toggleSelection = function () {
	if(this.selected==false) {
		this.selected = true;	
	} else {
		this.selected = false;
	}		
}

Row.prototype.highlight = function () {	
	if(this.row) {
		this.row.className = 'highlightedRow';
	}	
}

Row.prototype.unhighlight = function () {
	var styleClass = 'tbodyrow' + (this.number%2);
	if(this.row) {
		this.row.className = styleClass;
	}	
}