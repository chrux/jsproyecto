package com.clase.servlets;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.sql.Timestamp;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.clase.dao.ProyectosDao;
import com.clase.models.Cargo;
import com.clase.models.Proyecto;


/**
 * Servlet implementation class ServiceProyecto
 */
@WebServlet("/AddProyecto.do")
public class ServiceProyecto extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServiceProyecto() {
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
		
		Proyecto myProy= null;
		String accion = "";
		String mensaje = "";
		accion = request.getParameter("accion");
		
		int band = 0;
		int idProyecto,idTipoOferta,idCliente, numOferta,idMoneda;
		String nombre, estado, observaciones;
		Timestamp fechaOferta,fechaAceptacion,fechaCierre;//Aplicar como fecha Aceptación como de presupuesto y de inicio
		Double montoOferta, montoPresupuesto;
		System.out.println("Accion: " + accion);
		
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss.SSS");
		
		java.util.Date parsedDate;
		
		ProyectosDao service = new ProyectosDao();
		try
		{
			if (accion.equals("nuevo")) {
				System.out.println("Tipo Oferta " + request.getParameter("idTipoOferta"));
				System.out.println("Cliente " + request.getParameter("idCliente"));
				System.out.println("Núm Oferta " + request.getParameter("numOferta"));
				System.out.println("Moneda " + request.getParameter("idMoneda"));
				System.out.println("nombre " + request.getParameter("nombre"));
				System.out.println("estado " + request.getParameter("estado"));
				System.out.println("observaciones " + request.getParameter("observaciones"));
				System.out.println("fechaOferta " + request.getParameter("fechaOferta"));
				System.out.println("fechaAceptacion " + request.getParameter("fechaAceptacion"));
				System.out.println("fechaCierre " + request.getParameter("fechaCierre"));
				System.out.println("montoOferta " + request.getParameter("montoOferta"));
				System.out.println("Tipo Oferta " + request.getParameter("idTipoOferta"));
				System.out.println("Tipo Oferta " + request.getParameter("idTipoOferta"));
				
				
				
				idTipoOferta = Integer.parseInt(request.getParameter("idTipoOferta"));
				idCliente =Integer.parseInt(request.getParameter("idCliente"));
				nombre = request.getParameter("nombre");
				 //parsedDate = dateFormat.parse("2006-05-22 14:04:59:612");
				 parsedDate = dateFormat.parse(request.getParameter("fechaOferta"));
				fechaOferta = new java.sql.Timestamp(parsedDate.getTime()) ;
				numOferta = Integer.parseInt(request.getParameter("numOferta"));
				idMoneda = Integer.parseInt(request.getParameter("idMoneda"));
				estado = request.getParameter("estado");
				observaciones= request.getParameter("observaciones");
		
				if (idTipoOferta ==-1 ||  idCliente==-1 || idMoneda ==-1 ) {
					mensaje = "Datos Requeridos";
				
				} else{
					
								
					parsedDate = dateFormat.parse(request.getParameter("fechaAceptacion"));
					fechaAceptacion =new java.sql.Timestamp(parsedDate.getTime()) ;
					parsedDate = dateFormat.parse(request.getParameter("fechaCierre"));
					fechaCierre=new java.sql.Timestamp(parsedDate.getTime()) ;
					montoOferta = Double.parseDouble(request.getParameter("montoOferta"));
					montoPresupuesto = Double.parseDouble(request.getParameter("montoOferta"));
					myProy = new Proyecto(0,idTipoOferta,idCliente,nombre,fechaOferta,montoOferta,fechaAceptacion,numOferta,montoPresupuesto,estado,fechaCierre,observaciones,idMoneda );
					band = service.addProyecto(myProy);
					mensaje = "Registro Agregado Correctamente!";
				}
					
			}
			else if (accion.equals("edit")) {
				idTipoOferta = Integer.parseInt(request.getParameter("idTipoOferta"));
				idCliente =Integer.parseInt(request.getParameter("idCliente"));
				nombre = request.getParameter("nombre");
				 parsedDate = dateFormat.parse(request.getParameter("fechaOferta"));
					fechaOferta = new java.sql.Timestamp(parsedDate.getTime()) ;
					
				numOferta = Integer.parseInt(request.getParameter("numOferta"));
				idMoneda = Integer.parseInt(request.getParameter("idMoneda"));
				
				estado = request.getParameter("estado");
				observaciones= request.getParameter("observaciones");
				
				parsedDate = dateFormat.parse(request.getParameter("fechaAceptacion"));
				fechaAceptacion =new java.sql.Timestamp(parsedDate.getTime()) ;
				parsedDate = dateFormat.parse(request.getParameter("fechaCierre"));
				fechaCierre=new java.sql.Timestamp(parsedDate.getTime()) ;
				montoOferta = Double.parseDouble(request.getParameter("montoOferta"));
				montoPresupuesto = Double.parseDouble(request.getParameter("montoOferta"));
				int id = Integer.parseInt(request.getParameter("IdProyecto"));
				
				myProy = new Proyecto( id, idTipoOferta,idCliente, nombre, fechaOferta , montoOferta, fechaAceptacion, numOferta, montoPresupuesto, estado, fechaCierre, observaciones, idMoneda );
				
				band = service.updProyecto(myProy);

				//if (band == 1) {
					mensaje = "Registro Editado Correctamente!";
				//}
				
			} else if (accion.equals("cancel")) {

				mensaje = "Acción Cancelada";
			} else {
				System.out.println(request.getParameter("idProy"));
				int id = Integer.parseInt(request.getParameter("idProy"));
				
				myProy = new Proyecto();
				myProy.setIdProyecto(id);

			  band =service.delProyecto(myProy);

				mensaje = "Registro Eliminado Correctamente!";

			}
			if (mensaje.contains("Requeridos"))
				response.sendRedirect(request.getContextPath()+"/procesos/AddProyecto.jsp?show=1&mensaje=" + mensaje);
			else
				response.sendRedirect(request.getContextPath()+ "/procesos/Proyectos.jsp?mensaje=" + mensaje);

		}
		catch (Exception e)
		{
			mensaje = "Error al realizar la accion!";
			response.sendRedirect(request.getContextPath() + "/procesos/Proyectos.jsp?mensaje=" + mensaje);
			System.out.println("Error Form: " + e.getMessage());
			e.printStackTrace();
		}
		
	}

}
