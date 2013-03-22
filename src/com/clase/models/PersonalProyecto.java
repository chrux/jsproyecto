package com.clase.models;

import java.sql.Timestamp;
import java.util.Date;

public class PersonalProyecto {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	
	private int IdPersonalProy;
	private int IdPersonal;
	private int IdCargo;
	private int IdOficio;
	private Timestamp fechaInicio;
	private Timestamp fechaFinal;
	private int IdProyecto;
	public int getIdPersonalProy() {
		return IdPersonalProy;
	}
	public void setIdPersonalProy(int idPersonalProy) {
		IdPersonalProy = idPersonalProy;
	}
	public int getIdPersonal() {
		return IdPersonal;
	}
	public void setIdPersonal(int idPersonal) {
		IdPersonal = idPersonal;
	}
	public int getIdCargo() {
		return IdCargo;
	}
	public void setIdCargo(int idCargo) {
		IdCargo = idCargo;
	}
	public int getIdOficio() {
		return IdOficio;
	}
	public void setIdOficio(int idOficio) {
		IdOficio = idOficio;
	}
	public Timestamp getFechaInicio() {
		return fechaInicio;
	}
	public void setFechaInicio(Timestamp fechaInicio) {
		this.fechaInicio = fechaInicio;
	}
	public Timestamp getFechaFinal() {
		return fechaFinal;
	}
	public void setFechaFinal(Timestamp fechaFinal) {
		this.fechaFinal = fechaFinal;
	}
	public int getIdProyecto() {
		return IdProyecto;
	}
	public void setIdProyecto(int idProyecto) {
		IdProyecto = idProyecto;
	}
	
	public PersonalProyecto(int var0,int var1, int var2, int var3, int var4, Timestamp var5,Timestamp var6  ){
		this.IdCargo=var0;
		this.IdOficio=var1;
		this.IdPersonal=var2;
		this.IdPersonalProy=var3;
		this.IdProyecto=var4;
		this.fechaInicio= var5;
		this.fechaFinal=var6;
	}
	
	
	public PersonalProyecto(){
		this.IdCargo=0;
		this.IdOficio=0;
		this.IdPersonal=0;
		this.IdPersonalProy=0;
		this.IdProyecto=0;
		this.fechaInicio=new Timestamp(new Date().getTime());
		this.fechaFinal=new Timestamp(new Date().getTime());
	}
	
	
	

}
