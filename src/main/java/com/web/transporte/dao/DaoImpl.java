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

import com.web.transporte.dto.model.Persona;
import com.web.transporte.dto.model.Usuario;
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
			// this.conexion.commit();
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
	public void crearCotizacion(String domicilioEnvio, String domicilioRetiro, String observaciones, String dni) {
		try {
			obtenerConexion();
			String sql = "insert into cotizacion(direccion_envio, direccion_recibo, observaciones,dni)" + " values (?, ?, ?, ?)";

			PreparedStatement sentencia = conexion.prepareStatement(sql);
			sentencia.setString(1, domicilioEnvio);
			sentencia.setString(2, domicilioRetiro);
			sentencia.setString(3, observaciones);
			sentencia.setString(4, dni);
			sentencia.executeUpdate();
			// usuarioLogin = new UsuarioLogin();

			sentencia.close();
			cerrarConexionConCommit();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
