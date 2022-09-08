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
 * A Producto.
 */
@JsonIgnoreProperties(value = { "new" })
@Entity
@Table(name = "producto")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@SuppressWarnings("common-java:DuplicatedBlocks")
public class Producto implements Serializable, Persistable<String> {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "id")
    private String id;

    @Column(name = "producto")
    private String producto;

    @Column(name = "descripcion")
    private String descripcion;

    @Column(name = "precio")
    private Integer precio;

    @Column(name = "stock")
    private Integer stock;

    @Column(name = "categoria")
    private String categoria;

    @Column(name = "cantidad_carrito")
    private Integer cantidadCarrito;

    @Transient
    private boolean isPersisted;

    @OneToMany(mappedBy = "idCarrito")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    @JsonIgnoreProperties(value = { "idCarrito", "ids" }, allowSetters = true)
    private Set<Carrito> ids = new HashSet<>();

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getProducto() {
		return producto;
	}

	public void setProducto(String producto) {
		this.producto = producto;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public Integer getPrecio() {
		return precio;
	}

	public void setPrecio(Integer precio) {
		this.precio = precio;
	}

	public Integer getStock() {
		return stock;
	}

	public void setStock(Integer stock) {
		this.stock = stock;
	}

	public String getCategoria() {
		return categoria;
	}

	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}

	public Integer getCantidadCarrito() {
		return cantidadCarrito;
	}

	public void setCantidadCarrito(Integer cantidadCarrito) {
		this.cantidadCarrito = cantidadCarrito;
	}

	public boolean isPersisted() {
		return isPersisted;
	}

	public void setPersisted(boolean isPersisted) {
		this.isPersisted = isPersisted;
	}

	public Set<Carrito> getIds() {
		return ids;
	}

	public void setIds(Set<Carrito> ids) {
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
