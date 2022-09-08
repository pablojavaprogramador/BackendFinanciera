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


public interface BitcoinService {
	Bitcoin findById(Long id);
	RespuestaOk save(Bitcoin bitcoin);
	void delete(Bitcoin bitcoin);
	List<Bitcoin> findAll();
	RespuestaOk saveAll(List<Bitcoin> bitcoin);
	void deleteAll();
	boolean existsById(Long id);
	RespuestaOk actualizar(Bitcoin bitcoin);

	
}
