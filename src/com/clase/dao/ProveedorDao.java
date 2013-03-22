package com.clase.dao;

import java.util.ArrayList;


import com.clase.models.Proveedor;

public class ProveedorDao {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	
	public ArrayList<Proveedor> getLista() {
		ArrayList<Proveedor> Lista = new ArrayList<Proveedor>();
		String q = "select * from proveedor order by Id_Proveedor;";

		ConnDB cx = new ConnDB();
		try {
			cx.consulta(q);
			while (cx.getNext()) {
				Proveedor myProv = new Proveedor();
				myProv.setIdProveedor(cx.getInt("Id_Proveedor"));
				myProv.setTipoProveedor(cx.getInt("Id_Tipo_Proveedor"));
				myProv.setNombre(cx.getString("Nombre"));
				myProv.setContacto(cx.getString("Contacto"));
				myProv.setTelefono(cx.getString("Telefono"));
				myProv.setDirPostal(cx.getString("Direccion_Postal"));
				myProv.setPagWeb(cx.getString("Pagina_Web"));
				myProv.setRuc(cx.getString("Ruc"));
				myProv.seteMail(cx.getString("E_mail"));
				myProv.setEstado(cx.getString("Estado"));
				
				Lista.add(myProv);
			}
			cx.cleanup();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return Lista;
	}
	
	public Proveedor findByID(int idProv) {
		Proveedor myProv = new Proveedor();
		ConnDB cx = new ConnDB();

		String sql = "Select * from proveedor where Id_Proveedor=?;";

		try {
			cx.Prepare(sql);
			cx.setInts(1, idProv);

			cx.executestmt();

			while (cx.getNext()) {
				myProv.setTipoProveedor(cx.getInt("Id_Tipo_Proveedor"));
				myProv.setNombre(cx.getString("Nombre"));
				myProv.setContacto(cx.getString("Contacto"));
				myProv.setTelefono(cx.getString("Telefono"));
				myProv.setDirPostal(cx.getString("Direccion_Postal"));
				myProv.setPagWeb(cx.getString("Pagina_Web"));
				myProv.setRuc(cx.getString("Ruc"));
				myProv.seteMail(cx.getString("E_mail"));
				myProv.setEstado(cx.getString("Estado"));
				
				
			}
			cx.cleanup();

		} catch (Exception e) {
			e.printStackTrace();
		}

		return myProv;
	}

}
