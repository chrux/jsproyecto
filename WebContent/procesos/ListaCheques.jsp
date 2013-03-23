<?xml version="1.0" encoding="utf-8" ?>
<%@ page language="java"
	  contentType="text/html; charset=utf-8"
	 pageEncoding="utf-8"%>
<%
String ruta = request.getContextPath();
String mensaje = request.getParameter("mensaje");
%>
<%@page import="com.clase.dao.ProveedorDao"%>
<%@page import="com.clase.models.Proveedor"%>
<%@page import="com.clase.dao.ChequesDao"%>
<%@page import="com.clase.models.Cheques"%>
<%@page import="com.clase.models.Usuario"%>
<%@page import="com.clase.dao.ProyectosDao"%>
<%@page import="com.clase.models.Proyecto"%>
<%@page import="java.util.ArrayList"%>
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
	<title>Cheques de Proyecto | ProjectManager</title>
<%
DecimalFormat format = new DecimalFormat("###,###.##"); //para formato

//Formato de fechas
java.util.Date date = new java.util.Date(); 
java.text.SimpleDateFormat sdf=new java.text.SimpleDateFormat("dd/MM/yyyy");

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
	<h3>Cheques del proyecto</h3>
	<hr />
	<table class="table table-bordered">
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