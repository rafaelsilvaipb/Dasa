package br.com.dasa.api.repository;

import br.com.dasa.api.entities.Exames;
import br.com.dasa.api.entities.Laboratory;
import br.com.dasa.api.entities.Unidade;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Transactional()
public interface LaboratoryRepository extends JpaRepository<Laboratory, Long> {


    Optional<Laboratory> findById(Long id);

    List<Laboratory> findAllByExames(Exames exames);

    List<Laboratory> findAllByUnidades(Unidade exams);
}
