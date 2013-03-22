package com.clase.dao;

import java.util.ArrayList;

import com.clase.models.Moneda;



public class MonedaDao {
	
	public Moneda findById(int id){
		Moneda myMoneda = new Moneda();
		
		ConnDB cx = new ConnDB();

		String sql = "Select * From moneda where Id_Moneda=?;";
		System.out.println("Entra a la sentencia: " + sql + id);

		try {
			cx.Prepare(sql);
			cx.setInts(1, id);

			cx.executestmt();

			while (cx.getNext()) {
				myMoneda.setIdMoneda(id);
				myMoneda.setNombreMoneda(cx.getString("Nombre_Moneda"));
				myMoneda.setEstado(cx.getString("Estado"));
			}
			cx.cleanup();

		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return myMoneda;
	}
	
	/**
	 *Lista de Monedas 
	 * 
	 **/
	public ArrayList<Moneda> getLista() {
		ArrayList<Moneda> Lista = new ArrayList<Moneda>();
		String q = "select * from moneda order by Id_Moneda;";

		ConnDB cx = new ConnDB();
		try {
			cx.consulta(q);
			while (cx.getNext()) {
				Moneda myMoney = new Moneda();
				myMoney.setIdMoneda(cx.getInt("Id_Moneda"));
				myMoney.setNombreMoneda(cx.getString("Nombre_Moneda"));
				myMoney.setEstado(cx.getString("Estado"));
				
				Lista.add(myMoney);
			}
			cx.cleanup();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return Lista;
	}
	

}
