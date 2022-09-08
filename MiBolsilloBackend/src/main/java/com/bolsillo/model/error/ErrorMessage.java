package com.bolsillo.model.error;

import java.util.Date;
import java.util.List;

public class ErrorMessage {
	private int codigo;
	  private Date fecha;
	  private String mensaje;
	  private String descripcion;
	  private String url;
	  
	  
	  
	public ErrorMessage(int codigo, Date fecha, String mensaje, String descripcion, String url) {
		super();
		this.codigo = codigo;
		this.fecha = fecha;
		this.mensaje = mensaje;
		this.descripcion = descripcion;
		this.url = url;
	}
	
	public int getCodigo() {
		return codigo;
	}
	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}
	public Date getFecha() {
		return fecha;
	}
	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}
	public String getMensaje() {
		return mensaje;
	}
	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	  
	  
	
	 
}
