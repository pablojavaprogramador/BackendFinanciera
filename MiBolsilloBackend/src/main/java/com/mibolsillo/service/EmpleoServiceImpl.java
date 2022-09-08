package com.mibolsillo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mibolsillo.model.Domicilio;
import com.mibolsillo.model.Empleos;
import com.mibolsillo.model.RespuestaOk;
import com.mibolsillo.repository.EmpleosRepository;



@Service
public class EmpleoServiceImpl  implements EmpleoService{

	@Autowired
	EmpleosRepository repositoryEmpleo;
	
	@Override
	public Empleos findById(Long id) {
		Empleos empleos=repositoryEmpleo.findById(id).orElse(new Empleos());
		return empleos;
	}

	@Override
	public RespuestaOk save(Empleos empleo) {
		
		try {
			repositoryEmpleo.save(empleo);
			}catch(Exception e){
				
			}
			RespuestaOk respuesta = new RespuestaOk();
			respuesta.setEstatus("OK");
			respuesta.setMensaje("Empleo Guardado Correctamente");
		
		return respuesta;
	}
	
	@Override
	public RespuestaOk actualizar(Empleos empleo) {
		
		try {
			repositoryEmpleo.save(empleo);
			}catch(Exception e){
				
			}
			RespuestaOk respuesta = new RespuestaOk();
			respuesta.setEstatus("OK");
			respuesta.setMensaje("Empleo Actualizado Correctamente");
		
		return respuesta;
	}

	@Override
	public List<Empleos> findAll() {
		List<Empleos> empleos = repositoryEmpleo.findAll();
		return empleos;
	}

	@Override
	public void deleteAll() {
		repositoryEmpleo.deleteAll();

		
	}

	@Override
	public void saveAll(List<Empleos> listEmpleos) {
		repositoryEmpleo.saveAll(listEmpleos);
		
	}

	@Override
	public void delete(Empleos empleos) {
	repositoryEmpleo.delete(empleos);
		
	}

	@Override
	public boolean existsById(Long id) {
		boolean existeEmpleo = repositoryEmpleo.existsById(id);
		if(existeEmpleo==true) {
			return true;
		}
		return false;
	}

}
