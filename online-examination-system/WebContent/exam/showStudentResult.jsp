<%@page import="org.apache.jasper.tagplugins.jstl.core.ForEach"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="com.lara.oes.entity.*"%>
<%@page import="java.util.*"%>
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
<body>
<%
		Student student=(Student)request.getAttribute("studentDetails");
		Marks marks=(Marks)request.getAttribute("allMarks");
		QuestionPaper list=(QuestionPaper)request.getAttribute("questionPaper");
	%>
<div id="wrapper" >

	<%-- <%@ include file="header.jsp"%> --%>
<div id="exam_header"></div>
	
  <!-- header end -->
  
	<div id="content">
		
			<div class="exam_info">
				<div class="exam_Section">
					<div style="overflow: hidden;">
					 <ul id="sidemanu">
		  				<li ><a href="#" ><c:out value="${UserFullName}"/></a></li>
						<!-- <li ><a href="#" title="Logout">Logout</a></li> -->
	  				</ul>
					</div>
					 <!-- <div>QUESTION PAPER DESCRIPTION:-</div> -->
				</div>
				<div class="profile">
					<div class="exam_photo">
						<img alt='Sourav' src="images/userProfile.png">
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
				<c:forEach items="${QuesIdsStatus}" var="QuesOps">
					<c:set var="count" value="${count + 1}" scope="page"/>
					<c:if test="${QuesOps.status == 1}" >
					   	<button class="btn_gray" id="quesIds" name="quesIds" value="<c:out value="${QuesOps.quesId}" />">
						<c:out value="${count}"/></button>	
					</c:if>	
					<c:if test="${QuesOps.status == 2}" >
					   	<button class="btn_red" id="quesIds" name="quesIds" value="<c:out value="${QuesOps.quesId}" />">
						<c:out value="${count}"/></button>	
					</c:if>	
					<c:if test="${QuesOps.status == 3}" >
					   	<button class="btn_purpal" id="quesIds" name="quesIds" value="<c:out value="${QuesOps.quesId}" />">
						<c:out value="${count}"/></button>	
					</c:if>	
				</c:forEach>				
				</div>
			</div>
		</div>		
<!--  ---------- Question Paper Options end------------------- -->

<!--  ---------- Exam Summary Display Begin------------------- -->
	<div id="QuesDisplay">
			<div id="main">		
			<div class="main_header">
				<span class="questionPaper">Question Papers:-</span><span class="PaperName"><c:out value="${QuestionPaperName}"></c:out></span>
			</div>
			<hr/>
			
			<div id="questionShow" class="QuesShow">
			<div id="questiontitle" class="Questitle"><h6>Your Result:-</h6><hr></div>			
				<div id="questionDesc" class="QuesDesc">
				<div class="QuesSummary">
				<div class="QuesSummaryTable">					
					<table>
						<tr><td><label style="font-size: 15px;">
							
					 			<%int user = marks.getStatus(); %>
								  <%if(user == 0)
								  	{
								   	 %>
								   	 <b style="color:red;">Failed!! Please Try Hard..</b>
								   	 <%   
								  	}
								   else 
								   {
								   %>
								    	<b style="color:green;">Congratulation!! you have Passed The Exam..</b>								   
								   <% } %>  
							</label></td></tr>
						<tr>
							<td>
								 <table>
									<tr>
										<th>Student Id</th>
										<th>Paper Name</th>
										<th>Full marks</th>
										<th>Pass Marks</th>
										<th>Obtained Marks</th>
										<th>Percentage</th>
									</tr>
									<tr>
										<td><%=student.getStuId() %>
										<td><%=list.getQuesPaperDesc() %></td>
										<td><%=list.getQuesPaperTotalMark() %>
										<td><%=list.getQuesPaperPassingMark() %>
										<td><%=marks.getObtainedMarks() %>
										<td><%=marks.getPercentage() %>
										
									</tr>
								</table> 
							</td>
						</tr>
						<tr>
						<td>
							<table>
								
							</table>
							</td>
						</tr>
						
						<tr>
							<td style="text-align: center">
								<div class="QuesSummaryConfirm">
									<div id="ConfirmLabel">
										<label style="font-size: 15px;font-weight: 600px;">
												<b>Click Submit to Close this window.</b></label>
									</div>
										<div class="confirm">
										<form action="#" id="close" name="close">
										<div id="confirmYes" style="margin: 0 68px;">
											<input type="button" id="close" name="close" Value="Close" onclick="window.close()"/>
										</div>
										</form>
									</div>
								</div>
							</td>
						</tr>
					</table>						
				</div>
			</div>
			</div>
		</div>
	</div>	
		
		</div>
		</div>		
	</div>
	<div id="main1" style="padding-bottom: 86px">	
			<div class="clickEvent" style="border:none">
				<div class="clickEventleft">
					
				</div>
						
			</div>
			<div class="sidebar_right" style="border:none">				
				 <img alt="Option Description" src="images/opsDesc1.png">			
				
			</div>
	</div>
</div>	
</div>	
		
<!--  ---------- Question Display end------------------- -->

</body>
<script type="text/javascript" src="Javascript/jquery-1.3.2.min.js"></script>
<script type="text/javascript" src="Javascript/coundown.js"></script>
<script type="text/javascript" src="Javascript/Security.js"></script>
</html>