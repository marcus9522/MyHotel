<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page contentType="text/html; charset=ISO-8859-1" import="java.util.*,camera.CameraBean,servizio.ServizioBean"%>
    
<!DOCTYPE html >
<html>
<head>
<%String email= (String) session.getAttribute("email");
String ruolo= (String) session.getAttribute("ruolo");
CameraBean camera = (CameraBean) request.getAttribute("camera");
Collection<?> servizi = (Collection<?>) request.getAttribute("servizi");%>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Camera &nbsp <%=camera.getNumeroCamera()%></title>
<head>

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
    <link rel="stylesheet" type="text/css" href="css/index.css">
    <link href="font-awesome/css/font-awesome.min.css" rel="stylesheet" type="text/css">
</head>
<body>
<script type="text/javascript">
var site = window.location.href
if(site.indexOf("add=yes")!=-1) {alert("Prodotto aggiunto al carrello")}
</script>
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

        <!-- Page Heading/Breadcrumbs -->
        <div class="row">
            <div class="col-lg-12">
                <h1 class="page-header">Camera
                    <small><%=camera.getNumeroCamera() %></small>
                </h1>
            </div>
        </div>
        <!-- /.row -->

        <!-- Intro Content -->
        <div class="row">
            <div class="col-md-6">
                <img class="img-responsive" src="<%= camera.getImmagine() %>" alt="">
            </div>
            <div class="col-md-6">
                <h2><%= camera.getTipologia()%></h2>
                <p><%=camera.getDescrizione() %>  </p>
               <br>
                <b><p>PREZZO PER NOTTE: <%=camera.getPrezzo()%> &euro;</p></b>
            </div>
            <%if (ruolo==null) {%>
	                    <div align="center"><a href="/login.jsp"><button>Effettua Il Login Per Prenotare Questa Camera</button></a></div>
	                    <%}else if (ruolo.equals("user")){  %>
	                    <form action="" method = "post">
	                    <label for="data">DATA INIZIO PRENOTAZIONE:</label> 
		                <input name="datainizio" type="date" value =<%=new java.sql.Date(System.currentTimeMillis()) %>   required ><br>			
		                <label for="data">DATA FINE PRENOTAZIONE:</label> 
		                <input name="datafine" type="date" required ><br>			
		                </form>
		                <label for ="servizi">SERVIZI DISPONIBILI:</label><br>
		                <% Iterator<?> it = servizi.iterator();
		                while (it.hasNext()) {
					ServizioBean servizio = (ServizioBean) it.next();%>
					<input type="checkbox" name="servizio" value= "<%=servizio.getNome()%>"><%=servizio.getNome() %>
					<%} %>
	                    <div align="center"><a href="login.jsp"><button>Effettua Prenotazione</button></a></div>
	                    <%} else if(ruolo.equals("admin")){ %>
	                    <%} %>
        </div>
        <!-- /.row -->

        

        <hr>
        

    </div>
    <!-- /.container -->

    <!-- jQuery -->
    <script src="js/jquery.js"></script>

    <!-- Bootstrap Core JavaScript -->
    <script src="js/bootstrap.min.js"></script>

</body>

</html>
