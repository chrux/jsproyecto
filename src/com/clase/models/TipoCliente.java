package com.clase.models;

public class TipoCliente {
	
	private int idTipoCliente;
	private String tipoCliente;
	private String estado;
	
	public int getIdTipoCliente() {
		return idTipoCliente;
	}
	public void setIdTipoCliente(int idTipoCliente) {
		this.idTipoCliente = idTipoCliente;
	}
	public String getTipoCliente() {
		return tipoCliente;
	}
	public void setTipoCliente(String tipoCliente) throws Exception  {
		if(tipoCliente.length()> 3) {
			this.tipoCliente = tipoCliente;
		} else {
			throw new Exception(
					"Tipo de Cliente no puede contener menos de 4 caracteres!");
		}
		
	}
	
	
	public String getEstado(boolean word)
	{
		if(estado.contains("1"))
		return "Activo";
		else
			return "Inactivo";
	}
	
	
	public String getEstado() {
		return estado;
	}
	public void setEstado(String estado) {
		this.estado = estado;
	}
	
	public TipoCliente(){
		this.tipoCliente="";
		this.estado="";
	
	}
	
	public TipoCliente(String var1, String var2) throws Exception {
		
		setTipoCliente(var1);
		setEstado(var2);
		//this.tipoCliente="";
		//this.estado="";
	
	}
	
public TipoCliente(int var0,String var1, String var2) throws Exception {
		this.setIdTipoCliente(var0);
		setTipoCliente(var1);
		setEstado(var2);
		
	
	}
	
	
	/**
	 * @param args
	 */
	/*public static void main(String[] args) {
		// TODO Auto-generated method stub

	}*/

}
