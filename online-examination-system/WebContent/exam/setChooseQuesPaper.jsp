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
<body <%-- onload="CheakValid(<c:out value="${QuestionPaperDuration}"/>)" --%>>
	
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
							<td><span><c:out value="${UserFullName}"></c:out></span></td>
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
				<span class="questionPaper">Exam Instruction Information </span>
			</div>
			<hr/>
			
			<div id="questionShow" class="QuesShow">
			<form action="SetExam.htm" method="Post">
					<div class="QuesSummaryTable">					
					<table>
						<tr><td><label style="font-size: 15px;"><b>Please Select The Question Paper To Proceed</b></label></td></tr>
						<tr>
							<td>
								 <table id="showQuesPaper">
									<tr>
										<th>SLNO</th>
										<th>Question Paper Description</th>
										<th>Select</th>
										
									</tr>
									<c:set var="count" value="0" scope="page" />	
									<c:forEach items="${QuestionPaperDescription}" var="quesDesc">
									<c:set var="count" value="${count + 1}" scope="page"/>
									<tr>
										<td><c:out value="${count}" /></td>
										<td><c:out value="${quesDesc.quesPaperDesc}"></c:out></td>
										<td><input type="radio" name="QuesPaperId" id="QuesPaperId" value="<c:out value="${quesDesc.quesPaperId}"/>" ></td>						
									</tr>
									</c:forEach>					
								</table> 
								<input type="hidden" name="QuesPaperStatus" id="QuesPaperStatus" value='<c:out value="${QuestionPaperActiveStatus}"></c:out>' >
							</td>
						</tr>
						<tr>
						<td>
							
								<div class="QuesSummaryConfirm">
									<div id="ConfirmLabel">
										<label style="font-size: 15px;font-weight: 600px;">
												<b>Please Select Question Paper</b></label>
									</div>
									<div class="confirm">
										<c:if test="${QuestionPaperActiveStatus == 0}" >
		   									<div id="confirmYes">
											<input type="submit" style="opacity: 0.6;" id="next" name="next" value="NEXT" disabled="disabled">
										</div>
										</c:if>	
										<c:if test="${QuestionPaperActiveStatus == 1}" >
		   									<div id="confirmYes">
											<input type="submit" id="next" name="next" value="NEXT">
										</div>
										</c:if>	
									<!-- 											
										<div id="confirmYes">
											<input type="submit" id="next" name="next" value="NEXT"  onclick="return QuesPaperValidate();">
										</div> -->
									</div>
								</div>
							</td>
						</tr>
					</table>						
				</div>
				</form>
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

</body>
<script type="text/javascript" charset="utf8" src="Javascript/jquery-1.8.2.min.js"></script>
<script type="text/javascript" src="Javascript/Security.js"></script>
<script type="text/javascript">
$(function(){
   
    $(jQuery.unique(
       
        $('INPUT:radio')
            .map(function(i,e){
                return $(e).attr('name') }
            ).get()
    ))
  
    .each(function(i,e){
        
        $('INPUT:radio[name="'+e+'"]:visible:first')
            .attr('checked','checked');
    });
});
</script>
</html>
