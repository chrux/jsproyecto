package com.clase.dao;

import java.util.ArrayList;


import com.clase.models.Personal;

public class PersonalDao {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	
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
	
	
	
	

}
