package com.clase.models;

public class Usuario {
	
	
	private String nlogin;
	private String pclave;
	private String nombre;
	
	public String getNlogin() {
		return nlogin;
	}
	public void setNlogin(String nlogin) {
		this.nlogin = nlogin;
	}
	public String getPclave() {
		return pclave;
	}
	public void setPclave(String pclave) {
		this.pclave = pclave;
	}
	
	public String getNombre() {
		if(nombre!=null)
		return nombre;
		else
			return "";
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	

}
