<%@page import="org.apache.jasper.tagplugins.jstl.core.ForEach"%>
<%@page import="java.util.*"%>
<%@page import="com.lara.oes.entity.QuestionOption"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE HTML>
<html lang="en">
<head>
<meta charset=utf-8>
<title>Online Examination System</title>

<link type="image/x-icon" href="/a/images/favicon.ico" rel="shortcut icon" />
<link rel="stylesheet" href="css/Style2.css" type="text/css" />
<link href='https://fonts.googleapis.com/css?family=Quando' rel='stylesheet' type='text/css'>
<link rel="stylesheet" type="text/css" href="css/jquery.dataTables.css">
<link rel="stylesheet" type="text/css" href="css/jquery-ui.min.css">
<style type="text/css">
#header{
height: 35px;
}
textarea {
    resize: none;
}
img{
height: 26px;
}
</style>
</head>
<body>
	
<div id="wrapper">
		
	<div id="header">
       <h1><a href="#"><img id="clogo" src="images/logo.png" alt="Oes Admin" /></a></h1>
	</div>
  <!-- header end -->
	<div id="content">
		<div id="main">
			<h3>Update Question &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<span id="status" style="color:brown"></span></h3>
			<hr>
			<div id="q_bank4">
				<form action="UpdateQuestionFinal.htm" id="UpdateQuestion" name="UpdateQuestion" method="POST">
					<div style="width: 100%; float: left;">
					 <div id="Question">

					 	<p>
							<label>Selected Subject Name:-:</label>
								<label><c:out value="${SubjectName}" /></label>
							</p>
							<p>
							<label>Selected Topic Name:-</label>
								<label><c:out value="${TopicName}" /></label>
							</p>
						</div>
					</div>						

					<div style="margin: 10px auto;">
						<h6>Question Description:- </h6>
						<textarea name="QuestionDesc" rows="8" cols="56" id="QuestionDesc"><c:out value="${Question}"></c:out></textarea>
						<input type="hidden" name="QuestionId" value="<c:out value="${QuestionID}"></c:out>" />
					</div>
										<div style="width: 100%; float: left;">
					 <div id="Question">
					 	<div id='TextBoxesGroup'>
					 	
						<c:set var="count" value="0" scope="page" />
							<c:forEach items="${Options}" var="opes">
								<c:set var="count" value="${count + 1}" scope="page"/>
								<div id="TextBoxDiv<c:out value="${count}" />">
									 <table border=0>
										<tr><th style="vertical-align: top;font-size: 18px;"><c:out value="${count}" />:-</th>
										<td><textarea name="Options" id="textbox<c:out value="${count}"/>" rows="2" cols="18">${opes.optionDesc}</textarea>
										</td></tr>
									</table>
								</div>
								</c:forEach>
							</div>
							<div style="margin: 10px auto;">
								<input type='button' class="button"  id='addMore' value="+ Add Option">
							  <input type='button' class="button" id="removeButton" value="- Remove Option">
							</div>
							
							<p>
							    <label>Correct Answer:</label>
							    <% List<QuestionOption> QuestionOptionCount = (List<QuestionOption>)request.getAttribute("QuestionOptionCount"); 
							StringBuffer sb = new StringBuffer();
							StringBuffer sbId = new StringBuffer();
							for (int i =0; i < QuestionOptionCount.size(); i++)
							{
								if(i != QuestionOptionCount.size()-1){
									sb.append(QuestionOptionCount.get(i).getOpsCount()+", ");
									sbId.append(QuestionOptionCount.get(i).getId()+", ");
								}
								else{
									sb.append(QuestionOptionCount.get(i).getOpsCount());
									sbId.append(QuestionOptionCount.get(i).getId());
								}
							}
							
							%>
							<input class="Input_height" type="text" id="answer" name="answer" 
							     value="<%= sb.toString() %>"/>
							     <input type="hidden" id="optionId" name="optionId" 
							     value="<%= sbId.toString() %>"/>
							</p>
							<p>
							<input type="submit" class="btn_green"  id="btnsave" value="Update Question"  onclick="return QuestionPaperValidate()" >
							</p>
						</div>
					</div>
				</form>
			</div>
		</div>
	</div>
	</div>
</body>

<script type="text/javascript" charset="utf8" src="Javascript/jquery-1.3.2.min.js"></script>
<script type="text/javascript" charset="utf8" src="Javascript/jquery-1.8.2.min.js"></script>
<script type="text/javascript" src="Javascript/jquery.js"></script>
<script type="text/javascript">
$(document).ready(function() {
		
	var counter = 2;
	counter = document.forms[0].Options.length + 1 ;
	   $("#addMore").click(function() {			
	   	if (counter > 9) 
	   		{
				alert("Sorry!! Only 9 Options allowed");
				return false;
			}
		var newTextBox = $(document.createElement('div')).attr("id",'TextBoxDiv'+ counter);
	
			newTextBox.after().html('<table border=0><tr><th style="vertical-align: top;font-size: 18px;">'+ counter +':-</th><td>'
			+ '<textarea name="Options" id="textbox'+ counter +'" rows="2" cols="18"></textarea>'
			+'</td></tr></table>');
		
			newTextBox.appendTo("#TextBoxesGroup");
			counter++;
	});

    // removed add TextBoxDiv . 
	$("#removeButton").click(function() {
		
	  if (counter == 2) 
	      {
			alert("Minimum One Option Is Required");
			return false;
		  }
		counter--;
		$("#TextBoxDiv" + counter).remove();
		});
	
	var form = $('#UpdateQuestion');
	form.submit(function() {

		$.ajax({
			type : form.attr('method'),
			url : form.attr('action'),
			data : form.serialize(),
			success : function(data) {

				var result = data;
				$('#status').html(result);
			   //window.close().delay(3000);
			}
		});
		return false;
	});
});
</script>
</html>
