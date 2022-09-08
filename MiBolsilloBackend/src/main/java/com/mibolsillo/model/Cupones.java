package com.mibolsillo.model;

import java.io.Serializable;
import javax.persistence.*;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A Cupones.
 */
@Entity
@Table(name = "cupones")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@SuppressWarnings("common-java:DuplicatedBlocks")
public class Cupones implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "nombrecupon")
    private String nombrecupon;

    @Column(name = "promocion")
    private String promocion;

    @Column(name = "imagen")
    private String imagen;

    @Column(name = "ubicacion")
    private String ubicacion;

    @Column(name = "restricciones")
    private String restricciones;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public Cupones id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombrecupon() {
        return this.nombrecupon;
    }

    public Cupones nombrecupon(String nombrecupon) {
        this.setNombrecupon(nombrecupon);
        return this;
    }

    public void setNombrecupon(String nombrecupon) {
        this.nombrecupon = nombrecupon;
    }

    public String getPromocion() {
        return this.promocion;
    }

    public Cupones promocion(String promocion) {
        this.setPromocion(promocion);
        return this;
    }

    public void setPromocion(String promocion) {
        this.promocion = promocion;
    }

    public String getImagen() {
        return this.imagen;
    }

    public Cupones imagen(String imagen) {
        this.setImagen(imagen);
        return this;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    public String getUbicacion() {
        return this.ubicacion;
    }

    public Cupones ubicacion(String ubicacion) {
        this.setUbicacion(ubicacion);
        return this;
    }

    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }

    public String getRestricciones() {
        return this.restricciones;
    }

    public Cupones restricciones(String restricciones) {
        this.setRestricciones(restricciones);
        return this;
    }

    public void setRestricciones(String restricciones) {
        this.restricciones = restricciones;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Cupones)) {
            return false;
        }
        return id != null && id.equals(((Cupones) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Cupones{" +
            "id=" + getId() +
            ", nombrecupon='" + getNombrecupon() + "'" +
            ", promocion='" + getPromocion() + "'" +
            ", imagen='" + getImagen() + "'" +
            ", ubicacion='" + getUbicacion() + "'" +
            ", restricciones='" + getRestricciones() + "'" +
            "}";
    }
}
