package com.mibolsillo.service;

import java.util.List;
import java.util.Optional;

import com.mibolsillo.model.Bitcoin;
import com.mibolsillo.model.Cupones;
import com.mibolsillo.model.Domicilio;
import com.mibolsillo.model.PagodeServicios;
import com.mibolsillo.model.ReferenciaPersonales;
import com.mibolsillo.model.RespuestaOk;
import com.mibolsillo.model.SolicitudCredito;
import com.mibolsillo.model.TarjetasdeRegalo;


public interface PagosService {
	PagodeServicios findById(Long id);
	RespuestaOk save(PagodeServicios pagodeServicios);
	void delete(PagodeServicios pagodeServicios);
	List<PagodeServicios> findAll();
	RespuestaOk saveAll(List<PagodeServicios> pagodeServicios);
	void deleteAll();
	boolean existsById(Long id);
	RespuestaOk actualizar(PagodeServicios pagodeServicios);

	
}
