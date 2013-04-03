package com.clase.models;

public class Personal {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	
	private int idPersonal;
	private int idTipoPersonal;
	private int idOficio;
	private int idTipoIdentificacion;
	private String nombre;
	private String cedula;
	private String nacional;
	private String estadoCivil;
	private String direccion;
	private String genero;
	private String estado;
	
	public int getIdPersonal() {
		return idPersonal;
	}
	public void setIdPersonal(int idPersonal) {
		this.idPersonal = idPersonal;
	}
	public int getIdTipoPersonal() {
		return idTipoPersonal;
	}
	public void setIdTipoPersonal(int idTipoPersonal) {
		this.idTipoPersonal = idTipoPersonal;
	}
	public int getIdOficio() {
		return idOficio;
	}
	public void setIdOficio(int idOficio) {
		this.idOficio = idOficio;
	}
	public int getIdTipoIdentificacion() {
		return idTipoIdentificacion;
	}
	public void setIdTipoIdentificacion(int idTipoIdentificacion) {
		this.idTipoIdentificacion = idTipoIdentificacion;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getCedula() {
		return cedula;
	}
	public void setCedula(String cedula) {
		this.cedula = cedula;
	}
	public String getNacional() {
		if(nacional.contains("1"))
			return "Nacional";
			else
				return "Extranjero";
		
	}
	public void setNacional(String nacional) {
		this.nacional = nacional;
	}
	public String getEstadoCivil() {
		if(estadoCivil.contains("1"))
			return "Soltero";
			else
				return "Casado";
		
	}
	public void setEstadoCivil(String estadoCivil) {
		this.estadoCivil = estadoCivil;
	}
	public String getDireccion() {
		return direccion;
	}
	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}
	public String getGenero() {
		if(genero.contains("1"))
			return "Masculino";
			else
				return "Femenino";
		
	}
	public void setGenero(String genero) {
		this.genero = genero;
	}
	public String getEstado() {
		if(estado.contains("1"))
			return "Activo";
			else
				return "Inactivo";
	}
	public void setEstado(String estado) {
		this.estado = estado;
	}
	
	public Personal() {
		this.idPersonal=0;
		this.idOficio=0;
		this.idTipoIdentificacion=0;
		this.idTipoPersonal=0;
		this.nombre="";
		this.direccion="";
		this.cedula="";
		this.nacional="";
		this.estadoCivil="";
		this.genero="";
		this.estado="";
	}
	
	public Personal(int var0,int var1, int var2, int var3,String var4, String var5,String var6,String var7,String var8,String var9,String var10 ){
		this.setIdPersonal(var0);
		this.setIdOficio(var1);
		this.setIdTipoIdentificacion(var2);
		this.setIdTipoPersonal(var3);
		this.setNombre(var4);
		this.setDireccion(var5);
		this.setCedula(var6);
		this.setNacional(var7);
		this.setEstadoCivil(var8);
		this.setGenero(var9);
		this.setEstado(var10);
		
	}
	
	public Personal(int var1, int var2, int var3,String var4, String var5,String var6,String var7,String var8,String var9,String var10 ){
		this.setIdOficio(var1);
		this.setIdTipoIdentificacion(var2);
		this.setIdTipoPersonal(var3);
		this.setNombre(var4);
		this.setDireccion(var5);
		this.setCedula(var6);
		this.setNacional(var7);
		this.setEstadoCivil(var8);
		this.setGenero(var9);
		this.setEstado(var10);
		
	}
	
	
	
	

}
