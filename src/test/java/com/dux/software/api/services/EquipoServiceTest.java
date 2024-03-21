package com.dux.software.api.services;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.SpringBootConfiguration;

import com.dux.software.api.models.Equipo;
import com.dux.software.api.repositories.IEquipoRepository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@SpringBootConfiguration
class EquipoServiceTest {
	
	@Mock
	private IEquipoRepository iEquipoRepository;
	
	@InjectMocks
	private EquipoService equipoService;
	
	private Equipo mockEquipo = new Equipo(1, "Real Madrid", "La Liga", "España");	
	
	private List<Equipo> equipos = new ArrayList<>();

	

	void cargaMocks() {
		equipos.add(new Equipo(1, "Real Madrid", "La Liga", "España"));
		equipos.add(new Equipo(2, "FC Barcelona", "La Liga", "España"));
		equipos.add(new Equipo(3, "Manchester United", "Premier League", "Inglaterra"));
		equipos.add(new Equipo(4, "Liverpool FC", "Premier League", "Inglaterra"));
		equipos.add(new Equipo(5, "Juventus FC", "Serie A", "Italia"));
		equipos.add(new Equipo(6, "AC Milan", "Serie A", "Italia"));
		equipos.add(new Equipo(7, "Bayern Munich", "Bundesliga", "Alemania"));
		equipos.add(new Equipo(8, "Borussia Dortmund", "Bundesliga", "Alemania"));
		equipos.add(new Equipo(9, "Paris Saint-Germain", "Ligue 1", "Francia"));
		equipos.add(new Equipo(10, "Olympique de Marseille", "Ligue 1", "Francia"));
		equipos.add(new Equipo(11, "FC Porto", "Primeira Liga", "Portugal"));
		equipos.add(new Equipo(12, "Sporting CP", "Primeira Liga", "Portugal"));
		equipos.add(new Equipo(13, "Ajax Amsterdam", "Eredivisie", "Países Bajos"));
		equipos.add(new Equipo(14, "Feyenoord", "Eredivisie", "Países Bajos"));
		equipos.add(new Equipo(15, "Celtic FC", "Scottish Premiership", "Escocia"));
		equipos.add(new Equipo(16, "Rangers FC", "Scottish Premiership", "Escocia"));
		equipos.add(new Equipo(17, "Galatasaray SK", "Süper Lig", "Turquía"));
		equipos.add(new Equipo(18, "Fenerbahçe SK", "Süper Lig", "Turquía"));
		equipos.add(new Equipo(19, "FC Zenit Saint Petersburg", "Premier League Rusa", "Rusia"));
		equipos.add(new Equipo(20, "Spartak Moscow", "Premier League Rusa", "Rusia"));
		equipos.add(new Equipo(21, "SL Benfica", "Primeira Liga", "Portugal"));
		equipos.add(new Equipo(22, "Besiktas JK", "Süper Lig", "Turquía"));
		equipos.add(new Equipo(23, "SSC Napoli", "Serie A", "Italia"));
		equipos.add(new Equipo(24, "Atlético Madrid", "La Liga", "España"));
	}

	@Test
	void testCrearEquipo() {
		//createEquipo
		when(iEquipoRepository.save(mockEquipo)).thenReturn(mockEquipo);		
		Equipo mockEquipoCreado = equipoService.createEquipo(mockEquipo);		
		assertNotNull(mockEquipoCreado);
        assertEquals(mockEquipo, mockEquipoCreado);
	}
	
	@Test
	void testUpdateEquipo() {
		//updateEquipo
        when(iEquipoRepository.save(mockEquipo)).thenReturn(mockEquipo);        
		Equipo mockEquipoMod = equipoService.updateEquipo(mockEquipo);		
		assertNotNull(mockEquipoMod);
        assertEquals(mockEquipo, mockEquipoMod);
	}
	
