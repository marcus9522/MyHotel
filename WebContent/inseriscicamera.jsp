<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@ page contentType="text/html; charset=ISO-8859-1" import="java.util.*,servizio.ServizioBean"%>
<%Collection<?> servizi = (Collection<?>) request.getAttribute("servizi"); 
%>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Inserisci Nuova Camera</title>

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
    <link href="font-awesome/css/font-awesome.min.css" rel="stylesheet" type="text/css">
</head>
<body>
   <%@ include file ="navadmin.jsp" %>
       <script src="javascript/info.js"></script>
   
<div class="nuovacamera" align=center> 
<form action="camera?action=insert" name="registrazione" method="post">
<br>
<div class="row">
            <div class="col-lg-12">
                <h1 class="page-header">Inserisci Nuova Camera </h1>
<label >NumeroCamera:</label><br> 
<input name="numerocamera" type="number" required  ><br>
<label>Prezzo:</label><br> 
<input name="prezzo" type="number"  required ><br>
<label>Tipologia:</label><br> 
<input  type="radio" name="tipologia" value="Singola" checked> Singola<br>
<input  type="radio" name="tipologia" value="Doppia"> Doppia<br>
<input  type="radio" name="tipologia" value="Suite"> Suite<br>
<label>Descrizione:</label><br> 
<textarea name="descrizione"  type="text" maxlength="200" rows="4" cols="60"  required placeholder="Inserisci una descrizione"></textarea><br>

<label>Immagine</label><br>
<input name="immagine" id="immagine" type="text" placeholder="Inserisci il link dell'immagine" required ><br>
<label>Servizi Disponibili</label><br>
<% if(!servizi.isEmpty()){
Iterator<?> it = servizi.iterator(); 
  while(it.hasNext()){
	  ServizioBean bean = (ServizioBean) it.next();
  %>
  <input type="checkbox" name=servizi value=<%=bean.getNome() %>><%=bean.getNome().toUpperCase()	%>
  <%} } %>
<br>
   <input id="registra" type="submit" value="Invia">
  </form>
</div>
</body> 
<script>
var site = window.location.href
if(site.indexOf("done=no")!=-1) {
alert("Numero camera già presente")
history.go(-1);
}
</script>
</html>