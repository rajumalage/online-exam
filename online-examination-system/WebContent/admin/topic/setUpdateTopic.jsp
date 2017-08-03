<%@page import="org.apache.jasper.tagplugins.jstl.core.ForEach"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE HTML>
<html lang="en">
<head>
<meta charset=utf-8>
<title>Online Examination System</title>
<link type="image/x-icon" href="images/favicon.ico" rel="shortcut icon" />
<link rel="stylesheet" href="css/Style2.css" type="text/css" />
</head>
<body>
	
<div id="wrapper">
		
	<%@ include file="header.jsp"%>
		<!-- topnav end -->
	  <ul id="nav">
	      <li ><a href="SetTopic.htm" >Topic</a></li>
	      <li><a href="SetNewTopic.htm">New Topic</a></li>
	       <li class="active"><a href="SetUpdateTopic.htm">Update Topic</a></li>
	      <li ><a href="#">help</a></li>
	   </ul>
	  <!-- nav end -->  
</div>
  <!-- header end -->
	<div id="content">
		<div id="sidebar">
			<ul class="sidebar_nav">
				<li><a href="#" class="active">Topic</a></li>
				<li><a href="SetNewTopic.htm"">Add Topic</a></li>
			</ul>
			<div class="sidebar_note message note">
			<p>Add Update Topic</p>
		</div>
	</div>
		<div id="main">
			<h3>Update Topic</h3>
			<hr>
			<div id="q_bank4">
				<form action="UpdateTopicFinal.htm" id="ajaxform" name="ajaxform" method="POST">
			    
			    <c:forEach items="${topics}" var="top">
					<div style="width: 100%; float: left;">
					
					 <div id="Subject">
				
		
		<p>
		
	
		
		
			
							<label>Update Topic Name:- </label>
						
							<input class="Input_height" type="text" id="TopicUpdateName" name="TopicUpdateName"   value="<c:out value="${top.topicName}"/>"   placeholder="Please Enter the Subject Name"/>
							<input class="Input_height" type='hidden' id="TopicName" name="TopicName"   value="<c:out value="${top.id}"/>"  /> 
							<small>Required</small> 
						</p>
		
		</div>
													
					
						<div style="margin: 10px auto;">
						<h6>Topic  Description:</h6>
						<textarea id="TopicUpdateDesc" name="TopicUpdateDesc"  rows="8" cols="56"><c:out value="${top.topicDesc}"/></textarea>
					</div>
			
					
					
		
				
					</div>
					</c:forEach>
					<div>
						<input type="submit" class="btn_green"  id="submit" value="Update Topic" onclick="return TopicUpdateValidate()" >							
					</div>					
				</form>
				</div>
				</div>
			</div>
		
	
</body>

<script type="text/javascript" charset="utf8" src="Javascript/validation.js"></script>

<script>
	$(document).ready(function() {
	   window.setTimeout("fadeMyDiv();", 13000); //call fade in 3 seconds
	 }
	)
	
	function fadeMyDiv() {
	   $(".inputform_left_alert").fadeOut('slow');
	}
</script>

<script type="text/javascript" charset="utf8" src="Javascript/jquery-1.3.2.min.js"></script>
<script type="text/javascript" charset="utf8" src="Javascript/jquery-1.8.2.min.js"></script>
<script type="text/javascript" src="Javascript/jquery.js"></script>
<script type="text/javascript" charset="utf8" src="Javascript/validation.js"></script>
<SCRIPT TYPE="text/javascript">

/* Function called on change event of Dept Drop Down */   
function showTopic(value)
	{
		xhro = new XMLHttpRequest();
		xhro.onreadystatechange=showSubDetails;
		// alert("Topic " + xhro.readyState);
		xhro.open("GET","showallSubject?SubId="+ value +"", true);
		xhro.send();
	}
	function showSubDetails()
	{
		if(xhro.readyState == 4)
			{
				document.getElementById("subResults").innerHTML=xhro.responseText;
			}
	}
	
	
	/* Value To submit to database */
	var form = $('#ajaxform');
	form.submit(function() {

		$.ajax({
			type : form.attr('method'),
			url : form.attr('action'),
			data : form.serialize(),
			success : function(data) {
				$("#Status").delay(19000).fadeOut('slow');	
				var result = data;
				$('#Status').html(result);
				closeWindow();
			}
		});
		return false;
	});
	function closeWindow() 
	{
	    setTimeout(function() {
	    window.close();
	    }, 3000);
	}
	
	
	
	/* Value To submit to database */
	var form = $('#ajaxform');
	form.submit(function() {

		$.ajax({
			type : form.attr('method'),
			url : form.attr('action'),
			data : form.serialize(),
			success : function(data) {
				$("#content").delay(19000).fadeOut('slow');	
				var result = data;
				$('#content').html(result);

			}
		});
		$("#content").fadeIn('fast');
		return false;
	});

</SCRIPT>
</html>
