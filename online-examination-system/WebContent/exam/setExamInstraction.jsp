<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE HTML>
<html lang="en">
<head>
<meta charset=utf-8>
<title>Online Examination System</title>
<link type="image/x-icon" href="images/favicon.ico" rel="shortcut icon" />
<link rel="stylesheet" href="css/Style_exam.css" type="text/css" />
<!-- <link href='https://fonts.googleapis.com/css?family=Quando' rel='stylesheet' type='text/css'>
 -->
</head>
<body>
	
<div id="wrapper">
	
	<%@ include file="header.jsp"%>
	
  <!-- header end -->
  
<div id="content">

	<!--  ---------- Exam Candidate Profile------------------- -->
	<div style="overflow: hidden;">
			<div id="sidebar_right">
			<div>
				<div style="padding: 6px 0; color: #055788;font-size: 17px;font-weight: 400; margin-bottom: 3px;border-bottom: 1px solid;">
					Candidate Profile --
				</div>
				
				
			</div>
			<div class="sidebar_div">
			
				<div class="userProfile">
					<div class="userPhoto">
						<img alt="sourav" src="images/userProfile.png">
					</div>
					
					<div class="userName">
					<table>
						<tr>
							<th>Name: </th>
							<td><span><c:out value="${UserFullName}"/></span></td>
						</tr>
						<tr>
							<th>Exam Name: </th>
							<td><c:out value="${QuestionPaperName}"/></td>
						</tr>
						</table>
					</div>
				</div>
				
				
			</div>
		</div>
		
<!--  ---------- Question Paper Options end------------------- -->

<!--  ---------- Question Display Begin------------------- -->
		<div id="main">		
			<div class="main_header">
				<span class="questionPaper">Please Read the Instructions Given Carefully!</span>
			</div>
			<hr/>
			
			<div id="questionShow" class="QuesShow">
				<div id="examinstraction" class="Examins">
					<div>
						<%@ include file="Examinstruction.jsp" %>
					</div>
				</div>
			</div>
			<div class="clickEvent">
				<div class="clickEventAgree">
					<input type="button" name="next" id="next" disabled="disabled" value="NEXT" onclick="document.location.href='SetExamTwo.htm'">
				</div>
			</div>
		</div>
	</div>	
<!--  ---------- Question Display end------------------- -->
<div class="footer"> 
	<div>
		<p> Online Examination System. Copyright &copy; 2016 by 
		<a href="#">Lara Dudes</a>. 
		All Rights Reserved</p>

	</div>
</div>


	</div>
</div>
</body>
<script type="text/javascript" charset="utf8" src="Javascript/jquery-1.8.2.min.js"></script>
<script type="text/javascript" src="Javascript/Security.js"></script>
<script type="text/javascript">
	$(document).ready(function(){
	    $('input[type="checkbox"]').click(function()
	    {
	    	if($(this). prop("checked") == true)
	    	{
	    		 $("#next").prop('disabled', false);
	    	}
	    	else if($(this). prop("checked") == false)
	    	{
	    		$("#next").prop('disabled', true);
	    	}
	    });
	});
</script>
</html>
