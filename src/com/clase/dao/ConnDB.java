package com.clase.dao;

import java.io.Serializable;
import java.sql.*;

/**
 * 
 * @author Eduardo Clase de conexión
 */
public class ConnDB implements Serializable {
	private static final long serialVersionUID = 1L;
	Connection c = null;
	PreparedStatement ps = null;
	Statement s = null;
	ResultSet rs = null;
	CallableStatement miProcedimiento = null;
	//private String connectionUrl = "jdbc:postgresql://localhost:5432/uca?user=postgres&password=3382736";

	private String connectionUrl = "jdbc:mysql://localhost/bd_mcgregor?user=mcgregor&password=mcgregor";

	public ConnDB() {
		try {

			//Class.forName("org.postgresql.Driver");
			Class.forName("com.mysql.jdbc.Driver");
			c = DriverManager.getConnection(connectionUrl);

		} catch (java.lang.ClassNotFoundException e) {
			System.out.println("Sql JDBC Driver not found in class path");
			System.out.println(e.getMessage());
			return;
		} catch (java.sql.SQLException e) {
			System.out.println("Driver manager failed to register driver");
			showSQLException(e);
			return;
		}

	}
	
	public Connection getConnection(){
		return this.c;
	}

	public void consulta(String sql) throws Exception {
		s = c.createStatement();
		rs = s.executeQuery(sql);
	}

	public boolean getNext() throws Exception {
		if (rs.next()) {
			return true;
		} else {
			return false;
		}
	}

	public String getString(String campo) throws Exception {
		return rs.getString(campo);
	}
	
	public Date getDate(String campo)throws Exception {
		return rs.getDate(campo);
	}

	public Timestamp getDateStamp(String campo) throws Exception{
		return rs.getTimestamp(campo);
	}
	
	public String getString(int campo) throws Exception {
		return rs.getString(campo);
	}
	
	public double getDouble(String campo) throws Exception {
		return rs.getDouble(campo);
	}

	public double getDouble(int campo) throws Exception {
		return rs.getDouble(campo);
	}

	public String simpleconsulta(String sql, String campo) throws Exception {
		System.out.println(sql);
		s = c.createStatement();
		rs = s.executeQuery(sql);
		rs.next();
		return (rs.getString(campo));
	}

	public void multipleconsulta(String sql) throws Exception {
		System.out.println(sql);
		s = c.createStatement();
		rs = s.executeQuery(sql);
	}

	public int getInt(String campo) throws Exception {
		return (rs.getInt(campo));
	}

	public void callProc(String sql) throws Exception {
		miProcedimiento = c.prepareCall("CALL " + sql);
	}

	public void setString(int i, String valor) throws Exception {
		miProcedimiento.setString(i, valor);
	}

	public void execute() throws Exception {
		miProcedimiento.execute();
	}

	public void Prepare(String sqlStatement) throws Exception {
		ps = c.prepareStatement(sqlStatement);
	}

	/* Seteo de valores */
	public void setInts(int pos, int val) throws Exception {
		ps.setInt(pos, val);
	}

	public void setStrings(int pos, String val) throws Exception {
		ps.setString(pos, val);
	}
	
	public void setDoubles(int pos, double val) throws Exception {
		ps.setDouble(pos, val);
	}
	
	public void setDates(int pos, Date val)throws Exception {
		ps.setDate(pos, val);
	}
	
	public void setTimestamp(int pos, Timestamp val ) throws Exception{
		ps.setTimestamp(pos, val);
	}

		
	public boolean executestmt() throws Exception {
		try {
		this.rs = ps.executeQuery();
		//return ps.execute();
		return (this.rs != null);
		}
		catch (SQLException ex) {
			System.out.println("Error: SQL: "+ ex.getMessage());
			return false;
		}
	}
	
	public boolean executestmt(int num) throws Exception {
		try {
		//this.rs = ps.executeQuery();
		return ps.execute();
		//return (this.rs != null);
		}
		catch (SQLException ex) {
			System.out.println("Error: SQL: "+ ex.getMessage());
			return false;
		}
	}
	
	/* Ejecutar ps sin devolver valores */
	public boolean delete() throws Exception {

		try {
			if (ps.execute()) {
				return (true);
			} else {
				return false;
			}
		}

		catch (SQLException es) {

			System.out.println(es.getMessage());

			return false;

		}

	}

	public boolean PrepareUpdate() throws Exception {

		if (ps.execute()) {
			return (true);
		} else {
			return false;
		}

	}

	public void cleanup() throws Exception {
		if (rs != null) {
			rs.close();
		}
		if (ps != null) {
			ps.close();
		}
		if (c != null) {
			c.close();
		}
	}

	public static void showSQLException(java.sql.SQLException e) {
		java.sql.SQLException next = e;
		while (next != null) {
			System.out.println(next.getMessage());
			System.out.println("Error Code: " + next.getErrorCode());
			System.out.println("SQL State: " + next.getSQLState());
			next = next.getNextException();
		}
	}
}