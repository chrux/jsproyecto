<?xml version="1.0" encoding="utf-8" ?>
<%@ page language="java"
	  contentType="text/html; charset=utf-8"
	 pageEncoding="utf-8"%>
<%
String ruta = request.getContextPath();
String mensaje = request.getParameter("mensaje");
%>
<%@page import="com.clase.dao.UsuarioDao"%>
<%@page import="com.clase.dao.CargoDao"%>
<%@page import="com.clase.models.Cargo"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.clase.models.Usuario"%>
<%@page import="com.clase.servlets.ServiceBanco"%>
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
	<title>Cargos | ProjectManager</title>
<%
	Cargo miCargo = new Cargo();
	int id = 0;
	try {
		id = Integer.parseInt(request.getParameter("IdCargo"));
	} catch (Exception e) {
		id = 0;
	}

	if (id != 0) {
		CargoDao servicio = new CargoDao();
		miCargo = servicio.findByID(id);
	}
	
	Usuario user = new Usuario();
	user = (Usuario) session.getAttribute("usuario");

	if (user == null) {
		response.sendRedirect(ruta + "/login.jsp");
		return;
	}
	
	UsuarioDao permission = new UsuarioDao();
	String name = "";
	if (request instanceof HttpServletRequest) {
		name = ((HttpServletRequest) request).getRequestURI();
	}
	config.getServletContext().log(
			"Ruta: " + name);

	

%>
	<link href="<%= ruta %>/assets/css/bootstrap.css" rel="stylesheet">
    <link href="<%= ruta %>/assets/css/bootstrap-responsive.css" rel="stylesheet">
    <link href="<%= ruta %>/assets/css/todc-bootstrap.css" rel="stylesheet">
    <link href="<%= ruta %>/assets/css/style.css" rel="stylesheet">

    <!-- HTML5 shim, for IE6-8 support of HTML5 elements -->
    <!--[if lt IE 9]>
    <script src="<%= ruta %>/assets/js/modernizr.js"></script>
    <![endif]-->
</head>
<body>
	<div class="container-narrow">
      <div class="masthead">
        <ul class="nav nav-pills pull-right">
        <jsp:include page="../include/menu.jsp">
          <jsp:param name="ruta" value="<%=ruta %>"></jsp:param>
        </jsp:include>
        </ul>
        <h3 class="muted">Project Manager</h3>
      </div>

      <hr>
		
	  <div class="row-fluid">
      	<div class="span9">
      		<h3 class="no-margin"><% if (id != 0) { %>Editar<% } else { %>Nuevo<% } %> Cargo</h3>
      	</div>
      	<div class="span3 align-right">
      		&nbsp;
      	</div>
      </div>
      
      <div>
      <form class="form-horizontal" action="<%= ruta %>/Cargo.do">
      	  <% if (id != 0) { %>
      	  <input type="hidden" id="IdCargo" name="IdCargo" value="<%=id%>" />
      	  <input type="hidden" id="accion" name="accion" value="edit" />
      	  <% } else { %>
      	  <input type="hidden" id="accion" name="accion" value="nuevo" />
      	  <% } %>
		  <div class="control-group">
		    <label class="control-label" for="nombreCargo">Nombre</label>
		    <div class="controls">
		      <input type="text" id="nombreCargo" name="nombreCargo" value="<%=miCargo.getNombreCargo() %>" placeholder="Nombre del Cargo">
		    </div>
		  </div>
		  <div class="control-group">
		    <label class="control-label" for="estado">Estado</label>
		    <div class="controls">
		      <select name="estado" style="width:128px" id="estado">
		 		<option value="1" <% if (miCargo.getEstado().contains("1")){ %> selected="selected"<% } %> >Activo</option>
		 		<option value="0" <% if (miCargo.getEstado().contains("0")){ %> selected="selected"<% } %> >Inactivo</option>
		 	  </select>
		    </div>
		  </div>
		  <div class="control-group">
		    <div class="controls">
		      <button type="submit" class="btn btn-primary">Guardar</button>
		      <a href="<%= ruta %>/catalogos/Cargo.jsp" class="btn">Cancelar</a>
		    </div>
		  </div>
		</form>
      </div>
	

      <jsp:include page="../include/footer.jsp">
      	<jsp:param name="ruta" value="<%=ruta%>"></jsp:param>
      </jsp:include>

    </div> <!-- /container -->
    
	<!-- Le javascript
    ================================================== -->
    <!-- Placed at the end of the document so the pages load faster -->
    <script src="<%= ruta %>/assets/js/jquery.js"></script>
    <script src="<%= ruta %>/assets/js/bootstrap.js"></script>
</body>
</html>