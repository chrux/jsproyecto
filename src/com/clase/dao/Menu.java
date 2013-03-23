package com.clase.dao;

import com.clase.dao.ConnDB;
import com.clase.models.Usuario;

public class Menu {

	public String getListMenu(Usuario user, String ruta) {
		String datos = "";

		String sql = "Select o.idopcion, o.url, o.descripcion, os.Usuario, (select count(idopcion) from Opcion h where h.padre=o.idopcion) hijos  from Opcion o " + 
" inner join opcion_por_usuario os on os.idopcion=o.idopcion "+ 
" where o.padre=0 and o.orden<>0 and os.Usuario=? order by o.orden;";

		System.out.println("Esta es la query para el menú: " + sql + " Este es el login: " + user.getNlogin() );
		ConnDB cx = new ConnDB();

		try {
			cx.Prepare(sql);

			cx.setStrings(1, user.getNlogin());

			cx.executestmt();

			while (cx.getNext()) {
				if ( cx.getInt("hijos") > 0 ) {
					datos = datos + "<li class=\"dropdown\">";
					datos = datos + "<a href=\"#\" class=\"dropdown-toggle\" data-toggle=\"dropdown\">" + cx.getString("descripcion") + " <b class=\"caret\"></b></a>";
					datos = datos + getHijos(user.getNlogin(), cx.getInt("idopcion"), ruta);
				} else {
					datos = datos + "<li>";
					datos = datos + "<a href=\"" + ruta + '/' + cx.getString("url") + "\" class=\"\">" + cx.getString("descripcion") + "</a>";
				}

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

		String sql = "Select o.idopcion, o.url, o.descripcion, os.Usuario, (select count(idopcion) from Opcion h where h.padre=o.idopcion) hijos from Opcion o " + 
" inner join opcion_por_usuario os on os.idopcion=o.idopcion " +
" where o.padre=?  and os.Usuario=? order by o.orden;";

		System.out.println("Esta es la query para el menú: " + sql + " Este es el padre: " + padre + " Este es el usuario" + usuario );
		ConnDB cx = new ConnDB();

		try {
			cx.Prepare(sql);
			cx.setInts(1, padre);
			cx.setStrings(2, usuario);
			

			cx.executestmt();

			hijos = hijos + "<ul class=\"dropdown-menu\">";
			while (cx.getNext()) {
				if ( cx.getInt("hijos") > 0 ) {
					hijos += "<li class=\"dropdown-submenu\">";
					hijos += "<a href=\"#\">" + cx.getString("descripcion") + "</a>";
					hijos += getHijos(usuario, cx.getInt("idopcion"), ruta);
				} else {
					hijos += "<li>";
					hijos += "<a href=\"" + ruta + "/" + cx.getString("url") + "\">" + cx.getString("descripcion") + "</a>";
				}
				hijos += "</li>";
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
