package com.web.transporte.dto.model;


public class FormularioCotizacion {
	private String correo;
	private String domicilioEnvio;
	private String domicilioRetiro;
	private String observaciones;
	private String servicio;
	private String nombreService;
	private String tipoCarga;
	private Integer peso;
	private Integer volumen;
	
	public String getCorreo() {
		return correo;
	}
	public void setCorreo(String correo) {
		this.correo = correo;
	}
	public String getDomicilioEnvio() {
		return domicilioEnvio;
	}
	public void setDomicilioEnvio(String domicilioEnvio) {
		this.domicilioEnvio = domicilioEnvio;
	}
	public String getDomicilioRetiro() {
		return domicilioRetiro;
	}
	public void setDomicilioRetiro(String domicilioRetiro) {
		this.domicilioRetiro = domicilioRetiro;
	}
	public String getObservaciones() {
		return observaciones;
	}
	public void setObservaciones(String observaciones) {
		this.observaciones = observaciones;
	}
	public String getServicio() {
		return servicio;
	}
	public void setServicio(String servicio) {
		this.servicio = servicio;
	}
	public String getNombreService() {
		return nombreService;
	}
	public void setNombreService(String nombreService) {
		this.nombreService = nombreService;
	}
	public String getTipoCarga() {
		return tipoCarga;
	}
	public void setTipoCarga(String tipoCarga) {
		this.tipoCarga = tipoCarga;
	}
	public Integer getPeso() {
		return peso;
	}
	public void setPeso(Integer peso) {
		this.peso = peso;
	}
	public Integer getVolumen() {
		return volumen;
	}
	public void setVolumen(Integer volumen) {
		this.volumen = volumen;
	}
}
