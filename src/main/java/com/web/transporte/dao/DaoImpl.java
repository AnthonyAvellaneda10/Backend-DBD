package com.web.transporte.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.web.transporte.dto.model.Cotizacion;
import com.web.transporte.dto.model.NombreServicio;
import com.web.transporte.dto.model.Persona;
import com.web.transporte.dto.model.Reclamo;
import com.web.transporte.dto.model.ServicioCotizacion;
import com.web.transporte.dto.model.TipoCarga;
import com.web.transporte.dto.model.TipoReclamo;
import com.web.transporte.dto.model.TipoServicio;
import com.web.transporte.dto.model.UsuarioLogin;

@Repository
public class DaoImpl implements Dao {
	@Autowired
	private JdbcTemplate jdbcTemplate;

	private Connection conexion;

	private void obtenerConexion() {
		try {
			this.conexion = jdbcTemplate.getDataSource().getConnection();
		} catch (SQLException throwables) {
			throwables.printStackTrace();
		}
	}

	private void cerrarConexion(ResultSet resultado, Statement sentencia) {
		try {
			if (resultado != null)
				resultado.close();
			if (sentencia != null)
				sentencia.close();
			 //this.conexion.commit();
			this.conexion.close();
			this.conexion = null;
		} catch (SQLException throwables) {
			throwables.printStackTrace();
		}
	}

	private void cerrarConexionSinCommit() throws SQLException {
		// conexion.commit();
		conexion.close();
		conexion = null;
	}

	private void cerrarConexionConCommit() throws SQLException {
		conexion.setAutoCommit(false);
		conexion.commit();
		conexion.close();
		conexion = null;
	}

	public List<Persona> obtenerPersona() {
		List<Persona> lista = new ArrayList<>();

		String sql = "SELECT id_persona, nombres, apellido_mat, apellido_pat" + " FROM persona";

		try {
			obtenerConexion();
			Statement sentencia = conexion.createStatement();
			ResultSet resultado = sentencia.executeQuery(sql);
			while (resultado.next()) {
				lista.add(extraerPersonas(resultado));
			}
			cerrarConexion(resultado, sentencia);
		} catch (SQLException throwables) {
			throwables.printStackTrace();
		}
		return lista;
	}

	private Persona extraerPersonas(ResultSet resultado) throws SQLException {
		Persona retorno = new Persona(resultado.getString("id_persona"), resultado.getString("nombres"),
				resultado.getString("apellido_mat"), resultado.getString("apellido_pat"));
		return retorno;
	}

