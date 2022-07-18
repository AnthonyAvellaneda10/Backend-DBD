package com.web.transporte.dto.model;

public class TipoServicio {
	private Integer codigoTipoServicio;
	private String nombreTipoServicio;
	
	public TipoServicio(Integer codigoTipoServicio, String nombreTipoServicio) {
		super();
		this.codigoTipoServicio = codigoTipoServicio;
		this.nombreTipoServicio = nombreTipoServicio;
	}
	
	public Integer getCodigoTipoServicio() {
		return codigoTipoServicio;
	}
	public void setCodigoTipoServicio(Integer codigoTipoServicio) {
		this.codigoTipoServicio = codigoTipoServicio;
	}
	public String getNombreTipoServicio() {
		return nombreTipoServicio;
	}
	public void setNombreTipoServicio(String nombreTipoServicio) {
		this.nombreTipoServicio = nombreTipoServicio;
	}
}
