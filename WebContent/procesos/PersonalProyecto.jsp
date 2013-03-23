<?xml version="1.0" encoding="utf-8" ?>
<%@ page language="java"
	  contentType="text/html; charset=utf-8"
	 pageEncoding="utf-8"%>
<%
String ruta = request.getContextPath();
String mensaje = request.getParameter("mensaje");
%>
<%@page import="com.clase.dao.OficioDao"%>
<%@page import="com.clase.models.Oficio"%>
<%@page import="com.clase.dao.PersonalDao"%>
<%@page import="com.clase.models.Personal"%>
<%@page import="com.clase.dao.CargoDao"%>
<%@page import="com.clase.models.Cargo"%>
<%@page import="com.clase.dao.ProyectosDao"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.clase.dao.PersonalProyectoDao"%>
<%@page import="com.clase.models.PersonalProyecto"%>
<%@page import="com.clase.models.Usuario"%>
<%@page import="com.clase.models.Proyecto"%>
<%@page import="java.text.DecimalFormat"%>
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
	<title>Personal del Proyecto | ProjectManager</title>
<%
DecimalFormat format = new DecimalFormat("###,###.##"); //para formato

//Formato de fechas
java.util.Date date = new java.util.Date(); 
java.text.SimpleDateFormat sdf=new java.text.SimpleDateFormat("dd/MM/yyyy");
String fecha = sdf.format(date);

Proyecto proy = new Proyecto();

//Datos de personal Proyecto
ArrayList <PersonalProyecto> ListPers= new ArrayList<PersonalProyecto>(); 
PersonalProyectoDao ServiceProy= new PersonalProyectoDao();
PersonalProyecto persProy = new PersonalProyecto() ;
//ListPers = ServiceProy.getLista();

//Datos Cargo
Cargo myCargo= new Cargo();
CargoDao serviceCargo = new CargoDao();
String cargoName="PRUEBA";
int idCargo=0;

//Datos Oficio
Oficio myPos = new Oficio();
OficioDao servicePosi= new OficioDao();
String positionName="Prueba";
int idPosition=0;

//Datos de la Persona
Personal myPersona = new Personal();
PersonalDao servicePersonal = new PersonalDao();
String NombrePersona="Prueba";
int idPersona=0;

//Para verificar el usuario autenticado
Usuario user = new Usuario();
user = (Usuario) session.getAttribute("usuario");

if (user == null) {
	response.sendRedirect(ruta + "/login.jsp");
	return;
}

String paramProy = request.getParameter("idProy");
int param=0;

if (!paramProy.isEmpty())
{
	param= Integer.parseInt(paramProy);
	System.out.println("Entra aquí con Parámetro: " + paramProy);
	PersonalProyectoDao servicio = new PersonalProyectoDao();
	persProy = servicio.findById( param);
	ListPers = ServiceProy.getLista(param);
	//int idCargo=
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
<div id="lista">
<br />
	<h3 class="no-margin">Personal del proyecto</h3>
	<hr />
	<table class="table table-bordered">
	<thead>
				<tr>
					<th>No.</th>
					<th>Persona</th>
					<th>Cargo</th>
					<th>Oficio</th>
					<th>Fecha Inicio</th>
					<th>Fecha Fin</th>
					<th>Opciones</th>
					
				</tr>
			</thead>
			<tbody>
			<%
				for (int i = 0; i < ListPers.size(); i++) {
					
					//Obteniendo el cargo
					idCargo=ListPers.get(i).getIdCargo();
		System.out.println("Si es diferente de 0: " + idCargo);
		myCargo=serviceCargo.findByID(idCargo);
		
		cargoName= myCargo.getNombreCargo();
		
		//obteniendo el nombre de persona
		idPersona=ListPers.get(i).getIdPersonal();
		myPersona=servicePersonal.findByID(idPersona);
		
		NombrePersona= myPersona.getNombre();
		
		//Obteniendo el oficio
		idPosition = ListPers.get(i).getIdOficio();
		myPos=servicePosi.findByID(idPosition);
		positionName = myPos.getOficio();
					
			%>
			<tr>
			<td><%=i+1 %></td>
			<td><%=NombrePersona %></td>
			<td><%=cargoName  %></td>
			<td><%=positionName   %></td>
			<td><%=sdf.format(ListPers.get(i).getFechaInicio()) %></td>
			<td><%=sdf.format(ListPers.get(i).getFechaFinal()) %></td>
			<td>
					<%-- &nbsp; <a
					href="./AddPersonalProyecto.jsp?idPersProy=<%=ListPers.get(i).getIdPersonalProy()%>&idProy=<%=param%>">Editar</a> --%>
						&nbsp; <a 
						href="../PersonalProy.do?accion=del&idPersProy=<%=ListPers.get(i).getIdPersonalProy()%>&idProy=<%=paramProy%>">Delete</a>
				</td>
					</tr>
					<%
				}
			%>
			
			</tbody>
	</table>
</div>
</body>
</html>