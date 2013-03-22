package com.clase.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.clase.models.Banco;
import com.clase.models.TipoCliente;
import com.clase.dao.MacGregorDao;

/**
 * Servlet implementation class ServiceTipoCliente
 */
@WebServlet("/AddTipoCliente.do")
public class ServiceTipoCliente extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ServiceTipoCliente() {
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
		TipoCliente myClientTy = null;
		String accion = "";
		String mensaje = "";
		accion = request.getParameter("accion");
		int band = 0;
		String tipoCliente, estado;

		System.out.println("Accion: " + accion);
		MacGregorDao service = new MacGregorDao();
		try {
			if (accion.equals("nuevo")) {
				{
					tipoCliente = request.getParameter("tipoCliente");
					estado = request.getParameter("estado");
					if (tipoCliente.length() != 0 && estado.length() != 0) {
						myClientTy = new TipoCliente(tipoCliente, estado);

						System.out.println("Datos: Tipo de Cliente = " + tipoCliente
								+ " Estado = " + estado);
						band = service.addClientTy(myClientTy);
						mensaje = "Registro Agregado Correctamente!";
					

					} else
						mensaje = "Datos Requeridos";
				}
			} else if (accion.equals("edit")) {

				tipoCliente = request.getParameter("tipoCliente");
				estado = request.getParameter("estado");
				int id = Integer.parseInt(request
						.getParameter("Id_Tipo_Cliente"));

				myClientTy = new TipoCliente(id, tipoCliente, estado);

				band = service.updClientTy(myClientTy);

				// if (band == 1) {
				mensaje = "Registro Editado Correctamente!";
				// }

			} else if (accion.equals("cancel")) {

				mensaje = "Acción Cancelada";
			} else {
				int id = Integer.parseInt(request
						.getParameter("Id_Tipo_Cliente"));
				myClientTy = new TipoCliente();
				myClientTy.setIdTipoCliente(id);

				band = service.delClientTy(myClientTy);

				mensaje = "Registro Eliminado Correctamente!";

			}

			if (mensaje.contains("Requeridos"))
				response.sendRedirect(request.getContextPath()+ "/catalogos/AddTipoClientes.jsp?mensaje=" + mensaje);
			else
				response.sendRedirect(request.getContextPath()+ "/catalogos/TipoClientes.jsp?mensaje=" + mensaje);
		} catch (Exception e) {
			mensaje = "Error al realizar la accion!";
			response.sendRedirect(request.getContextPath() + "/catalogos/TipoClientes.jsp?mensaje=" + mensaje);
			System.out.println("Error Form: " + e.getMessage());
			e.printStackTrace();
		}

	}
}
