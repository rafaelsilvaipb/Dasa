
package br.com.dasa.api.services;

import br.com.dasa.api.entities.Exames;
import br.com.dasa.api.entities.Unidade;

import java.util.List;
import java.util.Optional;

public interface UnidadeService {

    Unidade salvar(Unidade unidade);

    Optional<Unidade> findById(Long id);

    List<Unidade> findAll();

}

