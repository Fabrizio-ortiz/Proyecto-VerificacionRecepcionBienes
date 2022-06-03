package com.VerificacionRecepcionBienes.services;

import java.util.List;

import com.VerificacionRecepcionBienes.entidad.Bien;
import com.VerificacionRecepcionBienes.entidad.OrdenCompra;
import com.VerificacionRecepcionBienes.fabrica.DAOFactory;
import com.VerificacionRecepcionBienes.interfaces.BienDAO;
import com.VerificacionRecepcionBienes.interfaces.OrdenCompraDAO;

public class BienService {
	private DAOFactory fabrica=DAOFactory.getDAOFactory(1);

	private BienDAO objBien=fabrica.getBienDAO();
	
	public int registrar(Bien bean) {
		return objBien.save(bean);
		
	}
	public int actualizar(Bien bean) {
		return objBien.update(bean);
		
	}
	public int eliminarPorId(int cod) {
		return objBien.deleteById(cod);
		
	}
	public Bien buscarPorId(int cod) {
		return objBien.findById(cod);
		
	}
	public List<Bien> listTodos(){
		return objBien.listAll();
		
	}

}
