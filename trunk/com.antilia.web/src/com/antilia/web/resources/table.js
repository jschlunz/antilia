
function Table(id, url, rows, ncols, rendringCount, urls, ie6, dragColumns) {
	this.id = id;
	// rows is an array of Rows	
	this.rows = rows;
	this.url = url;
	this.ncols = ncols;	
	this.rendringCount = rendringCount;
	this.urls = urls;
	this.ie6 = ie6;
	this.dragColumns = dragColumns;
	this.columns = new Array();    
    for(var i = 0; i < this.ncols; i++) {               
        this.addColumn(i);
    }        
}

Table.prototype.createDraggables = function() { 
    if(!this.dragColumns)
    	return;
    var trashId  = this.id + '_' + 'dropCol';
    new YAHOO.util.DDTarget(trashId , this.id);
    
    for(var j = 1; j < this.ncols; j++) {               
        var titleId = this.id + '_dragger_'  + this.rendringCount+ '_' + j;
        var url = this.urls[j-1];
       new TColumn(this.id, titleId, url,this.ie6);
    }
    
    var lastHeader  = this.id + '_' + 'dropLas';
    new YAHOO.util.DDTarget(lastHeader , this.id);
}


Table.prototype.addColumn = function(col) {
    var url = this.url;	
	var column = new Column(this.id, col, url);	
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

function Column(tableId, number, url) {
	this.tableId = tableId;
	this.url = url;
	this.number = number;
	this.resizeHandleId = this.tableId + '_c_' + this.number;
	this.resizeHandle = document.getElementById(this.resizeHandleId);	
	if(this.resizeHandle) {
	     this.resizeHandle.url = url;
	     this.resizeHandle.number = number;
		Antilia.Drag.init(this.resizeHandle, function() {} , this.onEndDrag, this.onResize);
	}		
}


Column.prototype.onResize = function (obj, deltaX, deltaY) {
	var td = obj.parentNode.parentNode.parentNode.parentNode;		
	if ((parseInt(td.offsetWidth)+(deltaX))>40) {
    	td.style.width =  parseInt(td.offsetWidth)+deltaX+"px";
		td.getElementsByTagName("input")[0].value=parseInt(td.style.width);
    }
    else
  		td.style.width=1;
  	var res = [0, 0];
	return res;
}	
 
Column.prototype.onEndDrag = function (obj) {
    var td = obj.parentNode.parentNode.parentNode.parentNode;  
    var url = this.url+ '&sourceId=' + parseInt(td.style.width) + '&targetId=resize' + '&number=' + this.number
    wicketAjaxGet(url);      
}

function TColumn(tableId, id, url, ie6) {
    this.tableId = tableId;
    this.id = id;
    this.url = url;
    this.ie6 = ie6;
    this.initialize();
}

TColumn.prototype.initialize = function() { 
    var el = YAHOO.util.Dom.get(this.id);     
    this.dd = new YAHOO.util.DD(this.id, this.tableId);    
    this.startPos = YAHOO.util.Dom.getXY(el);
    this.dd.url = this.url;
    el.ie6 = this.ie6;
      
    this.dd.onDragDrop = function(e, id) { 
        wicketAjaxGet(this.url+ '&sourceId=' + this.getEl().id + '&targetId=' + id);
    }
    
    this.dd.onInvalidDrop = function(e) {         
           // Animating the move is more intesting 
       var Dom = YAHOO.util.Dom;               
       //Dom.setStyle(this.getEl(), "opacity", 1);     
       if(this.getEl().ie6==true) {
       		Dom.setStyle(this.getEl(), "border", "none");
       } else {
       		Dom.setStyle(this.getEl(), "border", "1px solid transparent");
       }
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
        Dom.setStyle(this.getEl(), "border", "1px dashed white");
    }
    
    this.dd.onDragOver =  function(e, id) { 
        var Dom = YAHOO.util.Dom;
        var el = YAHOO.util.Dom.get(id);
        el.parentNode.className='droptarget';
        //Dom.setStyle(el, "opacity", 0.8);
        Dom.setStyle(el, "border", "1px dashed gray");   
    }
    
    this.dd.onDragOut =  function(e, id) { 
        var Dom = YAHOO.util.Dom;
        var el = YAHOO.util.Dom.get(id);
        el.parentNode.className='';        
        //Dom.setStyle(el, "opacity", 1);          
        if(this.getEl().ie6==true) {
        	Dom.setStyle(el, "border", "none");
        } else {
        	Dom.setStyle(el, "border", "1px solid transparent");
        }
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