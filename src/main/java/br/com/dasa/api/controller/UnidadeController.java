package br.com.dasa.api.controller;


import br.com.dasa.api.dtos.UnidadeDTO;
import br.com.dasa.api.services.ExamesService;
import br.com.dasa.api.services.LaboratoryService;
import br.com.dasa.api.services.UnidadeService;
import br.com.dasa.api.util.Populate;
import br.com.dasa.api.util.Validar;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.List;
import java.util.stream.Collectors;

import static br.com.dasa.api.mapper.UnidadeMapper.dtoToUnidade;
import static br.com.dasa.api.mapper.UnidadeMapper.unidadeToDTO;

@RestController
@RequestMapping("/dasa/unidade/")
@CrossOrigin(origins = "*")
public class UnidadeController {

    @Autowired
    private UnidadeService unidadeService;

    @Autowired
    private LaboratoryService laboratoryService;

    @Autowired
    private ExamesService examesService;

    @Autowired
    private Validar validar;

    @Autowired
    private Populate populate;


    @GetMapping(value = "/{id}" )
    public UnidadeDTO unidadePorID(@PathVariable("id") long id) throws ParseException {
        validar.validUnidade(id);
        return  unidadeToDTO(unidadeService.findById(id).get());

    }

    @GetMapping
    public ResponseEntity<List<UnidadeDTO>> listarUnidades() {
        return ResponseEntity.ok(
                unidadeService.findAll()
                        .stream()
                        .map(c -> unidadeToDTO(c))
                        .collect(Collectors.toList()));
    }

    @PostMapping
    public ResponseEntity<UnidadeDTO> adicionar(@RequestBody UnidadeDTO unidadeDTO) throws ParseException {
        return ResponseEntity.ok(unidadeToDTO(unidadeService.salvar(dtoToUnidade(unidadeDTO))));
    }

    @PutMapping
    public ResponseEntity<UnidadeDTO> alterar(@RequestBody UnidadeDTO unidadeDTO) throws ParseException {
        validar.validUnidade(unidadeDTO.getCod_unidade());
        return ResponseEntity.ok(unidadeToDTO(unidadeService.salvar(dtoToUnidade(unidadeDTO))));
    }

}