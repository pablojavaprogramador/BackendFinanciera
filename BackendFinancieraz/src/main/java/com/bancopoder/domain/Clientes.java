package com.bancopoder.domain;

import java.io.Serializable;
import javax.persistence.*;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A Clientes.
 */
@Entity
@Table(name = "clientes")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Clientes implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "nombre")
    private String nombre;

    @Column(name = "apellido_paterno")
    private String apellidoPaterno;

    @Column(name = "apellido_materno")
    private String apellidoMaterno;

    @Column(name = "fecha_nacimiento")
    private String fechaNacimiento;

    @Column(name = "identificacion_ocr")
    private String identificacionOCR;

    @Column(name = "clave_elector")
    private String claveElector;

    @Column(name = "telefono")
    private String telefono;

    @Column(name = "celular")
    private String celular;

    @Column(name = "correo_electronico")
    private String correoElectronico;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public Clientes id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return this.nombre;
    }

    public Clientes nombre(String nombre) {
        this.setNombre(nombre);
        return this;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidoPaterno() {
        return this.apellidoPaterno;
    }

    public Clientes apellidoPaterno(String apellidoPaterno) {
        this.setApellidoPaterno(apellidoPaterno);
        return this;
    }

    public void setApellidoPaterno(String apellidoPaterno) {
        this.apellidoPaterno = apellidoPaterno;
    }

    public String getApellidoMaterno() {
        return this.apellidoMaterno;
    }

    public Clientes apellidoMaterno(String apellidoMaterno) {
        this.setApellidoMaterno(apellidoMaterno);
        return this;
    }

    public void setApellidoMaterno(String apellidoMaterno) {
        this.apellidoMaterno = apellidoMaterno;
    }

    public String getFechaNacimiento() {
        return this.fechaNacimiento;
    }

    public Clientes fechaNacimiento(String fechaNacimiento) {
        this.setFechaNacimiento(fechaNacimiento);
        return this;
    }

    public void setFechaNacimiento(String fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public String getIdentificacionOCR() {
        return this.identificacionOCR;
    }

    public Clientes identificacionOCR(String identificacionOCR) {
        this.setIdentificacionOCR(identificacionOCR);
        return this;
    }

    public void setIdentificacionOCR(String identificacionOCR) {
        this.identificacionOCR = identificacionOCR;
    }

    public String getClaveElector() {
        return this.claveElector;
    }

    public Clientes claveElector(String claveElector) {
        this.setClaveElector(claveElector);
        return this;
    }

    public void setClaveElector(String claveElector) {
        this.claveElector = claveElector;
    }

    public String getTelefono() {
        return this.telefono;
    }

    public Clientes telefono(String telefono) {
        this.setTelefono(telefono);
        return this;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getCelular() {
        return this.celular;
    }

    public Clientes celular(String celular) {
        this.setCelular(celular);
        return this;
    }

    public void setCelular(String celular) {
        this.celular = celular;
    }

    public String getCorreoElectronico() {
        return this.correoElectronico;
    }

    public Clientes correoElectronico(String correoElectronico) {
        this.setCorreoElectronico(correoElectronico);
        return this;
    }

    public void setCorreoElectronico(String correoElectronico) {
        this.correoElectronico = correoElectronico;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Clientes)) {
            return false;
        }
        return id != null && id.equals(((Clientes) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Clientes{" +
            "id=" + getId() +
            ", nombre='" + getNombre() + "'" +
            ", apellidoPaterno='" + getApellidoPaterno() + "'" +
            ", apellidoMaterno='" + getApellidoMaterno() + "'" +
            ", fechaNacimiento='" + getFechaNacimiento() + "'" +
            ", identificacionOCR='" + getIdentificacionOCR() + "'" +
            ", claveElector='" + getClaveElector() + "'" +
            ", telefono='" + getTelefono() + "'" +
            ", celular='" + getCelular() + "'" +
            ", correoElectronico='" + getCorreoElectronico() + "'" +
            "}";
    }
}
