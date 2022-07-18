package com.web.transporte.dao;

import java.util.List;

import com.web.transporte.dto.model.Cotizacion;
import com.web.transporte.dto.model.NombreServicio;
import com.web.transporte.dto.model.Persona;
import com.web.transporte.dto.model.TipoCarga;
import com.web.transporte.dto.model.TipoServicio;
import com.web.transporte.dto.model.UsuarioLogin;

public interface Dao {
	public List<Persona> obtenerPersona();
	
	public Integer existeUsuario(String email, String contrasenia);
	
	public Integer existePersona(String dni, String correo, String nombres, String apellidoPaterno, String apellidoMaterno, String nroCelular);

	public Persona obtenerNombres(UsuarioLogin usuarioLogin);
	
	public void crearPersona(String dni, String correo, String nombres, String apellidoPaterno, String apellidoMaterno, String nroCelular);
	public void crearUsuario(String correo, String contrasenia, String dni);
	
	public void crearCotizacion(String domicilioEnvio, String domicilioRetiro, String observaciones, String correo);
	public int crearServicio(String tipoServicio, String nombreServicio, String correo);
	public void crearCarga(String tipoCarga, Integer peso, Integer volumen, Integer codigoServicio);
	
	public List<Cotizacion> obtenerCotizacionUsuario(String correo);
	
	public List<TipoServicio> obtenerTiposServicios();
	
	public List<NombreServicio> obtenerNombresServicios(Integer codigoTipoServicio);
	
	public List<TipoCarga> obtenerTipoCargas();
	
	public Persona obtenerDatosPersonales(String correo);
}
