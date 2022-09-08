package com.mibolsillo.model;

import java.io.Serializable;
import javax.persistence.*;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A SolicitudCredito.
 */
@Entity
@Table(name = "solicitud_credito")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@SuppressWarnings("common-java:DuplicatedBlocks")
public class SolicitudCredito implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "no_cliente")
    private String noCliente;

    @Column(name = "id_solicitud")
    private String idSolicitud;

    @Column(name = "motivo_solicitud")
    private String motivoSolicitud;

    @Column(name = "numerode_cuenta")
    private String numerodeCuenta;

    @Column(name = "revision_aprobacion")
    private String revisionAprobacion;

    @Column(name = "aceptacion_transferencia_datos")
    private String aceptacionTransferenciaDatos;

    @Column(name = "aceptacion_acuerdos")
    private String aceptacionAcuerdos;

    @Column(name = "monto")
    private String monto;

    @Column(name = "plazo")
    private String plazo;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public SolicitudCredito id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNoCliente() {
        return this.noCliente;
    }

    public SolicitudCredito noCliente(String noCliente) {
        this.setNoCliente(noCliente);
        return this;
    }

    public void setNoCliente(String noCliente) {
        this.noCliente = noCliente;
    }

    public String getIdSolicitud() {
        return this.idSolicitud;
    }

    public SolicitudCredito idSolicitud(String idSolicitud) {
        this.setIdSolicitud(idSolicitud);
        return this;
    }

    public void setIdSolicitud(String idSolicitud) {
        this.idSolicitud = idSolicitud;
    }

    public String getMotivoSolicitud() {
        return this.motivoSolicitud;
    }

    public SolicitudCredito motivoSolicitud(String motivoSolicitud) {
        this.setMotivoSolicitud(motivoSolicitud);
        return this;
    }

    public void setMotivoSolicitud(String motivoSolicitud) {
        this.motivoSolicitud = motivoSolicitud;
    }

    public String getNumerodeCuenta() {
        return this.numerodeCuenta;
    }

    public SolicitudCredito numerodeCuenta(String numerodeCuenta) {
        this.setNumerodeCuenta(numerodeCuenta);
        return this;
    }

    public void setNumerodeCuenta(String numerodeCuenta) {
        this.numerodeCuenta = numerodeCuenta;
    }

    public String getRevisionAprobacion() {
        return this.revisionAprobacion;
    }

    public SolicitudCredito revisionAprobacion(String revisionAprobacion) {
        this.setRevisionAprobacion(revisionAprobacion);
        return this;
    }

    public void setRevisionAprobacion(String revisionAprobacion) {
        this.revisionAprobacion = revisionAprobacion;
    }

    public String getAceptacionTransferenciaDatos() {
        return this.aceptacionTransferenciaDatos;
    }

    public SolicitudCredito aceptacionTransferenciaDatos(String aceptacionTransferenciaDatos) {
        this.setAceptacionTransferenciaDatos(aceptacionTransferenciaDatos);
        return this;
    }

    public void setAceptacionTransferenciaDatos(String aceptacionTransferenciaDatos) {
        this.aceptacionTransferenciaDatos = aceptacionTransferenciaDatos;
    }

    public String getAceptacionAcuerdos() {
        return this.aceptacionAcuerdos;
    }

    public SolicitudCredito aceptacionAcuerdos(String aceptacionAcuerdos) {
        this.setAceptacionAcuerdos(aceptacionAcuerdos);
        return this;
    }

    public void setAceptacionAcuerdos(String aceptacionAcuerdos) {
        this.aceptacionAcuerdos = aceptacionAcuerdos;
    }

    public String getMonto() {
        return this.monto;
    }

    public SolicitudCredito monto(String monto) {
        this.setMonto(monto);
        return this;
    }

    public void setMonto(String monto) {
        this.monto = monto;
    }

    public String getPlazo() {
        return this.plazo;
    }

    public SolicitudCredito plazo(String plazo) {
        this.setPlazo(plazo);
        return this;
    }

    public void setPlazo(String plazo) {
        this.plazo = plazo;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof SolicitudCredito)) {
            return false;
        }
        return id != null && id.equals(((SolicitudCredito) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "SolicitudCredito{" +
            "id=" + getId() +
            ", noCliente='" + getNoCliente() + "'" +
            ", idSolicitud='" + getIdSolicitud() + "'" +
            ", motivoSolicitud='" + getMotivoSolicitud() + "'" +
            ", numerodeCuenta='" + getNumerodeCuenta() + "'" +
            ", revisionAprobacion='" + getRevisionAprobacion() + "'" +
            ", aceptacionTransferenciaDatos='" + getAceptacionTransferenciaDatos() + "'" +
            ", aceptacionAcuerdos='" + getAceptacionAcuerdos() + "'" +
            ", monto='" + getMonto() + "'" +
            ", plazo='" + getPlazo() + "'" +
            "}";
    }
}
