package com.clase.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.clase.dao.MonedaDao;
import com.clase.models.Moneda;

/**
 * Servlet implementation class ServiceMoneda
 */
@WebServlet("/ServiceMoneda")
public class ServiceMoneda extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServiceMoneda() {
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
		Moneda objMoneda = null;
		String accion = "";
		String mensaje = "";
		accion = request.getParameter("accion");
		int band = 0;
		String nombre, estado;
		System.out.println("Accion: " + accion);
		MonedaDao service = new MonedaDao();
		try{
			if (accion.equals("nuevo")) {
				nombre = request.getParameter("nombre");
				estado = request.getParameter("estado");
				if (nombre.length() != 0 && estado.length() != 0) {
					objMoneda = new Moneda(nombre, estado);
					System.out.println("Datos: Nombre = " + nombre
							+ " Estado = " + estado);
					band = service.addMoneda(objMoneda);
					if (band == 1) {
						mensaje = "Registro Agregado Correctamente!";
					}				
				} else{
					mensaje = "Datos Requeridos";
					}
			}else if (accion.equals("edit")){
				nombre = request.getParameter("nombre");
				estado = request.getParameter("estado");
				int id = Integer.parseInt(request.getParameter("Id_Moneda"));
				objMoneda = new Moneda(id, nombre, estado);
				band = service.updMoneda(objMoneda);
				if (band == 1) {
					mensaje = "Registro Editado Correctamente!";
				}
			}else if (accion.equals("cancel")){
				mensaje="Acción Cancelada";
			}else{
				int id = Integer.parseInt(request.getParameter("Id_Banco"));
				objMoneda = new Moneda();
				objMoneda.setIdMoneda(id);
				band = service.delMoneda(objMoneda);
				if (band == 1) {
					mensaje = "Registro Eliminado Correctamente!";
				}
			}
		}catch (Exception e) {
			mensaje = "Error al realizar la accion!";
			response.sendRedirect("./catalogos//Moneda.jsp?mensaje=" + mensaje);
			System.out.println("Error Form: " + e.getMessage());
			e.printStackTrace();
		}		
	}

}
