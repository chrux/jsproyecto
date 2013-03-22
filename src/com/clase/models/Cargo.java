package com.clase.models;

public class Cargo {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	
	private int idCargo;
	private String nombreCargo;
	private String estado;
	public int getIdCargo() {
		return idCargo;
	}
	public void setIdCargo(int idCargo) {
		this.idCargo = idCargo;
	}
	public String getNombreCargo() {
		return nombreCargo;
	}
	public void setNombreCargo(String nombreCargo) {
		this.nombreCargo = nombreCargo;
	}
	
	public String getEstado() {
		return estado;
	}
	
	public String getEstado( boolean a) {
		if(estado.contains("1"))
			return "Activo";
			else
				return "Inactivo";
	}
	public void setEstado(String estado) {
		this.estado = estado;
	}

	
	public Cargo(){
		this.nombreCargo="";
		this.estado= "";
	}
	
	public Cargo(int var0,String var1, String var2){
		setIdCargo(var0);
		setNombreCargo(var1);
		setEstado(var2);
	}
	
	
	
}
