package com.web.transporte.dto.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_NULL)
public class Cotizacion {
	private String domicilioEnvio;
	private String domicilioRetiro;
	private String observaciones;
	
	public Cotizacion(String domicilioEnvio, String domicilioRetiro) {
		super();
		this.domicilioEnvio = domicilioEnvio;
		this.domicilioRetiro = domicilioRetiro;
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
}
