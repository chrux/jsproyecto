package com.clase.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.clase.dao.CargoDao;
import com.clase.models.Banco;
import com.clase.models.Cargo;

/**
 * Servlet implementation class ServiceCargo
 */
@WebServlet("/Cargo.do")
public class ServiceCargo extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServiceCargo() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request,response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		Cargo miCargo = null;
		String accion = "";
		String mensaje = "";
		accion = request.getParameter("accion");
		int band = 0;
		String nombreCargo, estado;
		
		System.out.println("Accion: " + accion);
		CargoDao service = new CargoDao();
		
		try {
			if (accion.equals("nuevo")) {
				nombreCargo = request.getParameter("nombreCargo");
				estado = request.getParameter("estado");
				if (nombreCargo.length() != 0 && estado.length() != 0) {
					miCargo = new Cargo(0,nombreCargo, estado);

					System.out.println("Datos: Nombre = " + nombreCargo
							+ " Estado = " + estado);
					band = service.addCargo(miCargo);
					mensaje = "Registro Agregado Correctamente!";

				
				} else
					mensaje = "Datos Requeridos";

			}
			else if (accion.equals("edit")) {

				nombreCargo = request.getParameter("nombreCargo");
				estado = request.getParameter("estado");
				int id = Integer.parseInt(request.getParameter("IdCargo"));

				miCargo = new Cargo(id, nombreCargo, estado);

				band = service.updCargo(miCargo);

					mensaje = "Registro Editado Correctamente!";
			

			}else if (accion.equals("cancel")) {
				
				mensaje="Acción Cancelada";
			} 
			else {
				int id = Integer.parseInt(request.getParameter("IdCargo"));
				miCargo = new Cargo();
				miCargo.setIdCargo(id);

				band = service.delCargo(miCargo);
				//if (band == 1) {
					mensaje = "Registro Eliminado Correctamente!";
				//}
			}
			if (mensaje.contains("Requeridos"))
				response.sendRedirect("./catalogos/AddCargo.jsp?mensaje=" + mensaje);
			else
				response.sendRedirect("./catalogos/Cargo.jsp?mensaje=" + mensaje);
			
			
		}
		 catch (Exception e) {
				mensaje = "Error al realizar la accion!";
				response.sendRedirect("./catalogos//Bancos.jsp?mensaje=" + mensaje);
				System.out.println("Error Form: " + e.getMessage());
				e.printStackTrace();
			}

		
	}

}
