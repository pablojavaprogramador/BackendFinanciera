package com.mibolsillo.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.*;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.springframework.data.domain.Persistable;

/**
 * A Carrito.
 */
@JsonIgnoreProperties(value = { "new" })
@Entity
@Table(name = "carrito")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@SuppressWarnings("common-java:DuplicatedBlocks")
public class Carrito implements Serializable, Persistable<String> {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "id")
    private String id;

    @Column(name = "cantidadproductos")
    private Integer cantidadproductos;

    @Column(name = "productos")
    private String productos;

    @Column(name = "precio_total")
    private Integer precioTotal;

    @Transient
    private boolean isPersisted;

    @ManyToOne
    @JsonIgnoreProperties(value = { "ids" }, allowSetters = true)
    private Producto idCarrito;

    @OneToMany(mappedBy = "idCarrito")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    @JsonIgnoreProperties(value = { "compras", "idCarrito" }, allowSetters = true)
    private Set<Cliente> ids = new HashSet<>();

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Integer getCantidadproductos() {
		return cantidadproductos;
	}

	public void setCantidadproductos(Integer cantidadproductos) {
		this.cantidadproductos = cantidadproductos;
	}

	public String getProductos() {
		return productos;
	}

	public void setProductos(String productos) {
		this.productos = productos;
	}

	public Integer getPrecioTotal() {
		return precioTotal;
	}

	public void setPrecioTotal(Integer precioTotal) {
		this.precioTotal = precioTotal;
	}

	public boolean isPersisted() {
		return isPersisted;
	}

	public void setPersisted(boolean isPersisted) {
		this.isPersisted = isPersisted;
	}

	public Producto getIdCarrito() {
		return idCarrito;
	}

	public void setIdCarrito(Producto idCarrito) {
		this.idCarrito = idCarrito;
	}

	public Set<Cliente> getIds() {
		return ids;
	}

	public void setIds(Set<Cliente> ids) {
		this.ids = ids;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public boolean isNew() {
		// TODO Auto-generated method stub
		return false;
	}


}
