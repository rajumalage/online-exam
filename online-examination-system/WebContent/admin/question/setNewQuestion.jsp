<%@page import="org.apache.jasper.tagplugins.jstl.core.ForEach"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="s" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE HTML>
<html lang="en">
<head>
<meta charset=utf-8>
<title>Online Examination System</title>

<link type="image/x-icon" href="/a/images/favicon.ico" rel="shortcut icon" />
<link rel="stylesheet" href="css/Style2.css" type="text/css" />

<script type="text/javascript">
</script>
</head>
<body>
	
<div id="wrapper">
		
	<%@ include file="header.jsp"%>
		<!-- topnav end -->
	  <ul id="nav">
	      <li ><a href="SetQuestion.htm">Question</a></li>
	      <li class="active"><a href="SetNewQuestion.htm">Add New Question</a></li>
	      <li><a href="#">Update Question</a></li>
	      <li ><a href="#">help</a></li>
	   </ul>
	  <!-- nav end -->  
</div>
  <!-- header end -->
	<div id="content">
		<div id="sidebar">
			<ul class="sidebar_nav">
				<li><a href="#" class="active">Question</a></li>
				<li><a href="#">Add Question</a></li>
			</ul>
			<div class="sidebar_note message note">
			<br>
			<p><u style="color:red">Note:-</u>
			<br>
			You Can Add Question Here, Please Do Not Leave Any Field Blank</p>
		</div>
	</div>
		<div id="main">
			<h3>Add Question Related Topic!</h3>
			<hr>
			<div id="q_bank4">
				<form action="SubmitQuestions.htm" name="SubmitQuestions" method="post" >
					<div style="width: 100%; float: left;">
					 <div id="Question">
					 	<p>
							<label>Select Subject Name:-:</label>
							<select class="Input_height" name="SubJectName"  onchange="showTopic(this.value)">
							<option value="0">Select Subject Name</option>	
						   	 <c:forEach items="${Subjects}" var="sub">
								<option value="${sub.id}">${sub.subName}</option>
							</c:forEach>
					</select>
								<small>Required</small>
							</p>
							<p>
							<label>Select Topic Name:-:</label>&nbsp;&nbsp;&nbsp;&nbsp;
							<select class="Input_height"  name="TopicID" id="TopicID">
								<option value="0">Select Topic Name  </option>	

								</select>  
								<small>Required</small>
							</p>
						</div>
					</div>						

					<div style="margin: 10px auto;">
					<div style="margin:20% right;">
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;					
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;						
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						<c:out value="${paramSuccess}"></c:out>
							</div>
						<h6>Question Description:- </h6>
						<textarea name="QuestionDesc" rows="12" cols="56" id="QuestionDesc" style="resize:none"></textarea>
					</div>
										<div style="width: 100%; float: left;">
					 <div id="Question">
					 	<div id='TextBoxesGroup'>
								<div id="TextBoxDiv1">
								 <table border=0>
									<tr><th style="font-size: 18px;">1:-</th><td><input  type="text" id="textbox" name="textbox" placeholder="option 1"/></td></tr>
								</table>
								</div>
							</div>
							<div style="margin: 10px auto;">
								<input type='button' class="button"  id='addMore' value="+ Add Option">
							  <input type='button' class="button" id="removeButton" value="- Remove Option">
							</div>
							<p>
							    <label>Correct Answer:</label>
								<input type="text" name="QuesAws" id="QuesId">
							</p>
							<p>
							<input type="submit" class="btn_green"  id="btnsave" value="Submit Question" onclick="return SubmitQuestion()">
							</p>
						</div>
					</div>
				</form>
			</div>
		</div>
	</div>
</body>
<script type="text/javascript" charset="utf8" src="Javascript/jquery-1.3.2.min.js"></script>
<script>
	$(document).ready(function() {
	$("#btnsave").click(function() {
		
		var select1=document.forms[0].SubJectName;
		if(select1.value=="0")
			{
			alert("Please select any SUBJECT");
			select1.focus();
			return false;
			}
		var select2=document.forms[0].TopicID;
		if(select2.value=="0")
			{
			alert("Please select any Topic");
			select2.focus();
			return false;
			}
		
		var question = document.forms[0].QuestionDesc;
		if (question.value.length == 0) {
			alert("Please add the Question");
			return false;
		}
		var ans = document.forms[0].QuesAws;
		if (ans.value.length == 0) {
			alert("Please add Answer");
			return false;
		}
		
	});
	});
	
	/* function fadeMyDiv() {
	   $(".inputform_left_alert").fadeOut('slow');
	} */

function showTopic(value)
{
	xhro = new XMLHttpRequest();
	xhro.onreadystatechange=setTopic;
	// alert("Topic " + xhro.readyState);
	xhro.open("GET","showalltopic?SubId="+ value +"", true);
	xhro.send();
}
function setTopic()
{
	if(xhro.readyState == 4)
		{
			document.getElementById("TopicID").innerHTML=xhro.responseText;
		}
}
	
	var counter = 2;
    $("#addMore").click(function() {
			
    	if (counter > 9) 
    		{
				alert("Sorry!! Only 9 Options allowed");
				return false;
			}
		var newTextBox = $(document.createElement('div')).attr("id",'TextBoxDiv'+ counter);

			newTextBox.after().html(' <table border=0><tr><th style="font-size: 18px;">'+ counter+ ':-</th><td>'
					+ '<input type="text" name="textbox" id="textbox"' 
					+ counter + ' placeholder="option '+ counter+ '"/></td></tr></table>');
			newTextBox.appendTo("#TextBoxesGroup");
			counter++;
	});

    // revoved add option . 
	$("#removeButton").click(function() {
		
	  if (counter == 2) 
	      {
			alert("Minimum One Option Is Required");
			return false;
		  }
		counter--;
		$("#TextBoxDiv" + counter).remove();
		});
	function noBack()
	{
		
		window.history.forward();
		
	}
	setTimeout("noBack()",0);
	window.onunload=function(){null;}
</script>
</html>
