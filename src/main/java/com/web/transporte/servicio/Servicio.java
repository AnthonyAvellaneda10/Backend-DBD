package com.web.transporte.servicio;

import java.util.List;

import com.web.transporte.dto.model.Cotizacion;
import com.web.transporte.dto.model.FormularioCotizacion;
import com.web.transporte.dto.model.NombreServicio;
import com.web.transporte.dto.model.Persona;
import com.web.transporte.dto.model.RegistroUsuario;
import com.web.transporte.dto.model.TipoCarga;
import com.web.transporte.dto.model.TipoServicio;
import com.web.transporte.dto.model.UsuarioLogin;

public interface Servicio {
	public List<Persona> obtenerPersona();
	
	// LOGIN
	public Persona autenticarUsuario(UsuarioLogin usuarioLogin);

	// REGISTRO
	public void registrarUsuario(RegistroUsuario usuarioRegistro);

	// SOLICITUD DE COTIZACION
	public void registrarCotizacion(FormularioCotizacion solicitarCotizacion);
	
	public List<TipoServicio> obtenerTiposServicios();
	public List<NombreServicio> obtenerNombresServicios(Integer codigoTipoServicio);
	public List<TipoCarga> obtenerTipoCargas();
	
	public List<Cotizacion> obtenerCotizacionUsuario(String correo);
	
	public Persona obtenerDatosPersonales(String correo);
		
}
