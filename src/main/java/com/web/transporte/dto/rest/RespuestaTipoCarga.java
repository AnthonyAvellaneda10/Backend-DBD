package com.web.transporte.dto.rest;

import java.util.List;

import com.web.transporte.dto.model.TipoCarga;

public class RespuestaTipoCarga {
	private List<TipoCarga> listaTipoCarga;

	public List<TipoCarga> getListaTipoCarga() {
		return listaTipoCarga;
	}

	public void setListaTipoCarga(List<TipoCarga> listaTipoCarga) {
		this.listaTipoCarga = listaTipoCarga;
	}

	
}
