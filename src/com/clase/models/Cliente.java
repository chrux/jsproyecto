package com.clase.models;

public class Cliente {
	
	private int idCliente;
	private int idTipoCliente;
	private String nombre;
	private String contacto;
	private String direccionPos;
	private String direccionFis;
	private String telefono;
	private String email;
	private String nacionalidad;
	private String paginaWeb;
	private String estado;

	public int getIdCliente() {
		return idCliente;
	}

	public void setIdCliente(int idCliente) {
		this.idCliente = idCliente;
	}

	public int getIdTipoCliente() {
		return idTipoCliente;
	}

	public void setIdTipoCliente(int idTipoCliente) {
		this.idTipoCliente = idTipoCliente;
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

	public String getDireccionPos() {
		if(direccionPos==null){
			direccionPos="";
		}
		return direccionPos;
	}

	public void setDireccionPos(String direccionPos) {
		this.direccionPos = direccionPos;
	}

	public String getDireccionFis() {
		
		return direccionFis;
	}

	public void setDireccionFis(String direccionFis) {
		this.direccionFis = direccionFis;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getNacionalidad() {
		return nacionalidad;
	}

	public void setNacionalidad(String nacionalidad) {
		this.nacionalidad = nacionalidad;
	}

	public String getPaginaWeb() {
		return paginaWeb;
	}

	public void setPaginaWeb(String paginaWeb) {
		this.paginaWeb = paginaWeb;
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

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	
	public Cliente()
	{
		this.idCliente=0;
		this.idTipoCliente=0;
		this.nombre="";
		this.contacto="";
		this.direccionPos="";
		this.direccionFis="";
		this.telefono="";
		this.email="";
		this.nacionalidad="";
		this.paginaWeb="";
		this.estado="";
	}
	public Cliente(int var, int var0,String var1, String var2, String var3, String var4, String var5, String var6, String var7, String var8, String var9 )
	{
		this.setIdCliente(var);
		this.setIdTipoCliente(var0);
		this.setNombre(var1);
		this.setContacto(var2);
		this.setDireccionPos(var3);
		this.setDireccionFis(var4);
		this.setTelefono(var5);
		this.setEmail(var6);
		this.setNacionalidad(var7);
		this.setPaginaWeb(var8);
		this.setEstado(var9);
	}
	
	public Cliente(int var0,String var1, String var2, String var3, String var4, String var5, String var6, String var7, String var8, String var9 )
	{
		this.setIdTipoCliente(var0);
		this.setNombre(var1);
		this.setContacto(var2);
		this.setDireccionPos(var3);
		this.setDireccionFis(var4);
		this.setTelefono(var5);
		this.setEmail(var6);
		this.setNacionalidad(var7);
		this.setPaginaWeb(var8);
		this.setEstado(var9);
	}

}

