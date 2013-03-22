package com.clase.models;

import java.sql.Timestamp;
import java.util.Date;

public class Cheques {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	
	private int idCheque;
	private int idTasaCambio;
	private int idProveedor;
	private int idProyecto;
	private int idCuentasBancos;
	private double monto;
	private Timestamp fechaEmision;
	private int numCheque;
	private String concepto;
	private String estado;
	public int getIdCheque() {
		return idCheque;
	}
	public void setIdCheque(int idCheque) {
		this.idCheque = idCheque;
	}
	public int getIdTasaCambio() {
		return idTasaCambio;
	}
	public void setIdTasaCambio(int idTasaCambio) {
		this.idTasaCambio = idTasaCambio;
	}
	public int getIdProveedor() {
		return idProveedor;
	}
	public void setIdProveedor(int idProveedor) {
		this.idProveedor = idProveedor;
	}
	public int getIdProyecto() {
		return idProyecto;
	}
	public void setIdProyecto(int idProyecto) {
		this.idProyecto = idProyecto;
	}
	public int getIdCuentasBancos() {
		return idCuentasBancos;
	}
	public void setIdCuentasBancos(int idCuentasBancos) {
		this.idCuentasBancos = idCuentasBancos;
	}
	public double getMonto() {
		return monto;
	}
	public void setMonto(double monto) {
		this.monto = monto;
	}
	public Timestamp getFechaEmision() {
		return fechaEmision;
	}
	public void setFechaEmision(Timestamp fechaEmision) {
		this.fechaEmision = fechaEmision;
	}
	public int getNumCheque() {
		return numCheque;
	}
	public void setNumCheque(int numCheque) {
		this.numCheque = numCheque;
	}
	public String getConcepto() {
		return concepto;
	}
	public void setConcepto(String concepto) {
		this.concepto = concepto;
	}
	public String getEstado() {
		if(estado.contains("1"))
			return "Activo";
			else
				return "Inactivo";
	}
	
	public String getEstado(int a) {
		return estado;
	}
	
	public void setEstado(String estado) {
		this.estado = estado;
	}
	
	public Cheques(){
		/*
		 * private int idCheque;
		private int idTasaCambio;
		private int idProveedor;
		private int idProyecto;
		private int idCuentasBancos;
		private double monto;
		private Timestamp fechaEmision;
		private int numCheque;
		private String concepto;
		private String estado;
		 * */
		this.idCheque=0;
		this.idTasaCambio=0;
		this.idProveedor=0;
		this.idProyecto=0;
		this.idCuentasBancos=0;
		this.monto=0.0;
		this.fechaEmision=new Timestamp(new Date().getTime());
		this.numCheque=0;
		this.concepto="";
		this.estado="";
		
	}
	
	
	public Cheques(int var0, int var1, int var2, int var3, int var4, double var5, Timestamp var6,int var7,String var8, String var9){
				
		this.setIdCheque(var0);
		this.setIdTasaCambio(var1);
		this.setIdProveedor(var2);
		this.setIdProyecto(var3);
		this.setIdCuentasBancos(var4);
		this.setMonto(var5);
		this.setFechaEmision(var6);
		this.setNumCheque(var7);
		this.setConcepto(var8);
		this.setEstado(var9);
		
		
	}
	
	
	

}
