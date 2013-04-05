package com.clase.servlets;

import java.io.IOException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.clase.dao.ChequesDao;
import com.clase.models.Cheques;
import com.clase.models.PersonalProyecto;

/**
 * Servlet implementation class ServiceCheques
 */
@WebServlet("/Cheques.do")
public class ServiceCheques extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServiceCheques() {
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
		
		Cheques myCheque= null;
		ChequesDao service= new ChequesDao(); 
		String accion = "";
		String mensaje = "";
		accion = request.getParameter("accion");
		
		int band = 0;
		int idProy=0;
		int idCheque=0;
		try{
			idProy= Integer.parseInt(request.getParameter("idProy"));
		}
		catch (Exception ex)
		{
			idProy=0;
		}
		
		try{
			idCheque= Integer.parseInt(request.getParameter("idCheque"));
		}
		catch (Exception ex)
		{
			idCheque=0;
		}
		
		System.out.println("Proyecto es: " + idProy);
		System.out.println("Accion: " + accion);
		
		
		int idCheq, idTasa, idProveedor, idProyecto,idCuentasBancos,numCheque;
		double monto;
		Timestamp fechaEmision;
		String concepto,estado;
		
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
		java.util.Date parsedDate;
		try {
			if (accion.equals("del")) {
				System.out.println(" Id a eliminar " + request.getParameter("idProy"));
				int id = Integer.parseInt(request
						.getParameter("idProy")); //del
				int idCh = Integer.parseInt(request
						.getParameter("idCh"));
				
				myCheque = new Cheques();
				myCheque.setIdProyecto(id) ;
				myCheque.setIdCheque(idCh);

				band = service.DelCheque(myCheque) ;

				mensaje = "Registro Eliminado Correctamente!";
				
			}
			else if (accion.equals("nuevo")) {
				System.out.println(" Entra a agregar " );
				idProveedor = Integer.parseInt(request.getParameter("idProveedor"));
				idProyecto = Integer.parseInt(request.getParameter("idProy"));
				numCheque = Integer.parseInt(request.getParameter("numCheque"));
				monto= Double.parseDouble(request.getParameter("monto"));
				parsedDate = dateFormat.parse(request.getParameter("fechaEmision"));
				fechaEmision = new java.sql.Timestamp(parsedDate.getTime()) ;
				concepto = request.getParameter("concepto");
				estado= request.getParameter("estado");
				if(idProveedor ==-1 || idProyecto==-1 ){
					mensaje = "Datos Requeridos";
					
				}
				else{
					myCheque = new Cheques(0,0,idProveedor,idProyecto,0,monto,fechaEmision,numCheque,concepto,estado);
					band = service.addCheque(myCheque);
					mensaje = "Registro Agregado Correctamente!";	
					
				}
					
				
				
				
				
			}
			
			response.sendRedirect(request.getContextPath() + "/procesos/AddProyecto.jsp?idProy=" + idProy + "&show=0&mensaje=" + mensaje);
		}
		catch(Exception ex){
			mensaje = "Error al realizar la accion!";
			response.sendRedirect(request.getContextPath() + "/procesos/ProyectosCheques.jsp?mensaje=" + mensaje);
			System.out.println("Error Form  : " + ex.getMessage());
			ex.printStackTrace();
			
		}
	}

}
