package com.mibolsillo.service;

import java.util.List;
import java.util.Optional;

import com.mibolsillo.model.Cupones;
import com.mibolsillo.model.Domicilio;
import com.mibolsillo.model.PagodeServicios;
import com.mibolsillo.model.ReferenciaPersonales;
import com.mibolsillo.model.RespuestaOk;
import com.mibolsillo.model.SolicitudCredito;
import com.mibolsillo.model.TarjetasdeRegalo;


public interface CuponesService {
	Cupones findById(Long id);
	RespuestaOk save(Cupones cupones);
	void delete(Cupones cupones);
	List<Cupones> findAll();
	RespuestaOk saveAll(List<Cupones> cupones);
	void deleteAll();
	boolean existsById(Long id);
	RespuestaOk actualizar(Cupones cupones);

	
}
