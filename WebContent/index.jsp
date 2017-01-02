<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
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
<%@ include file="nav.jsp" %>
 
    <div id="filtro" align="center">
    <p>Filtra le camere disponibili</p>
    </div>
    <form action="">
    <div class=filtracamere align=center>
    <div align=center><label>TIPOLOGIA:</label>
		<select name="tipologia">
         <option value="Singola">Singola</option>
         <option value="Doppia">Doppia</option>
	     <option value="Suite">Suite</option>	     
	     </select><br></div>
    <div align=center><label>PREZZO MINIMO:</label> 
		<input name="prezzomin"  type="number" min=0  ><br></div>
<div align=center><label>PREZZO MASSIMO:</label> 
		<input name="prezzomax"  type="number" min=0 ><br>
  <div align=center><input id="cerca" type="submit" value="Cerca"></div></div>
  </div>
  </form>
    </body>
</html>