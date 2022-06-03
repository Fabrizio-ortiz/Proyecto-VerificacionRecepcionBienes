package com.VerificacionRecepcionBienes.entidad;

import java.sql.Date;

public class Bien {
	private int cod_bien,cod_provee,canti_bien,cod_orden;
	private double precio_bien;
	private Date fechapedi_bien,fechaentre_bien;
	private String estado_bien,descripcion_bien;
	public int getCod_bien() {
		return cod_bien;
	}
	public void setCod_bien(int cod_bien) {
		this.cod_bien = cod_bien;
	}
	public int getCod_provee() {
		return cod_provee;
	}
	public void setCod_provee(int cod_provee) {
		this.cod_provee = cod_provee;
	}

	public int getCanti_bien() {
		return canti_bien;
	}
	public void setCanti_bien(int canti_bien) {
		this.canti_bien = canti_bien;
	}
	public int getCod_orden() {
		return cod_orden;
	}
	public void setCod_orden(int cod_orden) {
		this.cod_orden = cod_orden;
	}
	public double getPrecio_bien() {
		return precio_bien;
	}
	public void setPrecio_bien(double precio_bien) {
		this.precio_bien = precio_bien;
	}
	public Date getFechapedi_bien() {
		return fechapedi_bien;
	}
	public void setFechapedi_bien(Date fechapedi_bien) {
		this.fechapedi_bien = fechapedi_bien;
	}
	public Date getFechaentre_bien() {
		return fechaentre_bien;
	}
	public void setFechaentre_bien(Date fechaentre_bien) {
		this.fechaentre_bien = fechaentre_bien;
	}
	public String getEstado_bien() {
		return estado_bien;
	}
	public void setEstado_bien(String estado_bien) {
		this.estado_bien = estado_bien;
	}
	public String getDescripcion_bien() {
		return descripcion_bien;
	}
	public void setDescripcion_bien(String descripcion_bien) {
		this.descripcion_bien = descripcion_bien;
	}
	

	

}
