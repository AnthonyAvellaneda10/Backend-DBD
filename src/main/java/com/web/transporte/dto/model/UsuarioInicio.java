package com.web.transporte.dto.model;

import java.util.Date;

public class UsuarioInicio extends Persona{	
	private String cod_usuario;
	private Date fecha_ingreso;
	private String contrasenia;
	Persona persona;
	
	public UsuarioInicio(String dni, String correo, String nombres, String apellido_materno, String apellido_paterno,
			String contrasenia, String nro_celular, String cod_usuario, Date fecha_ingreso, String contrasenia2,
			Persona persona) {
		super(dni, correo, nombres, apellido_materno, apellido_paterno, contrasenia, nro_celular);
		this.cod_usuario = cod_usuario;
		this.fecha_ingreso = fecha_ingreso;
		contrasenia = contrasenia2;
		this.persona = persona;
	}


	public Persona getPersona() {
		return persona;
	}


	public void setPersona(Persona persona) {
		this.persona = persona;
	}


	public UsuarioInicio() {
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
