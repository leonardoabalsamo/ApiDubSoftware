package com.dux.software.api.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dux.software.api.models.Equipo;
import com.dux.software.api.repositories.IEquipoRepository;

@Service
public class EquipoService {
	
	private IEquipoRepository iEquipoRepository;

	@Autowired
	public EquipoService(IEquipoRepository iEquipoRepository) {
		this.iEquipoRepository = iEquipoRepository;
	}
	
	public Equipo createEquipo(Equipo equipo) throws IllegalArgumentException {
		return iEquipoRepository.save(equipo);
	}

	public Equipo updateEquipo(Equipo equipo) {
		return iEquipoRepository.save(equipo);
	}
	
	public Optional<Equipo> findById(Integer id){
		return iEquipoRepository.findById(id);
	}
	
	public List<Equipo> findEquipos(){
		return iEquipoRepository.findAll();
	}
	
	public Optional<Equipo> findByNombre(String nombre){
		return iEquipoRepository.findByNombre(nombre);
	}
	
	public List<Equipo> findByLiga(String liga){
		return iEquipoRepository.findByLiga(liga);
	}
	
	public List<Equipo> findByPais(String pais)
	{
		return iEquipoRepository.findByPais(pais);
	}	
	
	public void deleteEquipoPorId(Integer id) {
		iEquipoRepository.deleteById(id);
	}

}
