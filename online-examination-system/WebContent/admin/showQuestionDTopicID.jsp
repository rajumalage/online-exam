<%@page import="org.apache.jasper.tagplugins.jstl.core.ForEach"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

  <tr><th></th><th>Question</th></tr>
	<c:forEach items="${QuestionSearch}" var="srcQues">
		<tr>
			<td Style="text-align: center;"><input style="width: 15px;" type="checkbox" name="questionIDs" id="questionIDs" value="${srcQues.id}"></td>
			<td>${srcQues.quesDesc}</td>
		</tr>
	</c:forEach>		


				 
	
		