<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@ page contentType="text/html; charset=ISO-8859-1" import="java.util.*,utente.UtenteBean"%>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<% String done = (String) request.getAttribute("done");%>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
 <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">

    <!-- Bootstrap Core CSS -->
    <link href="css/bootstrap.min.css" rel="stylesheet">

    <!-- Custom CSS -->
    <link href="css/modern-business.css" rel="stylesheet">

    <!-- Custom Fonts -->
    <link rel="stylesheet" type="text/css" href="css/index.css">
    
    <link href="font-awesome/css/font-awesome.min.css" rel="stylesheet" type="text/css">
<title>Registrazione</title>
</head>
<body>
<%@ include file="nav.jsp" %>
<div id="regD" align="left">
Benvenuti nella sezione relativa alla registrazione!<br>
Una volta compilato correttamente il form sarete <br>
inscritti alla piattaforma e potrete godere di tutti<br> 
i privilegi di essere un cliente di MyHotel!
</div>
<div class="registrazione" align=center> 
<form action="utente?action=registrazione" name="registrazione" method="post">
<br>
<h3></h3>
<label for="email">E-Mail:</label><br> 
		<input name="email" id="irec" type="text" maxlength="40" required placeholder="Inserisci e-mail" ><br>
<label for="password">Password:</label><br> 
		<input name="password" id="irec" type="password" maxlength="20" required placeholder="Inserisci password"><br>
		<input name="ruolo" id="irec" type="text" required value="normale" hidden="true">
<label for="nome">Nome:</label><br> 
		<input name="nome" id="irec" type="text" maxlength="20" required placeholder="Inserisci il tuo nome"><br>
<label for="cognome">Cognome:</label><br> 
		<input name="cognome" id="irec" type="text" maxlength="20" required placeholder="Inserisci il tuo cognome"><br>
<label for="data">Data Di Nascita</label><br>
<input name="datanascita" id="irec" type="date" max="<%=new java.sql.Date(System.currentTimeMillis()) %>" required ><br>		
		<p></p>
   <input id="registra" type="submit" value="Invia">
  <input id="registra" type ="reset" value="Reset"/>	 
  </form>
</div>
</html>