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
		String q = "select * from personal order by Id_Personal;";

		ConnDB cx = new ConnDB();
		try {
			cx.consulta(q);
			while (cx.getNext()) {
				Personal myPersona = new Personal();
				
				myPersona.setIdPersonal(cx.getInt("Id_Personal"));
				myPersona.setIdOficio(cx.getInt("Id_Oficio"));
				myPersona.setIdTipoIdentificacion(cx.getInt("Id_Tipo_Identificacion"));
				myPersona.setIdTipoPersonal(cx.getInt("Id_Tipo_Personal"));
				myPersona.setNombre(cx.getString("Nombre"));
				myPersona.setCedula(cx.getString("Cedula"));
				myPersona.setNacional(cx.getString("Nacional"));
				myPersona.setEstadoCivil(cx.getString("Estado_Civil"));
				myPersona.setDireccion(cx.getString("Direccion"));
				myPersona.setGenero(cx.getString("Genero"));
				myPersona.setEstado(cx.getString("Estado"));
				
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

		String sql = "Select * from personal where Id_Personal=?;";

		try {
			cx.Prepare(sql);
			cx.setInts(1, idPersona);

			cx.executestmt();

			while (cx.getNext()) {
				myPersona.setIdPersonal(cx.getInt("Id_Personal"));
				myPersona.setIdOficio(cx.getInt("Id_Oficio"));
				myPersona.setIdTipoIdentificacion(cx.getInt("Id_Tipo_Identificacion"));
				myPersona.setIdTipoPersonal(cx.getInt("Id_Tipo_Personal"));
				myPersona.setNombre(cx.getString("Nombre"));
				myPersona.setCedula(cx.getString("Cedula"));
				myPersona.setNacional(cx.getString("Nacional"));
				myPersona.setEstadoCivil(cx.getString("Estado_Civil"));
				myPersona.setDireccion(cx.getString("Direccion"));
				myPersona.setGenero(cx.getString("Genero"));
				myPersona.setEstado(cx.getString("Estado"));
				
			}
			cx.cleanup();

		} catch (Exception e) {
			e.printStackTrace();
		}

		return myPersona;
	}
	
	
	
	

}
