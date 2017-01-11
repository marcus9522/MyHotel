<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page contentType="text/html; charset=ISO-8859-1" import="java.util.*,utente.UtenteBean"%>
<%String ruolo = (String) session.getAttribute("ruolo");
String email = (String) session.getAttribute("email");
  String esito = (String) request.getAttribute("esito");%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Area Amministratore</title>
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
    <link rel="stylesheet" type="text/css" href="css/index.css">
    
<script type="text/javascript">
var site = window.location.href
if(site.indexOf("esito=yes")!=-1) {alert("Operazione effettuata correttamente")}
</script>
</head>
<body>

   <%@ include file ="navadmin.jsp" %>
<div class="container">

        <!-- Page Heading/Breadcrumbs -->
        <div class="row">
            <div class="col-lg-12">
                <h1 class="page-header">Area Amministratore
                    <small>MyHotel</small>
                </h1>
            </div>
        </div>
        <!-- /.row -->

        <!-- Team Members -->
            <div class="col-md-4 text-center">
                <div class="thumbnail">
                    <a href="prenotazioniadmin.jsp">
                    <img class="img-responsive" src="https://cdn2.iconfinder.com/data/icons/clipboard-2/100/clipboard2-512.png" alt="">
                    </a>
                    <div class="caption">
                        <h3>Gestione Prenotazioni<br>
                        </h3>
                        <p>In questa sezione potrai visualizzare lo storico prenotazioni effettuate dagli utenti</p>
                        </ul>
                    </div>
                </div>
            </div>
            <div class="col-md-4 text-center">
                <div class="thumbnail">
                   <a href="gestiscicamere.jsp">
                    <img class="img-responsive" src="foto/edit1600.png" alt="">
                    </a>
                    <div class="caption">
                        <h3>Gestione Camere<br>
                        </h3>
                        <p>In questa sezione potrai gestire le camere presenti nel database</p>
                    </div>
                </div>
            </div>
            <div class="col-md-4 text-center">
                <div class="thumbnail">
                    <a href="utente?action=getusers&email=<%=email%>">
                    <img class="img-responsive" src="foto/right-red-x.png" alt="">
                    </a>
                    <div class="caption">
                        <h3>Gestione Utenti<br>
                        </h3>
                        <p>In questa sezione potrai gestire gli utenti. </p>
                    </div>
                </div>
            </div>
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
