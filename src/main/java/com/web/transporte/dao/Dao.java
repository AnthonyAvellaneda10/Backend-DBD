package com.web.transporte.dao;

import java.util.List;

import com.web.transporte.dto.model.Persona;
import com.web.transporte.dto.model.Usuario;
import com.web.transporte.dto.model.UsuarioLogin;

public interface Dao {
	public List<Persona> obtenerPersona();
	
	public Integer existeUsuario(String email, String contrasenia);
	
	public Integer existePersona(String dni, String correo, String nombres, String apellidoPaterno, String apellidoMaterno, String nroCelular);

	public Persona obtenerNombres(UsuarioLogin usuarioLogin);
	
	public void crearPersona(String dni, String correo, String nombres, String apellidoPaterno, String apellidoMaterno, String nroCelular);
	public void crearUsuario(String correo, String contrasenia, String dni);
	
	public void crearCotizacion(String domicilioEnvio, String domicilioRetiro, String observaciones, String dni);
}
