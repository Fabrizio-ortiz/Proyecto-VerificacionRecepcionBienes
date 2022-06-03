package com.VerificacionRecepcionBienes.interfaces;

import java.util.List;

import com.VerificacionRecepcionBienes.entidad.Bien;



public interface BienDAO {
	//aca se ponen todos los metodos de la interface/ proyecto
	public int save(Bien bean);
	public int update(Bien bean);
	public int deleteById(int cod);
	public Bien findById(int cod);
	public List<Bien> listAll();

}
