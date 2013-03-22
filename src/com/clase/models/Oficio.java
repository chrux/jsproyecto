package com.clase.models;

public class Oficio {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	
	private int idOficio;
	private String Oficio;
	private String Estado;
	public int getIdOficio() {
		return idOficio;
	}
	public void setIdOficio(int idOficio) {
		this.idOficio = idOficio;
	}
	public String getOficio() {
		return Oficio;
	}
	public void setOficio(String oficio) {
		Oficio = oficio;
	}
	public String getEstado() {
		return Estado;
	}
	public void setEstado(String estado) {
		Estado = estado;
	}
	
	public Oficio(){
		this.idOficio=0;
		this.Oficio="";
		this.Estado="";
	}
	
	public Oficio(int var0, String var1, String var2){
		this.setIdOficio(var0);
		this.setOficio(var1);
		this.setEstado(var2);
	}

}
