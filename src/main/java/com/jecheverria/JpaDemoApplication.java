package com.jecheverria;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

import com.jecheverria.model.Categoria;
import com.jecheverria.model.Vacante;
import com.jecheverria.repository.CategoriasRepository;
import com.jecheverria.repository.VacantesRepository;

@SpringBootApplication
public class JpaDemoApplication implements CommandLineRunner {
	@Autowired
	private VacantesRepository repoVacantes;
	@Autowired
	private CategoriasRepository repoCategorias;

	public static void main(String[] args) {
		SpringApplication.run(JpaDemoApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		guardarVacante();
	}

	private void guardarVacante() {
		Vacante vacante = new Vacante();
		vacante.setNombre("Profesor de matematicas");
		vacante.setDescripcion("Se solcita profesor de matematicas");
		vacante.setFecha(new Date());
		vacante.setSalario(8500.00);
		vacante.setEstatus("Aprobada");
		vacante.setDestacado(0);
		vacante.setImagen("escuela.png");
		vacante.setDetalles("los requisitos para profesor de matematicas");
		Categoria cat = new Categoria();
		cat.setId(15);
		vacante.setCategoria(cat);

		repoVacantes.save(vacante);
	}

	private void buscarVacantes() {
		List<Vacante> lista = repoVacantes.findAll();
		for (Vacante v : lista) {
			System.out.println(v.getId() + " " + v.getNombre() + " " + v.getCategoria().getNombre());
		}
	}

	private void buscarTodoPaginacion() {
		Page<Categoria> page = repoCategorias.findAll(PageRequest.of(0, 5, Sort.by("nombre")));
		for (Categoria c : page.getContent()) {
			System.out.println(c.getId() + " " + c.getNombre());
		}

	}

	private void buscarTodosOrdenados() {
		List<Categoria> categorias = repoCategorias.findAll(Sort.by("nombre").descending());
		for (Categoria c : categorias) {
			System.out.println(c.getId() + " " + c.getNombre());
		}
	}

	private void borrarTodoEnBloque() {
		repoCategorias.deleteAllInBatch();
	}

	private void buscarTodosJpa() {
		List<Categoria> categorias = repoCategorias.findAll();
		for (Categoria c : categorias) {
			System.out.println(c.getId() + " " + c.getNombre());
		}
	}

	private void guardarTodas() {
		List<Categoria> categorias = getlistaCategorias();
		repoCategorias.saveAll(categorias);
	}

	private void existeId() {
		boolean existe = repoCategorias.existsById(1);
		System.out.println("La categoria existe: " + existe);
	}

	private void buscarTodos() {
		Iterable<Categoria> categorias = repoCategorias.findAll();
		for (Categoria c : categorias) {
			System.out.println(c);
		}
	}

	private void encontrarPorIds() {
		List<Integer> ids = new LinkedList<Integer>();
		ids.add(1);
		ids.add(4);
		ids.add(10);
		Iterable<Categoria> categoria = repoCategorias.findAllById(ids);
		for (Categoria c : categoria) {
			System.out.println(c);
		}
	}

	private void eliminarTodos() {
		repoCategorias.deleteAll();
	}

	private void conteo() {
		long count = repoCategorias.count();
		System.out.println("Total categorias: " + count);
	}

	private void eliminar() {
		repoCategorias.deleteById(1);
	}

	private void modificar() {
		Optional<Categoria> optional = repoCategorias.findById(1);
		if (optional.isPresent()) {
			Categoria catTmp = optional.get();
			catTmp.setNombre("Ing. de software");
			catTmp.setDescripcion("Desarrollo de sistemas");
			repoCategorias.save(catTmp);
			System.out.println(optional.get());
		} else {
			System.out.println("Categoria NO encontrada");
		}
	}

	private void buscarPorId() {
		Optional<Categoria> optional = repoCategorias.findById(1);
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
		repoCategorias.save(cat);
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
