<%@page import="org.apache.jasper.tagplugins.jstl.core.ForEach"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE HTML>
<html lang="en">
<head>
<meta charset=utf-8>
<title>Online Examination System</title>
<link type="image/x-icon" href="images/favicon.ico" rel="shortcut icon" />
<link rel="stylesheet" href="css/Style2.css" type="text/css" />
<link href='https://fonts.googleapis.com/css?family=Quando' rel='stylesheet' type='text/css'>
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
    <img id="clogo" src="images/logo.png" alt="Oes Admin">

</div>
  <!-- header end -->
	<div id="content">		
		<div id="main">
			<h3>Update Subject</h3><span id="Status"></span>
			<hr>
			<div id="q_bank4">
				<form action="UdateSubjectFinal.htm" id="updateSubject" name="updateSubject" method="POST">
	        <c:forEach items="${subNameDesc}" var="sub">		
	        <div style="width: 100%; float: left;">
					 <div id="Subject">
						<p>
					
						<label>Update Subject Name:- </label>
							<input class="Input_height" type="text" id="SubName" name="SubName" value="<c:out value="${sub.subName}"/>" placeholder="Please Enter the Subject Name"/>
							<input type="hidden" name="SubJectID" value="<c:out value="${sub.id}"/>">

							<small>Required</small>
						</p>							
						</div>
						<div style="margin: 10px auto;">
						<h6>Subject Description:</h6>
						<textarea id="SubDesc" name="SubDesc" rows="8" cols="56" ><c:out value="${sub.subDese}"/></textarea>
					</div>
					</div>	
					</c:forEach>
					<div>
						<input type="submit" class="btn_green"  id="submit" value="Update Subject" onclick="return UpdateSubjectvalidate()" >							
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
<script type="text/javascript" charset="utf8" src="Javascript/validation.js"></script>
<SCRIPT TYPE="text/javascript">

	/* Value To submit to database */
	var form = $('#updateSubject');
	form.submit(function() {

		$.ajax({
			type : form.attr('method'),
			url : form.attr('action'),
			data : form.serialize(),
			success : function(data) {
				$("#Status").delay(19000).fadeOut('slow');	
				var result = data;
				$('#Status').html(result);
				closeWindow();
			}
		});
		return false;
	});
	function closeWindow() 
	{
	    setTimeout(function() {
	    window.close();
	    }, 3000);
	}
</SCRIPT>
</html>
