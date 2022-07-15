package com.web.transporte.controlador;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.web.transporte.dto.model.Cotización;
import com.web.transporte.dto.model.Persona;
import com.web.transporte.dto.model.RegistroUsuario;
import com.web.transporte.dto.model.Usuario;
import com.web.transporte.dto.model.UsuarioLogin;
import com.web.transporte.dto.rest.RespuestaPersona;
import com.web.transporte.servicio.Servicio;

@RestController
public class Controlador {
	@Autowired
	private Servicio servicio;

	// LISTAR PERSONAS
	@RequestMapping(value = "/obtener-persona", method = RequestMethod.POST, consumes = "application/json;charset=utf-8", produces = "application/json;charset=utf-8")
	public @ResponseBody RespuestaPersona obtenerPersona() {
		RespuestaPersona respuestaPersona = new RespuestaPersona();
		respuestaPersona.setLista(servicio.obtenerPersona());
		return respuestaPersona;
	}

	// LOGIN USUARIO
	@RequestMapping(value = "/autenticar-usuario", method = RequestMethod.POST, produces = "application/json;charset=utf-8", consumes = "application/json;charset=utf-8")
	public @ResponseBody Persona autenticarUsuario(@RequestBody UsuarioLogin usuarioLogin) {
		return servicio.autenticarUsuario(usuarioLogin);
	}
	
	// REGISTRO USUARIO
		@RequestMapping(value = "/registrar-usuario", method = RequestMethod.POST, produces = "application/json;charset=utf-8", consumes = "application/json;charset=utf-8")
		public @ResponseBody void registrarUsuario(@RequestBody RegistroUsuario usuarioRegistro) {
			servicio.registrarUsuario(usuarioRegistro);
		}
		
	// SOLICITUD DE COTIZACIÓN
		@RequestMapping(value = "/solicitar-cotizacion", method = RequestMethod.POST, produces = "application/json;charset=utf-8", consumes = "application/json;charset=utf-8")
		public @ResponseBody void registrarCotizacion(@RequestBody Cotización solicitarCotizacion) {
			servicio.registrarCotizacion(solicitarCotizacion);
		}
}
