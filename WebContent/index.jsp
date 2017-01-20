<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
  <%@ page contentType="text/html; charset=ISO-8859-1" import="java.util.*"%>
    
    <%String ruolo= (String) session.getAttribute("ruolo");%>
<!DOCTYPE html>
<html lang="en">

<head>

    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>MyHotel</title>

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
    <!-- Header Carousel -->
    <header id="myCarousel" class="carousel slide">
        <!-- Indicators -->
        <ol class="carousel-indicators">
            <li data-target="#myCarousel" data-slide-to="0" class="active"></li>
            <li data-target="#myCarousel" data-slide-to="1"></li>
            <li data-target="#myCarousel" data-slide-to="2"></li>
        </ol>

        <!-- Wrapper for slides -->
        <div class="carousel-inner">
            <div class="item active">
                <div class="fill" style="background-image:url('http://placehold.it/1900x1080&text=Slide One');">
                <div align="center"><img width="1400" height="400"  src="http://www.litorehotel.com/web/en/images/placeholders/1920x1200-0.jpg">
                </div>
                </div>
                <div class="carousel-caption">
                    <h2></h2>
                </div>
            </div>
            <div class="item" >
                 <div class="fill" style="background-image:url('http://placehold.it/1900x1080&text=Slide Two');">
                 <div align="center"><img width="1400" height="400"  src="http://www.hotel-r.net/im/hotel/it/hotel-sunset-23.jpg">
                 </div>               
                <div class="carousel-caption">
                    <h2></h2>
                </div>
            </div>
            </div>
            <div class="item">
                <div class="fill" style="background-image:url('http://placehold.it/1900x1080&text=Slide Three');">
                <div align="center"><img width="1400" height="400"  src="http://www.artsfon.com/pic/201502/1920x1080/artsfon.com-51844.jpg">
                </div>
                <div class="carousel-caption">
                    <h2></h2>
                </div>
            </div>
        </div>

        <!-- Controls -->
        <a class="left carousel-control" href="#myCarousel" data-slide="prev">
            <span class="icon-prev"></span>
        </a>
        <a class="right carousel-control" href="#myCarousel" data-slide="next">
            <span class="icon-next"></span>
        </a>
    </header>

    <!-- Page Content -->
    <div class="container">

        <!-- Marketing Icons Section -->
        <div class="row">
            <div class="col-lg-12">
                <h1 class="page-header">
                    Benvenuti in MyHotel
                </h1>
            </div>
            <div class="col-md-4">
                <div class="panel panel-default">
                    <div class="panel-heading">
                        <h4><i class="fa fa-fw fa-check"></i> Prenotazione Rapida</h4>
                    </div>
                    <div class="panel-body">
                        <p>My Hotel permette di effettuare le tue prenotazione rapidamente grazie ad un'interfaccia semplice che permette di effettuare più prenotazioni simultaneamente.</p>
                     
                    </div>
                </div>
            </div>
           <div class="col-md-4">
                <div class="panel panel-default">
                    <div class="panel-heading">
                        <h4><i class="fa fa-fw fa-home"></i> Filtra le camere</h4>
                    </div>
                    <div class="panel-body">
                         <p>Filtra le camere disponibili</p>
    </div>
    <form action="camera?action=filtra" method="post">
    <div class=filtracamere align=center>
    <div align=center><label>TIPOLOGIA:</label>
		<select name="tipologia">
		<option value="Tutte">Tutte</option>
         <option value="Singola">Singola</option>
         <option value="Doppia">Doppia</option>
	     <option value="Suite">Suite</option>	     
	     </select><br></div>
    <div align=center><label>PREZZO MINIMO:&nbsp&nbsp&nbsp</label> 
		<input name="min"  type="number" min=0 value=0 ><br></div>
<div align=center><label>PREZZO MASSIMO:</label> 
		<input name="max"  type="number" min=0 value=0><br>
		<div align=center><label>ORDINA PER:</label>
		<select name="order">
		<option value="NUMEROCAMERA">Numerocamera</option>
		<option value="prezzo">Prezzo</option>
         <option value="tipologia">Tipologia</option>     
	     </select><br></div>
  <div align=center><input id="cerca" type="submit" value="Cerca"></div></div>
  </div>
  </form>
                    </div>
                </div>
         
            <div class="col-md-4">
                <div class="panel panel-default">
                    <div class="panel-heading">
                        <h4><i class="fa fa-fw fa-shopping-cart"></i> Scelta dei servizi</h4>
                    </div>
                    <div class="panel-body">
                        <p>Ogni camera dispone di una serie di servizi,l'utente può scegliere tra questi nella pagina dedicata alla visualizzazione della camera.</p>
                    </div>
                </div>
            </div>
           
        </div>
        <!-- /.row -->

      
                </div>

       

        <hr>
        <div id="filtro" align="center">
   
        <!-- Call to Action Section -->
        <div class="well">
            <div class="row">
                <div class="col-md-8">
                    <h4>Visita il nostro catalogo per visualizzare le camere disponibili</h4>
                </div>
                <div class="col-md-4">
                    <a class="btn btn-lg btn-default btn-block" href="camera?action=getcamere">Catalogo</a>
                </div>
            </div>
        </div>

        <hr>

        <!-- Footer -->
        <footer>
            <div class="row">
                <div class="col-lg-12">
                    <p>Copyright &copy; MyHotel 2017</p>
                </div>
            </div>
        </footer>

    </div>
    <!-- /.container -->

    <!-- jQuery -->
    <script src="js/jquery.js"></script>

    <!-- Bootstrap Core JavaScript -->
    <script src="js/bootstrap.min.js"></script>

    <!-- Script to Activate the Carousel -->
    <script>
    $('.carousel').carousel({
        interval: 5000 //changes the speed
    })
    </script>

</body>