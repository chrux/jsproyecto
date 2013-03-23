<?xml version="1.0" encoding="utf-8" ?>
<%@ page language="java"
	  contentType="text/html; charset=utf-8"
	 pageEncoding="utf-8"%>
<%
String ruta = request.getContextPath();
String mensaje = request.getParameter("mensaje");
%>
<%@page import="java.text.DecimalFormat"%>
<%@page import="com.clase.dao.ClienteDao"%>
<%@page import="com.clase.models.Cliente"%>
<%@page import="com.clase.models.Usuario"%>
<%@page import="com.clase.dao.ProyectosDao"%>
<%@page import="com.clase.models.Proyecto"%>
<%@page import="java.util.ArrayList"%>
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
	<title>Proyecto | ProjectManager</title>
<%
DecimalFormat format = new DecimalFormat("###,###.##"); //para formato

//Formato de fechas
java.util.Date date = new java.util.Date(); 
java.text.SimpleDateFormat sdf=new java.text.SimpleDateFormat("dd/MM/yyyy");

//Variables para los datos de proyecto
ArrayList <Proyecto > ListProy= new ArrayList<Proyecto>(); 
ProyectosDao ServiceProy= new ProyectosDao();
Proyecto proy = new Proyecto();
ListProy = ServiceProy.getLista();

//Variables para cliente
Cliente client= new Cliente();
ClienteDao serviceClient = new ClienteDao();

//Para verificar el usuario autenticado
Usuario user = new Usuario();
user = (Usuario) session.getAttribute("usuario");

if (user == null) {
	response.sendRedirect(ruta + "/login.jsp");
	return;
}

String table = request.getParameter("idProy");
int show=0;
try{
	show = Integer.parseInt(request.getParameter("show"));
}
catch (Exception ex){
	show=0;
}

int param=0;
String clientName="PRUEBA";

if (!table.isEmpty())
 {
	 param= Integer.parseInt(table);
	System.out.println("Entra aquí con Parámetro: " + table);
	ProyectosDao servicio = new ProyectosDao();
	proy = servicio.findById( param);
	ListProy = ServiceProy.getLista();
	int clientId =proy.getIdCliente();
	System.out.println("Id encontrado: " + clientId);
	if(clientId != 0){ 
		int idClient=proy.getIdCliente();
		System.out.println("Si es diferente de 0: " + idClient);
		client=serviceClient.findById(idClient);
		
		clientName= client.getNombre();
		System.out.println("Entró, nombre es: " + client);
	}
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
<div>
	<h3>Datos del proyecto <%= show %></h3>
	<table class="table table-bordered">
<thead>
				<tr>
					<th>No.</th>
					<th>Nombre</th>
					<% if(show==1){ %><th>Presupuesto</th><% }%>
					<th>Observaciones</th>
					<% if(show==1){ %><th>Cliente</th><% }%>
					<% if(show==1){ %><th>Cierre</th><% }%>
					<th width="80">Opciones</th>
					<%-- <% if(table.equals("2")) {%>
					<th>Precio</th>
					<%} %> --%>
				</tr>
			</thead>
			<tbody>
			<tr>
			<td><%=param %></td>
			<td><%=proy.getNombre()  %></td>
			<% if(show==1){ %><td><%=format.format(proy.getMontoPresupuesto() )%></td><% }%>
			<td><%=proy.getObservaciones() %></td>
			<% if(show==1){ %><td><%=clientName%></td><% }%>
			<% if(show==1){ %><td><%=sdf.format(proy.getFechaCierre()) %></td><% }%>
			<% if(show==1){ %><td>
					<a
					href="./AddProyecto.jsp?idProy=<%=param%>&show=1">Edit</a> 
						&nbsp; <a 
						href="../AddProyecto.do?accion=del&idProy=<%=param%>">Delete</a>
				</td>
				<% } else {%>
				<td>
					&nbsp; <a
					href="./AddProyecto.jsp?idProy=<%=param%>&show=0">Ver Cheques Proyecto</a> 
								</td><% }%>
					</tr>
				</tbody>
	</table>
</div>
</body>
</html>