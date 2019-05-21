package books.base;

import java.util.List;
import java.util.Optional;

import javax.persistence.Entity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;


public class BaseController<ENTITY extends BaseEntity, REPOSITORY extends BaseRepository<ENTITY>, SERVICE extends BaseService<ENTITY, REPOSITORY>> {
	@Autowired
	private SERVICE service;

	@Autowired
	private REPOSITORY repo;

	@PostMapping
	@CrossOrigin(origins = "http://localhost:3000")
	public ResponseEntity<String> post(@RequestBody ENTITY entity) {
		try {
			Optional<ENTITY> resultado = service.salvar(entity);
			if (resultado.isPresent()) {
				return ResponseEntity.status(HttpStatus.CREATED).body(resultado.get().getId().toString());
			}
		} catch (RuntimeException e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
		return ResponseEntity.badRequest().build();
	}

	@PutMapping
	@CrossOrigin(origins = "http://localhost:3000")
	public ResponseEntity<String> put(@RequestBody ENTITY entity) {
		Optional<ENTITY> resultado = service.salvar(entity);
		if (resultado.isPresent()) {
			return ResponseEntity.ok(resultado.get().getId().toString());
		}
		return ResponseEntity.notFound().build();
	}
	
	

	@GetMapping("/{id}")
	@CrossOrigin(origins = "http://localhost:3000")
	public ResponseEntity<ENTITY> getById(@PathVariable("id") Long id) {
		Optional<ENTITY> resultado = service.encontrarPeloId(id);
		if (resultado.isPresent()) {
			return ResponseEntity.ok(resultado.get());
		}
		return ResponseEntity.notFound().build();
	}

	@DeleteMapping
	@CrossOrigin(origins = "http://localhost:3000")
	public ResponseEntity<Void> delete(@RequestBody ENTITY entity) {
		service.excluir(entity.getId());
		return ResponseEntity.noContent().build();
	}
	
	@GetMapping(value="/listAll")
	@CrossOrigin(origins = "http://localhost:3000")
	public ResponseEntity<List<ENTITY>> listAll(){
		return ResponseEntity.ok().body(repo.findAll());
	}

	@GetMapping
	@CrossOrigin(origins = "http://localhost:3000")
	public Page<ENTITY> consultar(Pageable p,
			@RequestParam(name = "searchTerm", defaultValue = "", required = false) String searchTerm) {
		if (searchTerm.isEmpty()) {
			return repo.consultar(p);
		}
		Page<ENTITY> page = repo.consultar(p, "%" + searchTerm + "%");
		return page;
	}

}
