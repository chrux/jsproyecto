package com.clase.models;

public class Moneda {
	
	private int idMoneda;
	private String nombreMoneda;
	private String estado;
	
	
	public int getIdMoneda() {
		return idMoneda;
	}
	public void setIdMoneda(int idBanco) {
		this.idMoneda = idBanco;
	}
	public String getNombreMoneda() {
		return nombreMoneda;
	}
	public void setNombreMoneda(String nombreMoneda) {
		this.nombreMoneda = nombreMoneda;
	}
	public String getEstado() {
		return estado;
	}
	public void setEstado(String estado) {
		this.estado = estado;
	}
	
	public Moneda()
	{
		this.idMoneda=0;
		this.nombreMoneda="";
		this.estado="";
		
	}
	
	public Moneda(int var0, String var1, String var2) throws Exception   {
	this.setIdMoneda(var0);
	setNombreMoneda(var1);
	setEstado(var2);
	}

}
