package com.clase.dao;

import com.clase.dao.ConnDB;

import com.clase.models.Usuario;

public class UsuarioDao {

	/**
	 * @param args
	 */
	
	public Usuario getNameUser(String login)
	{
		Usuario user = new Usuario();
		ConnDB cx = new ConnDB();

		String sql = "Select * from usuario where Nombre_Usuario=?;";

		try {
			cx.Prepare(sql);
			cx.setStrings(1, login);
			

			cx.executestmt();

			if (cx.getNext()) {
				user.setNombre(cx.getString("Nombre"));
				user.setNlogin(cx.getString("Nombre_Usuario"));
				} else {
				user = null;
			}
			cx.cleanup();

		} catch (Exception e) {
			e.printStackTrace();
		}

		return user;
	}
	public Usuario getUsuario(String login, String clave) {
		Usuario user = new Usuario();
		ConnDB cx = new ConnDB();

		String sql = "Select * from usuario where Nombre_Usuario=? and Contrasena=?;";

		try {
			cx.Prepare(sql);
			cx.setStrings(1, login);
			cx.setStrings(2, clave);

			cx.executestmt();

			if (cx.getNext()) {
				user.setNombre(cx.getString("Nombre"));
				user.setNlogin(cx.getString("Nombre_Usuario"));
				user.setPclave(cx.getString("Contrasena"));
			} else {
				user = null;
			}
			cx.cleanup();

		} catch (Exception e) {
			e.printStackTrace();
		}

		return user;
	}
	
	public Boolean getUsuarioPermiso(String login, String url) {
		Boolean band = false;
		ConnDB cx = new ConnDB();

		String sql = "select o.url, u.Nombre_Usuario from usuario u inner join opcion_por_usuario os on u.Nombre_Usuario=os.Usuario inner join opcion o on os.idopcion=o.idopcion where u.Nombre_Usuario='"
				+ login + "' and o.url='" + url + "';";

		System.out.println(sql);
		try {
			cx.Prepare(sql);
			

			cx.executestmt();

			if (cx.getNext()) {
				band = true;
			} else {
				band = false;
			}
			cx.cleanup();

		} catch (Exception e) {
			e.printStackTrace();
		}

		return band;
	}

}
