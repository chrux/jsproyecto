<?xml version="1.0" encoding="ISO-8859-1" ?>
<%@page import="com.clase.dao.UsuarioDao"%>
<%@page import="com.clase.dao.MonedaDao"%>
<%@page import="com.clase.models.Moneda"%>
<%@page import="com.clase.dao.ClienteDao"%>
<%@page import="com.clase.models.Cliente"%>
<%@page import="com.clase.dao.TipoOfertaDao"%>
<%@page import="com.clase.models.TipoOferta"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.clase.dao.ProyectosDao"%>
<%@page import="com.clase.models.Proyecto"%>
<%@page import="com.clase.models.Usuario"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<%
	Proyecto myProy = new Proyecto();
/*Obtener la lista de Tipo de Oferta*/
ArrayList<TipoOferta> ListOfer= new ArrayList<TipoOferta>();
TipoOfertaDao  serviceTipoOferta = new TipoOfertaDao();
ListOfer = serviceTipoOferta.getLista();

//Variables para cliente
ArrayList<Cliente> ListClient= new ArrayList<Cliente>();
ClienteDao serviceClient = new ClienteDao();
ListClient = serviceClient.getLista();

//Variables para Moneda
ArrayList<Moneda> ListMoney= new ArrayList<Moneda>();
MonedaDao serviceMoney = new MonedaDao();
ListMoney = serviceMoney.getLista();

	String mensaje = request.getParameter("mensaje");
	int show=0;
	try {
	show = Integer.parseInt(request.getParameter("show"));
	} catch (Exception e) {
		show=0;
	}
	int id = 0;
	try {
		id = Integer.parseInt(request.getParameter("idProy"));
	} catch (Exception e) {
		id = 0;
	}

	if (id != 0) {
		ProyectosDao servicio = new ProyectosDao();
		myProy = servicio.findById(id);
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
<link href="<%=ruta%>/css/_vti_cnf/jquery-ui-timepicker-addon.css"
	type="text/css" media="all" rel="stylesheet" />
<link href="<%=ruta%>/css/_vti_cnf/jquery-ui-1.9.2.custom.css"
	type="text/css" media="all" rel="stylesheet" />
<link href="<%=ruta%>/css/menu.css" rel="stylesheet" type="text/css" />
<script src="<%=ruta%>/js/jquery-1.8.3.js" type="text/javascript"></script>
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
<link rel="stylesheet" type="text/css"
	href="<%=ruta%>/css/style-ie6.css" />
<!--<![endif]-->


<script>
	function showPersonal(str) {
		alert("Entra aquí");

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
		alert("Entra aquí " + str);
		xmlhttp.open("GET", "PersonalProyecto.jsp?idProy=" + str, true);
		xmlhttp.send();
	}
</script>


<title>Proyectos</title>
</head>
<body onload="P7_initPM(0,1,0,0,-1)">
	<div id="Container" style="width: 1050px; background-color: #fff;">
		<div id="header" style="width: 1010px;">
			<h1 style="color: #033C24;">
				Usuario:
				<%=user.getNombre()%>
			</h1>

		</div>
		<div id="navigation" style="width: 1050px;">
			<a
				style="color: #fff; text-decoration: none; padding-left: 4px; padding-top: 4px; display: block; float: left;"
				href="<%=ruta%>/login.do?cierre=si">Cerrar Sesión</a>

		</div>
		<!-- 	<div id="test" style="width: 1420px;">
		<br /> <br />
		</div> -->
		<table border="0" cellpadding="0" cellspacing="0" width="100%">
			<tbody>
				<tr>
					<td id="leftcontent" nowrap="nowrap" valign="top" width="190">
						<div class="bar">
							<img src="<%=ruta%>/images/hr_dots1.gif" alt="" height="3"
								width="150" />
						</div> <!-- ** Menu Start ** //--> <jsp:include
							page="../include/menu.jsp"><jsp:param name="ruta"
								value="<%=ruta%>"></jsp:param></jsp:include> <!-- ** Menu End ** //-->


					</td>
					<td valign="top" width="100%">
						<%
							if (mensaje != null) {
						%>
						<p style="color: red;"><%=mensaje%></p> <%
 	}
 %>
						<form action="../AddProyecto.do" method="post" id="form2"
							style="background-color: #fff; color: #033C24; width: '100%';">
							<h1>
								<span> Proyecto</span>
							</h1>
							<fieldset>
								<legend>Proyecto</legend>
								<table width="50%">
									<tr>
										<td align="left"><label>Número de Oferta:</label></td>
										<td align="left"><input id="numOferta"
											style="width: 246px" name="numOferta" type="text" size="10"
											value="<%=myProy.getNumOferta()%>" /><br /></td>
									</tr>
									<tr>
										<td align="left"><label>Tipo Oferta:</label></td>
										<td align="left"><select name="idTipoOferta"
											style="width: 250px" id="idTipoOferta">
												<option value="-1">Selecione Aquí..</option>
												<%
													for (int i = 0; i < ListOfer.size(); i++) {
														if (ListOfer.get(i).getIdTipoOferta() == myProy
																.getIdTipoOferta()) {
												%>
												<option value="<%=ListOfer.get(i).getIdTipoOferta()%>"
													selected="selected"><%=ListOfer.get(i).getNombreOferta()%></option>
												<%
													} else {
												%>
												<%-- <option value="<%=ListEmpleado.size() %>"><%=ListEmpleado.size()%></option> --%>
												<option value="<%=ListOfer.get(i).getIdTipoOferta()%>"><%=ListOfer.get(i).getNombreOferta()%></option>

												<%
													}
													}
												%>
										</select></td>
									</tr>
									<tr>
										<td align="left"><label>Cliente:</label></td>
										<td align="left"><select name="idCliente"
											style="width: 250px" id="idCliente">
												<option value="-1">Selecione Aquí..</option>
												<%
													for (int i = 0; i < ListClient.size(); i++) {
														if (ListClient.get(i).getIdCliente() == myProy.getIdCliente()) {
												%>
												<option value="<%=ListClient.get(i).getIdCliente()%>"
													selected="selected"><%=ListClient.get(i).getNombre()%>
												</option>
												<%
													} else {
												%>
												<option value="<%=ListClient.get(i).getIdCliente()%>"><%=ListClient.get(i).getNombre()%></option>

												<%
													}
													}
												%>
										</select></td>
									</tr>
									<tr>
										<td align="left"><label>Moneda:</label></td>
										<td align="left"><select name="idMoneda"
											style="width: 250px" id="idMoneda">
												<option value="-1">Selecione Aquí..</option>
												<%
													for (int i = 0; i < ListMoney.size(); i++) {
														if (ListMoney.get(i).getIdMoneda() == myProy.getIdMoneda()) {
												%>
												<option value="<%=ListMoney.get(i).getIdMoneda()%>"
													selected="selected"><%=ListMoney.get(i).getNombreMoneda()%>
												</option>
												<%
													} else {
												%>
												<option value="<%=ListMoney.get(i).getIdMoneda()%>"><%=ListMoney.get(i).getNombreMoneda()%>
												</option>

												<%
													}
													}
												%>

										</select></td>
									</tr>
									<tr align="left">
										<td align="left"><label>Nombre de Proyecto:</label></td>
										<td><input id="nombre" style="width: 247px" name="nombre"
											type="text" size="50" value="<%=myProy.getNombre()%>" /></td>
									</tr>

									<tr align="left">
										<td align="left"><label>Monto de Oferta:</label></td>
										<td><input id="montoOferta" style="width: 247px"
											name="montoOferta" type="text" size="10"
											value="<%=myProy.getMontoOferta()%>" /></td>
									</tr>

									<tr align="left">
										<td align="left"><label>Observaciones:</label></td>
										<td><textarea name="observaciones" style="width: 247px"
												id="observaciones"><%=myProy.getObservaciones()%></textarea>
											<%-- <input id="observaciones" style="width:247px" name="observaciones" type="text"
						size="100"  value="<%=myProy.getObservaciones() %>" /> --%></td>
									</tr>


									<tr>
										<td align="left"><label>Fecha de Oferta:</label></td>
										<td align="left"><input id="fechaOferta"
											style="width: 247px" name="fechaOferta" type="text"
											size="100" value="<%=myProy.getFechaOferta()%>" /></td>
									</tr>


									<tr>
										<td align="left"><label>Fecha de Aceptación:</label></td>
										<td align="left"><input id="fechaAceptacion"
											style="width: 247px" name="fechaAceptacion" type="text"
											size="100" value="<%=myProy.getFechaAceptacion()%>" /></td>
									</tr>

									<tr align="left">
										<td align="left"><label>Fecha de Cierre:</label></td>
										<td><input id="fechaCierre" style="width: 247px"
											name="fechaCierre" type="text" size="100"
											value="<%=myProy.getFechaCierre()%>" /></td>
									</tr>

									<tr align="left">


										<td align="left"><label>Estado:</label></td>
										<td align="left"><select name="estado"
											style="width: 250px" id="estado">
												<option value="1"
													<%if (myProy.getEstado().contains("1")) {%>
													selected="selected" <%}%>>Activo</option>
												<option value="0"
													<%if (myProy.getEstado().contains("0")) {%>
													selected="selected" <%}%>>Inactivo</option>
										</select></td>

									</tr>
								</table>
								<input type="hidden" id="accion" name="accion" value="" /> <br />
								<br />
								<%
									if (show == 1) {
								%>
								<%
									if (id != 0) {
								%>

								<input type="hidden" id="IdProyecto" name="IdProyecto"
									value="<%=id%>" />
								<p class="submit">
									<button type="submit" onclick="this.form.accion.value='edit';">Editar
										Proyecto</button>
								</p>
								<%
									} else {
								%>

								<p class="submit">

									<button type="submit" onclick="this.form.accion.value='nuevo';">Agregar
										Proyecto</button>
								</p>
								<%
									}
								%>
								<%
									}
								%>
								<br /> <br />
								<p class="submit"></p>
								<br /> <br /> <br /> <br /> <br />
							</fieldset>
						</form>

					</td>
				</tr>
			</tbody>
		</table>
		<div class="anybar">
			<img src="<%=ruta%>/images/bar_green.gif" alt="" height="4"
				width="900" />
		</div>
		<%
			if (show == 1) {
		%>
		<div id="detalle" style="height: 280px; width: 800px; color: #0B3B17;">
			<%
				if (id != 0) {
			%>
			<jsp:include page="../procesos/AddPersonalProyecto.jsp">
				<jsp:param value="<%=id%>" name="idProy" />
			</jsp:include>
			<%
				}
			%>
		</div>
		<%
			} else {
		%>
		<div id="detalle" style="height: 280px; width: 800px; color: #0B3B17;">
			<%
				if (id != 0) {
			%>
			<jsp:include page="../procesos/AddCheques.jsp">
				<jsp:param value="<%=id%>" name="idProy" />
			</jsp:include>
			</div>
			<%
				}
				}
			%>
			
			<div class="anybar">
				<img src="<%=ruta%>/images/bar_green.gif" alt="" height="4"
					width="900" />
			</div>
			<%
				if (show == 1) {
			%>
			<div id="grid"
				style="height: 250px; width: 800px; color: #0B3B17; bakcground: white">

				<%
					if (id != 0) {
				%>
				<jsp:include page="../procesos/PersonalProyecto.jsp">
					<jsp:param value="<%=id%>" name="idProy" />
				</jsp:include>

				<%
					}
				%>

			</div>

			<%
				} else {
			%>
			<div id="grid"
				style="height: 250px; width: 800px; color: #0B3B17; bakcground: white">
				<%
					if (id != 0) {
				%>
				<jsp:include page="../procesos/ListaCheques.jsp">
					<jsp:param value="<%=id%>" name="idProy" />
				</jsp:include>
</div>
				<%
					}
					}
				if (show == 1) {
			%>
				<form action="Proyectos.jsp">
					<input type="submit" value="Regresar" /> <br /> <br /> <br />
				</form>
				<%
					}
				else
				{
				%>
				<form action="ProyectosCheques.jsp">
					<input type="submit" value="Regresar" /> <br /> <br /> <br />
				</form>
				<%
					}%>
				<div id="footer" style="color: #0B3B17;">
					<jsp:include page="../include/footer.jsp"><jsp:param
							name="ruta" value="<%=ruta%>"></jsp:param></jsp:include>
				</div>
			</div>
</body>
</html>