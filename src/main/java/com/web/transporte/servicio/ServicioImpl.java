package com.web.transporte.servicio;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.web.transporte.dao.Dao;
import com.web.transporte.dto.model.Cotizacion;
import com.web.transporte.dto.model.FormularioCotizacion;
import com.web.transporte.dto.model.NombreServicio;
import com.web.transporte.dto.model.Persona;
import com.web.transporte.dto.model.RegistroUsuario;
import com.web.transporte.dto.model.TipoCarga;
import com.web.transporte.dto.model.TipoServicio;
import com.web.transporte.dto.model.UsuarioLogin;
import com.web.transporte.exception.NotFoundException;

@Service
@Transactional
public class ServicioImpl implements Servicio {
	@Autowired
	private Dao dao;

	public List<Persona> obtenerPersona() {
		return dao.obtenerPersona();
	}

	// LOGIN USUARIO
	private boolean existeUsuario(String correo, String contrasenia) {
		boolean existeUsuario = false;
		if (dao.existeUsuario(correo, contrasenia) == 1)
			existeUsuario = true;
		return existeUsuario;
	}

	@Override
	public Persona autenticarUsuario(UsuarioLogin usuarioLogin) {
		boolean existUsuario = existeUsuario(usuarioLogin.getCorreo(), usuarioLogin.getContrasenia());
		if (!existUsuario) {
			throw new NotFoundException("Usuario no existe");
		}

		Persona persona = dao.obtenerNombres(usuarioLogin);
		if (persona == null) {
			throw new NotFoundException("Usuario y/o contraseña incorrecta");
		}
		return persona;
	}

	// REGISTRO DE USUARIO
	private boolean existePersona(String dni, String correo, String nombres, String apellidoPaterno,
			String apellidoMaterno, String nroCelular) {
		boolean existPersona = false;
		if (dao.existePersona(dni, correo, nombres, apellidoPaterno, apellidoMaterno, nroCelular) == 1)
			existPersona = true;
		return existPersona;
	}

	public void registrarUsuario(RegistroUsuario usuarioRegistro) {
		boolean existenciaPersona = existePersona(usuarioRegistro.getDni(), usuarioRegistro.getCorreo(),
				usuarioRegistro.getNombres(), usuarioRegistro.getApellido_paterno(),
				usuarioRegistro.getApellido_materno(), usuarioRegistro.getNro_celular());
		if (!existenciaPersona) {
			dao.crearPersona(usuarioRegistro.getDni(), usuarioRegistro.getCorreo(), usuarioRegistro.getNombres(),
					usuarioRegistro.getApellido_paterno(), usuarioRegistro.getApellido_materno(),
					usuarioRegistro.getNro_celular());
			dao.crearUsuario(usuarioRegistro.getCorreo(), usuarioRegistro.getContrasenia(), usuarioRegistro.getDni());
		} else {
			if (existeUsuario(usuarioRegistro.getCorreo(), usuarioRegistro.getContrasenia())) {
				throw new NotFoundException("Este usuario ya existe");
			} else {
				dao.crearPersona(usuarioRegistro.getDni(), usuarioRegistro.getCorreo(), usuarioRegistro.getNombres(),
						usuarioRegistro.getApellido_paterno(), usuarioRegistro.getApellido_materno(),
						usuarioRegistro.getNro_celular());

				dao.crearUsuario(usuarioRegistro.getCorreo(), usuarioRegistro.getContrasenia(),
						usuarioRegistro.getDni());
			}
		}
	}

	// SOLICITUD DE COTIZACION
	/*
	 * public void registrarCotizacion(Cotización solicitarCotizacion) { boolean
	 * existenciaPersona = existePersona(solicitarCotizacion.getDni(),
	 * solicitarCotizacion.getCorreo(), solicitarCotizacion.getNombres(),
	 * solicitarCotizacion.getApellido_paterno(),
	 * solicitarCotizacion.getApellido_materno(),
	 * solicitarCotizacion.getNro_celular()); if (!existenciaPersona) {
	 * dao.crearPersona(solicitarCotizacion.getDni(),
	 * solicitarCotizacion.getCorreo(), solicitarCotizacion.getNombres(),
	 * solicitarCotizacion.getApellido_paterno(),
	 * solicitarCotizacion.getApellido_materno(),
	 * solicitarCotizacion.getNro_celular());
	 * 
	 * dao.crearCotizacion(solicitarCotizacion.getDomicilioEnvio(),
	 * solicitarCotizacion.getDomicilioRetiro(),
	 * solicitarCotizacion.getObservaciones(), solicitarCotizacion.getDni()); } else
	 * { dao.crearCotizacion(solicitarCotizacion.getDomicilioEnvio(),
	 * solicitarCotizacion.getDomicilioRetiro(),
	 * solicitarCotizacion.getObservaciones(), solicitarCotizacion.getDni()); }
	 * 
	 * }
	 */

	public void registrarCotizacion(FormularioCotizacion solicitarCotizacion) {
		
		dao.crearCotizacion(solicitarCotizacion.getDomicilioEnvio(), solicitarCotizacion.getDomicilioRetiro(),
				solicitarCotizacion.getObservaciones(), solicitarCotizacion.getCorreo());

		int codigoServicio = dao.crearServicio(solicitarCotizacion.getServicio(),
				solicitarCotizacion.getNombreService(), solicitarCotizacion.getCorreo());
		System.out.println("codigo servicio: " + codigoServicio);

		dao.crearCarga(solicitarCotizacion.getTipoCarga(), solicitarCotizacion.getPeso(),
				solicitarCotizacion.getVolumen(), codigoServicio);

	}

	// OBTENER LOS TIPOS DE SERVICIO
	public List<TipoServicio> obtenerTiposServicios() {
		return dao.obtenerTiposServicios();
	}

	// OBTENER LOS NOMBRES DE SERVICIO

	public List<NombreServicio> obtenerNombresServicios(Integer codigoTipoServicio) {
		return dao.obtenerNombresServicios(codigoTipoServicio);
	}

	// OBTENER LOS TIPOS DE CARGA

	public List<TipoCarga> obtenerTipoCargas() {
		return dao.obtenerTipoCargas();
	}

	public Persona obtenerDatosPersonales(String correo) {
		return dao.obtenerDatosPersonales(correo);
	}
	
	// OBTENER COTIZACIONES POR USUARIO
	public List<Cotizacion> obtenerCotizacionUsuario(String correo){
		return dao.obtenerCotizacionUsuario(correo);
	}
}
