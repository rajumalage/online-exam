
$(document).ready(function() {
	$("#submit").hide();

	$("#reset").click(function clear()
	{
		var f=document.forms[1];
		var check=f.answer;
		for(var i=0;i<check.length;i++)
			{
			check[i].checked = false;
			}
		}); 
	$("#skip").click(function skip(){
		var f=document.forms[1];
		var check=f.answer;
		for(var i=0;i<check.length;i++)
			{
			check[i].checked = false;
			}
		
	});
	

	var form = $('#SubmitQuesAndNext');
	form.submit(function() {
		$.ajax({
			type : form.attr('method'),
			url : form.attr('action'),
			data : form.serialize(),
			success : function(data) 
			{
				var result = data;
				$('#ShowQuesAndOps').html(result);
				 showQuesIdsStatus();
				 CheakValid();
			}
		});
		return false;
	});
	
	$("#submit").click(function ()
	{
		var value = document.getElementById("QuesId").value;
		var form=$("#SubmitQuesAndNext");
		$.ajax({
			type : form.attr('method'),
			url : "UpdateCheck",
			data : form.serialize(),
			success : function(data) 
			{
				 showQuesIdsStatus();
				 CheakValid();
			}
		});
		
	});
	function showQuesIdsStatus()
	{
		xhro = new XMLHttpRequest();
		xhro.onreadystatechange=setQuesIdsColor;
		xhro.open("GET","ShowQuesIdsStatus.htm", true);
		xhro.send();
	}
	function setQuesIdsColor()
	{
		if(xhro.readyState === 4)
			{
				document.getElementById("ShowQuesIDs").innerHTML=xhro.responseText;
			}
	}
	/* id="ShowQuesIDs" */
});

function CheakValid()
{
	var arraysize = document.getElementById('arraySize').value ;
	var NoOfQues = document.getElementById('quesNo').value ;

	if(arraysize === NoOfQues)
	{		

		$("#submit").show();
		$("#continue").hide();
		document.getElementById("continue").disabled = true;
		jQuery('#skip').css('opacity','0.6');
	}
	else if(arraysize !== NoOfQues)
	{	
		$("#submit").hide();
		$("#continue").show();
		
		document.getElementById("continue").disabled = false;
		jQuery('#skip').css('opacity','1.0');
	}
}