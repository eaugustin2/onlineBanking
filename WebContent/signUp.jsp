<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Sign Up Page</title>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link href="styling.css" rel="stylesheet" type="text/css">
</head>
<body>

	<a href="index.jsp"><img src="images/logo.png" alt="logoDesign" id="logo"></a>
	<hr>
	<form action="signUp" method="post" class="login">
		<input type="text" placeholder="First Name" name="fname"> <br>
		<input type="text" placeholder="Last Name" name="lname"> <br>
		<input type="text" placeholder="Email" name="email"> <br>
		<input type="text" placeholder="Username" name="username"> <br>
		<input type="password" placeholder="Password" name="password"> <br>
		<input type="password" placeholder="Verify password" name="vpassword"> <br>
		<input type="text" placeholder="Phone: xxx-xxx-xxxx" name="phone"> <br>
		<input type="text" placeholder="YYYY-MM-DD" name="dob"> <br>
		
		<input type="submit" value="Sign Up" class="submit">
		
		<p>Already have an account? <a href="index.jsp">Log in</a> </p>
	</form>
	
	
</body>
</html>