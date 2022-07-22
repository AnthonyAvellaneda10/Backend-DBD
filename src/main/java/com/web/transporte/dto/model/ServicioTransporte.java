package com.web.transporte.dto.model;

public class ServicioTransporte {
	private String nombre_servicio;
    private String tipo_servicio;
    private UsuarioReclamo usuario;

    public ServicioTransporte(String nombre_servicio, String tipo_servicio, UsuarioReclamo usuario) {
        this.nombre_servicio = nombre_servicio;
        this.tipo_servicio = tipo_servicio;
        this.usuario = usuario;
    }

	public String getNombre_servicio() {
		return nombre_servicio;
	}

	public void setNombre_servicio(String nombre_servicio) {
		this.nombre_servicio = nombre_servicio;
	}

	public String getTipo_servicio() {
		return tipo_servicio;
	}

	public void setTipo_servicio(String tipo_servicio) {
		this.tipo_servicio = tipo_servicio;
	}

	public UsuarioReclamo getUsuario() {
		return usuario;
	}

	public void setUsuario(UsuarioReclamo usuario) {
		this.usuario = usuario;
	}
    
    
}
