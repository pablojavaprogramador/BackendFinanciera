package com.mibolsillo.model;

import java.io.Serializable;
import javax.persistence.*;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A Empleos.
 */
@Entity
@Table(name = "empleos")
public class Empleos implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "ingresos_comprobables")
    private String ingresosComprobables;

    @Column(name = "cargo_publico")
    private String cargoPublico;

    @Column(name = "familiar_cargo_publico")
    private String familiarCargoPublico;

    @Column(name = "calle")
    private String calle;

    @Column(name = "numero_exterior")
    private String numeroExterior;

    @Column(name = "codigo_postal")
    private String codigoPostal;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public Empleos id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getIngresosComprobables() {
        return this.ingresosComprobables;
    }

    public Empleos ingresosComprobables(String ingresosComprobables) {
        this.setIngresosComprobables(ingresosComprobables);
        return this;
    }

    public void setIngresosComprobables(String ingresosComprobables) {
        this.ingresosComprobables = ingresosComprobables;
    }

    public String getCargoPublico() {
        return this.cargoPublico;
    }

    public Empleos cargoPublico(String cargoPublico) {
        this.setCargoPublico(cargoPublico);
        return this;
    }

    public void setCargoPublico(String cargoPublico) {
        this.cargoPublico = cargoPublico;
    }

    public String getFamiliarCargoPublico() {
        return this.familiarCargoPublico;
    }

    public Empleos familiarCargoPublico(String familiarCargoPublico) {
        this.setFamiliarCargoPublico(familiarCargoPublico);
        return this;
    }

    public void setFamiliarCargoPublico(String familiarCargoPublico) {
        this.familiarCargoPublico = familiarCargoPublico;
    }

    public String getCalle() {
        return this.calle;
    }

    public Empleos calle(String calle) {
        this.setCalle(calle);
        return this;
    }

    public void setCalle(String calle) {
        this.calle = calle;
    }

    public String getNumeroExterior() {
        return this.numeroExterior;
    }

    public Empleos numeroExterior(String numeroExterior) {
        this.setNumeroExterior(numeroExterior);
        return this;
    }

    public void setNumeroExterior(String numeroExterior) {
        this.numeroExterior = numeroExterior;
    }

    public String getCodigoPostal() {
        return this.codigoPostal;
    }

    public Empleos codigoPostal(String codigoPostal) {
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
        if (!(o instanceof Empleos)) {
            return false;
        }
        return id != null && id.equals(((Empleos) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Empleos{" +
            "id=" + getId() +
            ", ingresosComprobables='" + getIngresosComprobables() + "'" +
            ", cargoPublico='" + getCargoPublico() + "'" +
            ", familiarCargoPublico='" + getFamiliarCargoPublico() + "'" +
            ", calle='" + getCalle() + "'" +
            ", numeroExterior='" + getNumeroExterior() + "'" +
            ", codigoPostal='" + getCodigoPostal() + "'" +
            "}";
    }
}
