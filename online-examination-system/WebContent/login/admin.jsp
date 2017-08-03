<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>

<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>Online Examination SysTem</title>

<link href="../css/style_userLogin.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="Javascript/jquery.min.js"></script>
<script type="text/javascript">
$(document).ready(function() {
	$(".username").focus(function() {
		$(".user-icon").css("left","-48px");
	});
	$(".username").blur(function() {
		$(".user-icon").css("left","0px");
	});
	
	$(".password").focus(function() {
		$(".pass-icon").css("left","-48px");
	});
	$(".password").blur(function() {
		$(".pass-icon").css("left","0px");
	});
});

function userLogin()
{
//	alert("HI I AM FROM USERLOGIN");
	var myForm = document.forms[0];
	var username = myForm.AuserName;
	if(username.value.length == 0)
		{
			alert("Please enter UserName");
			username.focus();
			return false;
		}
	var password = myForm.ApassWord;		
	if(password.value.length == 0)
	{
		alert("Please enter Password");
		password.focus();
		return false;
	}	
}
</script>

</head>
<body>

<!--WRAPPER-->
<div id="wrapper">

	<!--SLIDE-IN ICONS-->
    <div class="user-icon"></div>
    <div class="pass-icon"></div>
    <!--END SLIDE-IN ICONS-->

<!--LOGIN FORM-->
<form name="login-form" class="login-form" action="../AdminLogin.htm" method="post">

	<!--HEADER-->
    <div class="header">
    <!--TITLE--><h1>Admin Login Form</h1><!--END TITLE-->
    <!--DESCRIPTION-->
    <c:if test="${MsgStatus == 1}" >
			<span>Please Enter Valid Credentials.The password is case sensitive.</span>
	</c:if>
	<c:if test="${MsgStatus == 0}" >
			<span style="color:#D21010;font-size: 12px;"><c:out value="${Massage}"></c:out></span>
	</c:if>
    </div>
    <!--END HEADER-->	
	<!--CONTENT-->
    <div class="content">
   
	<input name="AuserName" type="text" class="input username" value="username" onfocus="this.value=''" />
    <input name="ApassWord" type="password" class="input password" value="password"  onfocus="this.value=''" />
    </div>
    <!--END CONTENT-->
    
    <!--FOOTER-->
    <div class="footer">
    <!--LOGIN BUTTON--><input type="submit" name="submit" value="Login" class="button" onclick="return userLogin()"/><!--END LOGIN BUTTON-->
    <!--REGISTER BUTTON--><!--END REGISTER BUTTON-->
    </div>
    <!--END FOOTER-->

</form>
<!--END LOGIN FORM-->

</div>
<!--END WRAPPER-->

<!--GRADIENT--><div class="gradient"></div><!--END GRADIENT-->

</body>
</html>
