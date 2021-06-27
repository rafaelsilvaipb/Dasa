package br.com.dasa.api.services;

import br.com.dasa.api.entities.Exames;
import br.com.dasa.api.entities.Laboratory;
import br.com.dasa.api.entities.Unidade;

import java.util.List;
import java.util.Optional;

public interface LaboratoryService {

    Laboratory salvar(Laboratory laboratory);

    Optional<Laboratory> findById(Long id);

    List<Laboratory> findAll();

    List<Laboratory> findAllByExams(Exames exams);

    List<Laboratory> findAllByUnidades(Unidade unidade);

}
