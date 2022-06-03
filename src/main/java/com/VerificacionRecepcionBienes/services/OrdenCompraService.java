package com.VerificacionRecepcionBienes.services;

import java.util.List;

import com.VerificacionRecepcionBienes.entidad.OrdenCompra;
import com.VerificacionRecepcionBienes.fabrica.DAOFactory;
import com.VerificacionRecepcionBienes.interfaces.OrdenCompraDAO;


public class OrdenCompraService {
	private DAOFactory fabrica=DAOFactory.getDAOFactory(1);

	private OrdenCompraDAO objOrdenCompra=fabrica.getOrdenCompraDAO();
	
	public int registrar(OrdenCompra bean) {
		return objOrdenCompra.save(bean);
		
	}
	public int actualizar(OrdenCompra bean) {
		return objOrdenCompra.update(bean);
		
	}
	public int eliminarPorId(int cod) {
		return objOrdenCompra.deleteById(cod);
		
	}
	public OrdenCompra buscarPorId(int cod) {
		return objOrdenCompra.findById(cod);
		
	}
	public List<OrdenCompra> listTodos(){
		return objOrdenCompra.listAll();
		
	}
}

