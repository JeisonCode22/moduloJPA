package com.jecheverria;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.jecheverria.model.Categoria;
import com.jecheverria.repository.CategoriasRepository;

@SpringBootApplication
public class JpaDemoApplication implements CommandLineRunner {

	@Autowired
	private CategoriasRepository repo;

	public static void main(String[] args) {
		SpringApplication.run(JpaDemoApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		buscarPorId();
	}

	private void modificar() {
		Optional<Categoria> optional = repo.findById(1);
		if (optional.isPresent()) {
			Categoria catTmp = optional.get();
			catTmp.setNombre("Ing. de software");
			catTmp.setDescripcion("Desarrollo de sistemas");
			repo.save(catTmp);
			System.out.println(optional.get());
		} else {
			System.out.println("Categoria NO encontrada");
		}
	}

	private void buscarPorId() {
		Optional<Categoria> optional = repo.findById(1);
		if (optional.isPresent()) {
			System.out.println(optional.get());
		} else {
			System.out.println("Categoria NO encontrada");
		}
	}

	private void guardar() {
		Categoria cat = new Categoria();
		cat.setNombre("Finanzas");
		cat.setDescripcion("Trabajo relacionado con finanzas y contabilidad");
		repo.save(cat);
	}

	private void eliminar() {

	}

}
