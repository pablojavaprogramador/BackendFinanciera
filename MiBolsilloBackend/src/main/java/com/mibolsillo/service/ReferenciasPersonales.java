package com.mibolsillo.service;

import java.util.List;
import java.util.Optional;

import com.mibolsillo.model.Domicilio;
import com.mibolsillo.model.ReferenciaPersonales;
import com.mibolsillo.model.RespuestaOk;




public interface ReferenciasPersonales {

	
	ReferenciaPersonales findById(Long id);



	RespuestaOk save(ReferenciaPersonales referencia);



	void delete(ReferenciaPersonales referenciasPersonales);



	List<ReferenciaPersonales> findAll();



	RespuestaOk saveAll(List<ReferenciaPersonales> listaReferencia);



	void deleteAll();



	boolean existsById(Long id);



	RespuestaOk actualizar(ReferenciaPersonales referencia);
	

	

}
