package com.clase.dao;

import java.sql.SQLException;
import java.util.ArrayList;

import com.clase.models.PersonalProyecto;



public class PersonalProyectoDao {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	
	public PersonalProyecto findById(int id){
		PersonalProyecto persProy = new PersonalProyecto();
		
		
		ConnDB cx = new ConnDB();

		String sql = "Select * From personal_proyecto where Id_Proyecto=?;";
		System.out.println("Entra a la sentencia: " + sql + id);

		try {
			cx.Prepare(sql);
			cx.setInts(1, id);

			cx.executestmt();

			while (cx.getNext()) {
				persProy.setIdProyecto(id);
				persProy.setIdCargo(cx.getInt("Id_Cargo"));
				persProy.setIdOficio(cx.getInt("Id_Oficio"));
				persProy.setIdPersonal(cx.getInt("Id_Personal"));
				persProy.setIdPersonalProy(cx.getInt("Id_Personal_Proyecto"));
				persProy.setFechaInicio(cx.getDateStamp("Fecha_Inicio"));
				persProy.setFechaFinal(cx.getDateStamp("Fecha_Final"));
				
			
			}
			cx.cleanup();

		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return persProy;
	
		
	}
	
	
	public ArrayList<PersonalProyecto> getLista(int id) {
		ArrayList<PersonalProyecto> Lista = new ArrayList<PersonalProyecto>();
		String q = "Select * From personal_proyecto where Id_Proyecto=?;";//+ id + ";" ;

		ConnDB cx = new ConnDB();
		try {
			
			cx.Prepare(q);
			cx.setInts(1, id);
			//cx.consulta(q);
			
			

		cx.executestmt();
			while (cx.getNext()) {
				PersonalProyecto persProy = new PersonalProyecto();
				persProy.setIdProyecto(cx.getInt("Id_Proyecto"));
				persProy.setIdCargo(cx.getInt("Id_Cargo"));
				persProy.setIdOficio(cx.getInt("Id_Oficio"));
				persProy.setIdPersonal(cx.getInt("Id_Personal"));
				persProy.setIdPersonalProy(cx.getInt("Id_Personal_Proyecto"));
				persProy.setFechaInicio(cx.getDateStamp("Fecha_Inicio"));
				persProy.setFechaFinal(cx.getDateStamp("Fecha_Final"));
				
				
				Lista.add(persProy);
			}
			cx.cleanup();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return Lista;
	}

	
	
	public ArrayList<PersonalProyecto> getLista() {
		ArrayList<PersonalProyecto> Lista = new ArrayList<PersonalProyecto>();
		String q = "Select * From personal_proyecto order by Id_Personal_Proyecto;";

		ConnDB cx = new ConnDB();
		try {
			cx.consulta(q);
			
			while (cx.getNext()) {
				PersonalProyecto persProy = new PersonalProyecto();
				persProy.setIdProyecto(cx.getInt("Id_Proyecto"));
				persProy.setIdCargo(cx.getInt("Id_Cargo"));
				persProy.setIdOficio(cx.getInt("Id_Oficio"));
				persProy.setIdPersonal(cx.getInt("Id_Personal"));
				persProy.setIdPersonalProy(cx.getInt("Id_Personal_Proyecto"));
				persProy.setFechaInicio(cx.getDateStamp("Fecha_Inicio"));
				persProy.setFechaFinal(cx.getDateStamp("Fecha_Final"));
				
				
				Lista.add(persProy);
			}
			cx.cleanup();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return Lista;
	}
	
	public int addProyecto(PersonalProyecto proy) {

		String q = "insert into personal_proyecto(Id_Personal_Proyecto, Id_Personal,Id_Cargo,Id_Oficio,Fecha_Inicio,Fecha_Final,Id_Proyecto) " +
		"values(?,?,?,?,?,?,?);";

		int newid = 0;
		int band = 0;
		String qmax = "select max(Id_Personal_Proyecto) + 1 as newid from personal_proyecto;";
System.out.println(qmax);
		ConnDB cx = new ConnDB();

		try {
			cx.consulta(qmax);

			while (cx.getNext()) {
				newid = cx.getInt("newid");
			}
			
			System.out.println(newid);
			System.out.println(proy.getIdPersonal());
			System.out.println("Entrará a insertar ");

			if (newid == 0)
				newid = 1;
//(Id_Personal_Proyecto, Id_Personal,Id_Cargo,Id_Oficio,Fecha_Inicio,Fecha_Final,Id_Proyecto
			//Fecha_Presupuesto,Fecha_Inicio,Numero_Oferta,Monto_Presupuesto, Estado,Fecha_Cierre,Observaciones,Id_Moneda
			cx.Prepare(q);
			cx.setInts(1, newid);
			cx.setInts(2, proy.getIdPersonal() );
			cx.setInts(3, proy.getIdCargo() );
			cx.setInts(4, proy.getIdOficio());
			cx.setTimestamp(5, proy.getFechaInicio() );
			cx.setTimestamp(6, proy.getFechaFinal());
			cx.setInts(7, proy.getIdProyecto() );

			
			System.out.println("Datos Dentro de la función: New Id = " +newid +" Nombre Proyecto= " + proy.getIdPersonal() +" Estado = "+  proy.getIdProyecto() );
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
	
	public int DelPersonalProy(PersonalProyecto myPerson){
		String q = "delete from personal_proyecto where Id_Personal_Proyecto=?;";
		int band=0;
		ConnDB cx = new ConnDB();

		try {
			cx.Prepare(q);
			cx.setInts(1, myPerson.getIdPersonalProy() );

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
