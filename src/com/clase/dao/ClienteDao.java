package com.clase.dao;

import java.util.ArrayList;

import com.clase.models.Cliente;


public class ClienteDao {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	
	public Cliente findById(int id){
		Cliente cliente = new Cliente();
		
		
		ConnDB cx = new ConnDB();

		String sql = "Select * From clientes where Id_Clientes=?;";
		System.out.println("Entra a la sentencia: " + sql + id);

		try {
			cx.Prepare(sql);
			cx.setInts(1, id);

			cx.executestmt();

			while (cx.getNext()) {
				cliente.setIdCliente(id);
				cliente.setNombre(cx.getString("Nombre"));
				cliente.setIdTipoCliente(cx.getInt("Id_Tipo_Cliente"));
				cliente.setContacto(cx.getString("Contacto"));
				cliente.setDireccionPos(cx.getString("Direccion_Postal"));
				cliente.setDireccionFis(cx.getString("Direccion"));
				cliente.setTelefono(cx.getString("Telefono"));
				cliente.setEmail(cx.getString("E_mail"));
				cliente.setNacionalidad(cx.getString("Nacionalidad"));
				cliente.setPaginaWeb(cx.getString("Pagina_Web"));
				cliente.setEstado(cx.getString("Estado"));
			}
			cx.cleanup();

		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return cliente;
	
		
	}
	
	/**
	 *Lista de Cliente 
	 * 
	 **/
	public ArrayList<Cliente> getLista() {
		ArrayList<Cliente> Lista = new ArrayList<Cliente>();
		String q = "select * from clientes order by Id_Clientes;";

		ConnDB cx = new ConnDB();
		try {
			cx.consulta(q);
			while (cx.getNext()) {
				Cliente myClient = new Cliente();
				myClient.setIdCliente(cx.getInt("Id_Clientes"));
				myClient.setIdTipoCliente(cx.getInt("Id_Tipo_Cliente"));
				myClient.setNombre(cx.getString("Nombre"));
				myClient.setContacto(cx.getString("Contacto"));
				myClient.setDireccionFis(cx.getString("Direccion_Postal"));
				myClient.setDireccionPos(cx.getString("Direccion"));
				myClient.setTelefono(cx.getString("Telefono"));
				myClient.setEmail(cx.getString("E_mail") );
				myClient.setNacionalidad(cx.getString("Nacionalidad"));
				myClient.setPaginaWeb(cx.getString("Pagina_Web"));
				myClient.setEstado(cx.getString("Estado"));
				
				Lista.add(myClient);
			}
			cx.cleanup();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return Lista;
	}
	

}
