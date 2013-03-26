package com.clase.dao;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;


import com.clase.models.Cheques;



public class ChequesDao {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	
	public ArrayList<Cheques> getLista() {
		ArrayList<Cheques> Lista = new ArrayList<Cheques>();
		String q = "select * from ejecucion order by id_ejecucion;";

		ConnDB cx = new ConnDB();
		try {
			cx.consulta(q);
			while (cx.getNext()) {
				Cheques misCheques = new Cheques();
				misCheques.setIdCheque(cx.getInt("id_ejecucion") );
				misCheques.setIdProveedor(cx.getInt("id_proveedor"));
				misCheques.setIdProyecto(cx.getInt("id_proyecto"));
				misCheques.setMonto(cx.getDouble("monto"));
				misCheques.setFechaEmision(cx.getDateStamp("fecha_emision"));
				misCheques.setNumCheque(cx.getInt("numero_cheque"));
				misCheques.setConcepto(cx.getString("concepto"));
				misCheques.setEstado(cx.getString("estado"));
				
				Lista.add(misCheques);
			}
			cx.cleanup();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return Lista;
	}
	
	public Cheques findByID(int idCargo) {
		Cheques misCheques = new Cheques();
		ConnDB cx = new ConnDB();

		String sql = "Select * from ejecucion where id_ejecucion=?;";

		try {
			cx.Prepare(sql);
			cx.setInts(1, idCargo);

			cx.executestmt();

			while (cx.getNext()) {
				misCheques.setIdProveedor(cx.getInt("id_proveedor"));
				misCheques.setIdProyecto(cx.getInt("id_proyecto"));
				misCheques.setMonto(cx.getDouble("monto"));
				misCheques.setFechaEmision(cx.getDateStamp("fecha_emision"));
				misCheques.setNumCheque(cx.getInt("numero_cheque"));
				misCheques.setConcepto(cx.getString("concepto"));
				misCheques.setEstado(cx.getString("estado"));
				
			}
			cx.cleanup();

		} catch (Exception e) {
			e.printStackTrace();
		}

		return misCheques;
	}
	
	public ArrayList<Cheques> getLista(int id) {
		ArrayList<Cheques> Lista = new ArrayList<Cheques>();
		String q = "Select * From ejecucion where id_proyecto=?;";//+ id + ";" ;
		
		System.out.println(q + " id=" + id);
		

		ConnDB cx = new ConnDB();
		try {
			
			cx.Prepare(q);
			cx.setInts(1, id);
			//cx.consulta(q);
			
			

		cx.executestmt();
			while (cx.getNext()) {
				Cheques misCheques = new Cheques();
				misCheques.setIdCheque(cx.getInt("id_ejecucion"));
				misCheques.setIdProveedor(cx.getInt("id_proveedor"));
				misCheques.setIdProyecto(cx.getInt("id_proyecto"));
				misCheques.setMonto(cx.getDouble("monto"));
				misCheques.setFechaEmision(cx.getDateStamp("fecha_emision"));
				misCheques.setNumCheque(cx.getInt("numero_cheque"));
				misCheques.setConcepto(cx.getString("concepto"));
				misCheques.setEstado(cx.getString("estado"));
				
				Lista.add(misCheques);
			}
			cx.cleanup();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return Lista;
	}

	public int DelCheque(Cheques myCheque){
		String q = "delete from ejecucion where id_proyecto=? and id_ejecucion=?;";
		int band=0;
		ConnDB cx = new ConnDB();

		try {
			cx.Prepare(q);
			cx.setInts(1, myCheque.getIdProyecto() );
			cx.setInts(2, myCheque.getIdCheque());

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
	
	
	public int addCheque(Cheques myCheque) {
		String q = "insert into ejecucion(id_ejecucion, id_tasa_cambio, id_proveedor, id_proyecto, id_cuentas_bancos, monto, fecha_emision, numero_cheque, concepto, estado) " +
				"values(?,?,?,?,?,?,?,?,?,?);";

		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss.SSS");
		java.util.Date parsedDate;
		
		int newid = 0;
		int idTasa=0;
		int idCuenta=0;
		int band=0;
		String qmax = "select max(id_ejecucion) + 1 as newid from ejecucion;";
		String tasa= "select max(id_tasa_cambio)  as idtasa from tasa_de_cambio;";
		String cuenta= "select max(id_cuenta)  as idcuenta from cuentas_bancos;";
		System.out.println(qmax);
				ConnDB cx = new ConnDB();
				try {
					
					cx.consulta(qmax);
					while (cx.getNext()) {
						newid = cx.getInt("newid");
					}
					
					cx.consulta(tasa);
					while (cx.getNext()) {
						idTasa=cx.getInt("idtasa");	
					}
					
					cx.consulta(cuenta);
					
					while (cx.getNext()) {
						idCuenta=cx.getInt("idcuenta");	
					}
					
					
					

					
					
					System.out.println(newid);
					System.out.println(myCheque.getIdCheque() );
					System.out.println("Entrará a insertar ");
					
					if (newid == 0)
						newid = 1;
					
					cx.Prepare(q);
					cx.setInts(1, newid);
					//Id_Tasa_Cambio,Id_Proveedor,
					//Id_Proyecto,Id_Cuentas_Bancos,Monto,Fecha_Emision,Numero_Cheque,Concepto,Estado
					cx.setInts(2, idTasa);
					cx.setInts(3,myCheque.getIdProveedor() );
					cx.setInts(4, myCheque.getIdProyecto() );
					cx.setInts(5, idCuenta);
					cx.setDoubles(6, myCheque.getMonto());
					cx.setTimestamp(7, myCheque.getFechaEmision());
					cx.setInts(8, myCheque.getNumCheque());
					cx.setStrings(9, myCheque.getConcepto());
					cx.setStrings(10, myCheque.getEstado(1));
					System.out.println("Datos Dentro de la función: New Id = " +newid +" Numero Cheque= " + myCheque.getNumCheque() +" Estado = "+  myCheque.getEstado() );
					System.out.println(q);
					if (cx.executestmt(1)) {
						band = 1;
					} else {
						band = 0;
					}
					
				}
	 catch (SQLException e) {
		System.out.println("Error SQL: " + e.getMessage());
		band = 0;
	} catch (Exception e) {
		e.printStackTrace();
		band= 0;
	}
		return band;
		
	}

}
