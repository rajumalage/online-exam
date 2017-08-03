$(document).ready(function() {

	document.oncontextmenu = function () {
	    return false;
	};
	
	
	$(document).keydown(function(e) { 

		if (e.keyCode === 8 || e.keyCode === 116) 
			{
			 e.preventDefault();
			$('#questionDisplay').focus();
			}
		});
});


if (document.layers) {   
    document.captureEvents(Event.MOUSEDOWN);
    document.onmousedown = function () {
        return false;
    };
}
else 
{
    document.onmouseup = function (e) {
        if (e !== null && e.type == "mouseup") 
        {
            if (e.which === 2 || e.which === 3)
            {
                return false;
            }
        }
    };
} 

function noBack()
{
	
	window.history.forward();
	
}
setTimeout("noBack()",0);
window.onunload=function(){null;}
