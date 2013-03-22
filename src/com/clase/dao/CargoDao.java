package com.clase.dao;

import java.sql.SQLException;
import java.util.ArrayList;


import com.clase.models.Cargo;

public class CargoDao {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	public ArrayList<Cargo> getLista() {
		ArrayList<Cargo> Lista = new ArrayList<Cargo>();
		String q = "select * from cargo order by Id_Cargo;";

		ConnDB cx = new ConnDB();
		try {
			cx.consulta(q);
			while (cx.getNext()) {
				Cargo myPosition = new Cargo();
				myPosition.setIdCargo(cx.getInt("Id_Cargo"));
				myPosition.setNombreCargo(cx.getString("Nombre_Cargo"));
				myPosition.setEstado(cx.getString("Estado"));
				
				Lista.add(myPosition);
			}
			cx.cleanup();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return Lista;
	}
	
	
	public int addCargo(Cargo cargo) {

		String q = "insert into Cargo(Id_Cargo, Nombre_Cargo, Estado) values(?,?,?);";

		int newid = 0;
		int band = 0;
		String qmax = "select count(Id_Cargo) + 1 as newid from Cargo;";

		ConnDB cx = new ConnDB();

		try {
			cx.consulta(qmax);

			while (cx.getNext()) {
				newid = cx.getInt("newid");
			}

			if (newid == 0)
				newid = 1;

			cx.Prepare(q);
			cx.setInts(1, newid);
			cx.setStrings(2, cargo.getNombreCargo().toUpperCase());
			cx.setStrings(3, cargo.getEstado() );

			
			System.out.println("Datos Dentro de la función: New Id = " +newid +" Nombre Banco= " + cargo.getNombreCargo() +" Estado = "+  cargo.getEstado() );
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
	
	public int updCargo(Cargo cargo) {

		String q = "update cargo set Nombre_Cargo=?,Estado=? where Id_Cargo=?;";

		int band = 0;

		ConnDB cx = new ConnDB();

		try {
			cx.Prepare(q);
			
			cx.setStrings(1, cargo.getNombreCargo().toUpperCase());
			cx.setStrings(2, cargo.getEstado() );
		    cx.setInts(3, cargo.getIdCargo());
		    
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
	
	public int delCargo(Cargo cargo) {
		String q = "delete from cargo where Id_Cargo=?;";

		int band = 0;

		ConnDB cx = new ConnDB();

		try {
			cx.Prepare(q);
			cx.setInts(1, cargo.getIdCargo());

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
	
	
	public Cargo findByID(int idCargo) {
		Cargo est = new Cargo();
		ConnDB cx = new ConnDB();

		String sql = "Select * from cargo where Id_Cargo=?;";

		try {
			cx.Prepare(sql);
			cx.setInts(1, idCargo);

			cx.executestmt();

			while (cx.getNext()) {
				est.setNombreCargo(cx.getString("Nombre_Cargo"));
				est.setEstado(cx.getString("Estado"));
				
			}
			cx.cleanup();

		} catch (Exception e) {
			e.printStackTrace();
		}

		return est;
	}
	
	

}
