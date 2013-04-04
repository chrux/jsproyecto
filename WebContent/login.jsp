<?xml version="1.0" encoding="utf-8" ?>
<%@ page language="java"
	  contentType="text/html; charset=utf-8"
	 pageEncoding="utf-8"%>
<%@page import="com.clase.models.Usuario"%>
<%
String ruta = request.getContextPath();
String mensaje = request.getParameter("mensaje");

Usuario user = new Usuario();
user = (Usuario) session.getAttribute("usuario");

if ( user != null ) {
	response.sendRedirect(ruta + "/inicio/home.jsp");
	return;
}
%>
<!DOCTYPE html>
<!--[if lt IE 7]>      <html class="no-js lt-ie9 lt-ie8 lt-ie7"> <![endif]-->
<!--[if IE 7]>         <html class="no-js lt-ie9 lt-ie8"> <![endif]-->
<!--[if IE 8]>         <html class="no-js lt-ie9"> <![endif]-->
<!--[if gt IE 8]><!--> <html class="no-js"> <!--<![endif]-->
<head lang="es">
  	<meta charset="utf-8">
  	<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<meta name="keywords" content="">
	<meta name="description" content="">
    <meta name="viewport" content="width=device-width">
	<title>Acceso al Sistema | ProjectManager</title>
	<link href="<%= ruta %>/assets/css/bootstrap.css" rel="stylesheet">
    <link href="<%= ruta %>/assets/css/bootstrap-responsive.css" rel="stylesheet">
    <link href="<%= ruta %>/assets/css/todc-bootstrap.css" rel="stylesheet">
    <link href="<%= ruta %>/assets/css/login.css" rel="stylesheet">

    <!-- HTML5 shim, for IE6-8 support of HTML5 elements -->
    <!--[if lt IE 9]>
    <script src="<%= ruta %>/assets/js/modernizr.js"></script>
    <![endif]-->
</head>
<body>
	<div class="container">

      <div class="signin">
        <div class="signin-box">
          <h2 class="form-signin-heading">Autenticación</h2>
		  <% if(mensaje!=null) {%>
		  <div class="alert alert-info">
		    <%=mensaje %>
		  </div>
		  <%} %>

          <form method="post" action="<%= ruta %>/login.do">
            <fieldset>
              <label for="name">Nombre de Usuario</label>
              <input type="text" class="input-block-level" name="name" id="name">
              <label for="pwd">Contraseña</label>
              <input type="password" class="input-block-level" name="pwd" id="pwd">

              <input type="submit" class="btn btn-primary" value="Entrar">
             <label class="remember">
                <input type="checkbox" name="rememberMe" value="yes">
                <strong class="remember-label">Recordarme</strong>
              </label>
            </fieldset>
          </form>
<% /* %>
          <ul>
            <li>
              <a id="link-forgot-pwd" href="#">Can't access your account?</a>
            </li>
          </ul>
<% */ %>
        </div>
      </div>

    </div> <!-- /container -->
	
	<!-- Le javascript
    ================================================== -->
    <!-- Placed at the end of the document so the pages load faster -->
    <script src="<%= ruta %>/assets/js/jquery.js"></script>
</body>
</html>