package br.com.dasa.api.repository;

import br.com.dasa.api.entities.Unidade;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Transactional()
public interface UnidadeRepository extends JpaRepository<Unidade, Long> {

    Optional<Unidade> findById(Long id);

}
