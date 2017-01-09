<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page contentType="text/html; charset=ISO-8859-1" import="java.util.*,camera.CameraBean,prenotazione.PrenotazioneBean"%>    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<%Collection<?> camere = (Collection<?>) request.getAttribute("camere");
Collection<?> prenotazioniall = (Collection<?>) request.getAttribute("prenotazioniall");
Collection<?> prenotazioni = (Collection<?>) request.getAttribute("prenotazioni");%>
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
<title>Gestisci Prenotazioni</title>
</head>
<body>
<%@ include file ="navadmin.jsp" %>
<%if(prenotazioniall!=null){ %>
<script src="javascript/info.js"></script>
<div align="center">
<select onChange="info(this.value)">
<option> Scegli un utente </option>
  <%Iterator<?> it = prenotazioniall.iterator();
     while(it.hasNext()){
     PrenotazioneBean bean = (PrenotazioneBean) it.next();%>
     	
  <option value="<%=bean.getEmail()%>"> <%=bean.getEmail()%></option>
  
<%} } %>
</select>
</div>
<%if (prenotazioni != null && prenotazioni.size() != 0) { 
Iterator<?> it = prenotazioni.iterator();
Iterator<?> it2 = camere.iterator();
Iterator<?> it3 = prenotazioni.iterator();
PrenotazioneBean email = (PrenotazioneBean) it3.next();		%>
<div class="container">
        <!-- Page Heading/Breadcrumbs -->
        <div class="row">
            <div class="col-lg-12">
                <h1 class="page-header">Le Prenotazioni di <%=email.getEmail() %>
                </h1>
            </div>
        </div>
            <%		
				while (it.hasNext()) {
					PrenotazioneBean prenotazione = (PrenotazioneBean) it.next();		
					CameraBean camera = (CameraBean) it2.next();
					%>
					
					<div class="col-md-4 text-center">
	                <div class="thumbnail">
	                <a href="camera?action=getcamera&numerocamera=<%=camera.getNumeroCamera()%>">
	                    <img class="img-responsive" src="<%=camera.getImmagine()%>" alt="">
	                    </a>
	                    <div class="caption">
	                        <h3><%=camera.getTipologia()%><br>
	                        <small>Id Prenotazione: <%=prenotazione.getIdprenotazione() %></small>
	                        </h3>
	                        <p>Prenotata dal : <%=prenotazione.getDatainizio() %></p>
	                        <p>Al : <%=prenotazione.getDatafine() %></p>
	                        <p>Prezzo Pagato: <%=prenotazione.getTotale()%> &euro;</p>
	                    </div>
	                </div>
	            </div>
	            <%} } else{ %>
              <div align="center"><h1>SCEGLI UN UTENTE</h1></div>
              <%} %>
        <hr>
      

    </div>
    <!-- /.container -->

    <!-- jQuery -->
    <script src="js/jquery.js"></script>

    <!-- Bootstrap Core JavaScript -->
    <script src="js/bootstrap.min.js"></script>

</body>

</html>