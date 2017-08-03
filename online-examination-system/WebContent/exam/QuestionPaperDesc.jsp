<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<body>
<div style="margin-top:12px;" class="generalIns">
<table>
<tr><th><u>Question Description Are given Below :</u></th></tr>
</table>
	<div class="generalInsOps">
		<div class="QuesDesc">
			<table >
					<tr>
						<td>
							<textarea rows="19" cols="135"><c:forEach items="${QuesPaperDesc}" var="quesDesc"><c:out value="${quesDesc.quesPaperInfo}"></c:out></c:forEach></textarea>
						</td>
					</tr>
			</table>
		</div>
	</div>
</div>

<div style="margin-top:12px;" class="ExamPaperAggree">
	<table>
		<tr><td><input type="checkbox" id="aggreefirst" name="aggreefirst" value="aggree"></td>
		<td><!-- I Have Read Complete Instructions And the Descriptions as well. -->I accept the terms and Am Ready To Go.</td>
		</tr>
	</table>
</div>
</body>
</html>