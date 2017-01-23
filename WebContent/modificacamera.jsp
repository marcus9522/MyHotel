<%@page import="com.sun.javafx.fxml.BeanAdapter"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page contentType="text/html; charset=ISO-8859-1" import="java.util.*,camera.CameraBean,servizio.ServizioBean"%>    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<%Collection<?> camere = (Collection<?>) request.getAttribute("camere");
CameraBean camera = (CameraBean) request.getAttribute("camera");
String ruolo = (String) session.getAttribute("ruolo");
%>
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
<title>Gestisci Camere</title>
</head>
<body>
<%@ include file ="navadmin.jsp" %>
<%if(camere!=null){ %>
<script src="javascript/info.js"></script>
<div align="center">
<select onChange="info3(this.value)">
<option> Scegli una camera </option>
  <%Iterator<?> it = camere.iterator();
     while(it.hasNext()){
     CameraBean bean = (CameraBean) it.next();%>
     	
  <option value="<%=bean.getNumeroCamera()%>">CameraNumero <%=bean.getNumeroCamera()%></option>
  
<%} } %>
</select>
</div>
<%if(camera!=null){ %>
<div class="row">
            <div class="col-lg-12">
                <h1 class="page-header">Informazioni sulla camera N° <%=camera.getNumeroCamera()%> </h1>
<div class="registrazione" align=center> 
<form action="camera?action=update" name="modificacamera" method="post">
<br>
<input name="numerocamera" type="number" required value ="<%=camera.getNumeroCamera()%>" hidden="true" ><br>
<label>Prezzo:</label><br> 
<input name="prezzo" type="number"  required value="<%=camera.getPrezzo() %>" ><br>
<label>Tipologia:</label><br> 
<input  type="radio" name="tipologia" value="Singola" checked> Singola<br>
<input  type="radio" name="tipologia" value="Doppia"> Doppia<br>
<input  type="radio" name="tipologia" value="Suite"> Suite<br>
<label>Descrizione:</label><br> 
<textarea name="descrizione"  type="text" maxlength="200" rows="4" cols="60"  required ><%=camera.getDescrizione()%></textarea><br>
<label>Immagine</label><br>
<input name="immagine" id="immagine" type="text" value="<%=camera.getImmagine()%>" required><br>
<input id="ord" type="submit" value="Modifica Camera"><br>
</form>
<a href="camera?action=delete&numerocamera=<%=camera.getNumeroCamera()%>"><button>Elimina Camera</button></a>
  </div>
</div>
</div>
<%} %>
        <hr>
      
    <!-- /.container -->

    <!-- jQuery -->
    <script src="js/jquery.js"></script>

    <!-- Bootstrap Core JavaScript -->
    <script src="js/bootstrap.min.js"></script>

</body>

</html>