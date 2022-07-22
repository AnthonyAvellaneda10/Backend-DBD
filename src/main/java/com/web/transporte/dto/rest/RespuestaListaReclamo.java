package com.web.transporte.dto.rest;

import java.util.List;

import com.web.transporte.dto.model.Reclamo;

public class RespuestaListaReclamo {
	private List<Reclamo> listaReclamoUsuario;


    public List<Reclamo> getListaReclamoUsuario() {
        return listaReclamoUsuario;
    }

    public void setListaReclamoUsuario(List<Reclamo> listaReclamoUsuario) {
        this.listaReclamoUsuario = listaReclamoUsuario;
    }
}
