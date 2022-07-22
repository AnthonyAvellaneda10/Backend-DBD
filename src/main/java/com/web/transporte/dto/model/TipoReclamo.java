package com.web.transporte.dto.model;

public class TipoReclamo {
	private Integer codigotipoReclamo;
    private String nombreTipoReclamo;

    public TipoReclamo(Integer codigotipoReclamo, String nombreTipoReclamo) {
        this.codigotipoReclamo = codigotipoReclamo;
        this.nombreTipoReclamo = nombreTipoReclamo;
    }

	public Integer getCodigotipoReclamo() {
		return codigotipoReclamo;
	}

	public void setCodigotipoReclamo(Integer codigotipoReclamo) {
		this.codigotipoReclamo = codigotipoReclamo;
	}

	public String getNombreTipoReclamo() {
		return nombreTipoReclamo;
	}

	public void setNombreTipoReclamo(String nombreTipoReclamo) {
		this.nombreTipoReclamo = nombreTipoReclamo;
	}
    
    
}
