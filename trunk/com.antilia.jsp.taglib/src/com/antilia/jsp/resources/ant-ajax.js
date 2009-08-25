if (typeof(Antilia) == "undefined")
	Antilia = { };

Antilia.Ajax = {
	replaceWith: function(id, url, params) {
		alert(url);
		if(params != null) {
			$.get(url, params, function(data){
				    alert("Data Loaded: " + data);
			});
		} else {
			$.get(url, params, function(data){
			    alert("Data Loaded: " + data);
			});
		}
		
	}
};