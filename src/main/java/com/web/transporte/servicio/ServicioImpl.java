package com.web.transporte.servicio;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.web.transporte.dao.Dao;
import com.web.transporte.dto.model.Cotizacion;
import com.web.transporte.dto.model.FormularioCotizacion;
import com.web.transporte.dto.model.NombreServicio;
import com.web.transporte.dto.model.Persona;
import com.web.transporte.dto.model.Reclamo;
import com.web.transporte.dto.model.RegistroUsuario;
import com.web.transporte.dto.model.TipoCarga;
import com.web.transporte.dto.model.TipoReclamo;
import com.web.transporte.dto.model.TipoServicio;
import com.web.transporte.dto.model.UnidadTransporteTelefono;
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
	
	
	private boolean existeUsuario2(String correo) {
		boolean existeUsuario = false;
		if (dao.existeUsuario2(correo) == 1)
			existeUsuario = true;
		return existeUsuario;
	}

	private String obtenerPasswordBD(String correo) {
		String password = dao.obtenerPasswordBD(correo);
		System.out.println("Password: " + password);
		return password;
	}
	
	
	@Override
	public Persona autenticarUsuario(UsuarioLogin usuarioLogin) {
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		Persona persona = null;
		
		//boolean existUsuario = existeUsuario(usuarioLogin.getCorreo(), usuarioLogin.getContrasenia());
		
		String passwordbd = obtenerPasswordBD(usuarioLogin.getCorreo());
		if (passwordbd == null) {
			throw new NotFoundException("Usuario no existe");
		}else {
			boolean isPasswordMatch = passwordEncoder.matches(usuarioLogin.getContrasenia(), passwordbd);
			System.out.println("isPasswordMatch: " + isPasswordMatch);
			if(isPasswordMatch) {
				persona = dao.obtenerNombres(usuarioLogin);
			}
			else {
				throw new NotFoundException("Usuario no existe");
			}
		}
		return persona;
	}
	
	// COPIA
	/*@Override
	public Persona autenticarUsuario(UsuarioLogin usuarioLogin) {
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
//		String encodedPassword = passwordEncoder.encode(usuarioLogin.getContrasenia());
//
		boolean isPasswordMatch = passwordEncoder.matches(usuarioLogin.getContrasenia(), encodedPassword);
		
		boolean existUsuario = existeUsuario(usuarioLogin.getCorreo(), usuarioLogin.getContrasenia());
		if (!existUsuario) {
			throw new NotFoundException("Usuario no existe");
		}

		Persona persona = dao.obtenerNombres(usuarioLogin);
		if (persona == null) {
			throw new NotFoundException("Usuario y/o contraseña incorrecta");
		}
		return persona;
	}*/
	

	// REGISTRO DE USUARIO
	private boolean existePersona(String dni, String correo, String nombres, String apellidoPaterno,
			String apellidoMaterno, String nroCelular) {
		boolean existPersona = false;
		if (dao.existePersona(dni, correo, nombres, apellidoPaterno, apellidoMaterno, nroCelular) == 1)
			existPersona = true;
		return existPersona;
	}

	public void registrarUsuario(RegistroUsuario usuarioRegistro) {
		// ENCRIPTA LA CONTRASEÑA
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		String encodedPassword = passwordEncoder.encode(usuarioRegistro.getContrasenia());
				
		boolean existenciaPersona = existePersona(usuarioRegistro.getDni(), usuarioRegistro.getCorreo(),
				usuarioRegistro.getNombres(), usuarioRegistro.getApellido_paterno(),
				usuarioRegistro.getApellido_materno(), usuarioRegistro.getNro_celular());
		
		if (!existenciaPersona) {
			dao.crearPersona(usuarioRegistro.getDni(), usuarioRegistro.getCorreo(), usuarioRegistro.getNombres(),
					usuarioRegistro.getApellido_paterno(), usuarioRegistro.getApellido_materno(),
					usuarioRegistro.getNro_celular());
			dao.crearUsuario(usuarioRegistro.getCorreo(), encodedPassword, usuarioRegistro.getDni());
		} else {
			if (existeUsuario2(usuarioRegistro.getCorreo())) {
				throw new NotFoundException("Este usuario ya existe");
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
	
	public Reclamo agregarReclamo(Reclamo reclamo) {
		System.out.println(reclamo);

		if(dao.validarExistenciaReclamo(reclamo.getServicioTransporte().getUsuario().getPersona().getDni())){
			return dao.agregarReclamo(reclamo);
		}
		else{

			return new Reclamo();

		}
	}

	public List<TipoReclamo> obtenerTiposReclamos(){
		return dao.obtenerTiposReclamos();
	}

	public List<Reclamo> obtenerReclamosUsuario(String codigo_usuario){
		return dao.obtenerReclamosUsuario(codigo_usuario);
	}
	
	// GESTION DE SEGUIMIENTO
		public List<UnidadTransporteTelefono> obtenerUnidadTransporteTelefonos(UnidadTransporteTelefono unidadTransporteTelefono) {
			return dao.obtenerUnidadTransporteTelefonos(unidadTransporteTelefono);
		}
		//TRAER RECLAMOS 
		public List<Reclamo> obtenerReclamos(Reclamo reclamo) {
			return dao.obtenerReclamos(reclamo);
		}
		//RESPUESTA RECLAMOS
		public Reclamo respuestReclamo(Reclamo reclamo) {
			return dao.respuestReclamo(reclamo);
		}
}
