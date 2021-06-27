package br.com.dasa.api.util;

import br.com.dasa.api.error.FoundException;
import br.com.dasa.api.error.NotFoundException;
import br.com.dasa.api.services.ExamesService;
import br.com.dasa.api.services.LaboratoryService;
import br.com.dasa.api.services.UnidadeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static java.util.Optional.ofNullable;

@Service
public class Validar {

    @Autowired
    private ExamesService examesService;

    @Autowired
    private LaboratoryService laboratoryService;

    @Autowired
    private UnidadeService unidadeService;

    public  void validExams(Long id) throws FoundException {
        ofNullable(examesService.findById(id)
                .orElseThrow(() -> new NotFoundException("O id " + id + " não existe!")));
    }

    public void validLaboratorys(Long id) throws FoundException {
        ofNullable(laboratoryService.findById(id)
                .orElseThrow(() -> new NotFoundException("O id " + id + " não existe!")));

    }

    public  void validUnidade(Long id) throws FoundException {
        ofNullable(unidadeService.findById(id)
                .orElseThrow(() -> new NotFoundException("O id " + id + " não existe!")));
    }




}
