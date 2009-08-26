if (typeof(Antilia) == "undefined")
	Antilia = { };

Antilia.Ajax = {
	replaceWith: function(id, url, params) {
		if(params != null) {
			$.get(url, params, function(data){
				    alert("Data Loaded: " + data);
				    //alert(id);
				    $('#'+id).replaceWith(data);
				    alert(id);
			});
		} else {
			$.get(url,  function(data){
			    alert("Data Loaded: " + data);
			    //alert(id);
			    $('#'+id).replaceWith(data);
			    alert(id);
			});
		}
		
	}
};