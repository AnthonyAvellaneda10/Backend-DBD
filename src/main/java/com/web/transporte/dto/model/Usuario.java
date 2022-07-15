package com.web.transporte.dto.model;

import java.util.Date;

public class Usuario extends Persona{	
	private String cod_usuario;
	private Date fecha_ingreso;
	private String contrasenia;
		
	public Usuario() {
		super();
	}


	public String getCod_usuario() {
		return cod_usuario;
	}


	public void setCod_usuario(String cod_usuario) {
		this.cod_usuario = cod_usuario;
	}


	public Date getFecha_ingreso() {
		return fecha_ingreso;
	}


	public void setFecha_ingreso(Date fecha_ingreso) {
		this.fecha_ingreso = fecha_ingreso;
	}


	public String getContrasenia() {
		return contrasenia;
	}


	public void setContrasenia(String contrasenia) {
		this.contrasenia = contrasenia;
	}
	
	
}
