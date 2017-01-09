<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page contentType="text/html; charset=ISO-8859-1" import="java.util.*,camera.CameraBean,carrello.CarrelloBean"%>
    <%String email= (String) session.getAttribute("email");
    Collection<?> camere = (Collection<?>) request.getAttribute("camere");
    Collection<?> carrello = (Collection<?>) request.getAttribute("carrello");%>
<!DOCTYPE html >
<html>
<head>

<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Carrello</title>
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
    <link href="font-awesome/css/font-awesome.min.css" rel="stylesheet" type="text/css">
</head>
<body>


 <%@ include file ="navprotected.jsp" %>
 <%if(carrello!=null) 
 if(carrello.isEmpty()==false){%>
<div class="container">
       
        <div class="row">
            <div class="col-lg-12">
                <h2 class="page-header">Carrello</h2>
            </div>
            <%		
            Iterator<?> it = carrello.iterator();
			Iterator<?> it2 = camere.iterator();
			double totale=0;
			while (it.hasNext()) {
				CarrelloBean cart = (CarrelloBean) it.next();		
				CameraBean camera = (CameraBean) it2.next();
				
					%>
					
					<div class="col-md-4 text-center">
	                <div class="thumbnail">
	                <a href="camera?action=getcamera&numerocamera=<%=camera.getNumeroCamera()%>">
	                    <img class="img-responsive" src="<%=camera.getImmagine()%>" alt="">
	                    </a>
	                    <div class="caption">
	                        <h3>Camera N°<%=camera.getNumeroCamera()%><br>
	                            <small>Prezzo Totale:<%=cart.getTotale()%> &euro;</small>
	                        </h3>
	                        <p>Prezzo Per Notte: <%=camera.getPrezzo() %> &euro;</p>
	                        <p>Prenotata Dal: <%=cart.getDatainizio() %></p>
	                        <p>Al:<%=cart.getDatafine() %></p>
	                        <p>Tipologia:<%=camera.getTipologia() %></p>
	                        <a href="carrello?action=delete&numerocamera=<%=camera.getNumeroCamera()%>">
	                        <button>Elimina</button></a>
	                    </div>
	                </div>
	            </div>
       <%totale+=cart.getTotale(); %>
      <%}  %>
      </div>
<div align=center>
<h3>TOTALE: <%=totale %> &euro;</h1>
<a href="carrello?action=empty"><button>Svuota Carrello</button></a>
</div>
<br>
<div align=center>
<form action="prenotazione?action=insert" method="post" >
<label for="email">INTESTATARIO CARTA:</label> 
		<input name="intcarta" type="text"  required placeholder="Inserisci l'intestatario della carta" >
<label for="email">NUMERO CARTA:</label> 
		<input name="ncarta" type="number" maxlength="9999999999999999" required placeholder="Inserisci il numero della carta" ><br>
<label for="email">DATA SCADENZA CARTA:</label> 
		<input name="date" type="date" min="<%=new java.sql.Date(System.currentTimeMillis()) %>" required placeholder="Inserisci il numero della carta" >
<label for="email">CVV/CVC2:</label> 
		<input name="ncarta" type="number" max=999 required placeholder="CVV/CVC2"" >
</div>
<div align=center>
<input id="carrello" type="submit" value="EFFETTUA PRENOTAZIONE">
</div>
<br>
</form>
<%} %>        
 <%if((carrello==null)||(carrello.isEmpty()==true)) { %>
 <div align = center><h1>Carrello Vuoto</h1></div>
 <%} %>    
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
