if (typeof(Antilia) == "undefined")
	Antilia = { };

Antilia.decode = function(text) {
    return Antilia.replaceAll(text, "]^", "]");
}

Antilia.replaceAll = function(str, from, to) {
	eval('var regex = /' + from.replace( /\W/g ,'\\$&' ) + '/g ;');
	return str.replace(regex,to);
}

Antilia.Ajax = {
	replaceWith: function(id, url, params) {
		if(params != null) {
			$.get(url, params, function(data){
				var id1 = "#"+id;
				$(id1).replaceWith(data);
			});
		} else {
			$.get(url,  function(data){
			    alert(data);			    
			    var id1 = "#"+id;
			    $(document).ready(function(){
			    	$(id1).replaceWith(data);
			    });
			});
		}		
	},
	
	doAjax: function(url, params) {				
		if(params != null) {
			$.get(url, params, function(data){			
				var content = Antilia.Ajax.parseXml(data);
				var id = "#"+content.childNodes[0].getAttribute('id');
				if(id == "_NOTHING_TO_TO") {
					return;
				}
				var content = content.childNodes[0].childNodes[0];
				$(id).replaceWith(content.wholeText);								
			}, "text");
		} else {
			$.get(url,  function(data){
				var id = "#"+data.childNodes[0].getAttribute('id');
				if(id == "_NOTHING_TO_TO") {
					return;
				}
				var content = data.childNodes[0].childNodes[0];
				$(id).replaceWith(content.wholeText);				
			},"text");
		}
	},
	
	parseXml: function(text) {
		var xmldoc;
		if (window.ActiveXObject) {
	        xmldoc = new ActiveXObject("Microsoft.XMLDOM");
			xmldoc.loadXML(text);
		} else {
		    var parser = new DOMParser();    
		    xmldoc = parser.parseFromString(text, "text/xml");	
		}			
		return xmldoc;
	}
		

};