package com.mibolsillo.model;

import java.io.Serializable;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A Domicilio.
 */
@Entity
@Table(name = "domicilio")
public class Domicilio implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    @NotNull(message = "EL id no tiene que ser nulo")
    private Long id;

    @Column(name = "calle")
    private String calle;

    @Column(name = "numero_exterior")
    private String numeroExterior;

    @Column(name = "numero_interior")
    private String numeroInterior;

    @Column(name = "code_postal")
    private String codePostal;

    @Column(name = "domicilio_actual")
    private String domicilioActual;

    @Column(name = "manzana")
    private String manzana;

    @Column(name = "andador")
    private String andador;

    @Column(name = "edificio")
    private String edificio;

    @Column(name = "fecha_antiguedad")
    private String fechaAntiguedad;

    @Column(name = "codigo_postal")
    private String codigoPostal;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCalle() {
		return calle;
	}

	public void setCalle(String calle) {
		this.calle = calle;
	}

	public String getNumeroExterior() {
		return numeroExterior;
	}

	public void setNumeroExterior(String numeroExterior) {
		this.numeroExterior = numeroExterior;
	}

	public String getNumeroInterior() {
		return numeroInterior;
	}

	public void setNumeroInterior(String numeroInterior) {
		this.numeroInterior = numeroInterior;
	}

	public String getCodePostal() {
		return codePostal;
	}

	public void setCodePostal(String codePostal) {
		this.codePostal = codePostal;
	}

	public String getDomicilioActual() {
		return domicilioActual;
	}

	public void setDomicilioActual(String domicilioActual) {
		this.domicilioActual = domicilioActual;
	}

	public String getManzana() {
		return manzana;
	}

	public void setManzana(String manzana) {
		this.manzana = manzana;
	}

	public String getAndador() {
		return andador;
	}

	public void setAndador(String andador) {
		this.andador = andador;
	}

	public String getEdificio() {
		return edificio;
	}

	public void setEdificio(String edificio) {
		this.edificio = edificio;
	}

	public String getFechaAntiguedad() {
		return fechaAntiguedad;
	}

	public void setFechaAntiguedad(String fechaAntiguedad) {
		this.fechaAntiguedad = fechaAntiguedad;
	}

	public String getCodigoPostal() {
		return codigoPostal;
	}

	public void setCodigoPostal(String codigoPostal) {
		this.codigoPostal = codigoPostal;
	}

	@Override
	public String toString() {
		return "Domicilio [id=" + id + ", calle=" + calle + ", numeroExterior=" + numeroExterior + ", numeroInterior="
				+ numeroInterior + ", codePostal=" + codePostal + ", domicilioActual=" + domicilioActual + ", manzana="
				+ manzana + ", andador=" + andador + ", edificio=" + edificio + ", fechaAntiguedad=" + fechaAntiguedad
				+ ", codigoPostal=" + codigoPostal + "]";
	}

   
}
