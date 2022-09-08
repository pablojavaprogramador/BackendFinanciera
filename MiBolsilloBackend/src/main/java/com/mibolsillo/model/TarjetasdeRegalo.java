package com.mibolsillo.model;

import java.io.Serializable;
import javax.persistence.*;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A TarjetasdeRegalo.
 */
@Entity
@Table(name = "tarjetasde_regalo")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@SuppressWarnings("common-java:DuplicatedBlocks")
public class TarjetasdeRegalo implements Serializable {

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

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public TarjetasdeRegalo id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombreEmpresa() {
        return this.nombreEmpresa;
    }

    public TarjetasdeRegalo nombreEmpresa(String nombreEmpresa) {
        this.setNombreEmpresa(nombreEmpresa);
        return this;
    }

    public void setNombreEmpresa(String nombreEmpresa) {
        this.nombreEmpresa = nombreEmpresa;
    }

    public String getTipodeTarjeta() {
        return this.tipodeTarjeta;
    }

    public TarjetasdeRegalo tipodeTarjeta(String tipodeTarjeta) {
        this.setTipodeTarjeta(tipodeTarjeta);
        return this;
    }

    public void setTipodeTarjeta(String tipodeTarjeta) {
        this.tipodeTarjeta = tipodeTarjeta;
    }

    public String getMonto() {
        return this.monto;
    }

    public TarjetasdeRegalo monto(String monto) {
        this.setMonto(monto);
        return this;
    }

    public void setMonto(String monto) {
        this.monto = monto;
    }

    public String getTipodeServicio() {
        return this.tipodeServicio;
    }

    public TarjetasdeRegalo tipodeServicio(String tipodeServicio) {
        this.setTipodeServicio(tipodeServicio);
        return this;
    }

    public void setTipodeServicio(String tipodeServicio) {
        this.tipodeServicio = tipodeServicio;
    }

    public String getTarjeta() {
        return this.tarjeta;
    }

    public TarjetasdeRegalo tarjeta(String tarjeta) {
        this.setTarjeta(tarjeta);
        return this;
    }

    public void setTarjeta(String tarjeta) {
        this.tarjeta = tarjeta;
    }

    public String getNumerodeCuenta() {
        return this.numerodeCuenta;
    }

    public TarjetasdeRegalo numerodeCuenta(String numerodeCuenta) {
        this.setNumerodeCuenta(numerodeCuenta);
        return this;
    }

    public void setNumerodeCuenta(String numerodeCuenta) {
        this.numerodeCuenta = numerodeCuenta;
    }

    public String getCodigoBarras() {
        return this.codigoBarras;
    }

    public TarjetasdeRegalo codigoBarras(String codigoBarras) {
        this.setCodigoBarras(codigoBarras);
        return this;
    }

    public void setCodigoBarras(String codigoBarras) {
        this.codigoBarras = codigoBarras;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof TarjetasdeRegalo)) {
            return false;
        }
        return id != null && id.equals(((TarjetasdeRegalo) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "TarjetasdeRegalo{" +
            "id=" + getId() +
            ", nombreEmpresa='" + getNombreEmpresa() + "'" +
            ", tipodeTarjeta='" + getTipodeTarjeta() + "'" +
            ", monto='" + getMonto() + "'" +
            ", tipodeServicio='" + getTipodeServicio() + "'" +
            ", tarjeta='" + getTarjeta() + "'" +
            ", numerodeCuenta='" + getNumerodeCuenta() + "'" +
            ", codigoBarras='" + getCodigoBarras() + "'" +
            "}";
    }
}
