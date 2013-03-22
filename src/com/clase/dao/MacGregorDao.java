package com.clase.dao;

import java.sql.SQLException;
import java.util.ArrayList;

import com.clase.interfaces.MacGregorInf;
import com.clase.models.Banco;
import com.clase.models.TipoCliente;

public class MacGregorDao implements MacGregorInf {

	@Override
	public ArrayList<Banco> getLista() {
		ArrayList<Banco> Lista = new ArrayList<Banco>();
		String q = "select * from banco order by Id_Banco;";

		ConnDB cx = new ConnDB();
		try {
			cx.consulta(q);
			while (cx.getNext()) {
				Banco myBank = new Banco();
				myBank.setIdBanco(cx.getInt("Id_Banco"));
				myBank.setNombre(cx.getString("Nombre_Banco"));
				myBank.setEstado(cx.getString("Estado"));
				
				Lista.add(myBank);
			}
			cx.cleanup();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return Lista;
	}

	@Override
	public int addBanco(Banco banco) {

		String q = "insert into banco(Id_Banco, Nombre_Banco, Estado) values(?,?,?);";

		int newid = 0;
		int band = 0;
		String qmax = "select count(Id_Banco) + 1 as newid from banco;";

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
			cx.setStrings(2, banco.getNombre().toUpperCase());
			cx.setStrings(3, banco.getEstado() );
			/*cx.setStrings(4, banco.getCarrera());
			cx.setDoubles(5, banco.getMensualidad());*/
			
			System.out.println("Datos Dentro de la función: New Id = " +newid +" Nombre Banco= " + banco.getNombre() +" Estado = "+  banco.getEstado() );
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
	
	

	@Override
	public int updBanco(Banco banco) {

		String q = "update banco set Nombre_Banco=?,Estado=? where Id_Banco=?;";

		int band = 0;

		ConnDB cx = new ConnDB();

		try {
			cx.Prepare(q);
			
			cx.setStrings(1, banco.getNombre().toUpperCase());
			cx.setStrings(2, banco.getEstado() );
		    cx.setInts(3, banco.getIdBanco());
		    
		    if (cx.executestmt(1)) {
				band = 1;
			} else {
				band = 0;
			}

			/*if (cx.executestmt()) {
				band = 0;
			} else {
				band = 1;
			}*/

		} catch (SQLException e) {
			System.out.println("Error SQL: " + e.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return band;
	}

	@Override
	public int delBanco(Banco banco) {
		String q = "delete from banco where Id_Banco=?;";

		int band = 0;

		ConnDB cx = new ConnDB();

		try {
			cx.Prepare(q);
			cx.setInts(1, banco.getIdBanco());

			if (cx.executestmt(1)) {
				band = 1;
			} else {
				band = 0;
			}
			
			/*if (cx.executestmt()) {
				band = 0;
			} else {
				band = 1;
			}*/

		} catch (SQLException e) {
			System.out.println("Error SQL: " + e.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return band;
	}
	
	public ArrayList<TipoCliente> getListaTipoCl() {
		ArrayList<TipoCliente> Lista = new ArrayList<TipoCliente>();
		String q = "select * from tipo_Cliente order by Id_Tipo_Cliente;";

		ConnDB cx = new ConnDB();
		try {
			cx.consulta(q);
			while (cx.getNext()) {
				TipoCliente myTipoCl = new TipoCliente() ;
				myTipoCl.setIdTipoCliente(cx.getInt("Id_Tipo_Cliente"));
				myTipoCl.setTipoCliente(cx.getString("Tipo_Cliente"));
				myTipoCl.setEstado(cx.getString("Estado"));
				
				Lista.add(myTipoCl);
			}
			cx.cleanup();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return Lista;
	}
	
	
	public int addClientTy(TipoCliente clientTy) {

		String q = "insert into tipo_Cliente(Id_Tipo_Cliente, Tipo_Cliente, Estado) values(?,?,?);";

		int newid = 0;
		int band = 0;
		String qmax = "select max(Id_Tipo_Cliente) + 1 as newid from tipo_Cliente;";

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
			cx.setStrings(2, clientTy.getTipoCliente().toUpperCase());
			cx.setStrings(3, clientTy.getEstado() );
		
			
			System.out.println("Datos Dentro de la función: New Id = " +newid +" Nombre Banco= " + clientTy.getTipoCliente() +" Estado = "+  clientTy.getEstado() );
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
	
	
	public int updClientTy(TipoCliente clientTy) {

		String q = "update tipo_Cliente set Tipo_Cliente=?,Estado=? where Id_Tipo_Cliente=?;";

		int band = 0;

		ConnDB cx = new ConnDB();

		try {
			cx.Prepare(q);
			
			cx.setStrings(1, clientTy.getTipoCliente().toUpperCase());
			cx.setStrings(2, clientTy.getEstado() );
		    cx.setInts(3, clientTy.getIdTipoCliente());
		    
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
	

	public int delClientTy(TipoCliente clientTy) {
		String q = "delete from tipo_Cliente where Id_Tipo_Cliente=?;";

		int band = 0;

		ConnDB cx = new ConnDB();

		try {
			cx.Prepare(q);
			cx.setInts(1, clientTy.getIdTipoCliente() );

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
	
	@Override
	public Banco findByID(int idest) {
		Banco est = new Banco();
		ConnDB cx = new ConnDB();

		String sql = "Select * from banco where Id_Banco=?;";

		try {
			cx.Prepare(sql);
			cx.setInts(1, idest);

			cx.executestmt();

			while (cx.getNext()) {
				est.setNombre(cx.getString("Nombre_Banco"));
				est.setEstado(cx.getString("Estado"));
				
			}
			cx.cleanup();

		} catch (Exception e) {
			e.printStackTrace();
		}

		return est;
	}
	
	public TipoCliente findByIDType(int idType) {
		TipoCliente TipoCl = new TipoCliente();
		ConnDB cx = new ConnDB();

		String sql = "Select * from tipo_Cliente where Id_Tipo_Cliente=?;";

		try {
			cx.Prepare(sql);
			cx.setInts(1, idType);

			cx.executestmt();

			while (cx.getNext()) {
				TipoCl.setTipoCliente(cx.getString("Tipo_Cliente"));
				TipoCl.setEstado(cx.getString("Estado"));
				
			}
			cx.cleanup();

		} catch (Exception e) {
			e.printStackTrace();
		}

		return TipoCl;
	}

}
