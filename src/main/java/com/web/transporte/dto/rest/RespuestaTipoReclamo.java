package com.web.transporte.dto.rest;

import java.util.List;

import com.web.transporte.dto.model.TipoReclamo;

public class RespuestaTipoReclamo {
	private List<TipoReclamo> listaTipoReclamo;

    public List<TipoReclamo> getListaTipoReclamo() {
        return listaTipoReclamo;
    }

    public void setListaTipoReclamo(List<TipoReclamo> listaTipoReclamo) {
        this.listaTipoReclamo = listaTipoReclamo;
    }
}
