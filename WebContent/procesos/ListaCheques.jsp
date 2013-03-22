<?xml version="1.0" encoding="ISO-8859-1" ?>
<%@page import="com.clase.dao.ProveedorDao"%>
<%@page import="com.clase.models.Proveedor"%>
<%@page import="com.clase.dao.ChequesDao"%>
<%@page import="com.clase.models.Cheques"%>
<%@page import="com.clase.models.Usuario"%>
<%@page import="com.clase.dao.ProyectosDao"%>
<%@page import="com.clase.models.Proyecto"%>
<%@page import="java.util.ArrayList"%>
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

//obtener ruta del contexto
String ruta = request.getContextPath();


//Datos de Cheque
ArrayList <Cheques> ListCheque= new ArrayList<Cheques>(); 
ChequesDao ServiceCheque= new ChequesDao();
Cheques persProy = new Cheques() ;

//Variables para los datos de proyecto
ArrayList <Proyecto > ListProy= new ArrayList<Proyecto>(); 
ProyectosDao ServiceProy= new ProyectosDao();
Proyecto proy = new Proyecto();
ListProy = ServiceProy.getLista();
int idProy=0;
String nombreProyecto="N/A";

//Variables para los datos del proveedor
ArrayList <Proveedor > ListProveedor= new ArrayList<Proveedor>();
ProveedorDao ServiceProv = new ProveedorDao();
Proveedor myProv = new Proveedor();
int idProv=0;
String nameProv= "N/A";

//para obtener el mensaje en pantalla
String mensaje = request.getParameter("mensaje");

//Para verificar el usuario autenticado
Usuario user = new Usuario();
user = (Usuario) session.getAttribute("usuario");

if (user == null) {
	response.sendRedirect(ruta + "/login.jsp");
	return;
}


int table =0;
try {
	table=Integer.parseInt(request.getParameter("idProy"));	
}
catch (Exception ex){
	table=0;
}

int param=0;
if (table!=0)
{
	param = table;
	System.out.println("Entra aquí con Parámetro: " + table);
	ProyectosDao servicio = new ProyectosDao();
	proy = servicio.findById( param);
	ListProy = ServiceProy.getLista();
	ListCheque= ServiceCheque.getLista(param);
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
<title>Lista de Cheques por Proyecto</title>
</head>
<body onload="P7_initPM(0,1,0,0,-1)">
<div id="lista">
<br />
	<h1>Cheques del proyecto</h1>
	<br />

	<br />
	<hr />
	<table  border="0" width="100%" class="CssTable">
	<thead>
				<tr>
					<th>No.</th>
					<th>Nombre de Proyecto</th>
					<th>Proveedor</th>
					<th>Concepto</th>
					<th>Monto</th>
					<th>Fecha de Emision</th>
					<th>Estado</th>
					<th>Opciones</th>
					
				</tr>
			</thead>
			<tbody>
			<%
				for (int i = 0; i < ListCheque.size(); i++) {
					
					//Obteniendo el Nombre del Proyecto
					//idProy=ListCheque.get(i).getIdProyecto() ;
		System.out.println("Si es diferente de 0 (Cheque - Proyecto): " + idProy);
		proy= ServiceProy.findById(param) ;
		nombreProyecto = proy.getNombre();
		
		//obteniendo el nombre del proveedor
		idProv= ListCheque.get(i).getIdProveedor();
		System.out.println("Si el proveedor diferente de 0 (Cheque - Proveedor): " + idProv);
		myProv = ServiceProv.findByID(idProv);
		nameProv = myProv.getNombre();
					
					%>
					<tr>
			<td><%=i+1 %></td>
			<td><%=nombreProyecto  %></td>
			<td><%=nameProv  %></td>
			<td><%=ListCheque.get(i).getConcepto() %></td>
			<td><%=ListCheque.get(i).getMonto() %></td>
			<td><%=sdf.format(ListCheque.get(i).getFechaEmision()) %></td>
			<td><%=ListCheque.get(i).getEstado() %></td>
			<td>
					
						&nbsp; <a 
						href="../Cheques.do?accion=del&idProy=<%=param%>&idCh=<%=ListCheque.get(i).getIdCheque() %>">Delete</a>
				</td>
			
			</tr>		
					<%	}
			%>
			
			
			</tbody>
	</table>
</div>
</body>
</html>