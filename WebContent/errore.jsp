<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%String ruolo= (String) session.getAttribute("ruolo");%>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Errore</title>
</head>
<body>
<img src="foto/LogoA.png" width="300" height="300">
<h1 align="center">Errore, pagina non trovata.</h1>
<%if(ruolo==null){%>
<%@ include file="nav.jsp" %>
<%} 
 else if(ruolo.equals("user")){%>
<%@ include file="navprotected.jsp" %>
<%} 
 else if(ruolo.equals("admin")){%>
<%@ include file="navadmin.jsp" %>
<%}  %>
</body>
</html>