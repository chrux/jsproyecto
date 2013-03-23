<?xml version="1.0" encoding="utf-8" ?>
<%@ page language="java"
	  contentType="text/html; charset=utf-8"
	 pageEncoding="utf-8"%>
<%
String ruta = request.getContextPath();
String mensaje = request.getParameter("mensaje");
%>
<%@page import="com.clase.dao.UsuarioDao"%>
<%@page import="com.clase.dao.ProyectosDao"%>
<%@page import="com.clase.models.Proyecto"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.clase.models.Usuario"%>
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
	<title>Reporte por Proyecto | ProjectManager</title>
<%
//Formato de fechas
java.util.Date date = new java.util.Date(); 
java.text.SimpleDateFormat sdf=new java.text.SimpleDateFormat("dd/MM/yyyy");

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
	<link href="<%= ruta %>/assets/css/bootstrap.css" rel="stylesheet">
    <link href="<%= ruta %>/assets/css/bootstrap-responsive.css" rel="stylesheet">
    <link href="<%= ruta %>/assets/css/todc-bootstrap.css" rel="stylesheet">
    <link href="<%= ruta %>/assets/css/style.css" rel="stylesheet">

    <!-- HTML5 shim, for IE6-8 support of HTML5 elements -->
    <!--[if lt IE 9]>
    <script src="<%= ruta %>/assets/js/modernizr.js"></script>
    <![endif]-->
    
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
      
	  <div class="form-horizontal">		  
		  <div class="control-group">
		    <label class="control-label" for="estado">Estado</label>
		    <div class="controls">
		      <select name="Proyectos" id="Proyectos">
				<option value="-1">Selecione Aquí..</option>
				<%
					for (int i = 0; i < ListProy.size(); i++) {
				%> 
				<option value="<%=ListProy.get(i).getIdProyecto() %>"><%=ListProy.get(i).getNombre()%></option>
				
				<%
					}
				%>
			  </select>
		    </div>
		  </div>
		  <div class="control-group">
		    <div class="controls">
		      <button type="submit" onclick="redireccionar();" class="btn">Ver Reporte</button>
		    </div>
		  </div>
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
<% /* %>
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
<% */ %>
</body>
</html>