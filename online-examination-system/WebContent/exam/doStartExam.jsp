<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Online Examination System</title>
<link type="image/x-icon" href="images/favicon.ico" rel="shortcut icon" />
<link rel="stylesheet" href="css/Style_exam_dostart.css" type="text/css" />
</head>
<body onload="ExamDuration(<c:out value="${QuestionPaperDuration}"/>) ">

<div id="wrapper" >

	<%-- <%@ include file="header.jsp"%> --%>
<div id="exam_header"></div>
	
  <!-- header end -->
  
	<div id="content">
		
			<div class="exam_info">
				<div class="exam_Section">
					<div style="overflow: hidden;">
					 <ul id="sidemanu">
		  				<li ><a href="#" >Welcome!! <c:out value="${UserFullName}"/></a></li>
	  				</ul>
					</div>
				</div>
				<div class="profile">
					<div class="exam_photo">
						<img alt='Sourav' src="images/userProfile.png">
					</div>
					<div class="exam_time">
						<table>
							<tr><th style="color:#535050;font-weight: 700">Time Left:-</th>
							<td>
							 <form name="cd">
								<input id="txt" readonly type="text" value="10"  name="disp" >
							 </form>
							</tr>
						</table>
					</div>
				</div>
			</div>
			
<!--  ---------- Question Paper Options Begin------------------- -->
	
	<div id="ShowQuesWith">
		<div style="overflow: hidden;" >
			<div id="sidebar_right">
			<div>
				<div style="padding: 6px 0; color: #055788;font-size: 17px;font-weight: 400; margin-bottom: 3px;border-bottom: 1px solid;">
					Question Paper Options
				</div>
				
				
			</div>
			<div class="sidebar_div">
			<div style="color: #4A3C3C; font-size: 15px; font-weight: 400;padding: 6px 0; margin-bottom: 3px;">
					Question Palette
			</div>
				<div class="sidebar_Ques" id="ShowQuesIDs">
				<c:set var="count" value="0" scope="page" />
				<c:forEach items="${QuestionIdsDPaper}" var="QuesOps">
					<c:set var="count" value="${count + 1}" scope="page"/>
					<button class="btn_gray" id="quesIds" name="quesIds" value="<c:out value="${QuesOps.questionId}" />" onclick="ReturnQuesIdsStatus(this.value)"><c:out value="${count}"/></button>
				</c:forEach>
					
				</div>
			</div>
		</div>		
<!--  ---------- Question Paper Options end------------------- -->

<!--  ---------- Question Display Begin------------------- -->
<div id="QuesDisplay">
<form action="SubmitAndNextQues.htm" name="SubmitQuesAndNext"  id="SubmitQuesAndNext" method="Post">
	<div id="ShowQuesAndOps"> <!-- Ajax return output show in this div (show next question for quespaper) -->
		<div id="main">	
			<div class="main_header">
				<span class="questionPaper">Question Papers:-</span><span class="PaperName"><c:out value="${QuestionPaperName}"></c:out></span>
			</div>
			<hr/>
			<% 
				int arraySize = (Integer) session.getAttribute("ArrayListSize"); 
			   int quesNo = (Integer) session.getAttribute("QuestionNo"); 
			%>
			<input type="hidden" id="arraySize" name="arraySize" value="<%= arraySize %>">
			<input type="hidden" id="quesNo" name="quesNo" value="<%= quesNo %>">
			<div id="questionShow" class="QuesShow">		
				 <div id="questiontitle" class="Questitle"><h6>Question: &nbsp;<c:out value="${QuestionNo}" /> </h6><hr>
				 
				 </div>
				<div id="questionDesc" class="QuesDesc">
					<input type="hidden" name="QuesId" id="QuesId" value="<c:forEach items="${QuestionDesc}" var="QuesDesc"><c:out value="${QuesDesc.id}" /></c:forEach>">
					<textarea rows="10" cols="142" readonly><c:forEach items="${QuestionDesc}" var="QuesDesc"><c:out value="${QuesDesc.quesDesc}" /></c:forEach></textarea>
				</div>
				<div id="questionOptions" class="QuesOps">
				<label class="QuesAnslable">Question Option:- </label>
				<c:set var="count" value="0" scope="page" />
					<textarea rows="9" cols="142" readonly><c:forEach items="${QuesOptions}" var="QuesOps"><c:set var="count" value="${count + 1}" scope="page"/>&#13;<c:out value="${count}" />&nbsp;<c:out value="${QuesOps.optionDesc}" /></c:forEach></textarea>
				</div>
				<div id="questionAnswer" class="QuesAns">
				<label class="QuesAnslable">Correct Answer:- </label>
				<div class="quesAnsShow">
				<table>
				<tr>
				<c:set var="count" value="0" scope="page" />
				<c:forEach items="${QuesOptions}" var="QuesOps">
				<c:set var="count" value="${count + 1}" scope="page"/>
						<th><c:out value="${count}" />.</th>
						<td><input type="checkbox" class="rest"  name="answer" value="<c:out value="${QuesOps.id}" />"/></td>					
				</c:forEach>
				</tr>
				</table>
				</div>
				</div> 
				</div>	
			</div>
	</div>	<!-- Ajax return output show in this div (show next question for quespaper) -->
			
			<div id="main1">	
			<div class="clickEvent">
				<div class="clickEventleft">
					<input type="submit" name="skip" id="skip" value="Skip" onclick="SkipAndNext()">&nbsp;&nbsp;&nbsp;
					<input type="button" name="reset" id="reset" value="Clear Response"  onclick="questiondeseclean()">	
				</div>
			
				<div class='clickEventright' id='clickEventright'>
				<input type="button" name="submit" id="submit" value='SUBMIT'/>
				<input type="submit" name="continue" id="continue" value='Save & Next'/>
				</div>				
			</div>
			<div class="sidebar_right">				
				 <img alt="Option Description" src="images/opsDesc1.png">			
				<input type="button" id="ExamSubmit" name="ExamSubmit" value="Final Submit" onclick="document.location.href='SetExamSummary.htm'">			
			</div>
			</div>
		</form>
		</div>
		</div>		
	</div>
</div>	
</div>	
		
<!--  ---------- Question Display end------------------- -->

</body>
<script type="text/javascript" src="Javascript/jquery-1.3.2.min.js"></script>
<script type="text/javascript" src="Javascript/coundown.js"></script>
<script type="text/javascript" src="Javascript/dostartExam.js"></script>
<script type="text/javascript" src="Javascript/Security.js"></script>
<script type="text/javascript">

function ReturnQuesIdsStatus(value)
{
	xhro = new XMLHttpRequest();
	xhro.onreadystatechange=setTopic;
	xhro.open("GET","ShowQuesDescResQuesId.htm?QuesId="+ value +"", true);
	xhro.send();
	
}
function setTopic()
{
	if(xhro.readyState == 4)
		{
			document.getElementById("ShowQuesAndOps").innerHTML=xhro.responseText;
			CheakValid()
		}
	
}
</script>
</html>