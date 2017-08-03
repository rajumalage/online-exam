<%@page import="org.apache.jasper.tagplugins.jstl.core.ForEach"%>								
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="s" %>
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
		
	<%@ include file="header.jsp"%>
		<!-- topnav end -->
	  <ul id="nav">
	      <li ><a href="SetTopic.htm">Topic</a></li>
	      <li class="active"><a href="SetNewTopic.htm">New Topic</a></li>
	      <li><a href="SetUpdateTopic.htm">Update Topic</a></li>
	      <li ><a href="#">help</a></li>
	   </ul>
	  <!-- nav end -->  
</div>
  <!-- header end -->
	<div id="content">
		<div id="sidebar">
			<ul class="sidebar_nav">
				<li><a href="#" class="active">Topic</a></li>
				<li><a href="#">Add Topic</a></li>
			</ul>
			<div class="sidebar_note message note">
			<p>Add New Topic</p>
		</div>
	</div>
	
	
		 <div id="main">
			<h3>Create New Topic</h3> 
			<hr>
			<div id="inputform_left_alert">
				<form action="SubmitTopic.htm"  name="add topic" method="post" >
					<div style="width: 100%; float: left;">
					 <div id="Subject">
					 		<p>
							<label>Existing Subject Name:-:</label>
							<select name="Subject">
								<option value="">---- Select Subject Name----</option>	
								<c:forEach items="${Subjects}" var="subject">
									<option value="${subject.id}">${subject.subName}</option>
								</c:forEach> 
								</select>  
								<small>Required</small>
							</p>							
							<p>
								<label>New Topic Name:- </label>
								<input type="text" name="NewTopicName" id="NewTopicName" placeholder="Please enter topic Name"> 
								<small>Required</small>
							</p>							
						</div>
						<div style="margin: 10px auto;">
						<h6>Topic Description:</h6>
						<textarea id="TopicDesc" name="TopicDesc" rows="8" cols="56"></textarea>
					</div>
					</div>						
					<div>
						<input type="submit" class="btn_green"  id="submit" value="Add Topic" onclick="return  TopicValidate()" >							
					</div>
				</form>
			</div>
			<div class="inputform_left_alert">
			<p id="content"></p>		
		</div>
		</div> 
	</div>
</body>

<script type="text/javascript" charset="utf8" src="Javascript/jquery-1.8.2.min.js"></script>
<script type="text/javascript" charset="utf8" src="Javascript/validation.js"></script>
</html>