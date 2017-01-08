<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page contentType="text/html; charset=ISO-8859-1" import="java.util.*,camera.CameraBean"%>
    <%String email= (String) session.getAttribute("email");
Collection<?> camere = (Collection<?>) request.getAttribute("camere");%>
<!DOCTYPE html >
<html>
<head>

<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Catalogo</title>
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
<%if(email == null){ %>
   <%@ include file ="nav.jsp" %>
   <%}else{  %>
   <%@ include file ="navprotected.jsp" %>
   <%} %>
<div class="container">
           <h2 class="page-header">Filtra</h2>
           <%@ include file ="filtra.jsp" %>
       

        <!-- Team Members -->
        <div class="row">
            <div class="col-lg-12">
                <h2 class="page-header">Catalogo</h2>
            </div>
            <%		
            int i=0;
			if (camere != null && camere.size() != 0) {
				Iterator<?> it = camere.iterator();
				while (it.hasNext()&& i<5) {
					CameraBean bean = (CameraBean) it.next();
					%>
					
					<div class="col-md-4 text-center">
	                <div class="thumbnail">
	                <a href="camera?action=getcamere&numeroCamera=<%=bean.getNumeroCamera()%>">
	                    <img class="img-responsive" src="<%=bean.getImmagine()%>" alt="">
	                    </a>
	                    <div class="caption">
	                        <h3><%=bean.getNumeroCamera()%><br>
	                            <small><%=bean.getPrezzo()%> &euro;</small>
	                        </h3>
	                        <p><%=bean.getDescrizione()%></p>
	                    </div>
	    		</div>
	    		</div>
	            <%i++;%>
	            <%} }%>
	            
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
