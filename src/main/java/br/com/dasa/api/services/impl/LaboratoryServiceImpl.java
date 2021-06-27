package br.com.dasa.api.services.impl;

import br.com.dasa.api.entities.Exames;
import br.com.dasa.api.entities.Laboratory;
import br.com.dasa.api.entities.Unidade;
import br.com.dasa.api.repository.LaboratoryRepository;
import br.com.dasa.api.services.LaboratoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class LaboratoryServiceImpl implements LaboratoryService {

    @Autowired
    private LaboratoryRepository laboratoryRepository;

    @Override
    public Laboratory salvar(Laboratory laboratory) {
        return this.laboratoryRepository.save(laboratory);
    }

    @Override
    public Optional<Laboratory> findById(Long id) {
        return this.laboratoryRepository.findById(id);
    }

    @Override
    public List<Laboratory> findAll() {
        return laboratoryRepository.findAll();
    }

    @Override
    public List<Laboratory> findAllByExams(Exames exames) {
        return laboratoryRepository.findAllByExames(exames);
    }

    @Override
    public List<Laboratory> findAllByUnidades(Unidade unidade){
        return laboratoryRepository.findAllByUnidades(unidade);
    }


}