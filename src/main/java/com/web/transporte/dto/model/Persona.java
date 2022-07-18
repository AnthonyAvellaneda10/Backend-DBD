package com.web.transporte.dto.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_NULL)
public class Persona {
	private Integer id_persona;
	private String dni;
	private String correo;
	private String nombres;
	private String apellido_paterno;
	private String apellido_materno;
	private String nro_celular;
		
	public Persona(String dni, String correo, String nombres, String apellido_materno, String apellido_paterno,
			String contrasenia, String nro_celular) {
		super();
		this.dni = dni;
		this.correo = correo;
		this.nombres = nombres;
		this.apellido_materno = apellido_materno;
		this.apellido_paterno = apellido_paterno;
		this.nro_celular = nro_celular;
	}
	
	public Persona(String dni, String nombres, String apellido_materno, String apellido_paterno) {
		super();
		this.dni = dni;
		this.nombres = nombres;
		this.apellido_materno = apellido_materno;
		this.apellido_paterno = apellido_paterno;
	}
	
	public Persona() {
		// TODO Auto-generated constructor stub
	}

	public Integer getId_persona() {
		return id_persona;
	}

	public void setId_persona(Integer id_persona) {
		this.id_persona = id_persona;
	}

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

	public String getApellido_materno() {
		return apellido_materno;
	}

	public void setApellido_materno(String apellido_materno) {
		this.apellido_materno = apellido_materno;
	}

	public String getApellido_paterno() {
		return apellido_paterno;
	}

	public void setApellido_paterno(String apellido_paterno) {
		this.apellido_paterno = apellido_paterno;
	}

	public String getNro_celular() {
		return nro_celular;
	}

	public void setNro_celular(String nro_celular) {
		this.nro_celular = nro_celular;
	}
}
