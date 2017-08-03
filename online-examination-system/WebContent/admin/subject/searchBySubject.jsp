<%@page import="org.apache.jasper.tagplugins.jstl.core.ForEach"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<link href="css/Style2.css" rel="stylesheet">
</head>
<body>
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
</html>
			
		