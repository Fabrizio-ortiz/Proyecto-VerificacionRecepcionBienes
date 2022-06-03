package com.VerificacionRecepcionBienes.interfaces;

import java.util.List;

import com.VerificacionRecepcionBienes.entidad.OrdenCompra;

public interface OrdenCompraDAO {
	//aca se ponen todos los metodos de la interface/ proyecto
	public int save(OrdenCompra bean);
	public int update(OrdenCompra bean);
	public int deleteById(int cod);
	public OrdenCompra findById(int cod);
	public List<OrdenCompra> listAll();


}
