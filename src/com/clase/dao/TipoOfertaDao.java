package com.clase.dao;

import java.util.ArrayList;

import com.clase.models.TipoOferta;

public class TipoOfertaDao {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	
	public TipoOferta findById(int id){
		TipoOferta tipoOferta = new TipoOferta();
		
		
		ConnDB cx = new ConnDB();

		String sql = "Select * From tipo_oferta where Id_Tipo_Oferta=?;";
		System.out.println("Entra a la sentencia: " + sql + id);

		try {
			cx.Prepare(sql);
			cx.setInts(1, id);

			cx.executestmt();

			while (cx.getNext()) {
				tipoOferta.setIdTipoOferta(id);
				tipoOferta.setNombreOferta(cx.getString("Nombre_Oferta"));
				tipoOferta.setEstado(cx.getString("Estado"));
			}
			cx.cleanup();

		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return tipoOferta;
	
		
	}
	
	/**
	 *Lista de Tipos de Oferta 
	 * 
	 **/
	public ArrayList<TipoOferta> getLista() {
		ArrayList<TipoOferta> Lista = new ArrayList<TipoOferta>();
		String q = "select * from tipo_oferta order by Id_Tipo_Oferta;";

		ConnDB cx = new ConnDB();
		try {
			cx.consulta(q);
			while (cx.getNext()) {
TipoOferta myType = new TipoOferta();
				myType.setIdTipoOferta(cx.getInt("Id_Tipo_Oferta"));
			myType.setNombreOferta(cx.getString("Nombre_Oferta"));
						myType.setEstado(cx.getString("Estado"));
				
				Lista.add(myType);
			}
			cx.cleanup();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return Lista;
	}
	

}
