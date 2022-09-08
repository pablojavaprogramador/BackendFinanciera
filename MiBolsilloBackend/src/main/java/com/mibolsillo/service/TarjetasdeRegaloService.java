package com.mibolsillo.service;

import java.util.List;
import java.util.Optional;
import com.mibolsillo.model.Domicilio;
import com.mibolsillo.model.ReferenciaPersonales;
import com.mibolsillo.model.RespuestaOk;
import com.mibolsillo.model.TarjetasdeRegalo;


public interface TarjetasdeRegaloService {
	TarjetasdeRegalo findById(Long id);
	RespuestaOk save(TarjetasdeRegalo tarjetasdeRegalo);
	void delete(TarjetasdeRegalo tarjetasdeRegalo);
	List<TarjetasdeRegalo> findAll();
	RespuestaOk saveAll(List<TarjetasdeRegalo> tarjetasdeRegalo);
	void deleteAll();
	boolean existsById(Long id);
	RespuestaOk actualizar(TarjetasdeRegalo tarjetasdeRegalo);

	
}
