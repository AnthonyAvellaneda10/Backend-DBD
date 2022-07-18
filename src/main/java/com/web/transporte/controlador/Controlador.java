package com.web.transporte.controlador;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.web.transporte.dto.model.FormularioCotizacion;
import com.web.transporte.dto.model.Persona;
import com.web.transporte.dto.model.RegistroUsuario;
import com.web.transporte.dto.model.UsuarioLogin;
import com.web.transporte.dto.rest.RespuestaListaCotizacion;
import com.web.transporte.dto.rest.RespuestaNombreServicio;
import com.web.transporte.dto.rest.RespuestaPersona;
import com.web.transporte.dto.rest.RespuestaTipoCarga;
import com.web.transporte.dto.rest.RespuestaTipoServicio;
import com.web.transporte.servicio.Servicio;

@CrossOrigin(origins = { "*" })
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

	// INSERTAR DATOS DE SOLICITUD DE COTIZACIÓN
	@RequestMapping(value = "/solicitar-cotizacion", method = RequestMethod.POST, produces = "application/json;charset=utf-8", consumes = "application/json;charset=utf-8")
	public @ResponseBody void registrarCotizacion(@RequestBody FormularioCotizacion solicitarCotizacion) {
		servicio.registrarCotizacion(solicitarCotizacion);
	}

	// LISTAR TIPOS DE SERVICIOS
	@RequestMapping(value = "/obtener-tipos-servicios", method = RequestMethod.GET, produces = "application/json;charset=utf-8")
	public @ResponseBody RespuestaTipoServicio obtenerTiposServicios() {
		RespuestaTipoServicio respuestaPersona = new RespuestaTipoServicio();
		respuestaPersona.setLista(servicio.obtenerTiposServicios());
		return respuestaPersona;
	}

	// LISTAR NOMBRES DE SERVICIOS
	@RequestMapping(value = "/obtener-nombres-servicios/{codigoTipoServicio}", method = RequestMethod.GET, produces = "application/json;charset=utf-8")
	public @ResponseBody RespuestaNombreServicio obtenerNombresServicios(@PathVariable("codigoTipoServicio")Integer codigoTipoServicio) {
		RespuestaNombreServicio respuestaPersona = new RespuestaNombreServicio();
		respuestaPersona.setListaNombreServicio(servicio.obtenerNombresServicios(codigoTipoServicio));
		return respuestaPersona;
	}

	// LISTAR TIPO DE CARGAS
	@RequestMapping(value = "/obtener-tipo-cargas", method = RequestMethod.GET, produces = "application/json;charset=utf-8")
	public @ResponseBody RespuestaTipoCarga obtenerTipoCargas() {
		RespuestaTipoCarga respuestaPersona = new RespuestaTipoCarga();
		respuestaPersona.setListaTipoCarga(servicio.obtenerTipoCargas());
		return respuestaPersona;
	}
	
	// OBTENER DATOS PERSONALES DEL USUARIO
		@RequestMapping(value = "/obtener-datos-usuario/{correo}", method = RequestMethod.POST, produces = "application/json;charset=utf-8", consumes = "application/json;charset=utf-8")
		public @ResponseBody Persona obtenerDatosPersonales(@PathVariable("correo")String correo) {
			return servicio.obtenerDatosPersonales(correo);
		}
		
	// OBTENER SOLICITUDES POR CADA USUARIO	
		@RequestMapping(value = "/obtener-cotizacion-usuario/{correo}", method = RequestMethod.GET, produces = "application/json;charset=utf-8")
		public @ResponseBody RespuestaListaCotizacion obtenerCotizacionUsuario(@PathVariable("correo")String correo) {
			RespuestaListaCotizacion respuestaPersona = new RespuestaListaCotizacion();
			respuestaPersona.setListaCotizacionUsuario(servicio.obtenerCotizacionUsuario(correo));
			return respuestaPersona;
		}
}