	@Test
	void testFindById() {
		//findById
		when(iEquipoRepository.findById(99)).thenReturn(Optional.of(mockEquipo));		
		Optional<Equipo> optionalEquipo = equipoService.findById(99);		
		assertTrue(optionalEquipo.isPresent());			
		Equipo equipo = optionalEquipo.get();		
		assertNotNull(equipo);
		assertEquals(1, equipo.getId());
		assertEquals("Real Madrid", equipo.getNombre());
		assertEquals("La Liga", equipo.getLiga());
		assertEquals("España", equipo.getPais());
	}
	
    @Test
    void testFindById_EquipoNoEncontrado() {
        when(iEquipoRepository.findById(99)).thenReturn(Optional.empty());
        Optional<Equipo> optionalEquipo = equipoService.findById(99);
        assertFalse(optionalEquipo.isPresent());
    }
	
	@Test
	void testFindEquipos() {
		//findEquipos	
		this.cargaMocks();
		when(iEquipoRepository.findAll()).thenReturn(equipos);		
		List<Equipo> listaEquiposRetornados = equipoService.findEquipos();
		assertNotNull(listaEquiposRetornados);
		assertTrue(!listaEquiposRetornados.isEmpty());
		assertTrue(listaEquiposRetornados.size()>0);	
	}
	
	
	@Test
	void testFindByNombre() {
		//findByNombre
		when(iEquipoRepository.findByNombre("Real Madrid")).thenReturn(Optional.of(mockEquipo));		
		Optional<Equipo> optionalEquipo = equipoService.findByNombre("Real Madrid");		
		assertTrue(optionalEquipo.isPresent());			
		Equipo equipo = optionalEquipo.get();		
		assertNotNull(equipo);
		assertEquals(1, equipo.getId());
		assertEquals("Real Madrid", equipo.getNombre());
		assertEquals("La Liga", equipo.getLiga());
		assertEquals("España", equipo.getPais());
	}
	
    @Test
    void testFindByNombre_EquipoNoEncontrado() {
        when(iEquipoRepository.findByNombre("Nombre")).thenReturn(Optional.empty());
        Optional<Equipo> optionalEquipo = equipoService.findByNombre("Nombre");
        assertFalse(optionalEquipo.isPresent());
    }
	
	@Test
	void testFindByLiga() {
		//findByLiga
		this.cargaMocks();
		when(equipoService.findByLiga("La Liga")).thenReturn(equipos);		
		List<Equipo> listaEquiposRetornados = equipoService.findByLiga("La Liga");
		assertNotNull(listaEquiposRetornados);
		assertTrue(!listaEquiposRetornados.isEmpty());
		assertTrue(listaEquiposRetornados.size()>0);
	}
	
   @Test
    void testFindByLiga_LigaNoEncontrado() {
		this.cargaMocks();
        when(iEquipoRepository.findByLiga("Liga Liga")).thenReturn(new ArrayList<>());
        List<Equipo> listEquipo = equipoService.findByLiga("Liga Liga");
        assertTrue(listEquipo.isEmpty());
    }
	
	@Test
	void testFindByPais() {
		//findByPais
		this.cargaMocks();
		when(iEquipoRepository.findByPais("España")).thenReturn(equipos);		
		List<Equipo> listaEquiposRetornados = equipoService.findByPais("España");
		assertNotNull(listaEquiposRetornados);
		assertTrue(!listaEquiposRetornados.isEmpty());
		assertTrue(listaEquiposRetornados.size()>0);
	}
	
   @Test
    void testFindByPais_PaisNoEncontrado() {
	   	this.cargaMocks();
        when(iEquipoRepository.findByPais("Pais Pais")).thenReturn(new ArrayList<>());
        List<Equipo> listEquipo = equipoService.findByPais("Pais Pais");
        assertTrue(listEquipo.isEmpty());
    }
	
	@Test
	void testDeleteEquipoPorId() {
		//deleteEquipoPorId
		equipoService.deleteEquipoPorId(1);
	    verify(iEquipoRepository, times(1)).deleteById(1);	
	}
}
