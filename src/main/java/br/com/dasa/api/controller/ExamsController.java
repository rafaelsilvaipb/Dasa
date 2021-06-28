package br.com.dasa.api.controller;


import br.com.dasa.api.dtos.ExamsDTO;
import br.com.dasa.api.services.ExamesService;
import br.com.dasa.api.services.LaboratoryService;
import br.com.dasa.api.util.Populate;
import br.com.dasa.api.util.Validar;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.List;
import java.util.stream.Collectors;

import static br.com.dasa.api.mapper.ExamsMapper.dtoToExams;
import static br.com.dasa.api.mapper.ExamsMapper.examsToDTO;

@RestController
@RequestMapping("/dasa/exams/")
@CrossOrigin(origins = "*")
public class ExamsController {

    @Autowired
    private ExamesService examsService;

    @Autowired
    private LaboratoryService laboratoryService;

    @Autowired
    private Validar validar;

    @Autowired
    private Populate populate;

    @PostMapping
    public ResponseEntity<ExamsDTO> adicionar(@RequestBody ExamsDTO examsDTO) throws ParseException {
        return ResponseEntity.ok(examsToDTO(examsService.salvar(dtoToExams(examsDTO))));
    }

    @PutMapping
    public ResponseEntity<ExamsDTO> alterar(@RequestBody ExamsDTO examsDTO) throws ParseException {
        validar.validExams(examsDTO.getCod_exame());
        return ResponseEntity.ok(examsToDTO(examsService.salvar(dtoToExams(examsDTO))));
    }

    @GetMapping(value = "/{id}" )
    public ExamsDTO examePorID(@PathVariable("id") long id) throws ParseException {
        validar.validExams(id);
      return  examsToDTO(examsService.findById(id).get());

    }

    @GetMapping
    public ResponseEntity<List<ExamsDTO>> listarExames() {
        return ResponseEntity.ok(
                examsService.findAll()
                        .stream()
                        .map(c -> examsToDTO(c))
                        .collect(Collectors.toList()));
    }

}