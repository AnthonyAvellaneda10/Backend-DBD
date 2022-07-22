package com.web.transporte.dto.rest;

import java.util.List;

import com.web.transporte.dto.model.Reclamo;

public class RespuestaReclamo {
	private List<Reclamo> lista;

	public List<Reclamo> getLista() {
		return lista;
	}

	public void setLista(List<Reclamo> lista) {
		this.lista = lista;
	}
	
	
}
