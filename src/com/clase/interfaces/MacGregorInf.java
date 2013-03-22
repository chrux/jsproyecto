package com.clase.interfaces;

import java.sql.SQLException;
import java.util.ArrayList;

import com.clase.dao.ConnDB;
import com.clase.models.Banco;
import com.clase.models.TipoCliente;

public interface MacGregorInf {
	
	public ArrayList<Banco> getLista();
	
	public int addBanco(Banco banco);
	
	public int updBanco(Banco banco);
	
	public int delBanco(Banco banco);
	
	public Banco findByID(int idest);
	
	public int addClientTy(TipoCliente clientTy);
		
	public int updClientTy(TipoCliente clientTy) ;
	
	public int delClientTy(TipoCliente clientTy) ;
	public TipoCliente findByIDType(int idType);
	
	

}
