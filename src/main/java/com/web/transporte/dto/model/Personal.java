package com.web.transporte.dto.model;

import java.util.Date;

public class Personal {
	 private Integer codigo_personal;
	    private Date fecha_ingreso;
	    private String cargo;
	    Persona persona;
		public Integer getCodigo_personal() {
			return codigo_personal;
		}
		public void setCodigo_personal(Integer codigo_personal) {
			this.codigo_personal = codigo_personal;
		}
		public Date getFecha_ingreso() {
			return fecha_ingreso;
		}
		public void setFecha_ingreso(Date fecha_ingreso) {
			this.fecha_ingreso = fecha_ingreso;
		}
		public String getCargo() {
			return cargo;
		}
		public void setCargo(String cargo) {
			this.cargo = cargo;
		}
		public Persona getPersona() {
			return persona;
		}
		public void setPersona(Persona persona) {
			this.persona = persona;
		}
}
