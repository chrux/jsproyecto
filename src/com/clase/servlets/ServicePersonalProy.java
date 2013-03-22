package com.clase.servlets;

import java.io.IOException;
import java.text.SimpleDateFormat;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.clase.dao.PersonalProyectoDao;
import com.clase.models.PersonalProyecto;
import com.clase.models.TipoCliente;

import java.sql.Timestamp;

/**
 * Servlet implementation class ServicePersonalProy
 */
@WebServlet("/PersonalProy.do")
public class ServicePersonalProy extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServicePersonalProy() {
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
		PersonalProyecto myPersonal = null; 
		String accion = "";
		String mensaje = "";
		int idProy=0;
		try{
			idProy= Integer.parseInt(request.getParameter("idProy"));
		}
		catch (Exception ex)
		{
			idProy=0;
		}
		
		System.out.println("Proyecto es: " + idProy);
		accion = request.getParameter("accion");
		System.out.println("Accion: " + accion);

		int band = 0;
		
		int idPersonal, idCargo, idOficio, idProyecto;
		Timestamp fechaInicio,FechaFinal;
		System.out.println("Accion: " + accion);
		
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss.SSS");
		java.util.Date parsedDate;
		PersonalProyectoDao service = new PersonalProyectoDao();
				
		try {
		
			if (accion.equals("nuevo")) {
				System.out.println("IDPErsonal " + request.getParameter("idPersonal"));
				System.out.println("Cargo "+ request.getParameter("idCargo"));
				System.out.println("Oficio" + request.getParameter("idOficio"));
				System.out.println("Proyecto " + request.getParameter("idProy"));
				System.out.println("Fecha Inicio " + request.getParameter("fechaInicio"));
				System.out.println("Fecha Final " + request.getParameter("fechaFinal"));
				
				
				idPersonal = Integer.parseInt(request.getParameter("idPersonal"));
				idCargo = Integer.parseInt(request.getParameter("idCargo"));
				idOficio = Integer.parseInt(request.getParameter("idOficio"));
				idProyecto = Integer.parseInt(request.getParameter("idProy"));
				
					parsedDate = dateFormat.parse(request.getParameter("fechaInicio"));
					fechaInicio = new java.sql.Timestamp(parsedDate.getTime()) ;
					java.util.Date utilDate = new java.util.Date(); 
					parsedDate = dateFormat.parse(request.getParameter("fechaFinal"));
					FechaFinal = new java.sql.Timestamp(parsedDate.getTime()) ;
					System.out.println("Comparación Fecha "+ FechaFinal + " < " + fechaInicio );
					if(idPersonal ==-1 || idCargo==-1 || idOficio==-1 ){
						mensaje = "Datos Requeridos";
					}
					/*else if(utilDate.compareTo(FechaFinal) <utilDate.compareTo(fechaInicio)){
						mensaje = "Fecha de Fin no puede ser menor a fecha de inicio";
					}*/					
					else{	
					myPersonal = new PersonalProyecto(idCargo,idOficio,idPersonal,1,idProyecto,fechaInicio,FechaFinal);
					band= service.addProyecto(myPersonal);
					mensaje = "Registro Agregado Correctamente!";					
				}
				
			}
			else {
				int id = Integer.parseInt(request
						.getParameter("idPersProy")); //del
				System.out.println(" Id a eliminar " + request.getParameter("idPersProy"));
				myPersonal = new PersonalProyecto();
				myPersonal.setIdPersonalProy(id);

				band = service.DelPersonalProy(myPersonal);

				mensaje = "Registro Eliminado Correctamente!";

			}

			response.sendRedirect(request.getContextPath() + "/procesos/AddProyecto.jsp?show=1&idProy=" + idProy + "&mensaje=" + mensaje);
			
		}
		catch (Exception e) {
			mensaje = "Error al realizar la accion!";
			response.sendRedirect(request.getContextPath() + "/procesos/Proyectos.jsp?mensaje=" + mensaje);
			System.out.println("Error Form: " + e.getMessage());
			e.printStackTrace();
		}
		 
	}

}
