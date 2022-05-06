package com.jecheverria.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jecheverria.model.Categoria;

public interface CategoriasRepository extends JpaRepository<Categoria, Integer> {

}
