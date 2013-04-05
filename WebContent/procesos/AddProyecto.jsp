<?xml version="1.0" encoding="utf-8" ?>
<%@ page language="java"
	  contentType="text/html; charset=utf-8"
	 pageEncoding="utf-8"%>
<%
String ruta = request.getContextPath();
String mensaje = request.getParameter("mensaje");
%>
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
<%@page import="java.text.SimpleDateFormat"%>
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
	<title>Proyecto | ProjectManager</title>
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
	config.getServletContext().log("Ruta: " + name);
	SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy");
%>
	<link href="<%= ruta %>/assets/css/bootstrap.css" rel="stylesheet">
    <link href="<%= ruta %>/assets/css/bootstrap-responsive.css" rel="stylesheet">
    <link href="<%= ruta %>/assets/css/todc-bootstrap.css" rel="stylesheet">
    <link href="<%= ruta %>/assets/css/style.css" rel="stylesheet">
    <link href="<%= ruta %>/assets/css/datepicker.css" rel="stylesheet">

    <!-- HTML5 shim, for IE6-8 support of HTML5 elements -->
    <!--[if lt IE 9]>
    <script src="<%= ruta %>/assets/js/modernizr.js"></script>
    <![endif]-->
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
</head>
<body>
	<div class="container-narrow">
      <div class="masthead">
        <ul class="nav nav-pills pull-right">
        <jsp:include page="../include/menu.jsp">
          <jsp:param name="ruta" value="<%=ruta %>"></jsp:param>
        </jsp:include>
        </ul>
        <h3 class="muted">Project Manager</h3>
      </div>

      <hr>
		
	  <div class="row-fluid">
      	<div class="span9">
      		<h3 class="no-margin"><% if (id != 0) { %>Editar<% } else { %>Nuevo<% } %> Proyecto</h3>
      	</div>
      	<div class="span3 align-right">
      		&nbsp;
      	</div>
      </div>
      
      <div>
      <form class="form-horizontal" action="<%= ruta %>/AddProyecto.do">
      	  <% if (id != 0) { %>
      	  <input type="hidden" id="IdProyecto" name="IdProyecto" value="<%=id%>" />
      	  <input type="hidden" id="accion" name="accion" value="edit" />
      	  <% } else { %>
      	  <input type="hidden" id="accion" name="accion" value="nuevo" />
      	  <% } %>
		  <div class="control-group">
		    <label class="control-label" for="numOferta">Número de Oferta</label>
		    <div class="controls">
		      <input type="text" id="numOferta" name="numOferta" value="<%= ( myProy.getNumOferta() > 0 ? myProy.getNumOferta() : "" ) %>" placeholder="Número de Oferta">
		    </div>
		  </div>
		  <div class="control-group">
		    <label class="control-label" for="idTipoOferta">Tipo Oferta</label>
		    <div class="controls">
		      <select name="idTipoOferta" style="width: 250px" id="idTipoOferta">
				<option value="-1">Selecione Aquí..</option>
				<%
				for (int i = 0; i < ListOfer.size(); i++) {
					if (ListOfer.get(i).getIdTipoOferta() == myProy.getIdTipoOferta()) {
				%>
				<option value="<%=ListOfer.get(i).getIdTipoOferta()%>" selected="selected"><%=ListOfer.get(i).getNombreOferta()%></option>
				<%
					} else {
				%>
				<option value="<%=ListOfer.get(i).getIdTipoOferta()%>"><%=ListOfer.get(i).getNombreOferta()%></option>
				<%
					}
				}
				%>
			  </select>
		    </div>
		  </div>
		  <div class="control-group">
		    <label class="control-label" for="idCliente">Cliente</label>
		    <div class="controls">
		      <select name="idCliente" style="width: 250px" id="idCliente">
				<option value="-1">Selecione Aquí..</option>
				<%
				for (int i = 0; i < ListClient.size(); i++) {
					if (ListClient.get(i).getIdCliente() == myProy.getIdCliente()) {
				%>
				<option value="<%=ListClient.get(i).getIdCliente()%>" selected="selected"><%=ListClient.get(i).getNombre()%></option>
				<%
					} else {
				%>
				<option value="<%=ListClient.get(i).getIdCliente()%>"><%=ListClient.get(i).getNombre()%></option>
				<%
					}
				}
				%>
			  </select>
		    </div>
		  </div>
		  <div class="control-group">
		    <label class="control-label" for="idMoneda">Moneda</label>
		    <div class="controls">
		      <select name="idMoneda" style="width: 250px" id="idMoneda">
				<option value="-1">Selecione Aquí..</option>
				<%
				for (int i = 0; i < ListMoney.size(); i++) {
					if (ListMoney.get(i).getIdMoneda() == myProy.getIdMoneda()) {
				%>
				<option value="<%=ListMoney.get(i).getIdMoneda()%>" selected="selected"><%=ListMoney.get(i).getNombreMoneda()%></option>
				<%
					} else {
				%>
				<option value="<%=ListMoney.get(i).getIdMoneda()%>"><%=ListMoney.get(i).getNombreMoneda()%></option>
				<%
					}
				}
				%>
			  </select>
		    </div>
		  </div>
		  <div class="control-group">
		    <label class="control-label" for="nombre">Nombre del Proyecto</label>
		    <div class="controls">
		      <input type="text" id="nombre" name="nombre" value="<%=myProy.getNombre()%>" placeholder="Nombre del Proyecto">
		    </div>
		  </div>
		  <div class="control-group">
		    <label class="control-label" for="montoOferta">Monto de la Oferta</label>
		    <div class="controls">
		      <input type="text" id="montoOferta" name="montoOferta" value="<%=myProy.getMontoOferta()%>" placeholder="Monto de la Oferta">
		    </div>
		  </div>
		  <div class="control-group">
		    <label class="control-label" for="observaciones">Observaciones</label>
		    <div class="controls">
		      <textarea id="observaciones" name="observaciones" style="width: 247px; height: 100px;"><%=myProy.getObservaciones()%></textarea>
		    </div>
		  </div>
		  <div class="control-group">
		    <label class="control-label" for="fechaOferta">Fecha de la Oferta</label>
		    <div class="controls">
		      <div class="input-append date" id="dp1" data-date="<% if ( myProy != null ) { %><%=df.format(myProy.getFechaOferta()) %><% } %>" data-date-format="dd-mm-yyyy">
				<input class="span2" size="16" type="text" id="fechaOferta" name="fechaOferta" value="<% if ( myProy != null ) { %><%=df.format(myProy.getFechaOferta()) %><% } %>" readonly="" placeholder="Fecha de la Oferta">
				<span class="add-on"><i class="icon-calendar"></i></span>
			  </div>
		    </div>
		  </div>
		  <div class="control-group">
		    <label class="control-label" for="fechaAceptacion">Fecha de Aceptación</label>
		    <div class="controls">
		      <div class="input-append date" id="dp2" data-date="<% if ( myProy != null ) { %><%=df.format(myProy.getFechaAceptacion()) %><% } %>" data-date-format="dd-mm-yyyy">
				<input class="span2" size="16" type="text" id="fechaAceptacion" name="fechaAceptacion" value="<% if ( myProy != null ) { %><%=df.format(myProy.getFechaAceptacion()) %><% } %>" readonly="" placeholder="Fecha de Aceptación de la Oferta">
				<span class="add-on"><i class="icon-calendar"></i></span>
			  </div>
		    </div>
		  </div>
		  <div class="control-group">
		    <label class="control-label" for="fechaCierre">Fecha de Cierre</label>
		    <div class="controls">
		      <div class="input-append date" id="dp3" data-date="<% if ( myProy != null ) { %><%=df.format(myProy.getFechaCierre()) %><% } %>" data-date-format="dd-mm-yyyy">
				<input class="span2" size="16" type="text" id="fechaCierre" name="fechaCierre" value="<% if ( myProy != null ) { %><%=df.format(myProy.getFechaCierre()) %><% } %>" readonly="" placeholder="Fecha de Cierre de la Oferta">
				<span class="add-on"><i class="icon-calendar"></i></span>
			  </div>
		    </div>
		  </div>
		  <div class="control-group">
		    <label class="control-label" for="estado">Estado</label>
		    <div class="controls">
		      <select name="estado" style="width: 250px" id="estado">
				<option value="1" <%if (myProy.getEstado().contains("1")) {%> selected="selected" <%}%>>Activo</option>
				<option value="0" <%if (myProy.getEstado().contains("0")) {%> selected="selected" <%}%>>Inactivo</option>
			  </select>
		    </div>
		  </div>
		  <div class="control-group">
		    <div class="controls">
		      <button type="submit" class="btn btn-primary">Guardar</button>
		      <a href="<%= ruta %>/procesos/Proyectos.jsp" class="btn">Cancelar</a>
		    </div>
		  </div>
		</form>
      </div>
      <div>
      <%
	  if (show == 1) {
	  %>
	  <%
		if (id != 0) {
	  %>
	  	<jsp:include page="../procesos/PersonalProyecto.jsp">
			<jsp:param value="<%=id%>" name="idProy" />
		</jsp:include>
	  <%
	    }
	  %>
	  <%
	  } else {
	  %>
	  <!-- <div id="grid" style="height: 250px; width: 800px; color: #0B3B17; bakcground: white"> -->
	  <%
		if (id != 0) {
	  %>
		<jsp:include page="../procesos/ListaCheques.jsp">
			<jsp:param value="<%=id%>" name="idProy" />
		</jsp:include>
	  <%
		}
	  }
      %>
      </div>
      <div>
      <%
	  if (show == 1) {
	  %>
	  <%
	  	if (id != 0) {
	  %>
	    <jsp:include page="../procesos/AddPersonalProyecto.jsp">
			<jsp:param value="<%=id%>" name="idProy" />
		</jsp:include>
	  <%
	    }
	  %>
	  <%
	  } else {
	  %>
	  <%
	  	if (id != 0) {
	  %>
	  	<jsp:include page="../procesos/AddCheques.jsp">
			<jsp:param value="<%=id%>" name="idProy" />
		</jsp:include>
	  <%
		}
	  %>
	  <%
	  }
	  %>
	  </div>	
	  <%	  
	  if (show == 1) {
	  %>
	  <a href="<%= ruta %>/procesos/Proyectos.jsp" class="btn">Regresar</a>
	  <%
	  } else {
	  %>
	  <a href="<%= ruta %>/procesos/ProyectosCheques.jsp" class="btn">Regresar</a>
	  <%
	  }
	  %>

      <jsp:include page="../include/footer.jsp">
      	<jsp:param name="ruta" value="<%=ruta%>"></jsp:param>
      </jsp:include>

    </div> <!-- /container -->
    
	<!-- Le javascript
    ================================================== -->
    <!-- Placed at the end of the document so the pages load faster -->
    <script src="<%= ruta %>/assets/js/jquery.js"></script>
    <script src="<%= ruta %>/assets/js/bootstrap.js"></script>
    <script src="<%= ruta %>/assets/js/bootstrap-datepicker.js"></script>
    <script type="text/javascript">
    	$(".date")
    		.datepicker();
    </script>
</body>
</html>