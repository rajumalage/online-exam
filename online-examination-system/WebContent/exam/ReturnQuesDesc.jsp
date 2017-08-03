<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE HTML>
<html lang="en">
<head>
<meta charset=utf-8>
<link type="image/x-icon" href="images/favicon.ico" rel="shortcut icon" />
<link rel="stylesheet" href="css/Style_exam_dostart.css" type="text/css" />
 <!-- <link href='https://fonts.googleapis.com/css?family=Quando' rel='stylesheet' type='text/css'> -->
</head>
<body>
	<!--  full QuestionDesc data information into Hare respected Id -->
		<div id="main">	
			<div class="main_header">
				<span class="questionPaper">Question Papers:-</span><span class="PaperName"><c:out value="${QuestionPaperName}"></c:out></span>
			</div>
			<hr/>
			
			<div id="questionShow" class="QuesShow">		
				 <div id="questiontitle" class="Questitle"><h6>Question: &nbsp;<c:out value="${QuestionNo}" /> </h6><hr>
				 <% 
					int arraySize = (Integer) session.getAttribute("ArrayListSize"); 
			   		int quesNo = (Integer) session.getAttribute("QuestionNo"); 
				%>
					<input type="hidden" id="arraySize" name="arraySize" value="<%= arraySize %>">
					<input type="hidden" id="quesNo" name="quesNo" value="<%= quesNo %>">
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
						<td>
						<input type="checkbox" class="rest"  name="answer" value="<c:out value="${QuesOps.id}"/>" 						
						<c:forEach items="${CurrectOpsId}" var="QuesCOps">
							<c:if test="${QuesOps.id == QuesCOps.opesId}">checked="checked"</c:if>
						</c:forEach>
						/></td>					
				</c:forEach>
				</tr>
				</table>
				</div>
				</div> 
				</div>	
			</div>
	
</body>			