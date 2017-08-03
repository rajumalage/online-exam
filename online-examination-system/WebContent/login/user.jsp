<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Online Examination System</title>
<link href="../css/Style.css" rel="stylesheet">
</head>
<body>

	<!-- header -->
	<h2 >Online <span class="logo_title1">Examination</span><span class="logo_title2"> System</span></h2>
	<h3>c</h3>
	<!-- body -->
<div class="main">
	<div class="inputform_left">
		<h2 class="design">Registration Form</h2>
		<form action="RegisterServlet" method="post">
			<table cellspacing="10">
				<tr >
					<td>Name:</td>
					<td><input class="Input_height" type="text" name="name" /></td>
				</tr>
				<tr >
					<td>Email:</td>
					<td><input  class="Input_height" type="email" name="email" /></td>
				</tr>
				<tr>
					<td>Password:</td>
					<td><input class="Input_height" type="password" name="password" /></td>
				</tr>
				<tr>
					<td>Gender:</td>
					<td><input  id="gender1" type="radio" name="gender"
						value="male" />Male <input type="radio" id="gender2" name="gender"
						value="female" />Female</td>
				</tr>
				<tr>
					<td>D.O.B:</td>
					<td><input class="Input_height" type="date" name="dob" /></td>
				</tr>
				<tr>
					<td style="vertical-align: top">AddressLine:</td>
					<td><textarea name="addressLine" rows="5" cols="15"></textarea></td>
				</tr>
				<tr>
					<td>City:</td>
					<td><input class="Input_height" type="text" name="city" /></td>
				</tr>
				<tr>
					<td>State:</td>
					<td><input class="Input_height" type="text" name="state" /></td>
				</tr>
				<tr>
					<td>Country:</td>
					<td><select class="Input_height" name="country">
							<option>Select-Country:</option>
							<option>India</option>
							<option>Pakistan</option>
							<option>China</option>
							<option>Bhutan</option>
							<option>USA</option>
							<option>France</option>
							<option>Other</option>
					</select></td>
				</tr>
				<tr>
					<td>Contact:</td>
					<td><input class="Input_height" type="text" name="contact" /></td>
				</tr>
				<tr>
					<td colspan="2"><input id="submit" type="submit"
						value="register" /> <input id="reset" type="reset" value="clear" />
					</td>
				</tr>
			</table>
		</form>
	</div>

	<div class="inputform" style="float: right;">
		<h2 class="design">Login Form</h2>
		<form action="LoginServlet" method="post">
			<table cellspacing="10">
				<tr>
					<td>Email:</td>
					<td><input class="Input_height" type="email" name="email" /></td>
				</tr>
				<tr>
					<td>Password:</td>
					<td><input class="Input_height" type="password" name="password" /></td>
				</tr>
				<tr>
					<td colspan="2"><input id="submit" type="submit" value="login" />
					</td>
				</tr>
			</table>
		</form>
	</div>
</div>	
	<div class="footer">
		
		<p>Copyright &copy; 2016 Online Examination System . Built by 
		<a href="http://24technologies.com">24Technologies</a>. 
		All Rights Reserved</p>
		<h3>12</h3>
	</div>
</body>
</html>