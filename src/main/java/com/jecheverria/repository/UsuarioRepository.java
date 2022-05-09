package com.jecheverria.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jecheverria.model.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {

}
