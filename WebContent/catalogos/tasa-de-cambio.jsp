<?xml version="1.0" encoding="utf-8" ?>
<%@ page language="java"
	  contentType="text/html; charset=utf-8"
	 pageEncoding="utf-8"%>
<%
String ruta = request.getContextPath();
String mensaje = request.getParameter("mensaje");
%>
<%@page import="com.clase.dao.UsuarioDao"%>
<%@page import="com.clase.models.Usuario"%>
<%@page import="java.util.ArrayList" %>
<%@page import="ni.gob.bcn.servicios.*" %>
<%@page import="java.util.Calendar" %>
<%@page import="org.apache.axis.message.MessageElement" %>
<%@page import="java.util.Iterator" %>
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
    <title>Tasa de Cambio - desde BCN | ProjectManager</title>
<%
Usuario user = new Usuario();
UsuarioDao permission = new UsuarioDao();

user = (Usuario) session.getAttribute("usuario");

if (user == null) {
	response.sendRedirect(ruta + "/login.jsp");
	return;
}

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

// Consumiendo Web service
Tipo_Cambio_BCNLocator serviceLocator = null;
Tipo_Cambio_BCNSoap soap = null;
RecuperaTC_MesResponseRecuperaTC_MesResult result = null;

java.util.Date nowDate = new java.util.Date();
Calendar cal = Calendar.getInstance();
cal.setTime(nowDate);
int currentMonth = cal.get(Calendar.MONTH) + 1;
int currentYear = cal.get(Calendar.YEAR);
try {
	serviceLocator = new Tipo_Cambio_BCNLocator();
	soap = serviceLocator.getTipo_Cambio_BCNSoap();
	result = soap.recuperaTC_Mes(currentYear, currentMonth);
	System.out.println();
} catch (Exception e) {
	e.printStackTrace();
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
      		<h3 class="no-margin">Tasa de Cambio (desde BCN) para <%= currentMonth %>/<%= currentYear %> </h3>
      	</div>
      	<div class="span3 align-right">
      		&nbsp;
      	</div>
      </div>
      <br />
	  <div>
	  <table class="table table-bordered">
		<thead>
			<tr>
				<th width="30">No.</th>
				<th>Mes</th>
				<th width="300">Córdobas por USD</th>
			</tr>
		</thead>
		<tbody>
			<%
				int i = 1;
				if ( null != result && result.get_any()[0].getLength() > 0 ) {
					Iterator it = result.get_any()[0].getChildElements();
					while (it.hasNext()) {
			%>
			<tr>
				<td><%=i++ %></td>
			<%
						MessageElement me = (MessageElement) it.next();
						Iterator it2 = me.getChildElements();
						int j = 0;
						while (it2.hasNext()) {
							j++;
							if ( j > 2 ) {
								break;
							}
							MessageElement me2 = (MessageElement) it2.next();
			%>
				<td><%= me2.getValue() %></td>			
			<%
						}
			%>
			</tr>
			<%
					}
				}
			%>
			
		
		</tbody>
		</table>
		<br/>
		<br/>
		</td>
		</tr>
	</tbody>
			</table>
	  </div>
	  
      <jsp:include page="../include/footer.jsp">
      	<jsp:param name="ruta" value="<%=ruta%>"></jsp:param>
      </jsp:include>

    </div> <!-- /container -->
    
    <!-- Le javascript
    ================================================== -->
    <!-- Placed at the end of the document so the pages load faster -->
    <script src="<%= ruta %>/assets/js/jquery.js"></script>
    <script src="<%= ruta %>/assets/js/bootstrap.js"></script>
</body>
</html>