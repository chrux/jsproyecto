package com.clase.models;

public class Proveedor {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	private int idProveedor;
	private int tipoProveedor;
	private String nombre;
	private String contacto;
	private String telefono;
	private String dirPostal;
	private String pagWeb;
	private String ruc;
	private String eMail;
	private String estado;
	public int getIdProveedor() {
		return idProveedor;
	}
	public void setIdProveedor(int idProveedor) {
		this.idProveedor = idProveedor;
	}
	public int getTipoProveedor() {
		return tipoProveedor;
	}
	public void setTipoProveedor(int tipoProveedor) {
		this.tipoProveedor = tipoProveedor;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getContacto() {
		return contacto;
	}
	public void setContacto(String contacto) {
		this.contacto = contacto;
	}
	public String getTelefono() {
		return telefono;
	}
	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}
	public String getDirPostal() {
		return dirPostal;
	}
	public void setDirPostal(String dirPostal) {
		this.dirPostal = dirPostal;
	}
	public String getPagWeb() {
		return pagWeb;
	}
	public void setPagWeb(String pagWeb) {
		this.pagWeb = pagWeb;
	}
	public String getRuc() {
		return ruc;
	}
	public void setRuc(String ruc) {
		this.ruc = ruc;
	}
	public String geteMail() {
		return eMail;
	}
	public void seteMail(String eMail) {
		this.eMail = eMail;
	}
	public String getEstado() {
		return estado;
	}
	public void setEstado(String estado) {
		this.estado = estado;
	}
	
	public Proveedor(){

		this.idProveedor=0;
		this.tipoProveedor=0;
		this.nombre="";
		this.contacto="";
		this.telefono="";
		this.dirPostal="";
		this.pagWeb="";
		this.ruc="";
		this.eMail="";
		this.estado="";
		
	
		
	}
	
	public Proveedor(int var0, int var1, String var2, String var3, String var4, String var5, String var6, String var7, String var8, String var9){
		
		this.idProveedor=var0;
		this.tipoProveedor=var1;
		this.nombre=var2;
		this.contacto=var3;
		this.telefono=var4;
		this.dirPostal=var5;
		this.pagWeb=var6;
		this.ruc=var7;
		this.eMail=var8;
		this.estado=var9;
		
		
		
	}

}
