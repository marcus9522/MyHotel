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
  
<title>Filtra Camere</title>
</head>
<body>
<div class="filtra" align=center  >
<form action="camera?action=filtra" method="post" >
<label for="nome">MIN:</label>
<input type="number" name="min" required  min=0  value=0 width="5%" > 
<label for="nome">MAX:</label>
<input type="number" name="max" required  value=0 width="5%" > 
<small>Tipologia: </small>
<select name="tipologia">
         <option value="Tutte">Tutte</option>
	     <option value="Singola">Singola</option>
	     <option value="Doppia">Doppia</option>
	     <option value="Suite">Suite</option>
	     </select>
  <small>Ordina per: </small>
	     <select name="order">
	     <option value="NUMEROCAMERA">Numerocamera</option>
	     <option value="Tipologia">Tipologia</option>
	     <option value="Prezzo">Prezzo</option>
	     </select>

<input id="filtra" type="submit" value="Filtra">
<br>
</form>
</div>
</body>
</html>
