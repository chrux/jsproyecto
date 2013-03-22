package com.clase.dao;

import java.sql.SQLException;
import java.util.ArrayList;

import com.clase.models.Banco;
import com.clase.models.Proyecto;
import com.clase.dao.ConnDB ;

public class ProyectosDao {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	
	public Proyecto findById(int id){
		Proyecto proy = new Proyecto();
		
		
		ConnDB cx = new ConnDB();

		String sql = "Select * From proyecto where Id_Proyecto=?;";
		System.out.println("Entra a la sentencia: " + sql + id);

		try {
			cx.Prepare(sql);
			cx.setInts(1, id);

			cx.executestmt();

			while (cx.getNext()) {
				proy.setIdProyecto(id);
				proy.setIdCliente(cx.getInt("Id_Cliente"));
				proy.setIdMoneda(cx.getInt("Id_Moneda"));
				proy.setIdTipoOferta(cx.getInt("Id_Tipo_Oferta"));
				proy.setNombre(cx.getString("Nombre"));
				proy.setMontoPresupuesto(cx.getDouble("Monto_Presupuesto"));
				proy.setObservaciones(cx.getString("Observaciones"));
				proy.setEstado(cx.getString("Estado"));
				proy.setFechaCierre (cx.getDateStamp("Fecha_Cierre"));
				proy.setFechaAceptacion(cx.getDateStamp("Fecha_Aceptacion"));
				proy.setFechaOferta(cx.getDateStamp("Fecha_de_Oferta"));
				proy.setMontoOferta(cx.getDouble("Monto_Oferta"));
				proy.setNumOferta(cx.getInt("Numero_Oferta"));
			}
			cx.cleanup();

		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return proy;
	
		
	}
	
	
	
	public ArrayList<Proyecto> getLista() {
		ArrayList<Proyecto> Lista = new ArrayList<Proyecto>();
		String q = "select * from proyecto order by Id_Proyecto;";

		ConnDB cx = new ConnDB();
		try {
			cx.consulta(q);
			while (cx.getNext()) {
				Proyecto myProy = new Proyecto();
				myProy.setIdProyecto(cx.getInt("Id_Proyecto"));
				myProy.setIdTipoOferta(cx.getInt("Id_Tipo_Oferta"));
				myProy.setIdCliente(cx.getInt("Id_Cliente"));
				myProy.setNombre(cx.getString("Nombre"));
				myProy.setFechaOferta(cx.getDateStamp("Fecha_de_Oferta"));
				myProy.setMontoOferta(cx.getDouble("Monto_Oferta"));
				myProy.setFechaAceptacion(cx.getDateStamp("Fecha_Aceptacion"));//Aplicar como fecha de presupuesto y de inicio
				myProy.setNumOferta(cx.getInt("Numero_Oferta"));
				myProy.setMontoPresupuesto(cx.getDouble("Monto_Presupuesto"));
				myProy.setEstado(cx.getString("Estado"));
				myProy.setFechaCierre(cx.getDateStamp("Fecha_Cierre"));
				myProy.setObservaciones(cx.getString("Observaciones"));
				myProy.setIdMoneda(cx.getInt("Id_Moneda"));
				
				Lista.add(myProy);
			}
			cx.cleanup();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return Lista;
	}
	
	public int addProyecto(Proyecto proy) {

		String q = "insert into proyecto(Id_Proyecto, Id_Tipo_Oferta,Id_Cliente,Nombre,Fecha_de_Oferta,Monto_Oferta,Fecha_Aceptacion,Fecha_Presupuesto,Fecha_Inicio,Numero_Oferta,Monto_Presupuesto, Estado,Fecha_Cierre,Observaciones,Id_Moneda) " +
		"values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?);";

		int newid = 0;
		int band = 0;
		String qmax = "select max(Id_Proyecto) + 1 as newid from proyecto;";
System.out.println(qmax);
		ConnDB cx = new ConnDB();

		try {
			cx.consulta(qmax);

			while (cx.getNext()) {
				newid = cx.getInt("newid");
			}
			
			System.out.println(newid);
			System.out.println(proy.getIdTipoOferta());
			System.out.println("Entrará a insertar ");

			if (newid == 0)
				newid = 1;
//Id_Proyecto, Id_Tipo_Oferta,Id_Cliente,Nombre,Fecha_de_Oferta,Monto_Oferta,Fecha_Aceptacion,
			//Fecha_Presupuesto,Fecha_Inicio,Numero_Oferta,Monto_Presupuesto, Estado,Fecha_Cierre,Observaciones,Id_Moneda
			cx.Prepare(q);
			cx.setInts(1, newid);
			cx.setInts(2, proy.getIdTipoOferta() );
			cx.setInts(3, proy.getIdCliente());
			cx.setStrings(4, proy.getNombre().toUpperCase());
			cx.setTimestamp(5, proy.getFechaOferta());
			cx.setDoubles(6, proy.getMontoOferta());
			cx.setTimestamp(7, proy.getFechaAceptacion());
			cx.setTimestamp(8, proy.getFechaAceptacion());
			cx.setTimestamp(9,proy.getFechaAceptacion());
			cx.setInts(10, proy.getNumOferta());
			cx.setDoubles(11, proy.getMontoPresupuesto());
			cx.setStrings(12, proy.getEstado() );
			cx.setTimestamp (13,proy.getFechaCierre());
			cx.setStrings(14, proy.getObservaciones());
			cx.setInts(15, proy.getIdMoneda());

			
			System.out.println("Datos Dentro de la función: New Id = " +newid +" Nombre Proyecto= " + proy.getNombre() +" Estado = "+  proy.getEstado() );
			System.out.println(q);

			
			if (cx.executestmt(1)) {
				band = 1;
			} else {
				band = 0;
			}

		} catch (SQLException e) {
			System.out.println("Error SQL: " + e.getMessage());
			band = 0;
		} catch (Exception e) {
			e.printStackTrace();
			band= 0;
		}
		return band;
	}
	
	public int updProyecto(Proyecto proy) {
		int band = 0;
		String q = "update proyecto set Id_Tipo_Oferta=?,Id_Cliente=?,Nombre=?,Fecha_de_Oferta=?,Monto_Oferta=?,Fecha_Aceptacion=?,Numero_Oferta=?,Fecha_Presupuesto=?,Monto_Presupuesto=?,Fecha_Inicio=?,Fecha_Cierre=?,Estado=?,Observaciones=?,Id_Moneda=? " +
			" where Id_Proyecto=?;";
		
		ConnDB cx = new ConnDB();
		System.out.println(q);
		System.out.println(proy.getIdTipoOferta());
		System.out.println(proy.getIdCliente());
		System.out.println(proy.getNombre());
		System.out.println(proy.getFechaOferta());
		System.out.println(proy.getMontoOferta());
		System.out.println(proy.getFechaAceptacion());
		System.out.println(proy.getNumOferta());
		System.out.println(proy.getMontoPresupuesto());
		System.out.println(proy.getFechaCierre());
		System.out.println(proy.getEstado());
		System.out.println(proy.getObservaciones());
		System.out.println(proy.getIdMoneda());
		System.out.println( proy.getIdProyecto());
		try {
			cx.Prepare(q);
			cx.setInts(1, proy.getIdTipoOferta());
			cx.setInts(2, proy.getIdCliente() );
			cx.setStrings(3, proy.getNombre());
			cx.setTimestamp(4, proy.getFechaOferta());
			cx.setDoubles(5, proy.getMontoOferta());
			cx.setTimestamp(6, proy.getFechaAceptacion());
			cx.setInts(7, proy.getNumOferta());
			cx.setTimestamp(8,proy.getFechaAceptacion());
			cx.setDoubles(9,proy.getMontoPresupuesto());
			cx.setTimestamp(10,proy.getFechaAceptacion());
			cx.setTimestamp(11, proy.getFechaCierre());
			cx.setStrings(12,proy.getEstado());
			cx.setStrings(13, proy.getObservaciones());
			cx.setInts(14, proy.getIdMoneda());
			cx.setInts(15, proy.getIdProyecto());
			
		    
		    if (cx.executestmt(1)) {
				band = 1;
			} else {
				band = 0;
			}

			

		} catch (SQLException e) {
			System.out.println("Error SQL: " + e.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return band;
	}
	
	public int delProyecto(Proyecto myProyecto) {
		String q = "delete from proyecto where Id_Proyecto=?;";
		String delQ="delete from personal_proyecto where Id_Proyecto=?;";
		String delSq="delete from ejecucion where Id_Proyecto=?;";

		int band = 0;

		ConnDB cx = new ConnDB();

		try {
			cx.Prepare(q);
			cx.setInts(1, myProyecto.getIdProyecto() );

			if (cx.executestmt(1)) {
				band = 1;
			} else {
				band = 0;
			}
			cx.Prepare(delQ);//Eliminar proyectos asociados en personal proyecto
			cx.setInts(1, myProyecto.getIdProyecto());
			
			if (cx.executestmt(1)) {
				band = 1;
			} else {
				band = 0;
			}
			
			cx.Prepare(delSq);
			cx.setInts(1, myProyecto.getIdProyecto() );
			if (cx.executestmt(1)) {
				band = 1;
			} else {
				band = 0;
			}
			
			
		} catch (SQLException e) {
			System.out.println("Error SQL: " + e.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return band;
	}
	
	
	
 
}

