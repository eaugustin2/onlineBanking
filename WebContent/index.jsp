<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Login Page</title>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link href="styling.css" rel="stylesheet" type="text/css">
</head>
<body>
<a href="index.jsp"><img src="images/logo.png" alt="logoDesign" id="logo"></a>
	<form action="loginInfo" method="post" class="login">
		<input type="text" placeholder="username" name="username" id="user"> <br>
		<input type="password" placeholder="password" name="password"> <br>
		<p><a href="forgot.jsp">Forgot your Username/Password?</a></p>
		<p>Don't have an account? <a href="signUp.jsp">sign up</a></p>
		<input type="submit" value="Login" class="submit">
	</form>
</body>
</html>