<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@ page contentType="text/html; charset=ISO-8859-1" import="java.util.*,utente.UtenteBean"%>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<%String ruolo= (String) session.getAttribute("ruolo");%>
<link rel="stylesheet" type="text/css" href="css/index.css">
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>MyHotel</title>
</head>
<body>
<header>
		<img src="foto/LogoA.png" alt="Logo" height="300" width="300">
		<span id="header">MyHotel, il sito di prenotazione di soggiorni più
						utilizzato. </span>
	
</header>
<%if(ruolo==null){%>
<%@ include file="nav.jsp" %>
<%} 
 else if(ruolo.equals("user")){%>
<%@ include file="navprotected.jsp" %>
<%} 
 else if(ruolo.equals("admin")){%>
<%@ include file="navadmin.jsp" %>
<%}  %>
 
    <div id="filtro" align="center">
    <p>Filtra le camere disponibili</p>
    </div>
    <form action="camera?action=filtra" method="post">
    <div class=filtracamere align=center>
    <div align=center><label>TIPOLOGIA:</label>
		<select name="tipologia">
		<option value="Tutte">Tutte</option>
         <option value="Singola">Singola</option>
         <option value="Doppia">Doppia</option>
	     <option value="Suite">Suite</option>	     
	     </select><br></div>
    <div align=center><label>PREZZO MINIMO:&nbsp&nbsp&nbsp</label> 
		<input name="min"  type="number" min=0 value=0 ><br></div>
<div align=center><label>PREZZO MASSIMO:</label> 
		<input name="max"  type="number" min=0 value=0><br>
		<div align=center><label>ORDINA PER:</label>
		<select name="order">
		<option value="NUMEROCAMERA">Numerocamera</option>
		<option value="prezzo">Prezzo</option>
         <option value="tipologia">Tipologia</option>     
	     </select><br></div>
  <div align=center><input id="cerca" type="submit" value="Cerca"></div></div>
  </div>
  </form>
    </body>
</html>