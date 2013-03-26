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
		String q = "select * from banco order by id_banco;";

		ConnDB cx = new ConnDB();
		try {
			cx.consulta(q);
			while (cx.getNext()) {
				Banco myBank = new Banco();
				myBank.setIdBanco(cx.getInt("id_banco"));
				myBank.setNombre(cx.getString("nombre_banco"));
				myBank.setEstado(cx.getString("estado"));
				
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

		String q = "insert into banco(id_banco, nombre_banco, estado) values(?,?,?);";

		int newid = 0;
		int band = 0;
		String qmax = "select count(id_banco) + 1 as newid from banco;";

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

		String q = "update banco set nombre_banco=?,estado=? where id_banco=?;";

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
		String q = "delete from banco where id_banco=?;";

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
		String q = "select * from tipo_cliente order by id_tipo_cliente;";

		ConnDB cx = new ConnDB();
		try {
			cx.consulta(q);
			while (cx.getNext()) {
				TipoCliente myTipoCl = new TipoCliente() ;
				myTipoCl.setIdTipoCliente(cx.getInt("id_tipo_cliente"));
				myTipoCl.setTipoCliente(cx.getString("tipo_cliente"));
				myTipoCl.setEstado(cx.getString("estado"));
				
				Lista.add(myTipoCl);
			}
			cx.cleanup();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return Lista;
	}
	
	
	public int addClientTy(TipoCliente clientTy) {

		String q = "insert into tipo_cliente(id_tipo_cliente, tipo_cliente, estado) values(?,?,?);";

		int newid = 0;
		int band = 0;
		String qmax = "select max(id_tipo_cliente) + 1 as newid from tipo_cliente;";

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

		String q = "update tipo_cliente set tipo_cliente=?,estado=? where id_tipo_cliente=?;";

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
		String q = "delete from tipo_cliente where id_tipo_cliente=?;";

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

		String sql = "Select * from banco where id_banco=?;";

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

		String sql = "Select * from tipo_cliente where id_tipo_cliente=?;";

		try {
			cx.Prepare(sql);
			cx.setInts(1, idType);

			cx.executestmt();

			while (cx.getNext()) {
				TipoCl.setTipoCliente(cx.getString("tipo_cliente"));
				TipoCl.setEstado(cx.getString("estado"));
				
			}
			cx.cleanup();

		} catch (Exception e) {
			e.printStackTrace();
		}

		return TipoCl;
	}

}