	// LOGIN USUARIO
	public Integer existeUsuario(String correo, String contrasenia) {
		Integer filas = null;
		try {
			obtenerConexion();
			String sql = "SELECT COUNT(*)" + " FROM usuario" + " WHERE codigo_usuario = ? and contrasenia = ?";
			PreparedStatement sentencia = conexion.prepareStatement(sql);
			sentencia.setString(1, correo);
			sentencia.setString(2, contrasenia);
			ResultSet resultado = sentencia.executeQuery();
			if (resultado.next()) {
				filas = resultado.getInt(1);
			}
			sentencia.close();
			cerrarConexionSinCommit();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return filas;
	}
	
	// EXISTE USUARIO2
	public Integer existeUsuario2(String email) {
		Integer filas = null;
		try {
			obtenerConexion();
			String sql = "SELECT COUNT(*)" + " FROM usuario" + " WHERE codigo_usuario = ? ";
			PreparedStatement sentencia = conexion.prepareStatement(sql);
			sentencia.setString(1, email);
			ResultSet resultado = sentencia.executeQuery();
			if (resultado.next()) {
				filas = resultado.getInt(1);
			}
			sentencia.close();
			cerrarConexionSinCommit();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return filas;
	}
	
	public String obtenerPasswordBD(String correo) {
		String passwordBD = null;
		try {
			obtenerConexion();
			String sql = "SELECT contrasenia" + " FROM usuario" + " WHERE codigo_usuario = ?";

			PreparedStatement sentencia = conexion.prepareStatement(sql);
			sentencia.setString(1, correo);
			ResultSet resultado = sentencia.executeQuery();
			// usuarioLogin = new UsuarioLogin();
			while (resultado.next()) {
				passwordBD = resultado.getString("contrasenia");
			}
			sentencia.close();
			cerrarConexionSinCommit();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return passwordBD;
	}
	

	public Persona obtenerNombres(UsuarioLogin usuarioLogin) {
		Persona respuestaUsuario = null;
		try {
			obtenerConexion();
			String sql = "SELECT nombres, apellido_pat" + " FROM persona" + " WHERE correo = ?";

			PreparedStatement sentencia = conexion.prepareStatement(sql);
			sentencia.setString(1, usuarioLogin.getCorreo());
			ResultSet resultado = sentencia.executeQuery();
			// usuarioLogin = new UsuarioLogin();
			while (resultado.next()) {
				respuestaUsuario = new Persona();
				respuestaUsuario.setNombres(resultado.getString("nombres"));
				respuestaUsuario.setApellido_paterno(resultado.getString("apellido_pat"));
				// respuestaUsuario.setDni(resultado.getString("dni"));
				// respuestaUsuario.
			}
			sentencia.close();
			cerrarConexionSinCommit();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return respuestaUsuario;
	}

	// REGISTRO USUARIO
	public Integer existePersona(String dni, String correo, String nombres, String apellidoPaterno,
			String apellidoMaterno, String nroCelular) {
		Integer filas = null;
		try {
			obtenerConexion();
			String sql = "SELECT COUNT(*)" + " FROM persona"
					+ " WHERE dni = ? and correo = ? and nombres = ? and apellido_pat = ? and apellido_mat = ? and nro_celular = ?";
			PreparedStatement sentencia = conexion.prepareStatement(sql);
			sentencia.setString(1, dni);
			sentencia.setString(2, correo);
			sentencia.setString(3, nombres);
			sentencia.setString(4, apellidoPaterno);
			sentencia.setString(5, apellidoMaterno);
			sentencia.setString(6, nroCelular);
			ResultSet resultado = sentencia.executeQuery();
			if (resultado.next()) {
				filas = resultado.getInt(1);
			}
			sentencia.close();
			cerrarConexionConCommit();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return filas;
	}

	public void crearPersona(String dni, String correo, String nombres, String apellidoPaterno, String apellidoMaterno,
			String nroCelular) {
		try {
			obtenerConexion();
			String sql = "insert into persona(dni, correo, nombres, apellido_pat, apellido_mat, nro_celular)"
					+ " values (?, ?, ?, ?, ?, ?)";

			PreparedStatement sentencia = conexion.prepareStatement(sql);
			sentencia.setString(1, dni);
			sentencia.setString(2, correo);
			sentencia.setString(3, nombres);
			sentencia.setString(4, apellidoPaterno);
			sentencia.setString(5, apellidoMaterno);
			sentencia.setString(6, nroCelular);
			sentencia.executeUpdate();

			sentencia.close();
			cerrarConexionConCommit();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void crearUsuario(String correo, String contrasenia, String dni) {
		try {
			obtenerConexion();
			String sql = "insert into usuario(codigo_usuario, contrasenia, dni)" + " values (?, ?, ?)";

			PreparedStatement sentencia = conexion.prepareStatement(sql);
			sentencia.setString(1, correo);
			sentencia.setString(2, contrasenia);
			sentencia.setString(3, dni);
			sentencia.executeUpdate();
			// usuarioLogin = new UsuarioLogin();

			sentencia.close();
			cerrarConexionConCommit();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	// SOLICTAR COTIZACION
	public void crearCotizacion(String domicilioEnvio, String domicilioRetiro, String observaciones, String correo) {
		try {
			obtenerConexion();
			String sql = "insert into cotizacion(direccion_envio, direccion_recibo, observaciones, codigo_usuario)"
					+ " values (?, ?, ?, ?)";

			PreparedStatement sentencia = conexion.prepareStatement(sql);
			sentencia.setString(1, domicilioEnvio);
			sentencia.setString(2, domicilioRetiro);
			sentencia.setString(3, observaciones);
			sentencia.setString(4, correo);
			sentencia.executeUpdate();
			// usuarioLogin = new UsuarioLogin();
			sentencia.close();
			cerrarConexionConCommit();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	// CREAR SERVICIO
	public int crearServicio(String tipoServicio, String nombreServicio, String correo) {
		ServicioCotizacion servicio = new ServicioCotizacion();
		int resultado = 0;
		try {
			obtenerConexion();
			String sql = "insert into servicio(nombre_servicio, tipo_servicio, codigo_usuario)"
					+ " values (?, ?, ?)";

			PreparedStatement sentencia = conexion.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			sentencia.setString(1, tipoServicio);
			sentencia.setString(2, nombreServicio);
			sentencia.setString(3, correo);
			sentencia.executeUpdate();
			try (ResultSet generatedKeys = sentencia.getGeneratedKeys()) {
				//System.out.println("shd: " + generatedKeys.getInt(0));
	            if (generatedKeys.next()) {
	            	servicio.setCodigoServicio(generatedKeys.getInt(1));
	            	resultado = servicio.getCodigoServicio();
	                //user.setId(generatedKeys.getLong(1));
	            }
	            else {
	                throw new SQLException("Creating user failed, no ID obtained.");
	            }
	        }
			
			// usuarioLogin = new UsuarioLogin();
			sentencia.close();
			cerrarConexionConCommit();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return resultado;
	}
	
	// CREAR CARGA
	public void crearCarga(String tipoCarga, Integer peso, Integer volumen, Integer codigoServicio) {
		try {
			obtenerConexion();
			String sql = "insert into carga(tipo_carga, peso, volumen, codigo_servicio)"
					+ " values (?, ?, ?, ?)";

			PreparedStatement sentencia = conexion.prepareStatement(sql);
			sentencia.setString(1, tipoCarga);
			sentencia.setInt(2, peso);
			sentencia.setInt(3, volumen);
			sentencia.setInt(4, codigoServicio);
			sentencia.executeUpdate();
			// usuarioLogin = new UsuarioLogin();
			sentencia.close();
			cerrarConexionConCommit();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}


	// LISTAR TIPOS DE SERVICIOS
	public List<TipoServicio> obtenerTiposServicios() {
		List<TipoServicio> lista = new ArrayList<>();

		String sql = "SELECT codigo_tipo_servicio, nombre_tipo_servicio" + " FROM Tipo_servicios";

		try {
			obtenerConexion();
			Statement sentencia = conexion.createStatement();
			ResultSet resultado = sentencia.executeQuery(sql);
			while (resultado.next()) {
				lista.add(obtenerTiposServicios(resultado));
			}
			cerrarConexion(resultado, sentencia);
		} catch (SQLException throwables) {
			throwables.printStackTrace();
		}
		return lista;
	}

	private TipoServicio obtenerTiposServicios(ResultSet resultado) throws SQLException {
		TipoServicio retorno = new TipoServicio(resultado.getInt("codigo_tipo_servicio"),
				resultado.getString("nombre_tipo_servicio"));
		return retorno;
	}
	
	// LISTA COTIZACIONES POR USUARIO
	public List<Cotizacion> obtenerCotizacionUsuario(String correo){
			List<Cotizacion> lista = new ArrayList<>();

			String sql = "SELECT direccion_envio, direccion_recibo" + " FROM cotizacion"
					+ " WHERE codigo_usuario = ?";

			try {
				obtenerConexion();
				PreparedStatement sentencia = conexion.prepareStatement(sql);
				sentencia.setString(1, correo);
				ResultSet resultado = sentencia.executeQuery();
				while (resultado.next()) {
					lista.add(obtenerCotizacionUsuario(resultado));
				}
				cerrarConexion(resultado, sentencia);
			} catch (SQLException throwables) {
				throwables.printStackTrace();
			}
			return lista;
		}

		private Cotizacion obtenerCotizacionUsuario(ResultSet resultado) throws SQLException {
			Cotizacion retorno = new Cotizacion(resultado.getString("direccion_envio"),
					resultado.getString("direccion_recibo"));
			return retorno;
		}

	// LISTAR NOMBRES DE SERVICIOS
	public List<NombreServicio> obtenerNombresServicios(Integer codigoTipoServicio) {
		List<NombreServicio> lista = new ArrayList<>();

		String sql = "SELECT codigo_nombre_servicio, nombre_servicio" + " FROM Nombre_servicios"
				+ " WHERE codigo_tipo_servicio = ?";

		try {
			obtenerConexion();
			PreparedStatement sentencia = conexion.prepareStatement(sql);
			sentencia.setInt(1, codigoTipoServicio);
			ResultSet resultado = sentencia.executeQuery();
			while (resultado.next()) {
				lista.add(obtenerNombresServicios(resultado));
			}
			cerrarConexion(resultado, sentencia);
		} catch (SQLException throwables) {
			throwables.printStackTrace();
		}
		return lista;
	}

	private NombreServicio obtenerNombresServicios(ResultSet resultado) throws SQLException {
		NombreServicio retorno = new NombreServicio(resultado.getInt("codigo_nombre_servicio"),
				resultado.getString("nombre_servicio"));
		return retorno;
	}

	// LISTAR TIPOS DE CARGAS
	public List<TipoCarga> obtenerTipoCargas() {
		List<TipoCarga> lista = new ArrayList<>();

		String sql = "SELECT codigo_tipo_carga, nombre_tipo_carga" + " FROM tipo_cargas";

		try {
			obtenerConexion();
			Statement sentencia = conexion.createStatement();
			ResultSet resultado = sentencia.executeQuery(sql);
			while (resultado.next()) {
				lista.add(obtenerTipoCargas(resultado));
			}
			//cerrarConexion(resultado, sentencia);
		} catch (SQLException throwables) {
			throwables.printStackTrace();
		}
		return lista;
	}

	private TipoCarga obtenerTipoCargas(ResultSet resultado) throws SQLException {
		TipoCarga retorno = new TipoCarga(resultado.getInt("codigo_tipo_carga"),
				resultado.getString("nombre_tipo_carga"));
		return retorno;
	}
	
	// DATOS PERSONALES
	public Persona obtenerDatosPersonales(String correo) {
		Persona respuestaUsuario = null;
		try {
			obtenerConexion();
			String sql = "SELECT dni, nombres, apellido_pat, apellido_mat, nro_celular, correo" + " FROM persona"
					+ " WHERE correo = ?";
			PreparedStatement sentencia = conexion.prepareStatement(sql);
			sentencia.setString(1, correo);
			ResultSet resultado = sentencia.executeQuery();
			if (resultado.next()) {
				respuestaUsuario = new Persona();
				respuestaUsuario.setDni(resultado.getString("dni"));
				respuestaUsuario.setNombres(resultado.getString("nombres"));
				respuestaUsuario.setApellido_paterno(resultado.getString("apellido_pat"));
				respuestaUsuario.setApellido_materno(resultado.getString("apellido_mat"));
				respuestaUsuario.setNro_celular(resultado.getString("nro_celular"));
				respuestaUsuario.setCorreo(resultado.getString("correo"));
			}
			sentencia.close();
			cerrarConexionSinCommit();
		} catch (SQLException e) {
			e.printStackTrace();
		}		
		return respuestaUsuario;
	}
	
	//AGREGAR RECLAMO
		public Reclamo agregarReclamo(Reclamo reclamo) {

			String SQL = " INSERT INTO reclamo(tipo_reclamo, descripcion, " +
					" codigo_personal, codigo_servicio) \n" +
					" VALUES (?,?, \n" +
					" (SELECT p2.codigo_personal from personal p2 where p2.cargo = ? LIMIT 1), \n" +
					" (SELECT s.codigo_servicio from servicio s \n" +
					" inner join usuario u \n" +
					" on u.codigo_usuario  = s.codigo_usuario\n" +
					" inner join persona p \n" +
					" on p.dni  = u.dni \n" +
					" where p.dni = ?)) ";


			try {
				obtenerConexion();
				PreparedStatement sentencia = this.conexion.prepareStatement(SQL);
				sentencia.setString(1,reclamo.getTipo_reclamo());
				sentencia.setString(2,reclamo.getDescripcion());
				sentencia.setString(3,reclamo.getPersonal().getCargo());
				sentencia.setString(4,reclamo.getServicioTransporte().getUsuario().getPersona().getDni());
				sentencia.executeUpdate();
				cerrarConexion(null,sentencia);
			} catch (SQLException throwables) {
				throwables.printStackTrace();
			}
			return reclamo;
		}

		//VALIDAR RECLAMO
		public Boolean validarExistenciaReclamo(String dni) {
			Boolean encontrado = false;
			String SQL = " select p.nombres , p.correo , p.nro_celular \n" +
					" from persona p\n" +
					" inner join usuario u \n" +
					" on p.dni  = u.dni  \n" +
					" where p.dni = ? ";

			try {
				obtenerConexion();
				PreparedStatement sentencia = this.conexion.prepareStatement(SQL);

				System.out.println(dni);

				sentencia.setString(1,dni);

				ResultSet resultado = sentencia.executeQuery();
				while(resultado.next()){
					encontrado = true;
				}
				cerrarConexion(resultado,sentencia);
			} catch (SQLException throwables) {
				throwables.printStackTrace();
			}
			return encontrado;
		}

		private TipoReclamo obtenerTipoReclamos(ResultSet resultado) throws SQLException {
			TipoReclamo retorno = new TipoReclamo(resultado.getInt("codigo_tipo_reclamo"),
					resultado.getString("nombre_tipo_reclamo"));
			return retorno;
		}

		public List<TipoReclamo> obtenerTiposReclamos() {
			List<TipoReclamo> lista = new ArrayList<>();

			String sql = " SELECT codigo_tipo_reclamo, nombre_tipo_reclamo" + " FROM tipo_reclamos";

			try {
				obtenerConexion();
				Statement sentencia = conexion.createStatement();
				ResultSet resultado = sentencia.executeQuery(sql);
				while (resultado.next()) {
					lista.add(obtenerTipoReclamos(resultado));
				}
				cerrarConexion(resultado, sentencia);
			} catch (SQLException throwables) {
				throwables.printStackTrace();
			}
			return lista;
		}

		public List<Reclamo> obtenerReclamosUsuario(String codigo_usuario){
			List<Reclamo> lista = new ArrayList<>();

			String sql = " select r.descripcion, r.rpta_reclamo\n" +
					" from reclamo r\n" +
					" inner join servicio s \n" +
					" on s.codigo_servicio  = r.codigo_servicio \n" +
					" where s.codigo_usuario = ? ";

			try {
				obtenerConexion();
				PreparedStatement sentencia = conexion.prepareStatement(sql);
				sentencia.setString(1, codigo_usuario);
				ResultSet resultado = sentencia.executeQuery();
				while (resultado.next()) {
					lista.add(obtenerReclamosUsuario(resultado));
				}
				cerrarConexion(resultado, sentencia);
			} catch (SQLException throwables) {
				throwables.printStackTrace();
			}
			return lista;
		}

		private Reclamo obtenerReclamosUsuario(ResultSet resultado) throws SQLException {
			Reclamo retorno = new Reclamo(resultado.getString("descripcion"),
					resultado.getString("rpta_reclamo"));
			return retorno;
		}

}
