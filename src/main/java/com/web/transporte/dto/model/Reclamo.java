package com.web.transporte.dto.model;

public class Reclamo {
	private Integer codigo_reclamo;
	 private String tipo_reclamo;
	    private String descripcion;
	    private String rpta_reclamo;
	    private ServicioTransporte servicioTransporte ;
	    private ServicioCotizacion servicioCotizacion;
	    private PersonalReclamo personal;

	    public Reclamo(Integer codigo_reclamo, String tipo_reclamo, String descripcion, String rpta_reclamo,
				ServicioTransporte servicioTransporte, ServicioCotizacion servicioCotizacion,
				PersonalReclamo personal) {
			super();
			this.codigo_reclamo = codigo_reclamo;
			this.tipo_reclamo = tipo_reclamo;
			this.descripcion = descripcion;
			this.rpta_reclamo = rpta_reclamo;
			this.servicioTransporte = servicioTransporte;
			this.servicioCotizacion = servicioCotizacion;
			this.personal = personal;
		}




		public ServicioCotizacion getServicioCotizacion() {
			return servicioCotizacion;
		}




		public void setServicioCotizacion(ServicioCotizacion servicioCotizacion) {
			this.servicioCotizacion = servicioCotizacion;
		}




		public Integer getCodigo_reclamo() {
			return codigo_reclamo;
		}
		public void setCodigo_reclamo(Integer codigo_reclamo) {
			this.codigo_reclamo = codigo_reclamo;
		}

		public Reclamo(String descripcion, String rpta_reclamo) {
	        this.descripcion = descripcion;
	        this.rpta_reclamo = rpta_reclamo;
	    }

	    public Reclamo() {
	    }

		public String getTipo_reclamo() {
			return tipo_reclamo;
		}

		public void setTipo_reclamo(String tipo_reclamo) {
			this.tipo_reclamo = tipo_reclamo;
		}

		public String getDescripcion() {
			return descripcion;
		}

		public void setDescripcion(String descripcion) {
			this.descripcion = descripcion;
		}

		public String getRpta_reclamo() {
			return rpta_reclamo;
		}

		public void setRpta_reclamo(String rpta_reclamo) {
			this.rpta_reclamo = rpta_reclamo;
		}

		public ServicioTransporte getServicioTransporte() {
			return servicioTransporte;
		}

		public void setServicioTransporte(ServicioTransporte servicioTransporte) {
			this.servicioTransporte = servicioTransporte;
		}

		public PersonalReclamo getPersonal() {
			return personal;
		}

		public void setPersonal(PersonalReclamo personal) {
			this.personal = personal;
		}
	    
	    
}
