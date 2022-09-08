package com.mibolsillo.service;

import java.util.List;
import java.util.Optional;

import com.mibolsillo.model.Bitcoin;
import com.mibolsillo.model.Carrito;
import com.mibolsillo.model.Cupones;
import com.mibolsillo.model.Domicilio;
import com.mibolsillo.model.PagodeServicios;
import com.mibolsillo.model.ReferenciaPersonales;
import com.mibolsillo.model.RespuestaOk;
import com.mibolsillo.model.SolicitudCredito;
import com.mibolsillo.model.TarjetasdeRegalo;


public interface CarritoService {
	Carrito findById(Long id);
	RespuestaOk save(Carrito carrito);
	void delete(Carrito bitcoin);
	List<Carrito> findAll();
	RespuestaOk saveAll(List<Carrito> carrito);
	void deleteAll();
	boolean existsById(Long id);
	RespuestaOk actualizar(Carrito carrito);

	
}
