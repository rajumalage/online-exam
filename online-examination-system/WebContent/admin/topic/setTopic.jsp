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
	
<div id="wrapper">
	
	<%@include file="header.jsp"%>
	<!-- topnav end -->
	  <ul id="nav">
	      <li class="active"><a href="#" >DashBoard</a></li>
	      <li><a href="SetNewTopic.htm">Add New Topic </a></li>
	      <li><a href="SetUpdateTopic.htm">Update Topic</a></li>
	      <li ><a href="#">Help</a></li>
	   </ul>
	  <!-- nav end -->  
</div>

  <!-- header end -->
  
	<div id="content">
		<div id="sidebar">
			<ul class="sidebar_nav">
				<li><a href="SetNewTopic.htm" class="active">Add Topic</a></li>
				<li><a href="SetUpdateTopic.htm">Update Topic </a></li>
			</ul>
		
			<div class="sidebar_note message note">
				<p>Add Topic And Update Topic</p>
				
			</div>
			
			<div>
				<p></p>
			</div>
		</div>
	
		<div id="main"> 
			<h3>Topic</h3> <span id="SOP"></span><span id="Status"><c:out value="${successMassage}"></c:out></span>
			<hr />
				
		<div class="table_top">
			<a href="SetNewTopic.htm" class="btn_green_sml">Add New Topic</a>
		</div>
					
	  	  	<div id="q_bank" class="filterdiv" style="float: left;">
			<div id="q_bank" class="filterdiv" style="float: left;margin-top: 12px;">
			<form action="#" id="TopicSearch" name="TopicSearch" method="post" >
				<select class="Input_height" name="SubJectId">
					<option value="0">-- Select Subject Name--</option>	
				   	 <c:forEach items="${Subjects}" var="sub">
						<option value="${sub.id}">${sub.subName}</option>
					</c:forEach>
				</select>
				<input type="search" name="searchTopic" placeholder="Enter Topic Name">
				<button class="btn_green_sml" ><span>search</span></button>
			</form>
		</div>
		</div>
		<div id="showQuesPaper">
			<form action="#" name="QuestionPaperForm" method="post" >		
			<c:set var="count" value="0" scope="page" />			
				<table class="display" id="example" style="margin-top: 40px;">
					<thead>
						<tr>
							<th>Sr.No.</th>
							<th>Subject Name</th>
							<th>Topic Name</th>
							<th>Description</th>
							<th style="text-align: center;">Edit</th>
							<th style="text-align: center;">Delete</th>
						</tr>
					</thead>
					<tbody>
						<c:set var="count" value="0" scope="page" />
						<c:forEach items="${Topics}" var="tupic">
							<c:set var="count" value="${count+1}" scope="page"/>
			       		<tr>
							<td><c:out value="${count}"/></td>
							<td>${tupic.subName}</td>
							<td>${tupic.topicName}</td>
							<td><span style="color:#565253;font-size:15px;">${tupic.topicDesc}</span></td>
							<td style="text-align: center;">
							<a href="javascript:showDetails(${tupic.id});"><img style="opacity:0.7;" src="images/edit.png" title="Update description"/></a></td>
							<td style="text-align: center;">
							 <a href="javascript:deleteTopic(${tupic.id});"><img style="opacity:0.7;" src="images/delete-button.png" title="DeleteTopic"/></a>			
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
</body>
<script type="text/javascript" charset="utf8" src="Javascript/jquery-1.3.2.min.js"></script>
<script type="text/javascript" charset="utf8" src="Javascript/jquery-1.8.2.min.js"></script>
<script type="text/javascript" src="Javascript/jquery.js"></script>
<script type="text/javascript">


$(document).ready(function() 
		{
		   window.setTimeout("fadeMyDiv();", 13000); //call fade in 3 seconds
		 });
		
		function fadeMyDiv() {
		   $("#Status").fadeOut('slow');
		}

	function showDetails(value)
	{
		//alert(value);
		window.open("SetUpdateTopic.htm?topid="+value, 'ShowDetails', 'scrollbars=yes,height=650px,width=1067px'); 
		return false;		
	}
	
	
	var form = $('#SubjectSearch');
	form.submit(function() {
		$.ajax({
			type : form.attr('method'),
			url : form.attr('action'),
			data : form.serialize(),
			success : function(data) {
				var result = data;
				$('#showSubjectDetails').html(result);
			}
		});
		return false;
	}); 
	
	
	
	function deleteTopic(value)
	{
		var result=confirm("Do You Really Want To Delete Topic?");
		if(result)
		{			
			xhro = new XMLHttpRequest();
			xhro.onreadystatechange=DeleteSubject;
			xhro.open("GET","DeleteTopicFinal.htm?TopicName="+ value +"", true);
			xhro.send();
			 function DeleteSubject()
			{
				if(xhro.readyState == 4)
					{
						document.getElementById("SOP").innerHTML=xhro.responseText;
						refreshSubjectDetails();
					}
			} 
			return true;
		}
		else
		{
			return false;
		}
	}
	function refreshSubjectDetails()
	{
		xhro = new XMLHttpRequest();
		xhro.onreadystatechange=setTopic;
		xhro.open("GET","SearchBySubject?searchsubject="+"", true);
		xhro.send();
	}
	function setTopic()
	{
		if(xhro.readyState == 4)
			{
				document.getElementById("showSubjectDetails").innerHTML=xhro.responseText;
			}
	}
 
	/* function UpdateQuesPaper(value)
	{
		//alert(Do you want to delete the topic);
		window.open("DeleteTopicFinal.htm?TopicName="+ value , 'ShowDetails', 'scrollbars=yes,height=650px,width=1067px'); 
		return false;		
	} */
</script>
</html>
