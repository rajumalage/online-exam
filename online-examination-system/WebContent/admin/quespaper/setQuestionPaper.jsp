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
	      <li class="active"><a href="SetQuestionPaper.htm" >Question Paper</a></li>
	      <li><a href="SetNewQuestionPaper.htm">New Question Papers</a></li>
	      <li><a href="#">Question Select</a></li>
	      <li ><a href="#">Settings</a></li>
	   </ul>
	  <!-- nav end -->  
</div>

  <!-- header end -->
  
	<div id="content">
		<div id="sidebar">
			<ul class="sidebar_nav">
				<li><a href="#" class="active">Question Paper</a></li>
				<li><a href="#">Scheduled Exams </a></li>
			</ul>
		
			<div class="sidebar_note message note" >
				<br>
				<u style="font-size:13px;color:red">Note:-</u>
				<p align="left"><br>
				Dear Admin!!</p>
				<p align="left"><br>Here You Can Manipulate numerous questions along with multiple options.</p>
				<br>
			</div>
			
			<div>
				<p></p>
			</div>
		</div>
	
		<div id="main"> 
			<h3>Question Papers</h3>
			<hr />
				
		<div class="table_top">
			<a href="SetNewQuestionPaper.htm" class="btn_green_sml">New Question Paper</a>
		</div>
					
	  	<div id="q_bank" class="filterdiv" style="float: right;">
			<form action="#" method="post" >
				<input type="text" name="testname" value="" placeholder="Enter Question Paper Name">
				<button class="btn_green_sml" ><span>search</span></button>
			</form>
		</div>
		<div id="showQuesPaper">
			<form action="#" name="QuestionPaperForm" method="post" >		
			<c:set var="count" value="0" scope="page" />			
				<table class="display" id="example" style="margin-top: 40px;">
					<thead>
						<tr>
							<th>Sr.No.</th>
							<th>Question Paper Name</th>
							<th>Duration</th>
							<th>Questions</th>
							<th>Marks</th>
							<th style="text-align: center;">Show &nbsp;&nbsp;&nbsp; Delete</th>									
							<th colspan="2">Status &nbsp;&nbsp;(Active Or Deactive)</th>
						</tr>
					</thead>
					<tbody>
						
						<c:forEach items="${questionpaperdesc}" var="qpaper">
							<c:set var="count" value="${count + 1}" scope="page"/>
			       		<tr>
							<td><c:out value="${count}" /></td>
							<td>${qpaper.quesPaperDesc}&nbsp;<span class="highlight">(1 exam)</span> </td>
							<td>${qpaper.quesPaperDuration}&nbsp;Minutes</td>
							<td>${qpaper.noOfQues}</td>
							<td>${qpaper.quesPaperTotalMark}</td>
							<td style="text-align: center;">
							<a href="javascript:showDetails(${qpaper.quesPaperId});"><img style="opacity:0.7;" src="images/show.png" title="Show Question Paper Desc"/></a>
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;  <a href="javascript:UpdateQuesPaper(${qpaper.quesPaperId});"><img style="opacity:0.7;" src="images/delete-button.png" title="DeleteQuestionPaper"/></a>			
							</td>	
							<td style="text-align: center;">
								<%-- ${qpaper.status}&nbsp;Active --%>
								<c:if test="${qpaper.status == 1}" >
								<img style="opacity:0.7;" src="images/check_marks.png" title="Questin Paper Active"/>
	   								<b>&nbsp;Active</b>
								</c:if>
								<c:if test="${qpaper.status == 0}" >
								<img style="opacity:0.7;" src="images/cross_marks.png" title="Questin Paper Active"/>
	   								<b>&nbsp;Inactive</b>
								</c:if>
							</td>
							<td>
								<c:if test="${qpaper.status == 1}" >
	   								<a href="updateStatus.htm?quesId=${qpaper.quesPaperId}&&status=0" class="btn_orange_sml">Forget It</a>
								</c:if>
								<c:if test="${qpaper.status == 0}" >
	   								<a href="updateStatus.htm?quesId=${qpaper.quesPaperId}&&status=1" class="btn_green_sml">Assign</a>
								</c:if>
								
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
<script type="text/javascript">

	function showDetails(value)
	{
	//	alert(value);
		window.open("ShowQuestionPaperDetails.htm?quesId="+value, 'ShowDetails', 'scrollbars=yes,height=650px,width=1067px'); 
		return false;		
	}
	

	function UpdateQuesPaper(value)
	{
		var result=confirm("Do You Want To Delete QuestionPaper?");
		if(result)
		{			
			xhro = new XMLHttpRequest();
			xhro.onreadystatechange=DeleteQuestionPaper;
			xhro.open("GET","DeleteQuestionPaper.htm?QuesPaperId="+ value +"", true);
			xhro.send();
			 function DeleteQuestionPaper()
			{
				if(xhro.readyState == 4)
					{
					//	alert("hi");
						document.getElementById("showQuesPaper").innerHTML=xhro.responseText;
					}
			} 
			return true;
		}
		else
		{
			return false;
		}
	}
	/* <a href="mat/25.php" onclick="window.open(this.href, 'child', 'scrollbars,width=650,height=600'); return false">Food</a> */
</script>
</html>
