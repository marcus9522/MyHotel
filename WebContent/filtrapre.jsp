<html>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
 <head>    
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  
<title>Filtra Prenotazioni</title>
</head>
<body>
<div class="filtra" align=center  >
<form action="prenotazione?action=filtra" method="post" >
<label for="nome">MIN:</label>
<input type="num" name="totalemin" required  min=0  value=0 width="5%" > 
<label for="nome">MAX:</label>
<input type="num" name="totalemax" required  value=0 width="5%" > 
<small>Periodo: </small>
<select name="periodo">
         <option value="TUTTI">Tutto</option>
	     <option value="INCORSO">In Corso</option>
	     <option value="TERMINATE">Terminate</option>
	     <option value="FUTURE">Future</option>
	     </select>
  <small>Ordina per: </small>
	     <select name="order">
	     <option value="NUMEROCAMERA">Numerocamera</option>
	     <option value="TOTALE">Totale</option>
	     <option value="DATAINIZIO">Datainizio</option>
	     <option value="DATAFINE">Datafine</option>
	     </select>	     

<input id="filtra" type="submit" value="Filtra">
<br>
</form>
</div>
</body>
</html>
