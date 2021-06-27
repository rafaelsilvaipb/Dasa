
package br.com.dasa.api.services;

import br.com.dasa.api.entities.Exames;

import java.util.List;
import java.util.Optional;

public interface ExamesService {

    public Exames salvar(Exames exames);


    public Optional<Exames> findById(Long id);

    public List<Exames> findAll();

}

