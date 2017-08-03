<%@page import="org.apache.jasper.tagplugins.jstl.core.ForEach"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE HTML>
<html lang="en">
<head>
<meta charset=utf-8>
<title>Online Examination System</title>
<link type="image/x-icon" href="/a/images/favicon.ico" rel="shortcut icon" />
<link rel="stylesheet" href="css/Style-blogs.css" type="text/css" />
<!-- <link href='https://fonts.googleapis.com/css?family=Quando' rel='stylesheet' type='text/css'> -->

</head>
<body>
		<div id="main">
			<h3>Question Papers Details</h3> 
			<c:forEach items="${questionpaperdesc}" var="quesPaperDetails">			
			<div style="width:1060px;overflow: hidden;">				
					<div style="width: 45%; float: left;">
					 	<div class="QuestionPaper">

						 	 <table class="showQuestionDetails">
						 	<tr>
								<td><label class="QuesShowTag">Question Paper Name :- </label> </td>
								<td><label class="QuesShowName"><c:out value="${quesPaperDetails.quesPaperDesc}"/> </label></td>
							</tr>
							<tr>							
								<td><label class="QuesShowTag">Total Time :- </label></td>
								<td><label class="QuesShowName"><c:out value="${quesPaperDetails.quesPaperDuration }"/>&nbsp;&nbsp;Minutes</label></td>
							</tr>
							<tr>
								<td><label class="QuesShowTag">NO of Question:- </label></td>
								<td><label class="QuesShowName"><c:out value="${quesPaperDetails.noOfQues }"/></label></td>								
							</tr>
							<tr>
								<td><label class="QuesShowTag">Mark Per Question:- </label></td>
								<td><label class="QuesShowName"><c:out value="${quesPaperDetails.quesPaperMarksPerQues }"/> </label></td>								
							</tr>
							<tr>
								<td><label class="QuesShowTag">Negative No:- </label></td>
								<td><label class="QuesShowName"><c:out value="${quesPaperDetails.quesPaperNagativeMark }"/> (Zero mines No Negative Marks) </label></td>								
							</tr>
							<%-- <tr>
								<td style="vertical-align: top"><label class="QuesShowTag">Question Paper Description:- </label></td>
								<td><textarea style="margin-top: 12px;" rows="8" cols="90" readonly="readonly"><c:out value="${quesPaperDetails.quesPaperInfo }"/></textarea>
								<p> <c:out value="${quesPaperDetails.quesPaperInfo }"/></p> </td>								
							</tr> --%>
							</table>
						</div>
					</div>
					<div style="width: 55%; float: right;">
					 	<div class="QuestionPaper">

						 	 <table class="showQuestionDetails">
						 
							 <tr>
								<td style="vertical-align: top"><label class="QuesShowTag">Question Paper Description:- </label></td>
								<td><textarea style="margin-top: 12px;" rows="8" cols="90" readonly="readonly"><c:out value="${quesPaperDetails.quesPaperInfo }"/></textarea>								
							</tr> 
							</table>
						</div>
					</div>					
				</div>
				</c:forEach>
				<div class="Quesweaper">
					 <!-- <div class="QuesBody">
						<div class="QuesDescTh">
							<div class="tableTh">Sr.No.</div>
							<div class="tableTh">Total Questions ( 10 No.s ) </div>
						</div>
					</div>  -->
					<div class="QuesBodyTD">
						<div class="ShowQuesDesc">
							<section class="">
							  <div class="container">
							    <table>
							      <thead>
							        <tr class="header">
							          <th>
							            Sr.No
							            <div>Sr.No</div>
							          </th>
							          <th>
							            Total Questions ( 10 No.s )
							            <div>Total Questions ( 10 No.s )</div>
							          </th>
							        </tr>
							      </thead>
							      <tbody>
							        <c:set var="count" value="0" scope="page" />	
								<c:forEach items="${questionDesc}" var="quesDesc">
									<c:set var="count" value="${count + 1}" scope="page"/>		
								<tr>
									<td class="QuesSl"><c:out value="${count}" />.</td>
									<td class="Quesde"><c:out value="${quesDesc.quesDesc}"></c:out></td>
								</tr>
								</c:forEach>
							        
							      </tbody>
							    </table>
							  </div>
</section>			
						</div>
					</div>
				</div>
		</div>
</body>
</html>