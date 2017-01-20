
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page contentType="text/html; charset=ISO-8859-1"
	import="java.util.*"%>
<%String email= (String) session.getAttribute("email");%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Info</title>
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
<link href="font-awesome/css/font-awesome.min.css" rel="stylesheet"
	type="text/css">

</head>
<body>
	<%if(email == null){ %>
	<%@ include file="nav.jsp"%>
	<%}else{  %>
	<%@ include file="navprotected.jsp"%>
	<%} %>
	<div class="container">

		<!-- Page Heading/Breadcrumbs -->
		<div class="row">
			<div class="col-lg-12">
				<h1 class="page-header">
					Info <small>MyHotel</small>
				</h1>
				<ol class="breadcrumb">
					<li><a href="index.jsp">Home</a></li>
					<li class="active">Info</li>
				</ol>
			</div>
		</div>
		<!-- /.row -->

		<!-- Intro Content -->
		<div class="row">
			<div class="col-md-6">
				<img class="img-responsive" src="foto/LogoA.png" alt="">
			</div>
			<div class="col-md-6">
				<h2>MyHotel</h2>
				<p>MyHotel è una piattaforma nata con l'intento di agevolare
					quanto più possibile l'acquisto di un soggiorno, dando la
					possibilità al cliente di poter scegliere anche i servizi di cui
					poter usufruire durante la sua permanenza presso il nostro hotel.</p>
			</div>
		</div>
		<!-- /.row -->

		<!-- /.container -->

		<!-- jQuery -->
		<script src="js/jquery.js"></script>

		<!-- Bootstrap Core JavaScript -->
		<script src="js/bootstrap.min.js"></script>
</body>

</html>
