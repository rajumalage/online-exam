<%@page import="org.apache.jasper.tagplugins.jstl.core.ForEach"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE HTML>
<html lang="en">
<head>
<meta charset=utf-8>
<title>Online Examination System</title>
<link type="image/x-icon" href="images/favicon.ico" rel="shortcut icon" />
<link rel="stylesheet" href="css/Style2.css" type="text/css" />
<style type="text/css">
table{
width: 25px;
}
table td
{
	padding:12px;
}
.statusBar{
width:1033px;
height:120px;
}
.statusContent{
margin-left:40px;
float:left;
width:15%;
}
.statusPrint{
width: 128px;
height: 106px;
padding: 57px 25px 1px 15px;
text-align: center;
border:2px solid #0C7544;;
border-radius: 90px;
 background: #06A067;
/*   background: red; /* For browsers that do not support gradients 
  background: -webkit-linear-gradient(#A27308, rgba(160, 141, 6, 0.78), #061C33); /* For Safari 5.1 to 6.0 
  background: -o-linear-gradient(#A27308, rgba(160, 141, 6, 0.78), #061C33); /* For Opera 11.1 to 12.0 
  background: -moz-linear-gradient(#A27308, rgba(160, 141, 6, 0.78), #061C33); /* For Firefox 3.6 to 15 
  background: linear-gradient(#A27308, rgba(160, 141, 6, 0.78), #061C33); /* Standard syntax */
 
  background: red; 
  background: -webkit-linear-gradient(#9AAFC4, rgb(216, 111, 4), #91B8E0); 
  background: -o-linear-gradient(#9AAFC4, rgb(216, 111, 4), #91B8E0); 
  background: -moz-linear-gradient(#9AAFC4, rgb(216, 111, 4), #91B8E0); 
  background: linear-gradient(#9AAFC4, rgb(216, 111, 4), #91B8E0); 
   
  
}
.title{
color:white;
font-size:15px;
font-weight: 700;
}
.statusNo{
margin-top:12px;
font-size:20px;
font-weight: 700;
color:white;
}
</style>
</head>
<body>
<div id="contaner" style="overflow: hidden;">	
<div id="wrapper">
	<div id="header">
       <h1><a href="#"><img id="clogo" src="images/logo.png" alt="Oes Admin" /></a></h1>    
 		 <!-- top nav start -->	  
		  <ul id="topnav">
			  <li class="active"><a href="SetAdminDashboard.htm" >Admin Dashboard</a></li>
			  <li ><a href="SetSubject.htm" >Subject</a></li>
			  <li ><a href="SetTopic.htm" >Topic</a></li>
			  <li ><a href="SetQuestion.htm" >Question</a></li>
			  <li ><a href="SetQuestionPaper.htm" >Exam</a></li>
			  <li ><a href="index.html" title="Logout">Logout</a></li>
		  </ul>
	</div>		
</div>
  <!-- header end -->
	<div id="content">
		<div id="sidebar">
			<ul class="sidebar_nav">
				<li class="active"><a href="#">Admin Dashboard</a></li>
				<li><a href="#">About</a></li>
				<li><a href="#">Help</a></li>
			</ul>
			<div class="sidebar_note message note">
				<p><br>Hello Admin!!<br>
				How Are You Today?</p>
				<p><br>You can visit Multiple Tabs to proceed with the very vast features provided for admin in this Administrator panel. 
				</p>
			</div>
		</div>
		<div id="main">
			<h3>Dear Administrator!!</h3>
			<hr>			
			
			<div>
					<div class="inputform_left_admin">
		<h2 class="design"></h2>
		
		<h4>Welcome to the Admin panel, please read below description that you can do all into section</h4>
		<div class="statusBar">
			
			<div class="statusContent">
				<div class="statusPrint">
					<div class="title">No. of Subjects</div><br>
					<div class="statusNo"><c:out value="${showStatusInDetail.noOfSubjects}"></c:out></div>
				</div>
			</div>
			<div class="statusContent">
				<div class="statusPrint">
					<div class="title">No. of Topics</div><br>
					<div class="statusNo"><c:out value="${showStatusInDetail.noOfTopics}"></c:out></div>
				</div>
			</div>
			<div class="statusContent">
				<div class="statusPrint">
					<div class="title">No. of Questions</div><br>
					<div class="statusNo"><c:out value="${showStatusInDetail.noOfQuestions}"></c:out></div>
				</div>
			</div>
			<div class="statusContent">
				<div class="statusPrint">
					<div class="title">Total Question Papers</div>
					<div class="statusNo"><c:out value="${showStatusInDetail.noOfQuestioPaper}"></c:out></div>
				</div>
			</div>
			<div class="statusContent">
				<div class="statusPrint">
					<div class="title">Active Question Papers</div>
					<div class="statusNo"><c:out value="${showStatusInDetail.noOfActiveQuestionPaper}"></c:out></div>
				</div>
			</div>
		</div>
		<table class="display" id="example" style="margin-top: 40px;">
			<tr><th colspan="3">You can Manipulate various Sections e.g. Subject,Topic,Questions & even Add Question Paper</th></tr>
			<tr>
			<td><a href="SetSubject.htm" >Subject:-</a></td>
			
			<td>Add,Update Or Delete Multiple Subjects</td>
			
			<td>Example :- CoreJava ,JDBC,Hibernate & many more.</td>
			</tr>
			<tr>
			<td><a href="SetTopic.htm" >Topic:-</a></td>
			
			<td>Add,Update Or Delete Subject's Respective Topics.</td>
			
			<td>Example :- Constructor,Class,Polymorphism & many more.</td>
			</tr>
			<tr>
			<td ><a href="SetQuestion.htm" >Question:-</a></td>
			
			<td>Addition,Updation & Deletion Of Numerous Questions.</td>
			
			<td>Example :- Prepare And Set the Questions that can be used for the Exam Module</td>
			</tr>
			<tr>
			<td><a href="SetQuestionPaper.htm" >Question Paper:-</a></td>
			
			<td>Here You Can Set the Question Paper</td>
			
			<td>Example :-Set-1,Set-2 etc </td>
			</tr>
		</table>
	</div>		
			</div>
		</div>		
	</div>
</div>
	
<div id="footer">
	<div class="footer">
		<pre>Online Examination System. Copyright &copy; 2016 by <a href="#">Lara Dudes</a>.
All Rights Reserved</pre>	
		
	</div>
</div>	
	
</body>
</html>
