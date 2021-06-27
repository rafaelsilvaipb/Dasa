package br.com.dasa.api.services.impl;

import br.com.dasa.api.entities.Exames;
import br.com.dasa.api.entities.Unidade;
import br.com.dasa.api.repository.UnidadeRepository;
import br.com.dasa.api.services.UnidadeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class UnidadeServiceImpl implements UnidadeService {

    @Autowired
    private UnidadeRepository unidadeRepository;

    @Override
    public Unidade salvar(Unidade unidade) {
        return this.unidadeRepository.save(unidade);
    }

    @Override
    public Optional<Unidade> findById(Long id) {
        return this.unidadeRepository.findById(id);
    }

    @Override
    public List<Unidade> findAll() {
        return unidadeRepository.findAll();
    }

}
