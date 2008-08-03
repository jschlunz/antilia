Dom=YAHOO.util.Dom;

function TColumn(tableId, id) {
    this.tableId = tableId;
    this.id = id;
    this.initialize();
}

TColumn.prototype.initialize = function() { 
    var el = Dom.get(this.id); 
    this.startPos = Dom.getXY(el);        
    this.dd = new YAHOO.util.DD(this.id, this.tableId);
       
    this.dd.onDragDrop = function(e, id) { 
        alert('Column ' + this.getEl().id + ' dopped on column ' +id); 
    }
    
    this.dd.startPos = this.startPos;
    
    this.dd.onInvalidDrop = function(e) { 
          // return to the start position 
        // Dom.setXY(this.getEl(), startPos); 
        // Animating the move is more intesting 
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
}

