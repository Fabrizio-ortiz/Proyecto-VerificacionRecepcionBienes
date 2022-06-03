package com.VerificacionRecepcionBienes.services;

import java.util.List;

import com.VerificacionRecepcionBienes.entidad.Proveedor;
import com.VerificacionRecepcionBienes.fabrica.DAOFactory;
import com.VerificacionRecepcionBienes.interfaces.ProveedorDAO;

public class ProveedorService {
	private DAOFactory fabrica=DAOFactory.getDAOFactory(1);

	private ProveedorDAO objProveedor=fabrica.getProveedorDAO();
	
	public int registrar(Proveedor bean) {
		return objProveedor.save(bean);
		
	}
	public int actualizar(Proveedor bean) {
		return objProveedor.update(bean);
		
	}
	public int eliminarPorId(int cod) {
		return objProveedor.deleteById(cod);
		
	}
	public Proveedor buscarPorId(int cod) {
		return objProveedor.findById(cod);
		
	}
	public List<Proveedor> listTodos(){
		return objProveedor.listAll();
		
	}
}
