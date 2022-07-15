package com.web.transporte.dto.rest;

import java.util.List;

import com.web.transporte.dto.model.Persona;

public class RespuestaPersona {
	private List<Persona> lista;

	public List<Persona> getLista() {
		return lista;
	}

	public void setLista(List<Persona> lista) {
		this.lista = lista;
	}
}
