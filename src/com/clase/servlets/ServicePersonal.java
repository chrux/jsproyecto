package com.clase.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.clase.dao.PersonalDao;
import com.clase.models.Cliente;
import com.clase.models.Personal;

/**
 * Servlet implementation class ServicePersonal
 */
@WebServlet("/ServicePersonal")
public class ServicePersonal extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServicePersonal() {
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
		Personal objPersonal = null;
		String accion = "";
		String mensaje = "";
		accion = request.getParameter("accion");
		int band = 0;
		int idtipopersonal, idoficio, idtipoidentificacion;
		String nombre, cedula, nacional, estadocivil, direccion, genero, estado;
		PersonalDao service = new PersonalDao();
		try{
			if (accion.equals("nuevo")) {
				idtipopersonal = Integer.parseInt(request.getParameter("idtipopersonal"));
				idoficio = Integer.parseInt(request.getParameter("idoficio"));
				idtipoidentificacion = Integer.parseInt(request.getParameter("idtipoidentificacion"));				
				nombre = request.getParameter("nombre");
				cedula = request.getParameter("cedula");
				nacional = request.getParameter("nacional");
				estadocivil = request.getParameter("estadocivil");
				direccion = request.getParameter("direccion");
				genero = request.getParameter("genero");
				estado = request.getParameter("estado");
				objPersonal = new Personal(idtipopersonal, idoficio, idtipoidentificacion, nombre, cedula, nacional, estadocivil, direccion, genero, estado);
				band = service.addPersonal(objPersonal);
				if (band == 1) {
					mensaje = "Registro Agregado Correctamente!";
				}
			}else if (accion.equals("edit")){
				idtipopersonal = Integer.parseInt(request.getParameter("idtipopersonal"));
				idoficio = Integer.parseInt(request.getParameter("idoficio"));
				idtipoidentificacion = Integer.parseInt(request.getParameter("idtipoidentificacion"));				
				nombre = request.getParameter("nombre");
				cedula = request.getParameter("cedula");
				nacional = request.getParameter("nacional");
				estadocivil = request.getParameter("estadocivil");
				direccion = request.getParameter("direccion");
				genero = request.getParameter("genero");
				estado = request.getParameter("estado");
				int id = Integer.parseInt(request.getParameter("Id_Personal"));
				objPersonal = new Personal();
				band = service.updPersonal(objPersonal);
				if (band == 1) {
					mensaje = "Registro Editado Correctamente!";
				}
			}else if (accion.equals("cancel")){
				
			}else{
				int id = Integer.parseInt(request.getParameter("Id_Personal"));
				objPersonal = new Personal();
				objPersonal.setIdPersonal(id);
				band = service.delPersonal(objPersonal);
				if (band == 1) {
					mensaje = "Registro Eliminado Correctamente!";
				}
			}
		}catch (Exception e) {
			mensaje = "Error al realizar la accion!";
			response.sendRedirect("./catalogos/Personal.jsp?mensaje=" + mensaje);
			System.out.println("Error Form: " + e.getMessage());
			e.printStackTrace();
		}
	}

}
