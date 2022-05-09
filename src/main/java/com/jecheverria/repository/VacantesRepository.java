package com.jecheverria.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jecheverria.model.Vacante;

public interface VacantesRepository extends JpaRepository<Vacante, Integer> {
	List<Vacante> findByEstatus(String estatus);

	List<Vacante> findByDestacadoAndEstatusOrderByIdDesc(Integer destacado, String estatus);

	List<Vacante> findBySalarioBetweenOrderBySalarioDesc(Double s1, Double s2);

	List<Vacante> findByEstatusIn(String[] estatus);

}
