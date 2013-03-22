<?xml version="1.0" encoding="ISO-8859-1" ?>
<%@page import="com.clase.dao.UsuarioDao"%>
<%@page import="com.clase.dao.ProyectosDao"%>
<%@page import="com.clase.models.Proyecto"%>
<%@page import="java.util.ArrayList"%>
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


//Para lista de proyectos
ArrayList<Proyecto> ListProy= new ArrayList<Proyecto>(); 
ProyectosDao ServiceProy= new ProyectosDao();
Proyecto proy = new Proyecto();
ListProy = ServiceProy.getLista();

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
<SCRIPT Language="JavaScript">
function f(){
//var indice=document.getElementById('Proyectos').options.selectedIndex ; //posicion
var posicion=document.getElementById('Proyectos').options.selectedIndex  ; //posicion
//var posicion=document.getElementById('Proyectos').option[indice].value  ; //posicion
var valueS=document.getElementById('Proyectos').options[posicion].value;
//alert(" Se mostrará el reporte de " + document.getElementById('Proyectos').options[posicion].text + document.getElementById('Proyectos').options[posicion].value ); //valor
return valueS;
}

	function redireccionar() 
	{
		var pagina="ListReportes.jsp?idProy="+f()+"&tipo=rptPersonalProyecto";
	location.href=pagina;
	} 
	setTimeout ("redireccionar()", 20000);
</SCRIPT>
<title>Reporte de Personal por proyecto</title>
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
	<td align="right" ><label>Seleccione el Proyecto:</label></td>
										<td align="left"><select name="Proyectos" id="Proyectos">
			<option value="-1">Selecione Aquí..</option>
			<%
				for (int i = 0; i < ListProy.size(); i++) {
			%> 
			<option value="<%=ListProy.get(i).getIdProyecto() %>"><%=ListProy.get(i).getNombre()%></option>
			
			<%				}
			%>
		</select></td>
	</tr>
	<tr>
	<td align="center" colspan="2">
	 <br /> <br /> <br />
					<button type="submit" onclick="redireccionar();" style=" background:'<%=ruta%>/WebContent/images/butt.jpg;' " >Ver Reporte</button>
					
	
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