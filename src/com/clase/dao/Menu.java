package com.clase.dao;

import com.clase.dao.ConnDB;
import com.clase.models.Usuario;

public class Menu {

	public String getListMenu(Usuario user, String ruta) {
		String datos = "";

		String sql = "Select o.idopcion, o.descripcion, os.Usuario from Opcion o " + 
" inner join opcion_por_usuario os on os.idopcion=o.idopcion "+ 
" where o.padre=0 and o.orden<>0 and os.Usuario=? order by o.orden;";

		System.out.println("Esta es la query para el menú: " + sql + " Este es el login: " + user.getNlogin() );
		ConnDB cx = new ConnDB();

		try {
			cx.Prepare(sql);

			cx.setStrings(1, user.getNlogin());

			cx.executestmt();

			while (cx.getNext()) {
				datos = datos + "<li>";
				datos = datos + "<a href=\"#\" class=\"p7PMtrg\">" + cx.getString("descripcion") + "</a>";
				datos = datos + getHijos(user.getNlogin(), cx.getInt("idopcion"), ruta);

				datos = datos + "</li>";
			}
			cx.cleanup();

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return datos;

	}

	public String getHijos(String usuario, int padre, String ruta) {
		String hijos = "";

		String sql = "Select o.url, o.descripcion, os.Usuario from Opcion o " + 
" inner join opcion_por_usuario os on os.idopcion=o.idopcion " +
" where o.padre=?  and os.Usuario=? order by o.orden;";

		System.out.println("Esta es la query para el menú: " + sql + " Este es el padre: " + padre + " Este es el usuario" + usuario );
		ConnDB cx = new ConnDB();

		try {
			cx.Prepare(sql);
			cx.setInts(1, padre);
			cx.setStrings(2, usuario);
			

			cx.executestmt();

			hijos = hijos + "<ul class="">";
			while (cx.getNext()) {
				hijos = hijos + "<li><a href=\"" + ruta + "/"
						+ cx.getString("url") + "\">"
						+ cx.getString("descripcion") + "</a></li>";
			}
			hijos = hijos + "</ul>";
			cx.cleanup();

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return hijos;

	}

}
