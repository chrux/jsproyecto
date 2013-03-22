<?xml version="1.0" encoding="ISO-8859-1" ?>
<%@page import="com.clase.dao.UsuarioDao"%>
<%@page import="com.clase.models.Usuario"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@page import="com.clase.dao.ProyectosDao"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.clase.models.Proyecto "%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1" />

<%
ArrayList<Proyecto> ListProy= new ArrayList<Proyecto>(); 
ProyectosDao ServiceProy= new ProyectosDao();
String ruta = request.getContextPath();
Proyecto proy = new Proyecto();
ListProy = ServiceProy.getLista();
String mensaje = request.getParameter("mensaje");

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

<script>
	function showCustomer(str) {
		//alert("Entra aquí");
	
		var xmlhttp;
		if (str == "") {
			document.getElementById("txtHint").innerHTML = "";
			return;
		}
		if (window.XMLHttpRequest) {// code for IE7+, Firefox, Chrome, Opera, Safari
			xmlhttp = new XMLHttpRequest();
		} else {// code for IE6, IE5
			xmlhttp = new ActiveXObject("Microsoft.XMLHTTP");
		}
		xmlhttp.onreadystatechange = function() {
			if (xmlhttp.readyState == 4 && xmlhttp.status == 200) {
				document.getElementById("txtHint").innerHTML = xmlhttp.responseText;
			}
		}
		xmlhttp.open("GET", "ListaProyectos.jsp?idProy=" + str+"&show="+1, true);
		xmlhttp.send();
	}
	
	</script>

<title>Proyectos</title>
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
			
			<!-- <div id="content-container"> -->

<!-- <div id="content"> -->
<td valign="top" width="100%">
<br />
<% if(mensaje!=null) {%>
	<p style="color: red;"><%=mensaje %></p>
	<%} %>
	
<form action="">
		<select name="customers" onchange="showCustomer(this.value)">
			<option value="">Selecione Aquí..</option>
			<%
				for (int i = 0; i < ListProy.size(); i++) {
			%> 
			<option value="<%=ListProy.get(i).getIdProyecto() %>"><%=ListProy.get(i).getNombre()%></option>
			
			<%				}
			%>
		</select>
	</form>
	<br/>
		<div id="txtHint">Visualizar los proyectos</div>
		
		<br/>
		<br/>
		<br/>
		<div>
		<form action="AddProyecto.jsp?show=1"  method="post" >
		<input type="submit" value="Nuevo Proyecto" />
		</form>
				</div>
				<br/>
		<br/>
		</td>
		</tr>
		<tr>
		<td>
		
		</td>
		</tr>
		
			</tbody>
			</table>
			
		<div id="footer">
			<jsp:include page="../include/footer.jsp"><jsp:param
			name="ruta" value="<%=ruta%>"></jsp:param></jsp:include>
			</div>
		</div>
		</div>
</body>
</html>