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
	      <li class="active"><a href="#" >DashBoard</a></li>
	      <li><a href="SetNewSubject.htm">Add New Subject </a></li>
	      <li><a href="SetUpdateSubject.htm">Update Subject</a></li>
	      <li ><a href="#">Help</a></li>
	   </ul>
	  <!-- nav end -->  
</div>

  <!-- header end -->
  
	<div id="content">
		<div id="sidebar">
			<ul class="sidebar_nav">
				<li><a href="#" class="active">Add Subject</a></li>
				<li><a href="#">Update Subject </a></li>
			</ul>
		
			<div class="sidebar_note message note" >
				<br>
				<u style="font-size:13px;color:red">Note:-</u>
				<p align="left"><br>
				Select Any Options To Manipulate The Details Of Subject</p>
				<br>
			</div>
			
			<div>
				<p></p>
			</div>
		</div>
	
		<div id="main"> 
			<h3>Subject</h3> <span id="SOP"></span><span id="statuees"><c:out value="${successMassage}" /></span>
			<hr />
				
		<div class="table_top">
			<a href="SetNewSubject.htm" class="btn_green_sml">Add New Subject</a>
		</div>
					
	  	<div id="q_bank" class="filterdiv" style="float: right;">
			<form action="SearchBySubject" id="SubjectSearch" name="SubjectSearch" method="post" >
				<input type="search" name="searchsubject" placeholder="Enter Subject Name">
				<button class="btn_green_sml" ><span>search</span></button>
			</form>
		</div>
		<div id="showSubjectDetails">
		<div style="float: left;width: 100%;overflow:auto;height:400px;">
			<form action="#" name="QuestionPaperForm" method="post" >		
			<c:set var="count" value="0" scope="page" />			
				<table class="display" id="example" style="margin-top: 40px;">
					<thead>
						<tr>
							<th>Sr.No.</th>
							<th>Subject Name</th>
							<th>description</th>
							<th style="text-align: center;">Edit</th>
							<th style="text-align: center;">Delete</th>
						</tr>
					</thead>
					<tbody>
						
						<c:forEach items="${Subjects}" var="subject">
							<c:set var="count" value="${count + 1 }" scope="page"/>
			       		<tr>
							<td><c:out value="${count}" /></td>
							<td><c:out value="${subject.subName}" /></td>
							<td><c:out value="${subject.subDese}" /></td>
							<td style="text-align: center;">
							<a href="javascript:showDetails(<c:out value="${subject.id}" />);"><img style="opacity:0.7;" src="images/edit.png" title="Show Subject Desc"/></a></td>
							<td style="text-align: center;">
							 <a href="javascript:DeleteSubject(<c:out value="${subject.id}" />);"><img style="opacity:0.7;" src="images/delete-button.png" title="DeleteSubject"/></a>			
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
	   $("#statuees").fadeOut('slow');
	}
	
	function showDetails(value)
	{
	//	alert(value);
		window.open("SetUpdateSubject.htm?subId="+value, 'ShowDetails', 'scrollbars=yes,height=650px,width=1067px'); 
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

	function DeleteSubject(value)
	{
		var result=confirm("Do You Want To Delete Subject?");
		if(result)
		{			
			xhro = new XMLHttpRequest();
			xhro.onreadystatechange=DeleteSubject;
			xhro.open("GET","DeleteSubjectFinal.htm?SubJectID="+ value +"", true);
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
</script>
</html>
