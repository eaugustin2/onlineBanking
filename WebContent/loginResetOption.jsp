<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Reset Option</title>
<meta name="viewport" content="width=device-width initial-scale = 1.0">
<link href="styling.css" rel="stylesheet" type="text/css">
</head>
<body>

	<a><img src="images/logo.png" alt="logoDesign" id="logo"></a>
	<hr>
	
	<form action="resetOptionHandler" method="post" class="login">
		<input type="radio" name="reset" value="email"> <p>Retrieve account information with email</p> <br>
		<input type="submit" value="Continue" class="submit">
	</form>
</body>
</html>