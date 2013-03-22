<?xml version="1.0" encoding="ISO-8859-1" ?>
<%@page import="com.clase.dao.ProyectosDao"%>
<%@page import="com.clase.models.Proyecto"%>
<%@page import="com.clase.models.Usuario"%>
<%@page import="com.clase.dao.ChequesDao"%>
<%@page import="com.clase.models.Cheques"%>
<%@page import="com.clase.dao.ProveedorDao"%>
<%@page import="com.clase.models.Proveedor"%>
<%@page import="com.clase.models.PersonalProyecto"%>
<%@page import="java.text.DecimalFormat"%>
<%@page import="com.clase.dao.PersonalDao"%>
<%@page import="com.clase.models.Personal"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1" />
<%
	Proveedor myProv = new Proveedor(); //cambiar a cheques
	
	
	

	DecimalFormat format = new DecimalFormat("###,###.##"); //para formato

	//Formato de fechas
	java.util.Date date = new java.util.Date();
	java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat(
			"dd/MM/yyyy");

	//obtener ruta del contexto
	String ruta = request.getContextPath();

	/*Obtener la lista de Proveedores*/
	ArrayList<Proveedor> ListProveedor = new ArrayList<Proveedor>();
	ProveedorDao serviceProveedor = new ProveedorDao();
	ListProveedor = serviceProveedor.getLista();
	
	/*Obtener Lista de Proyectos*/
	ArrayList<Proyecto> ListProyecto = new ArrayList<Proyecto>();
	ProyectosDao serviceProyecto = new ProyectosDao();
	ListProyecto = serviceProyecto.getLista();
	
	//Datos del cheque
	Cheques cheque = new Cheques();
	ChequesDao servicePersonal1 = new ChequesDao();
		String NombrePersona = "Prueba";
		int idCheque = 0;
	
		//Para verificar el usuario autenticado
		Usuario user = new Usuario();
		user = (Usuario) session.getAttribute("usuario");

		if (user == null) {
			response.sendRedirect(ruta + "/login.jsp");
			return;
		}
		
		int table=0;
		try {
			table =Integer.parseInt(request.getParameter("idProy"));
			System.out.println("Entra aquí con Parámetro (table) : " + table);
		} catch (Exception e) {
			table = 0;
		}
		int param = 0;
		
		try {
			idCheque = Integer.parseInt(request.getParameter("idCheque"));
			System.out.println("Entra aquí con Parámetro (idCheque): " + idCheque);
		} catch (Exception e) {
			idCheque = 0;
		}
		
		if (table!=0) {
			param = table;
			System.out.println("Entra aquí con Parámetro: " + table);
			//ProyectosDao servicio = new ProyectosDao();
			//proy = servicio.findById( param);

		}

		
		
	%>
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
	
<title>Agregando Cheques</title>
</head>
<body onload="P7_initPM(0,1,0,0,-1)">
<form action="../Cheques.do?" method="post"
		class="cmxform">
		<fieldset>
		<legend>Cheques de Proyecto</legend>
		<table border="0" width="50%" class="CssTable">
		<tr>
		<td align="left"><label>Proveedor:</label>
		
		</td>
		<td align="left"><select name="idProveedor"
						style="width: 250px" id="idProveedor">
							<option value="-1">Selecione Aquí..</option>
							<%
								for (int i = 0; i < ListProveedor.size(); i++) {
									if (ListProveedor.get(i).getIdProveedor() == cheque.getIdProveedor()
										) {
						 %>
					<option value="<%=ListProveedor.get(i).getIdProveedor() %>"
								selected="selected"><%=ListProveedor.get(i).getNombre()%></option>
							 <% 
								} else {
							%>  
							<%-- <option value="<%=ListEmpleado.size() %>"><%=ListEmpleado.size()%></option> --%>
							<option value="<%=ListProveedor.get(i).getIdProveedor() %>"><%=ListProveedor.get(i).getNombre()%></option>
						
						 <%
								}
								}
							%> 
					</select></td>
		</tr>
		<%-- <tr>
		<td align="left"><label>Proyecto:</label>
		
		</td>
		<td align="left"><select name="idProyecto"
						style="width: 250px" id="idProyecto">
							<option value="-1">Selecione Aquí..</option>
							<%
								for (int i = 0; i < ListProyecto.size(); i++) {
									if (ListProyecto.get(i).getIdProyecto() == cheque.getIdProyecto()
										) {
						 %>
					<option value="<%=ListProyecto.get(i).getIdProyecto() %>"
								selected="selected"><%=ListProyecto.get(i).getNombre()%></option>
							 <% 
								} else {
							%>  
							<option value="<%=ListEmpleado.size() %>"><%=ListEmpleado.size()%></option>
							<option value="<%=ListProyecto.get(i).getIdProyecto() %>"><%=ListProyecto.get(i).getNombre()%></option>
						
						 <%
								}
								}
							%> 
					</select></td>
		
		</tr> --%>
		<tr>
		<td align="left"><label>Monto:</label>
		
		</td>
		<td align="left"><input id="monto" style="width: 247px"
											name="monto" type="text" size="10"
											value="<%=cheque.getMonto() %>" /></td>
		</tr>
		
		<tr>
		<td align="left"><label>Fecha Emisión:</label></td>
		<td align="left"><input id="fechaEmision" style="width: 247px"
											name="fechaEmision" type="text" size="10"
											value="<%=cheque.getFechaEmision() %>" /></td>
		</tr>
		
		<tr>
		<td align="left"><label>Número de Cheque:</label></td>
		<td align="left"><input id="numCheque" style="width: 247px"
											name="numCheque" type="text" size="10"
											value="<%=cheque.getNumCheque() %>" /></td>
		</tr>
		<tr>
		<td align="left"><label>Concepto:</label></td>
		<td align="left"><textarea name="concepto" style="width: 247px"
												id="concepto" ><%=cheque.getConcepto() %></textarea></td>
		</tr>
		<tr>
		<td align="left"><label>Estado:</label></td>
		<td align="left"><select name="estado"
											style="width: 250px" id="estado">
												<option value="1"
													<%if (cheque.getEstado().contains("1")) {%>
													selected="selected" <%}%>>Activo</option>
												<option value="0"
													<%if (cheque.getEstado().contains("0")) {%>
													selected="selected" <%}%>>Inactivo</option>
										</select></td>
		</tr>
		</table>
		<input type="hidden" id="idProy" name="idProy"
									value="<%=param%>" /> 
			<input type="hidden" id="accion" name="accion" value="" />
				<br />
				
				<p class="submit">
					
					<button type="submit" onclick="this.form.accion.value='nuevo';">Agregar Cheque</button>
				</p>
		</fieldset>
		
</form>		

</body>
</html>