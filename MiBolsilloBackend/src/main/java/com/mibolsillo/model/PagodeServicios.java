package com.mibolsillo.model;

import java.io.Serializable;
import javax.persistence.*;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A PagodeServicios.
 */
@Entity
@Table(name = "pagode_servicios")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@SuppressWarnings("common-java:DuplicatedBlocks")
public class PagodeServicios implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "nombre_empresa")
    private String nombreEmpresa;

    @Column(name = "tipode_tarjeta")
    private String tipodeTarjeta;

    @Column(name = "monto")
    private String monto;

    @Column(name = "tipode_servicio")
    private String tipodeServicio;

    @Column(name = "tarjeta")
    private String tarjeta;

    @Column(name = "numerode_cuenta")
    private String numerodeCuenta;

    @Column(name = "codigo_barras")
    private String codigoBarras;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNombreEmpresa() {
		return nombreEmpresa;
	}

	public void setNombreEmpresa(String nombreEmpresa) {
		this.nombreEmpresa = nombreEmpresa;
	}

	public String getTipodeTarjeta() {
		return tipodeTarjeta;
	}

	public void setTipodeTarjeta(String tipodeTarjeta) {
		this.tipodeTarjeta = tipodeTarjeta;
	}

	public String getMonto() {
		return monto;
	}

	public void setMonto(String monto) {
		this.monto = monto;
	}

	public String getTipodeServicio() {
		return tipodeServicio;
	}

	public void setTipodeServicio(String tipodeServicio) {
		this.tipodeServicio = tipodeServicio;
	}

	public String getTarjeta() {
		return tarjeta;
	}

	public void setTarjeta(String tarjeta) {
		this.tarjeta = tarjeta;
	}

	public String getNumerodeCuenta() {
		return numerodeCuenta;
	}

	public void setNumerodeCuenta(String numerodeCuenta) {
		this.numerodeCuenta = numerodeCuenta;
	}

	public String getCodigoBarras() {
		return codigoBarras;
	}

	public void setCodigoBarras(String codigoBarras) {
		this.codigoBarras = codigoBarras;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

    
}
