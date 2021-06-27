package br.com.dasa.api.repository;

import br.com.dasa.api.entities.Exames;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Transactional()
public interface ExamsRepository extends JpaRepository<Exames, Long> {

    Optional<Exames> findById(Long id);

}
