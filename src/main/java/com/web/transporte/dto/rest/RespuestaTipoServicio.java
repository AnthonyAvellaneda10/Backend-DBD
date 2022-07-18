package com.web.transporte.dto.rest;

import java.util.List;

import com.web.transporte.dto.model.TipoServicio;

public class RespuestaTipoServicio {
	private List<TipoServicio> lista;

	public List<TipoServicio> getLista() {
		return lista;
	}

	public void setLista(List<TipoServicio> lista) {
		this.lista = lista;
	}
}
