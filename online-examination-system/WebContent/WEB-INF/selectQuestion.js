	
	function showQuestions()
	{
	var eID = document.getElementById("SubjectName");
	var dayVal = eID.options[eID.selectedIndex].value;
	
	var tID = document.getElementById("TopicName");
	var topicId = tID.options[tID.selectedIndex].value;
//	alert(dayVal + " " + topicId);

	xhro = new XMLHttpRequest();
	xhro.onreadystatechange=setQuestion;
	xhro.open("GET","ShowQuestionDTopic?SubJectName="+ dayVal +"&&TopicName="+ topicId +"", true);
	xhro.send(); 
	}
 	function setQuestion()
	{
		if(xhro.readyState == 4)
		{
			document.getElementById("selqtable").innerHTML=xhro.responseText;
		}
	}

function addSelectedQuestions()
{
	selectQCount = document.getElementById("chooseqtable").rows.length - 1;
	chooseQCount = document.getElementById("chooseqtable").rows.length - 1;
	if(chooseQCount == 0){
		document.getElementById("ra").innerHTML = "<input type='checkbox' name='removeall' onchange='removeAll(this)'/>";
	}
	var givenquestion = document.getElementById("numberquestiongiven").value;
	var added = false;
	var existingQuestion = false;
	try {
        var table = document.getElementById("selqtable");
        var rowCount = table.rows.length;
        for(var i=1; i<rowCount; i++) 
        {
            var row = table.rows[i];
            var chkbox = row.cells[0].childNodes[0];
            var qtext = row.cells[1].innerHTML;
            if(null != chkbox && true == chkbox.checked) 
            {
            	if(selectQCount < givenquestion )
            	{
            		if(checkandRemoveQuestions(chkbox.value))
            		{
            			existingQuestion = true;
            	    }
            		else
            		{ 
		            	addRow("chooseqtable",chkbox.value,qtext);
		                table.deleteRow(i);
		                rowCount--;
		                i--;
		                selectQCount++;
		                added = true;
            	    } 
            	} 
            	if(selectQCount == givenquestion )
            	{
            		document.getElementById("Quesselect").disabled = true;
					jQuery('#Quesselect').css('opacity','0.6');
					
					document.getElementById("SubmitQuesDQuesPaper").disabled = false;
					jQuery('#SubmitQuesDQuesPaper').css('opacity','1.0');
					
				//	document.getElementById("Questionremove").disabled = false;
				//	jQuery('#Questionremove').css('opacity','1.0');
            	}
            	else if(selectQCount < givenquestion)
            		{
	            		document.getElementById("Questionremove").disabled = false;
						jQuery('#Questionremove').css('opacity','1.0');
            		}
            } 
        }
         jQuery('#selqtable tr').find('th:first :checkbox').each(function () {
    		jQuery(this).prop('checked', false);
    	}); 
    }
	catch(e) {
    	alert(e);
    }
     if(!added && !existingQuestion){
    	alert("Please select the Questions to be added.")
    }
    if(existingQuestion){
		alert("Selected Question already added.");
    }
    document.getElementById("selcount").innerHTML = "<b>" + selectQCount + "</b>"; 
}
 	
function addRow(tableID,qid,qtext) 
{
    var table = document.getElementById(tableID);

    var rowCount = table.rows.length;
    var row = table.insertRow(rowCount);

    var cell1 = row.insertCell(0);
    var element1 = document.createElement("input");
    element1.type = "checkbox";
    element1.name="questionIDs";
    element1.id="questionIDs";
    element1.value=qid;
    cell1.appendChild(element1);

    var cell2 = row.insertCell(1);
    cell2.innerHTML = qtext;
}
	
function checkandRemoveQuestions(qid)
{
	 var table = document.getElementById("chooseqtable");
     var rowCount = table.rows.length;
     for(var i=1; i<rowCount; i++) 
     {
         var row = table.rows[i];
         var chkbox = row.cells[0].childNodes[0];
         if(chkbox.value == undefined){
         	//skip this as header row
         }
         else
         {
         	if(chkbox.value == qid)
         		return true;
         }
     }
     return false;
}


function removeSelectedQuestions()
{
	selectQCount = document.getElementById("chooseqtable").rows.length - 1;
	var givenquestion = document.getElementById("numberquestiongiven").value;
	var subid = document.getElementById("SubjectName").value;
	var removed = false;
	try 
	{
        var table = document.getElementById("chooseqtable");
        var rowCount = table.rows.length;
        for(var i=1; i<rowCount; i++) 
        {
            var row = table.rows[i];
            var chkbox = row.cells[0].childNodes[0];
            var qtext = row.cells[1].innerHTML;
            if(null != chkbox && true == chkbox.checked) 
            {
            	if(chkbox.value.indexOf(subid) !== -1)
            	{
            	  addRow("selqtable",chkbox.value,qtext); // Add question to left side only when question belongs to selected (category questions loaded on left side) category  
            	}
                table.deleteRow(i);
                rowCount--;
                i--;
                selectQCount--;
                removed = true;
            }
        }
        jQuery('#chooseqtable tr').find('th:first :checkbox').each(function () 
        {
    		jQuery(this).prop('checked', false);
    	});
    }catch(e) 
    {
       alert(e);
    }
    if(selectQCount == 0 )
	{
    	 document.getElementById("Questionremove").disabled = true;
    	 jQuery('#Questionremove').css('opacity','0.6');
    	 
    	 document.getElementById("SubmitQuesDQuesPaper").disabled = true;
		 jQuery('#SubmitQuesDQuesPaper').css('opacity','0.6');
	}
    if(removed)
    {
        document.getElementById("Quesselect").disabled = false;
    	jQuery('#Quesselect').css('opacity','1.0');
    	
    	document.getElementById("SubmitQuesDQuesPaper").disabled = true;
		jQuery('#SubmitQuesDQuesPaper').css('opacity','0.6');
    }
    else
    {
    	alert("Please select Question to be removed.")
    }
    document.getElementById("selcount").innerHTML = "<b>" + selectQCount + "</b>";
}