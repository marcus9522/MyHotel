<html>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
 <head>    
  <% String order = (String) request.getAttribute("order");
     String tipologia = (String) request.getAttribute("tipologia");
     int min = (Integer) request.getAttribute("min");
     int max = (Integer) request.getAttribute("max");
  %>
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  
<link rel="stylesheet" type="text/css" href="CSS/my.css">
<title>Filtra Camere</title>
</head>
<body>
<div class="filtra" align=center  >
<form action="camera?action=filtra" id="search" method="post" >
<label for="nome">MIN:</label>
<input type="num" name="min" required  min=0  value=0 width="5%" > 
<label for="nome">MAX:</label>
<input type="num" name="max" required  value=0 width="5%" > 
<small>Tipologia: </small>
<select name="tipologia">
         <option value="">Tutte</option>
	     <option value="Singola">Singola</option>
	     <option value="Doppia">Doppia</option>
	     <option value="Tripla">Tripla</option>
	     </select>
  <small>Ordina per: </small>
	     <select name="order">
	     <option value="Tipologia">Tipologia</option>
	     <option value="Prezzo">Prezzo</option>
	     </select>

<input id="filtra" type="submit" value="Filtra">
<br>
</form>
</div>
</body>
</html>
