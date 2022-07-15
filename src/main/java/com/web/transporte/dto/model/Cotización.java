package com.web.transporte.dto.model;

public class Cotización {
	private String dni;
	private String correo;
	private String nombres;
	private String apellido_paterno;
	private String apellido_materno;
	private String nro_celular;
	private String domicilioEnvio;
	private String domicilioRetiro;
	private String observaciones;
	
	public String getDni() {
		return dni;
	}
	public void setDni(String dni) {
		this.dni = dni;
	}
	public String getCorreo() {
		return correo;
	}
	public void setCorreo(String correo) {
		this.correo = correo;
	}
	public String getNombres() {
		return nombres;
	}
	public void setNombres(String nombres) {
		this.nombres = nombres;
	}
	public String getApellido_paterno() {
		return apellido_paterno;
	}
	public void setApellido_paterno(String apellido_paterno) {
		this.apellido_paterno = apellido_paterno;
	}
	public String getApellido_materno() {
		return apellido_materno;
	}
	public void setApellido_materno(String apellido_materno) {
		this.apellido_materno = apellido_materno;
	}
	public String getNro_celular() {
		return nro_celular;
	}
	public void setNro_celular(String nro_celular) {
		this.nro_celular = nro_celular;
	}
	public String getDomicilioRetiro() {
		return domicilioRetiro;
	}
	public void setDomicilioRetiro(String domicilioRetiro) {
		this.domicilioRetiro = domicilioRetiro;
	}
	public String getDomicilioEnvio() {
		return domicilioEnvio;
	}
	public void setDomicilioEnvio(String domicilioEnvio) {
		this.domicilioEnvio = domicilioEnvio;
	}
	public String getObservaciones() {
		return observaciones;
	}
	public void setObservaciones(String observaciones) {
		this.observaciones = observaciones;
	}
}
