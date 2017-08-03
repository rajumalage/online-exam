<%@page import="org.apache.jasper.tagplugins.jstl.core.ForEach"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE HTML>
<html lang="en">
<head>
<meta charset=utf-8>
<title>Online Examination System</title>
<link type="image/x-icon" href="images/favicon.ico" rel="shortcut icon" />
<link rel="stylesheet" href="css/Style2.css" type="text/css" />
<!-- <link href='https://fonts.googleapis.com/css?family=Quando' rel='stylesheet' type='text/css'> -->

</head>
<body>
	<div id="test">
<div id="wrapper">
	
	<%@ include file="header.jsp"%>
	<!-- topnav end -->
	  <ul id="nav">
	      <li class="active"><a href="#" >DashBoard</a></li>
	      <li><a href="SetNewQuestion.htm">Add New Question </a></li>
	      <li><a href="#">Update Question</a></li>
	      <li ><a href="#">Help</a></li>
	   </ul>
	  <!-- nav end -->  
</div>

  <!-- header end -->
  
	<div id="content">
		<div id="sidebar">
			<ul class="sidebar_nav">
				<li><a href="#" class="active">Add Question</a></li>
				<li><a href="#">Update Question </a></li>
			</ul>
		
			<div class="sidebar_note message note" >
				<br>
				<u style="font-size:13px;color:red">Note:-</u>
				<p align="left"><br>
				Select Any Options To Manipulate The Details Of Questions</p><br>
			</div>
			
			<div>
				<p></p>
			</div>
		</div>
	
		<div id="main"> 
			<h3>Question</h3> <span id="SOP"></span>
			<hr />
		<center><h5><P style="color:<c:out value='${colors}'/>"><c:out value='${successMassage}'/></P></h5></center>		
		<div class="table_top">
			<a href="SetNewQuestion.htm" class="btn_green_sml">Add New Question</a>
		</div>
					
	  	<div id="q_bank" class="filterdiv" style="float: left;margin-top: 12px;">
			<form action="ShowQuestionDTopicAndSubject.htm" id="questionSearch" name="questionSearch" method="post" >
				<select class="Input_height" name="SubJectName"  onchange="showTopic(this.value)">
					<option value="0">-- Select Subject Name--</option>	
				   	 <c:forEach items="${Subjects}" var="sub">
						<option value="${sub.id}">${sub.subName}</option>
					</c:forEach>
				</select>
				<select class="Input_height"  name="TopicID" id="TopicID">
					<option value="0">--Select Topic Name--</option>	
				</select>
				<input type="search" name="searchQuestion" placeholder="Enter Question Name">
				<button class="btn_green_sml" ><span>search</span></button>
			</form>
		</div>
		<div id="showQuesPaper">
			<div style="float: left;width: 100%;overflow:auto;height:400px;">
				<form action="#" name="QuestionPaperForm" method="post" >		
				<c:set var="count" value="0" scope="page" />			
					<table class="display" id="example" style="margin-top: 40px;">
						<thead>
							<tr>
								<th>Sr.No.</th>
								<th>Subject Name</th>
								<th>Topic Name</th>
								<th>Question Name</th>
								<th style="text-align: center;">Edit</th>
								<th style="text-align: center;">Delete</th>
							</tr>
						</thead>
						<tbody>
							
							<c:forEach items="${questionsList}" var="questions">
								<c:set var="count" value="${count+1}" scope="page"/>
				       		<tr>
								<td>${count}</td>
								<td>${questions.subName}</td>
								<td>${questions.topicName}</td>
								<td>${questions.quesDesc}</td>
								<td style="text-align: center;">
								<a href="javascript:showDetails(${questions.id});"><img style="opacity:0.9;" src="images/edit.png" title="Edit Question"/></a>
								</td>
								<td style="text-align: center;">
								 <a href="javascript:deleteQuestion(${questions.id});"><img style="opacity:0.7;" src="images/delete-button.png" title="Delete Question"/></a>			
								</td>	
							</tr>
							</c:forEach>	
						</tbody>
					</table>
				</form>
				<table style="width:100%">						
					<tr>
						<td>						
							<font color="red">Total: <c:out value="${count}" /></font>					
						</td>
					</tr>
				</table>
				</div>
			</div>
		</div>
	</div>
	</div>
</body>
<script type="text/javascript" charset="utf8" src="Javascript/jquery-1.3.2.min.js"></script>
<script type="text/javascript" charset="utf8" src="Javascript/jquery-1.8.2.min.js"></script>
<script type="text/javascript" src="Javascript/jquery.js"></script>
<script type="text/javascript">

	function showDetails(value)
	{
	//	alert(value);
		window.open("SetUpdateQuestionFinal.htm?questionId="+value, 'ShowDetails', 'scrollbars=yes,height=650px,width=1067px'); 
		return false;		
	}
	function showTopic(value)
	{
		xhro = new XMLHttpRequest();
		xhro.onreadystatechange=setTopic;
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

	
	var form = $('#questionSearch');
	form.submit(function() {
		$.ajax({
			type : form.attr('method'),
			url : form.attr('action'),
			data : form.serialize(),
			success : function(data) {

				var result = data;
				$('#showQuesPaper').html(result);
			}
		});
		return false;
	});
	
	function deleteQuestion(value)
	{
		var result=confirm("Do You Really Want To Delete Question?");
		if(result)
		{			
			xhro = new XMLHttpRequest();
			xhro.onreadystatechange=DeleteSubject;
			xhro.open("POST","DeleteQuestionFinal.htm?DeleteQuestions="+ value, true);
			xhro.send();
			 function DeleteSubject()
			{
				if(xhro.readyState == 4)
					{
						document.getElementById("SOP").innerHTML=xhro.responseText;
					}
			}  
			 return false;
		}
		else
		{
			return false;
		}
	}
</script>
</html>
