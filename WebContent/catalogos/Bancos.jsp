<?xml version="1.0" encoding="utf-8" ?>
<%@ page language="java"
	  contentType="text/html; charset=utf-8"
	 pageEncoding="utf-8"%>
<%
String ruta = request.getContextPath();
String mensaje = request.getParameter("mensaje");
%>
<%@page import="com.clase.dao.UsuarioDao"%>
<%@page import="com.clase.models.Usuario"%>
<%@page import="com.clase.dao.MacGregorDao"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.clase.models.Banco"%>
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
    <title>Bancos | ProjectManager</title>
<%
MacGregorDao ServiceBanco = new MacGregorDao();
ArrayList<Banco> ListBanco = new ArrayList<Banco>();

ListBanco = ServiceBanco.getLista();

Usuario user = new Usuario();
UsuarioDao permission = new UsuarioDao();

user = (Usuario) session.getAttribute("usuario");

if (user == null) {
	response.sendRedirect(ruta + "/login.jsp");
	return;
}

String name = "";
if (request instanceof HttpServletRequest) {
	name = ((HttpServletRequest) request).getRequestURI();
}
config.getServletContext().log(
		"Ruta: " + name);

//ELIMINA EL CONTEXTO DE LA APLICACIÓN Y EL
//EL NOMBRE DEL RECURSO COINCIDA CON LA RUTA ESCRITA EN LA TABLA
name = name.substring(13, name.length());

if(!permission.getUsuarioPermiso(user.getNlogin() , name)){
	response.sendRedirect(ruta + "/inicio/home.jsp?mensaje=Permiso no establecido");
	return;
}
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
      		<h3 class="no-margin">Bancos</h3>
      	</div>
      	<div class="span3 align-right">
      		<a href="<%=ruta %>/catalogos/AddBanco.jsp" class="btn btn-primary">Nuevo Banco</a>
      	</div>
      </div>
      <br />
	  <div>
	  <table class="table table-bordered">
		<thead>
			<tr>
				<th width="30">No.</th>
				<th>Nombre Banco</th>
				<th width="80">Estado</th>
				<th width="80">Accion</th>
			</tr>
		</thead>
		<tbody>
			<%
				for (int i = 0; i < ListBanco.size(); i++) {
			%>
			<tr>
				<td><%=ListBanco.get(i).getIdBanco()  %></td>
				<td><%=ListBanco.get(i).getNombre()%></td>
				<td><%=ListBanco.get(i).getEstado(true) %></td>
				<td><a
					href="./AddBanco.jsp?Id_Banco=<%=ListBanco.get(i).getIdBanco()%>">Edit</a>
					&nbsp; <a
					href= "<%=ruta%>/AddBanco.do?accion=del&Id_Banco=<%=ListBanco.get(i).getIdBanco()%>">Delete</a>
				</td>
			
			 	</tr>
			<%
				}
			%>
			
		
		</tbody>
		</table>
		<br/>
		<br/>
		</td>
		</tr>
	</tbody>
			</table>
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