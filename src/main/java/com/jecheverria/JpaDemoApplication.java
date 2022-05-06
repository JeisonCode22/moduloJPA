package com.jecheverria;

import java.util.LinkedList;
import java.util.List;
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
		guardarTodas();
	}

	private void guardarTodas() {
		List<Categoria> categorias = getlistaCategorias();
		repo.saveAll(categorias);
	}

	private void existeId() {
		boolean existe = repo.existsById(1);
		System.out.println("La categoria existe: " + existe);
	}

	private void buscarTodos() {
		Iterable<Categoria> categorias = repo.findAll();
		for (Categoria c : categorias) {
			System.out.println(c);
		}
	}

	private void encontrarPorIds() {
		List<Integer> ids = new LinkedList<Integer>();
		ids.add(1);
		ids.add(4);
		ids.add(10);
		Iterable<Categoria> categoria = repo.findAllById(ids);
		for (Categoria c : categoria) {
			System.out.println(c);
		}
	}

	private void eliminarTodos() {
		repo.deleteAll();
	}

	private void conteo() {
		long count = repo.count();
		System.out.println("Total categorias: " + count);
	}

	private void eliminar() {
		repo.deleteById(1);
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

	private List<Categoria> getlistaCategorias() {
		List<Categoria> lista = new LinkedList<Categoria>();
		Categoria cat1 = new Categoria();
		cat1.setNombre("Programador de blockchain");
		cat1.setDescripcion("Trabajos relacionados con BitCoin y Criptomonedas");

		Categoria cat2 = new Categoria();
		cat2.setNombre("Soldador/pintura");
		cat2.setDescripcion("Trabajos relacionados con soldadura, pintura y enderezado");

		Categoria cat3 = new Categoria();
		cat3.setNombre("Ingeniero industrial");
		cat3.setDescripcion("Trabajos relacionados con Ing. Industrial");

		lista.add(cat1);
		lista.add(cat2);
		lista.add(cat3);
		return lista;
	}

}
