<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">

    <!-- Custom CSS -->
    <link href="css/modern-business.css" rel="stylesheet">

    <!-- Custom Fonts -->
    <link href="font-awesome/css/font-awesome.min.css" rel="stylesheet" type="text/css">
        <link rel="stylesheet" type="text/css" href="css/index.css">
    
<title>Login</title>
</head>
<body>
		<img src="foto/LogoA.png" alt="Logo" height="200" width="200">
<%@ include file="nav.jsp" %>
<p id="loginP" align=center>Benvenuti nella sezione relativa al login.<br> Una volta effettuato l'accesso
potrete godere di ogni servizio di questa piattaforma.<br> Grazie per averci scelto! </p>
<br><br><br>
<div align="center">
	<form action="" method=" ">
		<label><b>Email&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp</b></label>
    <input id="login" type="text" placeholder="Enter email" name="email" required>
	<br>
    <label><b>Password&nbsp</b></label>
    <input id="Login" type="password" placeholder="Enter Password" name="password" required>
     <br>
    <button type="submit">Login</button>
    <button type="reset">Reset</button>
</form>
</div>
<br><br>
<p id="loginP" align=center>Se non sei ancora registrato affrettati per usufruire di ogni
	nostro servizio! <a href="registrazione.jsp">Clicca qui!</a></p>
</body>
</html>