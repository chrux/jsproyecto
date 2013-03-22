<?xml version="1.0" encoding="ISO-8859-1" ?>
<%@page import="java.text.DecimalFormat"%>
<%@page import="com.clase.dao.ClienteDao"%>
<%@page import="com.clase.models.Cliente"%>
<%@page import="com.clase.models.Usuario"%>
<%@page import="com.clase.dao.ProyectosDao"%>
<%@page import="com.clase.models.Proyecto"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>

<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1" />
<%
DecimalFormat format = new DecimalFormat("###,###.##"); //para formato

//Formato de fechas
java.util.Date date = new java.util.Date(); 
java.text.SimpleDateFormat sdf=new java.text.SimpleDateFormat("dd/MM/yyyy");

//obtener ruta del contexto
String ruta = request.getContextPath();

//Variables para los datos de proyecto
ArrayList <Proyecto > ListProy= new ArrayList<Proyecto>(); 
ProyectosDao ServiceProy= new ProyectosDao();
Proyecto proy = new Proyecto();
ListProy = ServiceProy.getLista();

//Variables para cliente
Cliente client= new Cliente();
ClienteDao serviceClient = new ClienteDao();

//para obtener el mensaje en pantalla
String mensaje = request.getParameter("mensaje");

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
<link href="<%=ruta%>/css/menu.css" rel="stylesheet" type="text/css" />
<link href="<%=ruta%>/css/style.css" rel="stylesheet" type="text/css" />
<link href="<%=ruta%>/css/tabs.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="<%=ruta%>/js/tabs.js"></script>
<script type="text/javascript" src="<%=ruta%>/js/menu.js"></script>
<link rel="stylesheet" type="text/css" href="<%=ruta%>/css/style3.css" />
<link rel="stylesheet" type="text/css" href="<%=ruta%>/css/style2.css" />
<!--[if lt IE 8.]>-->
<link rel="stylesheet" type="text/css" href="<%=ruta%>/css/style-ie.css" />
<!--<![endif]-->
<!--[if lt IE 7.]>
<link rel="stylesheet" type="text/css" href="<%=ruta%>/css/style-ie6.css" />
<!--<![endif]-->
<title>Lista Proyectos</title>
</head>
<body onload="P7_initPM(0,1,0,0,-1)">
<div id="lista">
<br />
	<h1>Datos del proyecto</h1>
	<br />

	<br />
	<hr />
<table  border="0" width="100%" class="CssTable">
<thead>
				<tr>
					<th>No.</th>
					<th>Nombre de Proyecto</th>
					<% if(show==1){ %><th>Monto de Presupuesto</th><% }%>
					<th>Observaciones</th>
					<% if(show==1){ %><th>Cliente</th><% }%>
					<% if(show==1){ %><th>Fecha de Cierre</th><% }%>
					<th>Opciones</th>
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
					&nbsp; <a
					href="./AddProyecto.jsp?idProy=<%=param%>&show=1">Editar</a> 
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