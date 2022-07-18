package com.web.transporte.dto.model;

public class TipoCarga {
	private Integer codigoTipoCarga;
	private String nombreTipoCarga;
	
	public TipoCarga(Integer codigoTipoCarga, String nombreTipoCarga) {
		super();
		this.codigoTipoCarga = codigoTipoCarga;
		this.nombreTipoCarga = nombreTipoCarga;
	}
	
	public Integer getCodigoTipoCarga() {
		return codigoTipoCarga;
	}
	public void setCodigoTipoCarga(Integer codigoTipoCarga) {
		this.codigoTipoCarga = codigoTipoCarga;
	}
	public String getNombreTipoCarga() {
		return nombreTipoCarga;
	}
	public void setNombreTipoCarga(String nombreTipoCarga) {
		this.nombreTipoCarga = nombreTipoCarga;
	}
	
	
}
