<%@page import="com.clase.models.Usuario"%>
<%@page import="com.clase.dao.Menu"%>
<%
String rutamenu = request.getParameter("ruta");

Menu sMenu = new Menu();
%>

<%
	Usuario user1 = new Usuario();
	user1 = (Usuario) session.getAttribute("usuario");
%>
<%=sMenu.getListMenu(user1, rutamenu) %>
