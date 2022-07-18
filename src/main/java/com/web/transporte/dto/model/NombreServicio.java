package com.web.transporte.dto.model;

public class NombreServicio {
	private Integer codigoNombreServicio;
	private String nombreServicio;
	
	public NombreServicio(Integer codigoNombreServicio, String nombreServicio) {
		super();
		this.codigoNombreServicio = codigoNombreServicio;
		this.nombreServicio = nombreServicio;
	}
	
	public Integer getCodigoNombreServicio() {
		return codigoNombreServicio;
	}
	public void setCodigoNombreServicio(Integer codigoNombreServicio) {
		this.codigoNombreServicio = codigoNombreServicio;
	}
	public String getNombreServicio() {
		return nombreServicio;
	}
	public void setNombreServicio(String nombreServicio) {
		this.nombreServicio = nombreServicio;
	}	
}
