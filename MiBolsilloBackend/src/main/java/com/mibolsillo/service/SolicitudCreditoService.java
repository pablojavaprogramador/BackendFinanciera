package com.mibolsillo.service;

import java.util.List;
import java.util.Optional;
import com.mibolsillo.model.Domicilio;
import com.mibolsillo.model.ReferenciaPersonales;
import com.mibolsillo.model.RespuestaOk;
import com.mibolsillo.model.SolicitudCredito;
import com.mibolsillo.model.TarjetasdeRegalo;


public interface SolicitudCreditoService {
	SolicitudCredito findById(Long id);
	RespuestaOk save(SolicitudCredito solicitudCredito);
	void delete(SolicitudCredito solicitudCredito);
	List<SolicitudCredito> findAll();
	RespuestaOk saveAll(List<SolicitudCredito> solicitudCredito);
	void deleteAll();
	boolean existsById(Long id);
	RespuestaOk actualizar(SolicitudCredito solicitudCredito);

	
}
