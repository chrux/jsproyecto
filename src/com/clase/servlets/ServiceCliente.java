package com.clase.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.clase.dao.ClienteDao;
import com.clase.models.Cliente;


/**
 * Servlet implementation class ServiceCliente
 */
@WebServlet("/ServiceCliente")
public class ServiceCliente extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServiceCliente() {
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
		Cliente objCliente = null;
		String accion = "";
		String mensaje = "";
		accion = request.getParameter("accion");
		int band = 0;
		int idtipocliente;
		String nombre, contacto, direccionpos, direccionfis, telefono, email, nacionalidad, paginaweb, estado;
		System.out.println("Accion: " + accion);
		ClienteDao service = new ClienteDao();
		try{
			if (accion.equals("nuevo")) {
				idtipocliente = Integer.parseInt(request.getParameter("idtipocliente"));
				nombre = request.getParameter("nombre");
				contacto = request.getParameter("contacto");
				direccionpos = request.getParameter("direccionpos");
				direccionfis = request.getParameter("direccionfis");
				telefono = request.getParameter("telefono");
				email = request.getParameter("email");
				nacionalidad = request.getParameter("nacionalidad");
				paginaweb = request.getParameter("paginaweb");
				estado = request.getParameter("estado");				
					objCliente = new Cliente(idtipocliente, nombre, contacto, direccionpos, direccionfis, telefono, email, nacionalidad, paginaweb, estado);
					band = service.addCliente(objCliente);
					if (band == 1) {
						mensaje = "Registro Agregado Correctamente!";
					}							
			}else if (accion.equals("edit")){
				idtipocliente = Integer.parseInt(request.getParameter("idtipocliente"));
				nombre = request.getParameter("nombre");
				contacto = request.getParameter("contacto");
				direccionpos = request.getParameter("direccionpos");
				direccionfis = request.getParameter("direccionfis");
				telefono = request.getParameter("telefono");
				email = request.getParameter("email");
				nacionalidad = request.getParameter("nacionalidad");
				paginaweb = request.getParameter("paginaweb");
				int id = Integer.parseInt(request.getParameter("Id_Cliente"));
				objCliente = new Cliente();
				band = service.updCliente(objCliente);
				if (band == 1) {
					mensaje = "Registro Editado Correctamente!";
				}
			}else if (accion.equals("cancel")){
				mensaje="Acción Cancelada";
			}else{
				int id = Integer.parseInt(request.getParameter("Id_Cliente"));
				objCliente = new Cliente();
				objCliente.setIdCliente(id);
				band = service.delCliente(objCliente);
				if (band == 1) {
					mensaje = "Registro Eliminado Correctamente!";
				}
			}
		}catch (Exception e) {
			mensaje = "Error al realizar la accion!";
			response.sendRedirect("./catalogos/Cliente.jsp?mensaje=" + mensaje);
			System.out.println("Error Form: " + e.getMessage());
			e.printStackTrace();
		}	
		
	}

}
