package com.clase.servlets;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.clase.dao.UsuarioDao;
import com.clase.models.Usuario;

/**
 * Servlet implementation class ServiceLogin
 */
@WebServlet("/login.do")
public class ServiceLogin extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServiceLogin() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
HttpSession session = request.getSession();

		
		String login, password, mensaje,cierre = "";
		//Cierre de Sesión
cierre = request.getParameter("cierre");
		
		if(cierre!=null){
			mensaje = "Sesión Cerrada";
			session.invalidate();
			//session.removeAttribute("usuario");
			response.sendRedirect(request.getContextPath() + "/login.jsp?mensaje=" + mensaje);
			return;
		}
		
		
		Usuario usuario = new Usuario();

		
		UsuarioDao service = new UsuarioDao();

		login = request.getParameter("name");
		password = request.getParameter("pwd");
		
		if	(login.isEmpty() || password.isEmpty()) {
			mensaje = "Ingrese los Datos Solicitados";
			response.sendRedirect("./login.jsp?mensaje=" + mensaje);
			return;
		}
		
		if (login.isEmpty() || login.length() < 4) {
			mensaje = "Usuario no valido!";
			response.sendRedirect("./login.jsp?mensaje=" + mensaje);
			return;
		}
		if (password.isEmpty() || password.length() < 4) {
			mensaje = "Password no valido!";
			response.sendRedirect("./login.jsp?mensaje=" + mensaje);
			return;
		}
		
		//Verifica si el Usuario existe
		usuario = service.getNameUser(login);
		if(usuario == null) {
			mensaje = "Usuario no existe!";
			response.sendRedirect("./login.jsp?mensaje=" + mensaje);
			return;
		} else {
			usuario = service.getUsuario(login, password);
			if (usuario == null) {
				mensaje = "Usuario o Clave incorrectos!";
				response.sendRedirect("./login.jsp?mensaje=" + mensaje);
				return;
			} else {
				session.setAttribute("usuario", usuario);
				response.sendRedirect(request.getContextPath() + "/inicio/home.jsp");
				//response.sendRedirect("./home.jsp");
			}
		}
		
		
		
	}
	
	
		

}
