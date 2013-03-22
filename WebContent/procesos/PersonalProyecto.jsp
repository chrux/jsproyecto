<?xml version="1.0" encoding="ISO-8859-1" ?>
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
String fecha = sdf.format(date);

//obtener ruta del contexto
String ruta = request.getContextPath();

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
<title>Personal Proyecto</title>
</head>
<body onload="P7_initPM(0,1,0,0,-1)">
<div id="lista">
<br />
	<h1>Personal del proyecto</h1>
	<br />

	<hr />
	<table  border="0" width="100%" class="CssTable">
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