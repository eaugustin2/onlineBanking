<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width = device-width, initial-scale=1.0">
<title>Dashboard</title>
<link href="styling.css" rel="stylesheet" type="text/css">
</head>
<body>

<%
response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
response.addHeader("Cache-Control", "post-check=0, pre-check=0");
response.setHeader("Pragma", "no-cache");
response.setDateHeader("Expires", 0);
%>

	<h1>Hello, ${userLogin.fname }</h1> 
	
	
	 <br>
	 
	 <h3>Current Balance: <span id="moneyAmount">${userLogin.balance }</span> </h3>
	
	
	<div id="withdrawWrapper">Withdraw
		<div id="withdrawComponents">
			<form action="balanceUpdate" method="post">
				<label>Please enter an amount: </label>
				<input type="text" name="withdrawAmount" id="withdrawInput">
				<input type="submit" value="Withdraw" id="withdrawButton" class="submit">
			</form>
		</div>
	</div>
	
	<br>
	
	<div id="depositWrapper">Deposit
		<div id="depositComponents">
			<form action="balanceUpdate" method="post">
				<label>Please enter an amount: </label>
				<input type="text" name="depositAmount" id="depositInput">
				<input type="submit" value="Deposit" id="depositButton" class="submit">
			</form>
		</div>
	</div>
	<br>
	
	<form action="logOut" method="post">
	<input type="submit" value="logout" class="submit">
	</form>
	
	<script src="functionality.js"></script>
</body>
</html>