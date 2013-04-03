package com.clase.dao;

import java.sql.SQLException;
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
	
	public int addMoneda(Moneda moneda){
		String query = "insert into moneda (Id_Moneda,Nombre_Moneda,Estado) values(?,?,?,?)";
		int newid = 0;
		int band = 0;
		String queryMax = "Select count(Id_Moneda) + 1 as newid from moneda;";
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
			cnx.setStrings(2, moneda.getNombreMoneda());
			cnx.setStrings(3, moneda.getEstado());	
			//Logueamos los datos a insetar
			System.out.println("Datos Dentro de la función: New Id = " +newid +" Nombre Moneda= " + moneda.getNombreMoneda() +" Estado = "+  moneda.getEstado() );
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
	
	public int updMoneda(Moneda moneda){
		String query = "update moneda set Nombre_Moneda=?,estado=? where Id_Moneda=?;";
		int band = 0;
		ConnDB cnx = new ConnDB();
		try{
			//Preparamos los valores a actualizar
			cnx.Prepare(query);			
			cnx.setStrings(1, moneda.getNombreMoneda());
			cnx.setStrings(2, moneda.getEstado());	
			cnx.setInts(3, moneda.getIdMoneda());
			//Logueamos el update a ejecutar
			System.out.println(query);
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
	
	public int delMoneda(Moneda moneda){
		String query = "delete from moneda where Id_Moneda=?;";
		int band = 0;
		ConnDB cnx = new ConnDB();
		try{
			//Preparamos
			cnx.Prepare(query);
			cnx.setInts(1, moneda.getIdMoneda());
			//Ejecutamos para borrar
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
