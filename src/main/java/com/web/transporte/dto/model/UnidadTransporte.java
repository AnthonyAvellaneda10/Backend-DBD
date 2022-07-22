package com.web.transporte.dto.model;

public class UnidadTransporte {
	private String codigo_transporte;
	private String gps;
	private String tipo_transporte;
	ServicioCotizacion servicioCotizacion;
	
	
	
	public String getCodigo_transporte() {
		return codigo_transporte;
	}
	public void setCodigo_transporte(String codigo_transporte) {
		this.codigo_transporte = codigo_transporte;
	}
	public String getGps() {
		return gps;
	}
	public void setGps(String gps) {
		this.gps = gps;
	}
	public String getTipo_transporte() {
		return tipo_transporte;
	}
	public void setTipo_transporte(String tipo_transporte) {
		this.tipo_transporte = tipo_transporte;
	}
	public ServicioCotizacion getServicioCotizacion() {
		return servicioCotizacion;
	}
	public void setServicioCotizacion(ServicioCotizacion servicioCotizacion) {
		this.servicioCotizacion = servicioCotizacion;
	}
	
	
}
