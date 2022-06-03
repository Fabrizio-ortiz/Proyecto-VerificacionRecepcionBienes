package com.VerificacionRecepcionBienes.fabrica;

import com.VerificacionRecepcionBienes.dao.MySqlBienDAO;
import com.VerificacionRecepcionBienes.dao.MySqlOrdenCompraDAO;
import com.VerificacionRecepcionBienes.dao.MySqlProveedorDAO;
import com.VerificacionRecepcionBienes.dao.MySqlUsuarioDAO;
import com.VerificacionRecepcionBienes.interfaces.BienDAO;
import com.VerificacionRecepcionBienes.interfaces.OrdenCompraDAO;
import com.VerificacionRecepcionBienes.interfaces.ProveedorDAO;
import com.VerificacionRecepcionBienes.interfaces.UsuarioDAO;

public class MySqlDAOFactory extends DAOFactory {

	@Override
	public ProveedorDAO getProveedorDAO() {
		// TODO Auto-generated method stub
		return new MySqlProveedorDAO();
	}
	public OrdenCompraDAO getOrdenCompraDAO() {
		// TODO Auto-generated method stub
		return new MySqlOrdenCompraDAO();
	}
	public BienDAO getBienDAO() {
		// TODO Auto-generated method stub
		return new MySqlBienDAO();
	}
	@Override
	public UsuarioDAO getUsuarioDAO() {
		// TODO Auto-generated method stub
		return new MySqlUsuarioDAO();
	}

}
