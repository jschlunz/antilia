if (typeof(Antilia) == "undefined")
	Antilia = { };

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
	}
};