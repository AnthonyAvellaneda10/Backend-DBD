package com.web.transporte.dto.rest;

import java.util.List;

import com.web.transporte.dto.model.Cotizacion;

public class RespuestaListaCotizacion {
	private List<Cotizacion> listaCotizacionUsuario;

	public List<Cotizacion> getListaCotizacionUsuario() {
		return listaCotizacionUsuario;
	}

	public void setListaCotizacionUsuario(List<Cotizacion> listaCotizacionUsuario) {
		this.listaCotizacionUsuario = listaCotizacionUsuario;
	}
}
