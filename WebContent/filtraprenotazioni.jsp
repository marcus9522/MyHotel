<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page contentType="text/html; charset=ISO-8859-1" import="java.util.*,camera.CameraBean,prenotazione.PrenotazioneBean"%>
    <%String ruolo= (String) session.getAttribute("ruolo");
Collection<?> camere = (Collection<?>) request.getAttribute("camere");
Collection<?> prenotazioni = (Collection<?>) request.getAttribute("prenotazioni");%>
<!DOCTYPE html >
<html>
<head>

<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Filtra Prenotazioni</title>
<head>

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
<%if(ruolo==null){%>
<%@ include file="nav.jsp" %>
<%} 
 else if(ruolo.equals("user")){%>
<%@ include file="navprotected.jsp" %>
<%} 
 else if(ruolo.equals("admin")){%>
<%@ include file="navadmin.jsp" %>
<%}  %>
<div class="container">
           <h2 class="page-header">Filtra</h2>
           <%@ include file ="filtrapre.jsp" %>
       

        <!-- Team Members -->
        <div class="row">
            <div class="col-lg-12">
                <h2 class="page-header">Prenotazioni</h2>
            </div>
            <%
			if (prenotazioni != null && prenotazioni.size() != 0) {
				Iterator<?> it = prenotazioni.iterator();
				Iterator<?> it2 = camere.iterator();
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
	                        <h3>Camera N°<%=camera.getNumeroCamera()%><br>
	                            <p>Totale Pagato <%=prenotazione.getTotale()%> &euro;</p>
	                        </h3>
	                        <p>Prenotata Da <%=prenotazione.getEmail()%></p>
	                        <p>Dal <%=prenotazione.getDatainizio() %></p>
	                        <p>Al <%=prenotazione.getDatafine() %></p>
	                    </div>
	    		</div>
	    		</div>
	            <%}%>
	            <%}else{ %>
	            <div align=center><h1>Nessuna prenotazione rispecchia i parametri inseriti</h1></div>
	            <%} %>
	            
           </div>
        <!-- /.row -->

        <hr>

        <!-- Footer -->
        <footer>
            <div class="row">
                <div class="col-lg-12">
                    <p>Copyright &copy; MyHotel 2016</p>
                </div>
            </div>
        </footer>

    </div>
    <!-- /.container -->

    <!-- jQuery -->
    <script src="js/jquery.js"></script>

    <!-- Bootstrap Core JavaScript -->
    <script src="js/bootstrap.min.js"></script>

</body>

</html>
