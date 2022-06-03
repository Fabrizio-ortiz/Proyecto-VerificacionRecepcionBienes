package com.VerificacionRecepcionBienes.interfaces;

import java.util.List;

import com.VerificacionRecepcionBienes.entidad.Proveedor;

public interface ProveedorDAO {
	//aca se ponen todos los metodos de la interface/ proyecto
	public int save(Proveedor bean);
	public int update(Proveedor bean);
	public int deleteById(int cod);
	public Proveedor findById(int cod);
	public List<Proveedor> listAll();
}
