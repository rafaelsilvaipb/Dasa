package br.com.dasa.api.services.impl;

import br.com.dasa.api.entities.Exames;
import br.com.dasa.api.repository.ExamsRepository;
import br.com.dasa.api.services.ExamesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class ExamsServiceImpl implements ExamesService {

    @Autowired
    private ExamsRepository examsRepository;

    @Override
    public Exames salvar(Exames exams) {
        return this.examsRepository.save(exams);
    }

    @Override
    public Optional<Exames> findById(Long id) {
        return this.examsRepository.findById(id);
    }

    @Override
    public List<Exames> findAll() {
        return examsRepository.findAll();
    }

}
