package com.web.transporte.servicio;

import java.util.List;

import com.web.transporte.dto.model.Cotización;
import com.web.transporte.dto.model.Persona;
import com.web.transporte.dto.model.RegistroUsuario;
import com.web.transporte.dto.model.Usuario;
import com.web.transporte.dto.model.UsuarioLogin;

public interface Servicio {
	public List<Persona> obtenerPersona();
	
	// LOGIN
	public Persona autenticarUsuario(UsuarioLogin usuarioLogin);

	// REGISTRO
	public void registrarUsuario(RegistroUsuario usuarioRegistro);

	// SOLICITUD DE COTIZACION
	public void registrarCotizacion(Cotización solicitarCotizacion);
		
}
