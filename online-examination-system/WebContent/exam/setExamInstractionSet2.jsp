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
 <style type="text/css">
 #page       {display: block;}

 #loading    {display: none;
             position: absolute;
             top: 0;
             left: 0;
             z-index: 100;
             width: 100vw;
             height: 100vh;
             background-color: rgba(255, 255, 255, 0.5);
             background-image: url('images/pleaswait2.gif');
             background-repeat: no-repeat;
             background-position: center;
             }
</style>
</head>
<body>
<div id="page">
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
				<span class="questionPaper">Question Paper Description</span>
			</div>
			<hr/>
			
			<div id="questionShow" class="QuesShow">
				<div id="examinstraction" class="Examins">
					<div>
						<%@ include file="QuestionPaperDesc.jsp" %>
					</div>
				</div>
			</div>
			<div class="clickEvent">
				<div class="clickEventAgree">
					<input type="button" name="examStart" id="examStart" disabled="disabled" value="Let's Begin">
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
</div>
<div id="loading"></div>
</body>
<script type="text/javascript" charset="utf8" src="Javascript/jquery-1.8.2.min.js"></script>
<script type="text/javascript" src="Javascript/Security.js"></script>
<script type="text/javascript">
	$(document).ready(function(){
	    $('input[type="checkbox"]').click(function()
	    {
	    	if($(this). prop("checked") == true)
	    	{
	    		 $("#examStart").prop('disabled', false);
	    	}
	    	else if($(this). prop("checked") == false)
	    	{
	    		$("#examStart").prop('disabled', true);
	    	}
	    });
	    
	    $(document).ready(function () {
	        $("#examStart").click(function () {
	             $('#loading').show();
	             return true;
	        });
	    });
	    
	     $("#examStart").click(function(event){
	    	var v=confirm("Click Ok To Proceed To The Exam");
	    	if(v)
	    		{
	    			setTimeout("location.href = 'DoExamStart.htm';",1500);
	    		}
	    	else
	    		{
	    			event.preventDefault();
	    			return false;
	    		}
	    });
	});
</script>
</html>
