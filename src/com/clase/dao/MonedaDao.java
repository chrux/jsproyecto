package com.clase.dao;

import java.util.ArrayList;

import com.clase.models.Moneda;



public class MonedaDao {
	
	public Moneda findById(int id){
		Moneda myMoneda = new Moneda();
		
		ConnDB cx = new ConnDB();

		String sql = "Select * From moneda where id_moneda=?;";
		System.out.println("Entra a la sentencia: " + sql + id);

		try {
			cx.Prepare(sql);
			cx.setInts(1, id);

			cx.executestmt();

			while (cx.getNext()) {
				myMoneda.setIdMoneda(id);
				myMoneda.setNombreMoneda(cx.getString("nombre_moneda"));
				myMoneda.setEstado(cx.getString("estado"));
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
		String q = "select * from moneda order by id_moneda;";

		ConnDB cx = new ConnDB();
		try {
			cx.consulta(q);
			while (cx.getNext()) {
				Moneda myMoney = new Moneda();
				myMoney.setIdMoneda(cx.getInt("id_moneda"));
				myMoney.setNombreMoneda(cx.getString("nombre_moneda"));
				myMoney.setEstado(cx.getString("estado"));
				
				Lista.add(myMoney);
			}
			cx.cleanup();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return Lista;
	}
	

}
