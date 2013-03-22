package com.clase.controller;

import java.io.IOException;

import javax.servlet.*;
import javax.servlet.http.*;

import com.clase.dao.UsuarioDao;
import com.clase.models.Usuario;

/**
 * Servlet Filter implementation class Navegacion
 */

public class Navegacion implements Filter {

	/**
	 * Default constructor.
	 */

	private FilterConfig config;
	private String urlLogin;

	public Navegacion() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		config = null;
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig config) throws ServletException {
		this.config = config;
		this.urlLogin =  "/login.jsp";

		// config.getInitParameter("urlLogin"); // "login.jsp";//
		if (urlLogin == null || urlLogin.trim().length() == 0) {
			// Error al cargar la url de login
			throw new ServletException("No se ha configurado URL de login");
		}
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {

		try {
			long before = System.currentTimeMillis();
			//
			long after = System.currentTimeMillis();

			String name = "";

			if (request instanceof HttpServletRequest) {
				name = ((HttpServletRequest) request).getRequestURI();
			}
			config.getServletContext().log(
					"Ruta: " + name);
			

			
			//ESTO NO LO HE CORREGIDO. ES PARA ELIMINAR EL CONTEXTO DE LA APLICACIÓN Y EL
			//EL NOMBRE DEL RECURSO COINCIDA CON LA RUTA ESCRITA EN LA TABLA
			name = name.substring(14, name.length());
			
			
			Usuario user = new Usuario();

			// Extraer Sesión
			HttpSession session = ((HttpServletRequest) request).getSession();

			if (session.getAttribute("usuario") == null) {
				// NO hay una session con ususario
				RequestDispatcher dispatcher = request.getRequestDispatcher("../"
						+ urlLogin);
				dispatcher.forward(request, response);
			} else {
				user = (Usuario) session.getAttribute("usuario");
				UsuarioDao serviceuser = new UsuarioDao();
				if (!serviceuser.getUsuarioPermiso(user.getNlogin() , name)) {
					RequestDispatcher dispatcher = request
							.getRequestDispatcher("../" + urlLogin
									+ "?mensaje=Permiso No Establecido");
					dispatcher.forward(request, response);
				}
			}
			//+request.getContextPath()
			try {
				chain.doFilter(request, response);
			} catch (ServletException e) {
				config.getServletContext().log("Error filter: " + e.getLocalizedMessage());
				
			}
			config.getServletContext().log(name + ": " + (after - before) + "ms");

		} catch (ServletException e) {
			config.getServletContext().log("Error 1: " + e.getMessage());
		}

		catch (Exception e) {
			
			config.getServletContext().log("Error 2: " + e.getMessage());
		}

	}

}
