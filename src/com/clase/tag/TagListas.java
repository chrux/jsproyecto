package com.clase.tag;

import java.sql.SQLException;

import javax.servlet.jsp.tagext.TagSupport;

import com.clase.dao.ConnDB;

public class TagListas extends TagSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5591142113195693298L;

	private String tablename = "";
	private String campo_id = "";
	private String campo_des = "";
	private int idactual;
	private String idactualstring = "";
	private String condicion="";
	private String nombre="";
	private int tipo;

	ConnDB myconn = new ConnDB();

	public int getIdactual() {
		return idactual;
	}

	public void setIdactual(int idactual) {
		this.idactual = idactual;
	}

	public String getTablename() {
		return tablename;
	}

	public void setTablename(String tablename) {
		this.tablename = tablename;
	}

	public String getCampo_id() {
		return campo_id;
	}

	public void setCampo_id(String campoId) {
		campo_id = campoId;
	}

	public String getCampo_des() {
		return campo_des;
	}

	public void setCampo_des(String campoDes) {
		campo_des = campoDes;
	}

	public String getCondicion() {
		return condicion;
	}

	public void setCondicion(String condicion) {
		this.condicion = condicion;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getIdactualstring() {
		return idactualstring;
	}

	public void setIdactualstring(String idactualstring) {
		this.idactualstring = idactualstring;
	}

	public int getTipo() {
		return tipo;
	}

	public void setTipo(int tipo) {
		this.tipo = tipo;
	}

	public int doStartTag() {
		return SKIP_BODY;
	}

	public int doEndTag() {
		String sql = "Select " + getCampo_id() + ", " + getCampo_des()
				+ " from " + getTablename() + " where " + condicion + ";";
		String datos = "";
		try {
			myconn.consulta(sql);
			datos = datos + "<select id=\"" + nombre + "\" name=\""
					+ nombre + "\">";
			while (myconn.getNext()) {
				if (tipo == 1) {
					if (idactual == Integer.parseInt(myconn
							.getString("" + getCampo_id() + ""))) {
						datos = datos
								+ "<option selected=\"selected\" value=\""
								+ myconn.getString("" + getCampo_id() + "")
								+ " \">"
								+ myconn.getString("" + getCampo_id() + "")
								+ ": "
								+ myconn.getString("" + getCampo_des() + "")
								+ "</option>";
					} else {
						datos = datos + "<option value=\""
								+ myconn.getString("" + getCampo_id() + "")
								+ "\">"
								+ myconn.getString("" + getCampo_id() + "")
								+ ": "
								+ myconn.getString("" + getCampo_des() + "")
								+ "</option>";
					}
				} else {
					if (idactualstring == (myconn
							.getString("" + getCampo_id() + "")).trim()) {
						datos = datos
								+ "<option selected=\"selected\" value=\""
								+ myconn.getString("" + getCampo_id() + "")
								+ " \">"
								+ myconn.getString("" + getCampo_id() + "")
								+ ": "
								+ myconn.getString("" + getCampo_des() + "")
								+ "</option>";
					} else {
						datos = datos + "<option value=\""
								+ myconn.getString("" + getCampo_id() + "")
								+ "\">"
								+ myconn.getString("" + getCampo_id() + "")
								+ ": "
								+ myconn.getString("" + getCampo_des() + "")
								+ "</option>";
					}
				}
			}
			datos = datos + "</select>";
			pageContext.getOut().println(datos);
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return EVAL_PAGE;
	}

}
