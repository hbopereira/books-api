package books.base;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.query.Param;

@NoRepositoryBean 
public interface BaseRepository<ENTITY> extends JpaRepository<ENTITY, Long>{

	@Query(
			nativeQuery = true, 
			countQuery = "select count(id) from #{#entityName}", 
			value = "select * from #{#entityName} ")
	Page<ENTITY> consultar(Pageable pageable);
	
	@Query(
			nativeQuery = true, 
			countQuery = "select count(id) from #{#entityName} where title ilike :searchTerm ", 
			value = "select * from #{#entityName} where title like :searchTerm ")
	Page<ENTITY> consultar(Pageable pageable, @Param("searchTerm") String searchTerm);
	
}
