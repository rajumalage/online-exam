<%@page import="org.apache.jasper.tagplugins.jstl.core.ForEach"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE HTML>
<html lang="en">
<head>
<meta charset=utf-8>
<link rel="stylesheet" href="css/Style2.css" type="text/css" />
<!-- <link href='https://fonts.googleapis.com/css?family=Quando' rel='stylesheet' type='text/css'> -->

</head>
<body>
		
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
							<th style="text-align: center;">Option</th>									
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
		
</body>