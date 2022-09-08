package com.mibolsillo.service;

import java.util.List;
import java.util.Optional;

import com.mibolsillo.model.Bitcoin;
import com.mibolsillo.model.Cupones;
import com.mibolsillo.model.Domicilio;
import com.mibolsillo.model.PagodeServicios;
import com.mibolsillo.model.Producto;
import com.mibolsillo.model.ReferenciaPersonales;
import com.mibolsillo.model.RespuestaOk;
import com.mibolsillo.model.SolicitudCredito;
import com.mibolsillo.model.TarjetasdeRegalo;


public interface ProductoService {
	Producto findById(Long id);
	RespuestaOk save(Producto producto);
	void delete(Producto producto);
	List<Producto> findAll();
	RespuestaOk saveAll(List<Producto> producto);
	void deleteAll();
	boolean existsById(Long id);
	RespuestaOk actualizar(Producto producto);

	
}
