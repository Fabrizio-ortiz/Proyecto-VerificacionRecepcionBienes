package com.VerificacionRecepcionBienes.entidad;

import java.sql.Date;

public class OrdenCompra {
	private int codigo_orden,cantidad_orden;
	private String descripcion_orden, direccion_orden,representante_orden;
	private Date fecha_orden;
	private double precio_orden;
	public int getCodigo_orden() {
		return codigo_orden;
	}
	public void setCodigo_orden(int codigo_orden) {
		this.codigo_orden = codigo_orden;
	}
	public int getCantidad_orden() {
		return cantidad_orden;
	}
	public void setCantidad_orden(int cantidad_orden) {
		this.cantidad_orden = cantidad_orden;
	}
	public String getDescripcion_orden() {
		return descripcion_orden;
	}
	public void setDescripcion_orden(String descripcion_orden) {
		this.descripcion_orden = descripcion_orden;
	}

	public Date getFecha_orden() {
		return fecha_orden;
	}
	public void setFecha_orden(Date fecha_orden) {
		this.fecha_orden = fecha_orden;
	}
	public String getDireccion_orden() {
		return direccion_orden;
	}
	public void setDireccion_orden(String direccion_orden) {
		this.direccion_orden = direccion_orden;
	}
	public String getRepresentante_orden() {
		return representante_orden;
	}
	public void setRepresentante_orden(String representante_orden) {
		this.representante_orden = representante_orden;
	}
	public double getPrecio_orden() {
		return precio_orden;
	}
	public void setPrecio_orden(double precio_orden) {
		this.precio_orden = precio_orden;
	}
	
	

}
