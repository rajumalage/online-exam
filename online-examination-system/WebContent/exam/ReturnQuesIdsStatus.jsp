<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<body>
<c:set var="count" value="0" scope="page" />
<c:forEach items="${QuesIdsStatus}" var="QuesOps">
	<c:set var="count" value="${count + 1}" scope="page"/>
	<c:if test="${QuesOps.status == 1}" >
	   	<button class="btn_gray" id="quesIds" name="quesIds" value="<c:out value="${QuesOps.quesId}" />" onclick="ReturnQuesIdsStatus(this.value)">
		<c:out value="${count}"/></button>	
	</c:if>	
	<c:if test="${QuesOps.status == 2}" >
	   	<button class="btn_red" id="quesIds" name="quesIds" value="<c:out value="${QuesOps.quesId}" />" onclick="ReturnQuesIdsStatus(this.value)">
		<c:out value="${count}"/></button>	
	</c:if>	
	<c:if test="${QuesOps.status == 3}" >
	   	<button class="btn_purpal" id="quesIds" name="quesIds" value="<c:out value="${QuesOps.quesId}" />" onclick="ReturnQuesIdsStatus(this.value)">
		<c:out value="${count}"/></button>	
	</c:if>	
</c:forEach>
</body>	
<!-- 
class="btn_gray"
class="btn_red"	
class="btn_purpal"
class="btn_green"	
 -->