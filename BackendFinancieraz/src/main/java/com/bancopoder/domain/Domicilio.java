package com.bancopoder.domain;

import java.io.Serializable;
import javax.persistence.*;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A Domicilio.
 */
@Entity
@Table(name = "domicilio")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Domicilio implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
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

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public Domicilio id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCalle() {
        return this.calle;
    }

    public Domicilio calle(String calle) {
        this.setCalle(calle);
        return this;
    }

    public void setCalle(String calle) {
        this.calle = calle;
    }

    public String getNumeroExterior() {
        return this.numeroExterior;
    }

    public Domicilio numeroExterior(String numeroExterior) {
        this.setNumeroExterior(numeroExterior);
        return this;
    }

    public void setNumeroExterior(String numeroExterior) {
        this.numeroExterior = numeroExterior;
    }

    public String getNumeroInterior() {
        return this.numeroInterior;
    }

    public Domicilio numeroInterior(String numeroInterior) {
        this.setNumeroInterior(numeroInterior);
        return this;
    }

    public void setNumeroInterior(String numeroInterior) {
        this.numeroInterior = numeroInterior;
    }

    public String getCodePostal() {
        return this.codePostal;
    }

    public Domicilio codePostal(String codePostal) {
        this.setCodePostal(codePostal);
        return this;
    }

    public void setCodePostal(String codePostal) {
        this.codePostal = codePostal;
    }

    public String getDomicilioActual() {
        return this.domicilioActual;
    }

    public Domicilio domicilioActual(String domicilioActual) {
        this.setDomicilioActual(domicilioActual);
        return this;
    }

    public void setDomicilioActual(String domicilioActual) {
        this.domicilioActual = domicilioActual;
    }

    public String getManzana() {
        return this.manzana;
    }

    public Domicilio manzana(String manzana) {
        this.setManzana(manzana);
        return this;
    }

    public void setManzana(String manzana) {
        this.manzana = manzana;
    }

    public String getAndador() {
        return this.andador;
    }

    public Domicilio andador(String andador) {
        this.setAndador(andador);
        return this;
    }

    public void setAndador(String andador) {
        this.andador = andador;
    }

    public String getEdificio() {
        return this.edificio;
    }

    public Domicilio edificio(String edificio) {
        this.setEdificio(edificio);
        return this;
    }

    public void setEdificio(String edificio) {
        this.edificio = edificio;
    }

    public String getFechaAntiguedad() {
        return this.fechaAntiguedad;
    }

    public Domicilio fechaAntiguedad(String fechaAntiguedad) {
        this.setFechaAntiguedad(fechaAntiguedad);
        return this;
    }

    public void setFechaAntiguedad(String fechaAntiguedad) {
        this.fechaAntiguedad = fechaAntiguedad;
    }

    public String getCodigoPostal() {
        return this.codigoPostal;
    }

    public Domicilio codigoPostal(String codigoPostal) {
        this.setCodigoPostal(codigoPostal);
        return this;
    }

    public void setCodigoPostal(String codigoPostal) {
        this.codigoPostal = codigoPostal;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Domicilio)) {
            return false;
        }
        return id != null && id.equals(((Domicilio) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Domicilio{" +
            "id=" + getId() +
            ", calle='" + getCalle() + "'" +
            ", numeroExterior='" + getNumeroExterior() + "'" +
            ", numeroInterior='" + getNumeroInterior() + "'" +
            ", codePostal='" + getCodePostal() + "'" +
            ", domicilioActual='" + getDomicilioActual() + "'" +
            ", manzana='" + getManzana() + "'" +
            ", andador='" + getAndador() + "'" +
            ", edificio='" + getEdificio() + "'" +
            ", fechaAntiguedad='" + getFechaAntiguedad() + "'" +
            ", codigoPostal='" + getCodigoPostal() + "'" +
            "}";
    }
}
