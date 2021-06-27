package br.com.dasa.api.controller;


import br.com.dasa.api.dtos.AssociarDTO;
import br.com.dasa.api.dtos.ExamsDTO;
import br.com.dasa.api.dtos.LaboratoryListDTO;
import br.com.dasa.api.entities.Exames;
import br.com.dasa.api.entities.Laboratory;
import br.com.dasa.api.error.FoundException;
import br.com.dasa.api.services.ExamesService;
import br.com.dasa.api.services.LaboratoryService;
import br.com.dasa.api.util.Populate;
import br.com.dasa.api.util.Validar;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static br.com.dasa.api.mapper.ExamsMapper.dtoToExams;
import static br.com.dasa.api.mapper.ExamsMapper.examsToDTO;

@RestController
@RequestMapping("/dasa/util/")
@CrossOrigin(origins = "*")
public class UtilController {

    @Autowired
    private ExamesService examsService;

    @Autowired
    private LaboratoryService laboratoryService;

    @Autowired
    private Validar validar;

    @Autowired
    private Populate populate;

    @PutMapping(value = "associar/" )
    public void associar(@RequestBody AssociarDTO associarDTO) {
        validar.validExams(associarDTO.getIdExame());

        Exames exams = examsService.findById(associarDTO.getIdExame()).get();
        Laboratory laboratory = laboratoryService.findById(associarDTO.getIdLaboratory()).get();
        laboratory.getExames().add(exams);
        laboratory.setExames(laboratory.getExames().stream().distinct().collect(Collectors.toList()));
        laboratoryService.salvar(laboratory);
    }

    @PutMapping(value = "desassociar/" )
    public void desassociar(@RequestBody AssociarDTO associarDTO) throws ParseException {
        validar.validExams(associarDTO.getIdExame());
        validar.validLaboratorys(associarDTO.getIdLaboratory());

        Exames exams = examsService.findById(associarDTO.getIdExame()).get();
        Laboratory laboratory = laboratoryService.findById(associarDTO.getIdLaboratory()).get();
        laboratory.getExames().remove(exams);
        laboratoryService.salvar(laboratory);
    }


    @PostMapping(value = "populate/")
    public void populateInit() throws ParseException {
        populate.populate();
    }

}