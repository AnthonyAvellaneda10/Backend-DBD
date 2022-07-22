package com.web.transporte.dto.model;

public class ServicioCotizacion {
	private Integer codigoServicio;
	private String nombreServicio;
	private String tipoServicio;
	UsuarioInicio usuario;
	
	public ServicioCotizacion(Integer codigoServicio, String nombreServicio, String tipoServicio,
			UsuarioInicio usuario) {
		super();
		this.codigoServicio = codigoServicio;
		this.nombreServicio = nombreServicio;
		this.tipoServicio = tipoServicio;
		this.usuario = usuario;
	}
	
	public ServicioCotizacion() {
		
	}
	
	
	public UsuarioInicio getUsuario() {
		return usuario;
	}



	public void setUsuario(UsuarioInicio usuario) {
		this.usuario = usuario;
	}



	public Integer getCodigoServicio() {
		return codigoServicio;
	}
	public void setCodigoServicio(Integer codigoServicio) {
		this.codigoServicio = codigoServicio;
	}
	public String getNombreServicio() {
		return nombreServicio;
	}
	public void setNombreServicio(String nombreServicio) {
		this.nombreServicio = nombreServicio;
	}
	public String getTipoServicio() {
		return tipoServicio;
	}
	public void setTipoServicio(String tipoServicio) {
		this.tipoServicio = tipoServicio;
	}

}
