<%@page import="org.apache.jasper.tagplugins.jstl.core.ForEach"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE HTML>
<html lang="en">
<head>
<meta charset=utf-8>
<title>Online Examination System</title>
<link type="image/x-icon" href="/a/images/favicon.ico" rel="shortcut icon" />
<link rel="stylesheet" href="css/Style2.css" type="text/css" />
<!-- <link href='https://fonts.googleapis.com/css?family=Quando' rel='stylesheet' type='text/css'> -->
</head>
<body>
	
<div id="wrapper">
		
	<%@ include file="header.jsp"%>
		<!-- topnav end -->
	  <ul id="nav">
	      <li ><a href="SetQuestionPaper.htm" >Question Paper</a></li>
	      <li ><a href="SetNewQuestionPaper.htm">New Question Papers</a></li>
	      <li class="active"><a href="#">Question Selection</a></li>
	      <li ><a href="#">Settings</a></li>
	   </ul>
	  <!-- nav end -->  
</div>
	
 <!-- header end -->
  	
	<div id="content">
		<div id="sidebar">
			<ul class="sidebar_nav">
				<li><a href="#" class="active">Create Question Paper</a></li>
				<li><a href="#">Scheduled Exam</a></li>
			</ul>
			<div class="sidebar_note message note">
			<p>Combine questions from different categories and complexities to create a complete Question Paper.</p>
		</div>
	</div>
		<div id="main_createQuestionPaper">
			<h3>Select Individual Question</h3>
			<hr>
			<div style="margin-bottom: 23px;">
				<div style='float: left;padding-right: 10px;'>
					<select id="SubjectName"  name="SubjectName" style='width: 223px;' onchange="showTopic(this.value)">
						   	 <option value="0">--Select Subject  --</option>
						   	 <c:forEach items="${Subjects}" var="sub">
								<option value="${sub.id}">${sub.subName}</option>
							</c:forEach>
					</select><span class='mandatory'>*</span>
				</div> 
				<div style='float: left;padding-right: 10px;'>
					<select id="TopicName" name="TopicName" style='width: 223px;'>
						<option value="0">--Select Sub Topic --</option>
					</select>
				</div>
				<div>
					<button class='btn_green' onclick="return showQuestions()">Show Questions</button>
				</div> 
			</div>
				<hr>		 
					<div style="width: 100% ; text-align:center;"> 
						<div style='float: left;width: 40%;'> 
							<b>Choose Questions</b> 
						</div> 
						<div style='float: left;width: 20%;'> 
							 <b>Option</b> 
					 	</div> 
						 <div style='float: left;width: 40%;'> 
					 		<b>Selected Questions</b> 
					 	</div> 
					 <br><hr>
				 </div> 
			 	 			
				 <div style="width: 100%;text-align:center;"> 
				 
				 	<!-- Here Show the question -->			
				 	
				 		<div id="questtochoose" style="float: left;width: 40%;overflow:auto;height:400px;"> 
						 	<table border="1"  class="display" id="selqtable"> 
							 	<tr><th></th><th>Question</th></tr>							 
							 </table> <!-- showing -->
				 		</div> 
				 	
				 	<!-- Here Select the question -->			
				 		
					 	<div style="float: left;width: 20%;height:275px;padding-top: 125px;"> 
							 <span id='selcount'><b>0</b></span> Questions Selected from Total 
							 <span id='TotalMark'><b><c:forEach items="${QuestionPaperDesc}" var="quesDesc"><c:out value="${quesDesc.noOfQues}"></c:out></c:forEach></b></span><b></b><br> 
							 
							 
					 			<button class='btn_green' id='Quesselect' onclick='addSelectedQuestions();'>Add Selected</button> 
					 			<button style="opacity: 0.6;" class='btn_blue' id='Questionremove' disabled="disabled" onclick='removeSelectedQuestions();'>Remove Selected</button> 
					 	</div> 
					 		
				 	<!-- Here Showing Selected question -->			
				 		<form action="QuestionSelectFinal.htm" name="SubmitQuestionSelect" id="SubmitQuestionSelect" method="post">
				 		<input type="hidden" value="<c:forEach items="${QuestionPaperDesc}" var="quesDesc"><c:out value="${quesDesc.quesPaperId}"></c:out></c:forEach>" id="quesId" name="quesId">
				 		<input type="hidden" value="<c:forEach items="${QuestionPaperDesc}" var="quesDesc"><c:out value="${quesDesc.noOfQues}"></c:out></c:forEach>" id="numberquestiongiven" name="numberquestiongiven">	
					 	<div id='questselected' style='float: left;width: 40%;overflow:auto;height:400px;'> 
					 		<table border="1"  class="display" id="chooseqtable"> 
					 		<tr><th id='ra'></th><th>Question</th></tr> 
					 		</table> 
					 	</div> 
						 <hr> 
						 <div style='width: 100%;text-align:center;'>
						 <input style="opacity: 0.6;" type="submit" class="btn_green" id="SubmitQuesDQuesPaper" name="SubmitQuesDQuesPaper" disabled="disabled" onclick="return submitQues()" value="Save Selected Questions"></div>
						 </form> 
					</div>
		</div>
	</div>
</body>
<script type="text/javascript" charset="utf8" src="Javascript/jquery-1.8.2.min.js"></script>
<script type="text/javascript" charset="utf8" src="Javascript/selectQuestion.js"></script>
<script type="text/javascript">
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
				document.getElementById("TopicName").innerHTML=xhro.responseText;
			}
	}
</script>
<script type="text/javascript">
	function submitQues()
	{
		var myForm = document.forms[0];
		var questionId = myForm.questionIDs;
		for(var i=0;i<questionId.length;i++)
		{
			questionId[i].checked = true;
		}
	}
	
</script>
</html>


