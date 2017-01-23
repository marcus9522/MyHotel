<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ page contentType="text/html; charset=ISO-8859-1" import="java.util.*,utente.UtenteBean"%>
    
    <% String done = (String) request.getAttribute("done");
       UtenteBean bean = (UtenteBean) request.getAttribute("utente");
       String email= (String) session.getAttribute("email");
      %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>ModificaDati</title>
<meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">
    <link href="css/my.css" rel="stylesheet">

    <!-- Bootstrap Core CSS -->
    <link href="css/bootstrap.min.css" rel="stylesheet">

    <!-- Custom CSS -->
    <link href="css/modern-business.css" rel="stylesheet">

    <!-- Custom Fonts -->
    <link href="font-awesome/css/font-awesome.min.css" rel="stylesheet" type="text/css">
  
</head>
<body>
<%if(email == null){ %>
   <script type="text/javascript">
   alert("Devi loggarti per avere accesso a questa pagina")
   history.go(-1)
   </script>
   <%}else{  %>
   <%@ include file ="navprotected.jsp" %>
   <%} %>
<div class="row">
            <div class="col-lg-12">
                <h1 class="page-header">Modifica Dati </h1>
<div class="registrazione" align=center> 
<form action="utente?action=update" name="registrazione" method="post">
<br>
<label for="email">E-MAIL:</label><br> 
		<input name="email" id="irec" type="text" maxlength="30" required value="<%=bean.getEmail() %>" disabled="disabled" ><br>
<label for="password">PASSWORD:</label><br> 
		<input name="password" id="irec" type="password" maxlength="15" value="<%=bean.getPassword() %>" required ><br>
<label for="nome">NOME:</label><br> 
		<input name="nome" id="irec" type="text" maxlength="15" required value="<%=bean.getNome() %>" ><br>
<label for="cognome">COGNOME:</label><br> 
		<input name="cognome" id="irec" type="text" maxlength="15" required value="<%=bean.getCognome() %>"><br>
<label for="data">DATA DI NASCITA:</label><br> 
		<input name="datanascita" id="irec" type="date" value="<%=bean.getDataNascita()%>" max="<%=new java.sql.Date(System.currentTimeMillis()) %>" required ><br>			
		<input name="ruolo" id="irec" type="text" value="normale" hidden = "true"><br>
		<p></p>
   <input id="modifica" type="submit" value="Invia">
  </form>
</div>
</div>
</div>
</body>
</html>