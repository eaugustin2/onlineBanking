<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Forgot Login Details</title>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link href="styling.css" rel="stylesheet" type="text/css">
</head>
<body>

	<a><img src="images/logo.png" alt="logoDesign" id="logo"></a>
	<hr>
	
	<form action="forgotLogin" method="post" class="login">
	<input type="text" placeholder="First Name" name="fname"> <br>
	<input type="text" placeholder="Last Name" name="lname"> <br>
	<input type="text" placeholder="YYY-MM-DD" name="dob"><br>
	<input type="submit" value="Continue" class="submit">
	</form>
</body>
</html>