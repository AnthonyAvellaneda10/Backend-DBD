package com.web.transporte.dto.rest;

import java.util.List;

import com.web.transporte.dto.model.UnidadTransporteTelefono;

public class RespuestaUnidadTransporteTelefono {
	private List<UnidadTransporteTelefono> lista;

	public List<UnidadTransporteTelefono> getLista() {
		return lista;
	}

	public void setLista(List<UnidadTransporteTelefono> lista) {
		this.lista = lista;
	}
	
	
}	
