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
		
	<%@ include file="header.jsp"%>
		<!-- topnav end -->
	  <ul id="nav">
	      <li ><a href="ShowAllSubjectName" >Subject</a></li>
	      <li class="active"><a href="SetNewSubject.htm">New Subject</a></li>
	      <li ><a href="#">help</a></li>
	   </ul>
	  <!-- nav end -->  
</div>
  <!-- header end -->
	<div id="content">
		<div id="sidebar">
			<ul class="sidebar_nav">
				<li><a href="ShowAllSubjectName" class="active">Subject</a></li>
				<li><a href="SetNewSubject.htm">Add Subject</a></li>
			</ul>
			<div class="sidebar_note message note">
			<p>Add New Subject</p>
		</div>
	</div>
		<div id="main">
			<h3>Create New Subject</h3>
			<hr>
			<div id="q_bank4">
				<form action="SubmitSubject.htm" name="addsubject" method="post" >
					<div style="width: 100%; float: left;">
					 <div id="Subject">
					 		
						<p>
							<label>New Subject Name:- </label>
							<input type="text" name="setSubject" value="" id=""> 
							<small>Required</small>
						</p>							
						</div>
						<div style="margin: 10px auto;">
						<h6>Subject Description:</h6>
						<textarea id="SubDesc" name="setSubDesc" rows="8" cols="56"></textarea>
					</div>
					</div>						
					<div>
						<input type="submit" class="btn_green"  id="" value="Save Subject" onclick="return validate()" >							
					</div>
				</form>
			</div>
		</div>
	</div>
</body>
<script type="text/javascript" charset="utf8" src="Javascript/validation.js"></script>
</html>
