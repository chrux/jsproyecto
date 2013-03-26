package com.clase.dao;

import com.clase.dao.ConnDB;

import com.clase.models.Usuario;
import com.clase.util.Hash;

public class UsuarioDao {

	/**
	 * @param args
	 */
	
	public Usuario getNameUser(String login)
	{
		Usuario user = new Usuario();
		ConnDB cx = new ConnDB();

		String sql = "Select * from usuario where nombre_usuario=?;";

		try {
			cx.Prepare(sql);
			cx.setStrings(1, login);
			

			cx.executestmt();

			if (cx.getNext()) {
				user.setNombre(cx.getString("nombre"));
				user.setNlogin(cx.getString("nombre_usuario"));
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

		String sql = "Select * from usuario where nombre_usuario=? and contrasena=?;";

		try {
			cx.Prepare(sql);
			cx.setStrings(1, login);
			cx.setStrings(2, Hash.md5(clave));

			cx.executestmt();

			if (cx.getNext()) {
				user.setNombre(cx.getString("nombre"));
				user.setNlogin(cx.getString("nombre_usuario"));
				user.setPclave(cx.getString("contrasena"));
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

		String sql = "select o.url, u.nombre_usuario from usuario u inner join opcion_por_usuario os on u.nombre_usuario=os.usuario inner join opcion o on os.idopcion=o.idopcion where u.nombre_usuario='"
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
