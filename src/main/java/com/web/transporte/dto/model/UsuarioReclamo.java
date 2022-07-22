package com.web.transporte.dto.model;

public class UsuarioReclamo {
	private String cod_usuario;
    private String contrasenia;
    private String tipo_usuario;
    private PersonaReclamo persona;

    public UsuarioReclamo(String cod_usuario, String contrasenia, String tipo_usuario, PersonaReclamo persona) {
        this.cod_usuario = cod_usuario;
        this.contrasenia = contrasenia;
        this.tipo_usuario = tipo_usuario;
        this.persona = persona;
    }

    public UsuarioReclamo() {
    }

	public String getCod_usuario() {
		return cod_usuario;
	}

	public void setCod_usuario(String cod_usuario) {
		this.cod_usuario = cod_usuario;
	}

	public String getContrasenia() {
		return contrasenia;
	}

	public void setContrasenia(String contrasenia) {
		this.contrasenia = contrasenia;
	}

	public String getTipo_usuario() {
		return tipo_usuario;
	}

	public void setTipo_usuario(String tipo_usuario) {
		this.tipo_usuario = tipo_usuario;
	}

	public PersonaReclamo getPersona() {
		return persona;
	}

	public void setPersona(PersonaReclamo persona) {
		this.persona = persona;
	}
    
    
}
