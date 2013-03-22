<?xml version="1.0" encoding="ISO-8859-1" ?>
<%@page import="com.clase.dao.UsuarioDao"%>
<%@page import="com.clase.dao.CargoDao"%>
<%@page import="com.clase.models.Cargo"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.clase.models.Usuario"%>
<%@page import="com.clase.servlets.ServiceBanco"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<%
	Cargo miCargo = new Cargo();
String mensaje = request.getParameter("mensaje");
	int id = 0;
	try {
		id = Integer.parseInt(request.getParameter("IdCargo"));
	} catch (Exception e) {
		id = 0;
	}

	if (id != 0) {
		CargoDao servicio = new CargoDao();
		miCargo = servicio.findByID(id);
	}
	
	String ruta = request.getContextPath();
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

	

%>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1" />
<link href="<%=ruta%>/css/menu.css" rel="stylesheet" type="text/css" />
<link href="<%=ruta%>/css/style.css" rel="stylesheet" type="text/css" />
<link href="<%=ruta%>/css/tabs.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="<%=ruta%>/js/tabs.js"></script>
<script type="text/javascript" src="<%=ruta%>/js/menu.js"></script>
<link rel="stylesheet" type="text/css" href="<%=ruta%>/css/style3.css" />
<link rel="stylesheet" type="text/css" href="<%=ruta%>/css/style2.css" />
<!--[if lt IE 8.]>
<link rel="stylesheet" type="text/css" href="<%=ruta%>/css/style-ie.css" />
<![endif]-->
<!--[if lt IE 7.]>-->
<link rel="stylesheet" type="text/css" href="<%=ruta%>/css/style-ie6.css" />
<!--<![endif]-->
<title>Agregar Cargo</title>
</head>
<body onload="P7_initPM(0,1,0,0,-1)">
	<div id="Container" style="width: 1000px; background-color:#fff; " >
	<div id="header">
			<h1 style="color:#033C24;" >
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
	<% if(mensaje!=null) {%>
	<p style="color: red;"><%=mensaje %></p>
	<%} %>
	<br />
	<br />
		<form action="../Cargo.do" method="post" id="form2"  style="background-color:#fff;color:#033C24;width:'100%';">
			<h1>
			<% if(id!=0) {%>
				<span>Editar Cargo</span>
	<%}
			else {%>
				<span>Agregar Cargo</span>
				<%}%>
			</h1>
			<fieldset>
			<% if(id!=0) {%>
			<legend> Editar Cargo</legend>
			<%}
			else {%>
			<legend> Agregar Cargo</legend>
			<%}%>
			<table width="50%">
			<tr>
			<td align="left">
			<label>Nombre Cargo:</label>
			</td>
			<td align="left">
			<input id="nombreCargo" style="width:125px" name="nombreCargo" type="text"
						size="10" value="<%=miCargo.getNombreCargo() %>" /><br />
						</td>
			</tr>
			<tr>
			<td align="left">
			<label>Estado:</label>
			</td>
			<td align="left">
			<select name="estado" style="width:128px" id="estado">
		 		<option value="1" <% if (miCargo.getEstado().contains("1")){ %> selected="selected"<% } %> >Activo</option>
		 		<option value="0" <% if (miCargo.getEstado().contains("0")){ %> selected="selected"<% } %> >Inactivo</option>
		 		</select>
			</td>
			
			</tr>
			</table>
			<p class="first">
					
				</p>
				<p>
					

				</p>
				<input type="hidden" id="accion" name="accion" value="" />
				<br />
	<br />
				
				<%
					if (id != 0) {
				%>
				
				 <input
					type="hidden" id="IdCargo" name="IdCargo" value="<%=id%>" />
				<p class="submit">
					<button type="submit" onclick="this.form.accion.value='edit';">Editar Cargo</button>
				</p>
				<%
					} else {
				%>
				
				<p class="submit">
					
					<button type="submit" onclick="this.form.accion.value='nuevo';">Agregar Cargo</button>
				</p>
				<%
					}
				%>
				<br />
	<br />
				<p class="submit">
					<button type="submit" onclick="this.form.accion.value='cancel';">Cancelar</button>
				</p>
				<br />
	<br />
				<br />
	<br /><br />
	<br />
			</fieldset>
			<br />
	<br /><br />
	<br />
		</form>
		
		</td>
		</tr>
		</tbody>
		</table>
<div id="footer" style="color: #0B3B17;">
			<jsp:include page="../include/footer.jsp"><jsp:param
			name="ruta" value="<%=ruta%>"></jsp:param></jsp:include>
			</div>
	</div>
</body>
</html>