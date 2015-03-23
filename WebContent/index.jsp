<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%
	String m = request.getParameter("m");
	String a = request.getParameter("a");
	m = m == null ? "article" : m;
	a = a == null ? "display" : a;
	String link = "WEB-INF/modules/" + m + "/" + a + ".jsp";
%>

<%@ page import="application.Login,application.User"%>
<% String [] adminActions = new String [] {"cms"}; %>
<% 
	User user = Login.getSessionUser(request);
	for (String action : adminActions)  
	if (action.equals (a) && (user==null || !user.getRole().equals("admin"))) {
		response.sendRedirect("index.jsp"); //brak uprawnien
	}
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="description" content="">
<meta name="author" content="Lukasz Vilgefortz Zielinski">
<!--  meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1" -->
<!-- Jquery Core -->
<script type="text/javascript" src="scripts/jquery/jquery.js"></script>
<script type="text/javascript" src="scripts/messi/messi.js"></script>
<!-- Bootstrap Core CSS -->
<link href="scripts/bootstrap/css/bootstrap.min.css" rel="stylesheet">

<!-- Custom CSS -->
<link href="scripts/bootstrap/css/blog-post.css" rel="stylesheet">
<link href="css/custom.css" rel="stylesheet">
<title>Mini blog</title>
</head>
<body>
	<!-- Navigation -->
	<nav class="navbar navbar-inverse navbar-fixed-top" role="navigation">
	<div class="container">
		<!-- Brand and toggle get grouped for better mobile display -->
		<!-- Collect the nav links, forms, and other content for toggling -->
		<div class="collapse navbar-collapse"
			id="bs-example-navbar-collapse-1">
			<ul class="nav navbar-nav">
				<li><a href="index.jsp?m=article&a=display">HOME</a></li>
				<li><a href="index.jsp?m=main&a=cms">CMS</a></li>
			</ul>
		</div>
		<!-- /.navbar-collapse -->
	</div>
	</nav>
	<!-- /.container --> 
	<!-- Page Content -->
	<div class="container">
		<div class="row">
		<div style='float:right'><jsp:include page='WEB-INF/login.jsp' /></div>
		</div>
		<div class="row">
			<jsp:include page='<%=link%>' />
		</div>
	</div>
</body>
</html>
