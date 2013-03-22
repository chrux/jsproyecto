package com.clase.models;

import java.util.Date;
import java.sql.Timestamp;


public class Proyecto {

	/**
	 * @param args
	 */
	
	/**
	 * 
	 */
	private int idProyecto;
	private int idTipoOferta;
	private int idCliente;
	private String nombre;
	private Timestamp fechaOferta;
	private Double montoOferta;
	private Timestamp fechaAceptacion; //Aplicar como fecha de presupuesto y de inicio
	private int numOferta;
	private Double montoPresupuesto;
	private String estado;
	private Timestamp fechaCierre;
	private String observaciones;
	private int idMoneda;
	
	
	
	public int getIdProyecto() {
		return idProyecto;
	}
	public void setIdProyecto(int idProyecto) {
		this.idProyecto = idProyecto;
	}
	public int getIdTipoOferta() {
		return idTipoOferta;
	}
	public void setIdTipoOferta(int idTipoOferta) {
		this.idTipoOferta = idTipoOferta;
	}
	public int getIdCliente() {
		return idCliente;
	}
	public void setIdCliente(int idCliente) {
		this.idCliente = idCliente;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public Timestamp getFechaOferta() {
		return fechaOferta;
	}
	public void setFechaOferta(Timestamp fechaOferta) {
		this.fechaOferta = fechaOferta;
	}
	public Double getMontoOferta() {
		return montoOferta;
	}
	public void setMontoOferta(Double montoOferta) {
		this.montoOferta = montoOferta;
	}
	public Timestamp getFechaAceptacion() {
		return fechaAceptacion;
	}
	public void setFechaAceptacion(Timestamp fechaAceptacion) {
		this.fechaAceptacion = fechaAceptacion;
	}
	public int getNumOferta() {
		return numOferta;
	}
	public void setNumOferta(int numOferta) {
		this.numOferta = numOferta;
	}
	public Double getMontoPresupuesto() {
		return montoPresupuesto;
	}
	public void setMontoPresupuesto(Double montoPresupuesto) {
		this.montoPresupuesto = montoPresupuesto;
	}
	public String getEstado() {
		return estado;
	}
	
	public String getEstado(boolean word)
	{
		if(estado.contains("1"))
			return "Activo";
			else
				return "Inactivo";

	}
	
	public void setEstado(String estado) {
		this.estado = estado;
	}
	public Timestamp getFechaCierre() {
		if(fechaCierre==null)
			fechaCierre= new Timestamp((new Date().getTime()) -( new Date().getTime())-2000) ;
		return fechaCierre;
	}
	public void setFechaCierre(Timestamp fechaCierre) {
		this.fechaCierre = fechaCierre;
	}
	public String getObservaciones() {
		if(observaciones==null){
			observaciones="";
		}
		return observaciones;
	}
	public void setObservaciones(String observaciones) {
		this.observaciones = observaciones;
	}
	public int getIdMoneda() {
		return idMoneda;
	}
	public void setIdMoneda(int idMoneda) {
		this.idMoneda = idMoneda;
	}
	
	
	public Proyecto(int var0, int var1, int var2, String var3, Timestamp var4, Double var5, Timestamp var6, int var7, Double var8, String var9, Timestamp var10, String var11, int var12)
	{
		
		this.setIdProyecto(var0);
		this.setIdTipoOferta(var1);
		this.setIdCliente(var2);
		this.setNombre(var3);
		this.setFechaOferta(var4);
		this.setMontoOferta(var5);
		this.setFechaAceptacion(var6);
		this.setNumOferta(var7);
		this.setMontoPresupuesto(var8);
		this.setEstado(var9);
		this.setFechaCierre(var10);
		this.setObservaciones(var11);
		this.setIdMoneda(var12);
	}
	
	public Proyecto(){
		this.idProyecto=0;
		this.idTipoOferta=0;
		this.idCliente=0;
		this.nombre="";
		this.fechaOferta= new Timestamp(new Date().getTime()) ;
	this.montoOferta= 0.0 ;
	this.fechaAceptacion = new Timestamp(new Date().getTime());
	this.numOferta =0;
	this.montoPresupuesto=0.0;
	this.estado="";
	this.fechaCierre= new Timestamp(new Date().getTime());
	this.observaciones="";
	this.idMoneda=0;
		
		
		
	}

	
	public void print(){
		System.out.println("Nombre: " + this.getNombre());
		System.out.println("Estado: " + this.getEstado());

	}
	
	

}
