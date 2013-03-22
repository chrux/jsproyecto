<?xml version="1.0" encoding="ISO-8859-1" ?>
<%@page import="com.clase.dao.UsuarioDao"%>
<%@page import="com.clase.models.Usuario"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1" />
<%


//Formato de fechas
java.util.Date date = new java.util.Date(); 
java.text.SimpleDateFormat sdf=new java.text.SimpleDateFormat("dd/MM/yyyy");

//Parámetro de mensaje
String mensaje = request.getParameter("mensaje");

//obtener ruta del contexto
String ruta = request.getContextPath();

//Obteniendo el usuario
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

//ELIMINA EL CONTEXTO DE LA APLICACIÓN Y EL
//EL NOMBRE DEL RECURSO COINCIDA CON LA RUTA ESCRITA EN LA TABLA
name = name.substring(13, name.length());

if(!permission.getUsuarioPermiso(user.getNlogin() , name)){
	response.sendRedirect(ruta + "/inicio/home.jsp?mensaje=Permiso no establecido");
	return;
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
<title>Lista de Reportes Generales</title>
</head>
<body onload="P7_initPM(0,1,0,0,-1)">
<div id="container">
<div id="header">
<h1>
				Usuario:
				<%=user.getNombre()%>
			</h1>

</div>
<div id="navigation">
			<a style="color:#fff; text-decoration:none;padding-left:4px;padding-top:4px; display:block;float:left;" href="<%=ruta%>/login.do?cierre=si">Cerrar Sesión</a>
				</div>
				<table border="0" cellpadding="0" cellspacing="0" width="100%">
				<tbody>
				<tr>
				<td id="leftcontent" nowrap="nowrap" valign="top" width="190">
				<div class="bar">
						<img src="<%=ruta%>/images/hr_dots1.gif" alt="" height="3" width="150" />
					</div> <!-- ** Menu Start ** //--> <jsp:include
						page="../include/menu.jsp"><jsp:param name="ruta" value="<%=ruta %>"></jsp:param></jsp:include> <!-- ** Menu End ** //-->
				
				</td>
				
				<td valign="top" width="100%">
				<br />
<% if(mensaje!=null) {%>
	<p style="color: red;"><%=mensaje %></p>
	<%} %>
	<br />
	<h1>Reportes Generales</h1>
	<br />
	<table border="0" cellpadding="0" cellspacing="0" width="100%">
	<tr>
	<td>
	
		
			<a style="text-decoration:none;padding-left:60px;padding-top:4px; display:block;float:left;" href="<%=ruta%>/pagReportes/ListReportes.jsp?tipo=report1">Reporte de Tipos de Usuarios</a>	
	</td>
	</tr>
	<tr>
	<td>
	
		
			<a style="text-decoration:none;padding-left:60px;padding-top:4px; display:block;float:left;" href="<%=ruta%>/pagReportes/ListReportes.jsp?tipo=rptPersonal">Reporte de Personal</a>	
	</td>
	</tr>
	<tr>
	<td>
	
		
			<a style="text-decoration:none;padding-left:60px;padding-top:4px; display:block;float:left;" href="<%=ruta%>/pagReportes/ListReportes.jsp?tipo=rptProyectos">Lista de Proyectos</a>	
	</td>
	</tr>		
					
	</table>
					<br />
				<br />
				<br />
				<br />
				<br />
				<br />
				</td>
				
				</tr>
				
			
				</tbody>
				
				</table>
<div id="footer">
			<jsp:include page="../include/footer.jsp"><jsp:param
			name="ruta" value="<%=ruta%>"></jsp:param></jsp:include>
			</div>

</div>


</body>
</html>