<?xml version="1.0" encoding="ISO-8859-1" ?>
<%@page import="com.clase.dao.CargoDao"%>
<%@page import="com.clase.models.Cargo"%>
<%@page import="com.clase.dao.OficioDao"%>
<%@page import="com.clase.models.Oficio"%>
<%@page import="com.clase.dao.PersonalDao"%>
<%@page import="com.clase.models.Personal"%>
<%@page import="com.clase.models.PersonalProyecto"%>
<%@page import="com.clase.dao.TipoOfertaDao"%>
<%@page import="com.clase.models.TipoOferta"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.text.DecimalFormat"%>
<%@page import="com.clase.models.Usuario"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1" />
<%
	PersonalProyecto myPers = new PersonalProyecto();

	DecimalFormat format = new DecimalFormat("###,###.##"); //para formato

	//Formato de fechas
	java.util.Date date = new java.util.Date();
	java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat(
			"dd/MM/yyyy");

	//obtener ruta del contexto
	String ruta = request.getContextPath();

	/*Obtener la lista de Personal*/
	ArrayList<Personal> ListPersonal = new ArrayList<Personal>();
	PersonalDao servicePersonal = new PersonalDao();
	ListPersonal = servicePersonal.getLista();
	
	/*Obtener Lista de Oficio*/
	ArrayList<Oficio> ListOficio = new ArrayList<Oficio>();
	OficioDao serviceOficio = new OficioDao();
	ListOficio = serviceOficio.getLista();
	
	/*Obtener Lista de Cargo*/
	ArrayList<Cargo> ListCargo = new ArrayList<Cargo>();
	CargoDao serviceCargo = new CargoDao();
	ListCargo = serviceCargo.getLista();

	//Datos de la Persona
	Personal myPersona = new Personal();
	PersonalDao servicePersonal1 = new PersonalDao();
	String NombrePersona = "Prueba";
	int idPersona = 0;

	//Para verificar el usuario autenticado
	Usuario user = new Usuario();
	user = (Usuario) session.getAttribute("usuario");

	if (user == null) {
		response.sendRedirect(ruta + "/login.jsp");
		return;
	}

	String table="0";
	try {
		table = request.getParameter("idProy");
		System.out.println("Entra aquí con Parámetro (table) : " + table);
	} catch (Exception e) {
		table = "0";
	}

	int param = 0;

	int idPersProy;
	try {
		idPersProy = Integer.parseInt(request.getParameter("idPersProy"));
		System.out.println("Entra aquí con Parámetro (idPersProy): " + idPersProy);
	} catch (Exception e) {
		idPersProy = 0;
	}
	
	if (!table.isEmpty() && !table.contains("0")) {
		param = Integer.parseInt(table);
		System.out.println("Entra aquí con Parámetro: " + table);
		//ProyectosDao servicio = new ProyectosDao();
		//proy = servicio.findById( param);

	}
%>


<title>Agregar Personal</title>
</head>
<body onload="P7_initPM(0,1,0,0,-1)">

	<form action="../PersonalProy.do?" method="post"
		class="cmxform">
		<fieldset>
			<legend>Agregar Personal Proyecto</legend>
			<table border="0" width="50%" class="CssTable">

				<tr>
					<td align="left"><label>Persona:</label></td>
					<td align="left"><select name="idPersonal"
						style="width: 250px" id="idPersonal">
							<option value="-1">Selecione Aquí..</option>
							<%
								for (int i = 0; i < ListPersonal.size(); i++) {
									if (ListPersonal.get(i).getIdPersonal() == myPers
											.getIdPersonal()) {
							%>
							<option value="<%=ListPersonal.get(i).getIdPersonal()%>"
								selected="selected"><%=ListPersonal.get(i).getNombre()%></option>
							<%
								} else {
							%>
							<%-- <option value="<%=ListEmpleado.size() %>"><%=ListEmpleado.size()%></option> --%>
							<option value="<%=ListPersonal.get(i).getIdPersonal()%>"><%=ListPersonal.get(i).getNombre()%></option>

							<%
								}
								}
							%>
					</select></td>
				</tr>

<tr>
					<td align="left"><label>Cargo:</label></td>
					<td align="left"><select name="idCargo"
						style="width: 250px" id="idCargo">
							<option value="-1">Selecione Aquí..</option>
							<%
								for (int i = 0; i < ListCargo.size(); i++) {
									if (ListCargo.get(i).getIdCargo() == myPers
											.getIdCargo() ) {
							%>
							<option value="<%=ListCargo.get(i).getIdCargo() %>"
								selected="selected"><%=ListCargo.get(i).getNombreCargo()%></option>
							<%
								} else {
							%>
							
							<option value="<%=ListCargo.get(i).getIdCargo() %>"><%=ListCargo.get(i).getNombreCargo() %></option>

							<%
								}
								}
							%>
					</select></td>
					</tr>
					
					
					<tr>
					<td align="left"><label>Oficio:</label></td>
					<td align="left"><select name="idOficio"
						style="width: 250px" id="idOficio">
							<option value="-1">Selecione Aquí..</option>
							<%
								for (int i = 0; i < ListOficio.size(); i++) {
									if (ListOficio.get(i).getIdOficio() == myPers
											.getIdOficio() ) {
							%>
							<option value="<%=ListOficio.get(i).getIdOficio() %>"
								selected="selected"><%=ListOficio.get(i).getOficio()  %></option>
							<%
								} else {
							%>
							<%-- <option value="<%=ListEmpleado.size() %>"><%=ListEmpleado.size()%></option> --%>
							<option value="<%=ListOficio.get(i).getIdOficio() %>"><%=ListOficio.get(i).getOficio() %></option>

							<%
								}
								}
							%>
					</select></td>
					</tr>
										<tr>
					<td align="left"><label>Fecha de Inicio:</label></td>
					<td align="left"><input id="fechaInicio" style="width: 247px"
											name="fechaInicio" type="text" size="100"
											value="<%=myPers.getFechaInicio() %>" /></td>
					</tr>
							<tr>
					<td align="left"><label>Fecha Final:</label></td>
					<td align="left"><input id="fechaFinal" style="width: 247px"
											name="fechaFinal" type="text" size="100"
											value="<%=myPers.getFechaFinal() %>" /></td>
					</tr>
			</table>
			<input type="hidden" id="idProy" name="idProy"
									value="<%=param%>" /> 
			<input type="hidden" id="accion" name="accion" value="" />
				<br />
	<br />
				
				<%
					if (idPersProy != 0) {
				%>
				
				 <input
					type="hidden" id="idPersProy" name="idPersProy" value="<%=idPersProy%>" />
				<p class="submit">
					<button type="submit" onclick="this.form.accion.value='edit';">Editar Personal</button>
				</p>
				<%
					} else {
				%>
				
				<p class="submit">
					
					<button type="submit" onclick="this.form.accion.value='nuevo';">Agregar Personal</button>
				</p>
				<%
					}
				%>
				<br />
	<br />
				<!-- <p class="submit">
					<button type="submit" onclick="this.form.accion.value='cancel';">Cancelar</button>
				</p> -->
				<br />
	<br />
		</fieldset>

	</form>
</body>
</html>