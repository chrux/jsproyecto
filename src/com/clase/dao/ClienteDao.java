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

		String sql = "Select * From clientes where id_clientes=?;";
		System.out.println("Entra a la sentencia: " + sql + id);

		try {
			cx.Prepare(sql);
			cx.setInts(1, id);

			cx.executestmt();

			while (cx.getNext()) {
				cliente.setIdCliente(id);
				cliente.setNombre(cx.getString("nombre"));
				cliente.setIdTipoCliente(cx.getInt("id_tipo_cliente"));
				cliente.setContacto(cx.getString("contacto"));
				cliente.setDireccionPos(cx.getString("direccion_postal"));
				cliente.setDireccionFis(cx.getString("direccion"));
				cliente.setTelefono(cx.getString("telefono"));
				cliente.setEmail(cx.getString("e_mail"));
				cliente.setNacionalidad(cx.getString("nacionalidad"));
				cliente.setPaginaWeb(cx.getString("pagina_web"));
				cliente.setEstado(cx.getString("estado"));
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
		String q = "select * from clientes order by id_clientes;";

		ConnDB cx = new ConnDB();
		try {
			cx.consulta(q);
			while (cx.getNext()) {
				Cliente myClient = new Cliente();
				myClient.setIdCliente(cx.getInt("id_clientes"));
				myClient.setIdTipoCliente(cx.getInt("id_tipo_cliente"));
				myClient.setNombre(cx.getString("nombre"));
				myClient.setContacto(cx.getString("contacto"));
				myClient.setDireccionFis(cx.getString("direccion_postal"));
				myClient.setDireccionPos(cx.getString("direccion"));
				myClient.setTelefono(cx.getString("telefono"));
				myClient.setEmail(cx.getString("e_mail") );
				myClient.setNacionalidad(cx.getString("nacionalidad"));
				myClient.setPaginaWeb(cx.getString("pagina_web"));
				myClient.setEstado(cx.getString("estado"));
				
				Lista.add(myClient);
			}
			cx.cleanup();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return Lista;
	}
	

}
