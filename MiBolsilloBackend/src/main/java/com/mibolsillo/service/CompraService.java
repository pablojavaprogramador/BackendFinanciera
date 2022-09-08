package com.mibolsillo.service;

import java.util.List;
import java.util.Optional;

import com.mibolsillo.model.Bitcoin;
import com.mibolsillo.model.Compra;
import com.mibolsillo.model.Cupones;
import com.mibolsillo.model.Domicilio;
import com.mibolsillo.model.PagodeServicios;
import com.mibolsillo.model.ReferenciaPersonales;
import com.mibolsillo.model.RespuestaOk;
import com.mibolsillo.model.SolicitudCredito;
import com.mibolsillo.model.TarjetasdeRegalo;


public interface CompraService {
	Compra findById(Long id);
	RespuestaOk save(Compra compra);
	void delete(Compra compra);
	List<Compra> findAll();
	RespuestaOk saveAll(List<Compra> compra);
	void deleteAll();
	boolean existsById(Long id);
	RespuestaOk actualizar(Compra compra);

	
}
