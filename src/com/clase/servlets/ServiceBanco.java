package com.clase.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.clase.dao.MacGregorDao;
import com.clase.models.Banco;

/**
 * Servlet implementation class ServiceEstudiante
 */
@WebServlet("/AddBanco.do")
public class ServiceBanco extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ServiceBanco() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		Banco myBank = null;
		String accion = "";
		String mensaje = "";
		accion = request.getParameter("accion");

		int band = 0;
		String nombre, estado;
		

		System.out.println("Accion: " + accion);
		MacGregorDao service = new MacGregorDao();

		try {

			if (accion.equals("nuevo")) {

				nombre = request.getParameter("nombre");
				estado = request.getParameter("estado");
				if (nombre.length() != 0 && estado.length() != 0) {
					myBank = new Banco(nombre, estado);

					System.out.println("Datos: Nombre = " + nombre
							+ " Estado = " + estado);
					band = service.addBanco(myBank);

					//if (band == 1) {
						mensaje = "Registro Agregado Correctamente!";
					//}
				
				} else
					mensaje = "Datos Requeridos";
				// carrera = request.getParameter("carrera");
				// mensualidad =
				// Double.parseDouble(request.getParameter("mensualidad"));

			} else if (accion.equals("edit")) {

				nombre = request.getParameter("nombre");
				estado = request.getParameter("estado");
				int id = Integer.parseInt(request.getParameter("Id_Banco"));

				myBank = new Banco(id, nombre, estado);

				band = service.updBanco(myBank);

				//if (band == 1) {
					mensaje = "Registro Editado Correctamente!";
				//}

			}else if (accion.equals("cancel")) {
				
				mensaje="Acción Cancelada";
			} 
			else {
				int id = Integer.parseInt(request.getParameter("Id_Banco"));
				myBank = new Banco();
				myBank.setIdBanco(id);

				band = service.delBanco(myBank);
				//if (band == 1) {
					mensaje = "Registro Eliminado Correctamente!";
				//}
			}
			
			if (mensaje.contains("Requeridos"))
				response.sendRedirect("./catalogos/AddBanco.jsp?mensaje=" + mensaje);
			else
				response.sendRedirect("./catalogos/Bancos.jsp?mensaje=" + mensaje);
		} catch (Exception e) {
			mensaje = "Error al realizar la accion!";
			response.sendRedirect("./catalogos//Bancos.jsp?mensaje=" + mensaje);
			System.out.println("Error Form: " + e.getMessage());
			e.printStackTrace();
		}

	}

}
