package com.dux.software.api.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.dux.software.api.models.Equipo;


@Repository
public interface IEquipoRepository extends JpaRepository<Equipo, Integer>{
	
	Optional<Equipo> findById(Integer id);
		
	Optional<Equipo> findByNombre(String nombre);
	
	List<Equipo> findByLiga(String liga);
	
	List<Equipo> findByPais(String pais);
	
}
