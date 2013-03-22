package com.clase.dao;

import java.util.ArrayList;

import com.clase.models.Oficio;



public class OficioDao {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	
	public ArrayList<Oficio> getLista() {
		ArrayList<Oficio> Lista = new ArrayList<Oficio>();
		String q = "select * from oficio order by Id_Oficio;";

		ConnDB cx = new ConnDB();
		try {
			cx.consulta(q);
			while (cx.getNext()) {
				Oficio ofici = new Oficio();
				
				ofici.setIdOficio(cx.getInt("Id_Oficio"));
				ofici.setOficio(cx.getString("Oficio"));
				ofici.setEstado(cx.getString("Estado"));
				
				Lista.add(ofici);
			}
			cx.cleanup();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return Lista;
	}
	
	
	public Oficio findByID(int idOficio) {
		Oficio oficio = new Oficio();
		ConnDB cx = new ConnDB();

		String sql = "Select * from oficio where Id_Oficio=?;";

		try {
			cx.Prepare(sql);
			cx.setInts(1, idOficio);

			cx.executestmt();

			while (cx.getNext()) {
				oficio.setIdOficio(cx.getInt("Id_Oficio"));
				oficio.setOficio(cx.getString("Oficio"));		
				oficio.setEstado(cx.getString("Estado"));
				
			}
			cx.cleanup();

		} catch (Exception e) {
			e.printStackTrace();
		}

		return oficio;
	}
	
	

}
