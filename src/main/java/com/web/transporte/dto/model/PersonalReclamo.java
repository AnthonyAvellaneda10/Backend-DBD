package com.web.transporte.dto.model;

public class PersonalReclamo {
	private String codigo_personal;
    private String contrasenia;
    private String cargo;
    private String tipo_usuario;
    private Persona persona;

    public PersonalReclamo(String codigo_personal, String contrasenia, String cargo, String tipo_usuario, Persona persona) {
        this.codigo_personal = codigo_personal;
        this.contrasenia = contrasenia;
        this.cargo = cargo;
        this.tipo_usuario = tipo_usuario;
        this.persona = persona;
    }

	public String getCodigo_personal() {
		return codigo_personal;
	}

	public void setCodigo_personal(String codigo_personal) {
		this.codigo_personal = codigo_personal;
	}

	public String getContrasenia() {
		return contrasenia;
	}

	public void setContrasenia(String contrasenia) {
		this.contrasenia = contrasenia;
	}

	public String getCargo() {
		return cargo;
	}

	public void setCargo(String cargo) {
		this.cargo = cargo;
	}

	public String getTipo_usuario() {
		return tipo_usuario;
	}

	public void setTipo_usuario(String tipo_usuario) {
		this.tipo_usuario = tipo_usuario;
	}

	public Persona getPersona() {
		return persona;
	}

	public void setPersona(Persona persona) {
		this.persona = persona;
	}
    
    
}
