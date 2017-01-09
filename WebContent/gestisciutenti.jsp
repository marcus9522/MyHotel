<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page contentType="text/html; charset=ISO-8859-1" import="java.util.*,utente.UtenteBean"%>    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<%Collection<?> utenti = (Collection<?>) request.getAttribute("utenti");
UtenteBean utente = (UtenteBean) request.getAttribute("utente");
String email = (String) session.getAttribute("email");%>
<head>
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
<title>Gestisci Utenti</title>
</head>
<body>
<%@ include file ="navadmin.jsp" %>
<%if(utenti!=null){ %>
<script src="javascript/info.js"></script>
<div class="style-select slate" align="center">
<select onChange="info2(this.value)">
<option> Scegli un utente </option>
  <%Iterator<?> it = utenti.iterator();
     while(it.hasNext()){
     UtenteBean bean = (UtenteBean) it.next();%>
     	
  <option value="<%=bean.getEmail()%>"> <%=bean.getEmail()%></option>
  
<%} } %>
</select>
</div>
<div class="row">
            <div class="col-lg-12">
                <h1 class="page-header">Dati di <%=utente.getNome()%> <%=utente.getCognome() %> </h1>
<div class="registrazione" align=center> 
<form action="utente?action=update" name="registrazione" method="post">
<br>
<label for="email">E-MAIL:</label><br> 
		<input name="email" id="irec" type="text" maxlength="30" required value="<%=utente.getEmail() %>" disabled="disabled" ><br>
<label for="password">PASSWORD:</label><br> 
		<input name="password" id="irec" type="password" maxlength="15" value="<%=utente.getPassword() %>" disable="disabled" required ><br>
<label for="nome">NOME:</label><br> 
		<input name="nome" id="irec" type="text" maxlength="15" required value="<%=utente.getNome() %>" ><br>
<label for="cognome">COGNOME:</label><br> 
		<input name="cognome" id="irec" type="text" maxlength="15" required value="<%=utente.getCognome() %>"><br>
<label for="data">DATA DI NASCITA:</label><br> 
		<input name="datanascita" id="irec" type="date" value="<%=utente.getDataNascita()%>" max="<%=new java.sql.Date(System.currentTimeMillis()) %>" required ><br>			
		<input name="ruolo" id="irec" type="text" value="<%=utente.getRuolo() %>" hidden = "true"><br>
		<p></p>
   <input id="registra" type="submit" value="Invia">
  </form>
  <div align = "center">
  <%if(email.equals(utente.getEmail()) == false){ %>
  <a href="utente?action=delete&email=<%=utente.getEmail()%>"><button>Elimina Utente</button></a>
  <%} %>
  </div>
</div>
</div>
</div>
        <hr>
      
    <!-- /.container -->

    <!-- jQuery -->
    <script src="js/jquery.js"></script>

    <!-- Bootstrap Core JavaScript -->
    <script src="js/bootstrap.min.js"></script>

</body>

</html>