/**
  Based on the js file by Igor Vaynberg (ivaynberg)
 */
if (typeof(Wicket) == "undefined")
	Wicket = { };
	
Wicket.Veil = {

	/**
		toggles a veil over the element with the specified id
		options map must at least contain className key
	*/
		
	toggle:function(targetId, options) {		
		if (Wicket.Veil.hide(targetId)==false) {
		  Wicket.Veil.show(targetId, options);
		}
	},

	toggleModal: function() {		
		if (Wicket.Veil.hideModal()==false) {
		  Wicket.Veil.showModal();
		}
	},
	

	/**
		shows a veil over the element with the specified id
		options map must at least contain className key
	*/
	showModal:function() {		
		var target = document.getElementById("AT_body");		
		var veil=document.getElementById("AT_body_modal");	
		veil.style.position="absolute";
		veil.style.left=Wicket.Veil.left(target);
		veil.style.top=Wicket.Veil.top(target);		
		veil.style.width=target.clientWidth + "px";
		veil.style.height=target.clientHeight + "px";
		veil.style.display="block";			
		veil.style.zIndex="1";		
	},
	
	/**
		hides veil currently shown over the element with the specified id
		@return true if veil was hidden, false if there was none
	 */
	hideModal:function() {
		var veil=document.getElementById("AT_body_modal");		
		if (veil.style.display=="block") {
			veil.style.display="none";
			return true;
		} 		
		return false;
	},
	
	/**
		shows a veil over the element with the specified id
		options map must at least contain className key
	*/
	show:function(targetId, options) {
		var target = document.getElementById(targetId);		
		var veil=document.createElement("div");	
		veil.innerHTML="&nbsp;";
		veil.className=options.className;
		veil.style.position="absolute";
		veil.style.left=Wicket.Veil.left(target);
		veil.style.top=Wicket.Veil.top(target);		
		veil.style.width=target.clientWidth + "px";
		veil.style.height=target.clientHeight + "px";
		veil.style.display="block";	
		veil.style.zIndex="5000";
		veil.id="wicket_veil_"+targetId;	
		document.body.appendChild(veil);
		var body =document.createElement("div");
		body.innerHTML="&nbsp;";
        body.className='wicket-veil-busy';
        body.style.zIndex="5001";
        body.style.width=40 + 'px';
        body.style.height=40+ 'px';
        body.id="wicket_veil_re_"+targetId;
        body.style.position="absolute";        
        body.style.top = (parseInt (veil.style.top, 10) + (parseInt (veil.style.height, 10)/2) -40) + 'px';                
        body.style.left = (parseInt (veil.style.left, 10) + (parseInt (veil.style.width, 10)/2) -40) + 'px';        
        document.body.appendChild(body);	                  
	},

	/**
		hides veil currently shown over the element with the specified id
		@return true if veil was hidden, false if there was none
	*/
	hide:function(targetId) {
		var veil=document.getElementById("wicket_veil_"+targetId);
		if (veil!=null) {
			veil.style.display="none";
			document.body.removeChild(veil);
		} 
		
		var body = document.getElementById("wicket_veil_re_"+targetId);
		if (body!=null) {
            body.style.display="none";
            document.body.removeChild(body);
            return true;
        }
		return false;
	},

	/**
		finds left offset of the specified element in px
	*/
	left:function (e) { 
		if (e.offsetParent) {
			var c = 0;
			while (e.offsetParent) {
				c += e.offsetLeft
				e = e.offsetParent;
			}
			return c;
		} else if (e.x) {
			return e.x;
		} else {
			return 0;
		}	
	},

	/**
		finds top offset of the specified element in px
	*/
	top:function(e) {
		if (e.offsetParent) {
			var c = 0;
			while (e.offsetParent) {
				c += e.offsetTop
				e = e.offsetParent;
			}
			return c;
		} else if (e.y) {
			return e.y;
		} else {
			return 0;
		}				
	}
	
}