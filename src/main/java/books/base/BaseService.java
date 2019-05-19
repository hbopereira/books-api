package books.base;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;



public class BaseService<ENTITY extends BaseEntity, REPOSITORY extends BaseRepository<ENTITY>> {
	
	@Autowired
	private REPOSITORY repo;

	public Optional<ENTITY> encontrarPeloId(Long id) {
		return repo.findById(id);
	}

	public Optional<ENTITY> salvar(ENTITY entity) {
		ENTITY nova = repo.save(entity);
		return Optional.of(nova);
	}

	public Optional<ENTITY> atualizar(ENTITY entity) {
		if (repo.existsById(entity.getId())) {
			repo.save(entity);
			return Optional.of(entity);
		} else {
			return Optional.empty();
		}
	}

	public void excluir(ENTITY entity) {
		if (repo.existsById(entity.getId())) {
			repo.deleteById(entity.getId());
		} else
			throw new RuntimeException("Entidade [" + entity.getId() + "] não encontrada!");
	}

	public void excluir(Long id) {
		if (repo.existsById(id)) {
			repo.deleteById(id);
		} else
			throw new RuntimeException("Entidade [" + id + "] não encontrada!");
	}

}
