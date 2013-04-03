package com.clase.dao;

import java.sql.SQLException;
import java.util.ArrayList;

import com.clase.models.Cliente;


public class ClienteDao {


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
	
	public int addCliente(Cliente cliente){
		String query = "insert into clientes (id_clientes, id_tipo_cliente, nombre, contacto, direccion_postal, direccion, telefono, e_mail, nacionalidad, pagina_web, estado) values()";
		int newid = 0;
		int band = 0;
		String queryMax = "Select count(Id_clientes) + 1 as newid from clientes;";
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
			cnx.setInts(2, cliente.getIdTipoCliente());
			cnx.setStrings(3, cliente.getNombre());
			cnx.setStrings(4, cliente.getContacto());
			cnx.setStrings(5, cliente.getDireccionPos());
			cnx.setStrings(6, cliente.getDireccionFis());
			cnx.setStrings(7, cliente.getTelefono());
			cnx.setStrings(8, cliente.getEmail());
			cnx.setStrings(9, cliente.getNacionalidad());
			cnx.setStrings(10, cliente.getPaginaWeb());
			cnx.setStrings(11, cliente.getEstado());
			//Logueamos los datos a insetar
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
	
	public int updCliente(Cliente cliente){		
		String query = "update clientes set id_tipo_cliente=?, nombre=?, contacto=?, direccion_postal=?, direccion=?, telefono=?, e_mail=?, nacionalidad=?, pagina_web=?, estado=? where Id_clientes=?;";
		int band = 0;
		ConnDB cnx = new ConnDB();
		try{
		//Preparamos los valores a actualizar
		cnx.Prepare(query);	
		//cnx.setInts(1, newid);
		cnx.setInts(1, cliente.getIdTipoCliente());
		cnx.setStrings(2, cliente.getNombre());
		cnx.setStrings(3, cliente.getContacto());
		cnx.setStrings(4, cliente.getDireccionPos());
		cnx.setStrings(5, cliente.getDireccionFis());
		cnx.setStrings(6, cliente.getTelefono());
		cnx.setStrings(7, cliente.getEmail());
		cnx.setStrings(8, cliente.getNacionalidad());
		cnx.setStrings(9, cliente.getPaginaWeb());
		cnx.setStrings(10, cliente.getEstado());
		cnx.setInts(11, cliente.getIdCliente());		
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
	
	public int delCliente(Cliente cliente){
		String query = "delete from clientes where Id_Clientes=?;";
		int band = 0;
		ConnDB cnx = new ConnDB();
		try{
			cnx.Prepare(query);
			cnx.setInts(1, cliente.getIdCliente());
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
