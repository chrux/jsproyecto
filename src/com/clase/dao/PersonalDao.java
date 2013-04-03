package com.clase.dao;

import java.util.ArrayList;
import java.sql.SQLException;
import com.clase.models.Personal;

public class PersonalDao {

	public ArrayList<Personal> getLista() {
		ArrayList<Personal> Lista = new ArrayList<Personal>();
		String q = "select * from personal order by id_personal;";

		ConnDB cx = new ConnDB();
		try {
			cx.consulta(q);
			while (cx.getNext()) {
				Personal myPersona = new Personal();				
				myPersona.setIdPersonal(cx.getInt("id_personal"));
				myPersona.setIdOficio(cx.getInt("id_oficio"));
				myPersona.setIdTipoIdentificacion(cx.getInt("id_tipo_identificacion"));
				myPersona.setIdTipoPersonal(cx.getInt("id_tipo_personal"));
				myPersona.setNombre(cx.getString("nombre"));
				myPersona.setCedula(cx.getString("cedula"));
				myPersona.setNacional(cx.getString("nacional"));
				myPersona.setEstadoCivil(cx.getString("estado_civil"));
				myPersona.setDireccion(cx.getString("direccion"));
				myPersona.setGenero(cx.getString("genero"));
				myPersona.setEstado(cx.getString("estado"));
				
				Lista.add(myPersona);
			}
			cx.cleanup();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return Lista;
	}
	
	public Personal findByID(int idPersona) {
		Personal myPersona = new Personal();
		ConnDB cx = new ConnDB();

		String sql = "Select * from personal where id_personal=?;";

		try {
			cx.Prepare(sql);
			cx.setInts(1, idPersona);

			cx.executestmt();

			while (cx.getNext()) {
				myPersona.setIdPersonal(cx.getInt("id_personal"));
				myPersona.setIdOficio(cx.getInt("id_oficio"));
				myPersona.setIdTipoIdentificacion(cx.getInt("id_tipo_identificacion"));
				myPersona.setIdTipoPersonal(cx.getInt("id_tipo_personal"));
				myPersona.setNombre(cx.getString("nombre"));
				myPersona.setCedula(cx.getString("cedula"));
				myPersona.setNacional(cx.getString("nacional"));
				myPersona.setEstadoCivil(cx.getString("estado_civil"));
				myPersona.setDireccion(cx.getString("direccion"));
				myPersona.setGenero(cx.getString("genero"));
				myPersona.setEstado(cx.getString("estado"));
				
			}
			cx.cleanup();

		} catch (Exception e) {
			e.printStackTrace();
		}

		return myPersona;
	}	
	
	public int addPersonal(Personal personal){
		String query = "insert into personal (id_personal, id_tipo_personal, id_oficio, id_tipo_identificacion, nombre, cedula, nacional, estado_civil, direccion, genero, estado) values()";
		int band = 0;
		int newid = 0;
		String queryMax = "Select count(Id_clientes) + 1 as newid from clientes;";
		ConnDB cnx =  new ConnDB();	
		try{
			//Obtenemos el id que asignaremos al nuevo registro
			cnx.consulta(queryMax);
			while (cnx.getNext()) {
				newid = cnx.getInt("newid");
			}
			if (newid == 0)
			{
				newid = 1;
			}
			//Preparamos los valores a insertar
			cnx.Prepare(query);
			cnx.setInts(1, newid);
			cnx.setInts(2, personal.getIdTipoPersonal());
			cnx.setInts(3, personal.getIdOficio());
			cnx.setInts(4, personal.getIdTipoIdentificacion());
			cnx.setStrings(5, personal.getNombre());
			cnx.setStrings(6, personal.getCedula());
			cnx.setStrings(7, personal.getNacional()); 
			cnx.setStrings(8, personal.getEstadoCivil());
			cnx.setStrings(9, personal.getDireccion()); 
			cnx.setStrings(10, personal.getGenero());
			cnx.setStrings(11, personal.getEstado());
			//Logueamos los datos a insetar
			System.out.println(query);
			//Ejecutamos para insertar
			if (cnx.executestmt(1)) {
				band = 1;
			} else {
				band = 0;
			}
		}catch(SQLException e){
			System.out.println("Error SQL: " + e.getMessage());
			band = 0;			
		}catch (Exception e) {
			e.printStackTrace();
			band= 0;
		}		
		return band;
	}
	
	public int updPersonal(Personal personal){
		String query = "update personal set id_tipo_personal=?, id_oficio=?, id_tipo_identificacion=?, nombre=?, cedula=?, nacional=?, estado_civil=?, direccion=?, genero=?, estado=? where Id_personal=?;";
		int band = 0;
		ConnDB cnx = new ConnDB();
		try{
			//Preparamos los valores a actualizar
			cnx.Prepare(query);				
			cnx.setInts(1, personal.getIdTipoPersonal());
			cnx.setInts(2, personal.getIdOficio());
			cnx.setInts(3, personal.getIdTipoIdentificacion());
			cnx.setStrings(4, personal.getNombre());
			cnx.setStrings(5, personal.getCedula());
			cnx.setStrings(6, personal.getNacional()); 
			cnx.setStrings(7, personal.getEstadoCivil());
			cnx.setStrings(8, personal.getDireccion()); 
			cnx.setStrings(9, personal.getGenero());
			cnx.setStrings(10, personal.getEstado());
			cnx.setInts(11, personal.getIdPersonal());
			//Ejecutamos para insertar
			if (cnx.executestmt(1)) {
				band = 1;
			} else {
				band = 0;
			}
		}catch (SQLException e) {
			System.out.println("Error SQL: " + e.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
		}			
		return band;
	}
	
	public int delPersonal(Personal personal){
		String query = "delete from personal where Id_personal=?;";
		int band = 0;
		ConnDB cnx = new ConnDB();
		try{
			cnx.Prepare(query);
			cnx.setInts(1, personal.getIdPersonal());
			if (cnx.executestmt(1)) {
				band = 1;
			} else {
				band = 0;
			}
		}catch (SQLException e) {
			System.out.println("Error SQL: " + e.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
		}		
		return band;
	}
	
	

}
