package com.clase.models;

public class Banco {

	

	private int idBanco;
	private String nombre;
	private String estado;

	
	
	public int getIdBanco() {
		return idBanco;
	}

	public void setIdBanco(int idBanco) {
		this.idBanco = idBanco;
	}
	
	private static int noestudiante;
	
	
	public static int getNoEstudiante(){
		return noestudiante;
	}
	
	public void incrementarEstudiante(){
		noestudiante++;
	}
	
	public Banco(){
		this.nombre="";
		this.estado="";
		incrementarEstudiante();
	}
	
	public Banco(String var1, String var2) throws Exception   {
		setNombre(var1);
		//this.setApellidos(var2);
		//this.setCarrera(var3);
		this.setEstado(var2);
		//this.setMensualidad(mensualidad);
		incrementarEstudiante();
	}

	public Banco(int var0, String var1, String var2) throws Exception   {
		this.setIdBanco(var0);
		setNombre(var1);
		setEstado(var2);
		//this.setApellidos(var2);
		//this.setCarrera(var3);
		//this.setMensualidad(mensualidad);
		incrementarEstudiante();
	}
	
	public String getEstado()
	{
		return estado;
	}
	
	public String getEstado(boolean word)
	{
		if(estado.contains("1"))
		return "Activo";
		else
			return "Inactivo";
	}
	
	public void setEstado(String myestado) 
	{
		this.estado = myestado;
	}
	
	public String getNombre() {
		return nombre;
	}
	
	

	public void setNombre(String mynombre) throws Exception {
		if (mynombre.length() > 2) {
			this.nombre = mynombre;
		} else {
			throw new Exception(
					"Nombre no puede contener menos de 2 caracteres!");
		}
	}

	/*public String getApellidos() {
		return apellidos;
	}*/

	/*public void setApellidos(String apellidos) throws Exception {
		if (apellidos.length() > 2) {
			this.apellidos = apellidos;
		} else {
			throw new Exception(
					"Apellido no puede contener menos de 4 caracteres!");
		}
	}*/

	/*public String getCarrera() {
		return carrera;
	}*/

	/*public void setCarrera(String carrera) {
		this.carrera = carrera;
	}*/
	
	/*public double getMensualidad() {
		return mensualidad;
	}*/

	/*public void setMensualidad(double mensualidad) {
		this.mensualidad = mensualidad;
	}*/
	
	/*public double getMora(){
		return this.mensualidad * 0.1;
	}*/

	public void print(){
		System.out.println("------Datos " +  this.getClass().toString() + "-------");
		System.out.println("Nombre: " + this.getNombre());
		System.out.println("Estado: " + this.getEstado());
		//System.out.println("Apellido: " + this.getApellidos());
		//System.out.println("Carrera: " + this.getCarrera());
		//System.out.println("Mora: " + this.getMora());
	}

}
