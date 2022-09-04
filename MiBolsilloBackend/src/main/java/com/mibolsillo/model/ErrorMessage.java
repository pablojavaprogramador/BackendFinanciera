package com.mibolsillo.model;

import java.util.Date;

public class ErrorMessage {
	 private int codigo;
	  private Date folio;
	  private String mensaje;
	  private String descripcion;
	  
	  
	public ErrorMessage(int codigo, Date folio, String mensaje, String descripcion) {
		super();
		this.codigo = codigo;
		this.folio = folio;
		this.mensaje = mensaje;
		this.descripcion = descripcion;
	}
	public int getCodigo() {
		return codigo;
	}
	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}
	public Date getFolio() {
		return folio;
	}
	public void setFolio(Date folio) {
		this.folio = folio;
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
	  

	 
}
