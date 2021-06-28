package br.com.dasa.api.controller;


import br.com.dasa.api.dtos.LaboratoryDTO;
import br.com.dasa.api.dtos.LaboratoryListDTO;
import br.com.dasa.api.services.LaboratoryService;
import br.com.dasa.api.util.Validar;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.List;
import java.util.stream.Collectors;

import static br.com.dasa.api.mapper.LaboratoryMapper.*;
import static java.util.Optional.ofNullable;

@RestController
@RequestMapping("/dasa/laboratory/")
@CrossOrigin(origins = "*")
public class LaboratoryController {

    @Autowired
    private LaboratoryService laboratoryService;

    @Autowired
    private Validar validar;


    @PostMapping
    public ResponseEntity<LaboratoryDTO> adicionar(@RequestBody LaboratoryDTO laboratoryDTO) throws ParseException {
        return ResponseEntity.ok(laboratoryToDTO(
                ofNullable(laboratoryService.salvar(
                        dtoToLaboratory(laboratoryDTO))).get()));
    }

    @PutMapping
    public ResponseEntity<LaboratoryDTO> alterar(@RequestBody LaboratoryDTO laboratoryDTO) throws ParseException {
        validar.validLaboratorys(laboratoryDTO.getCod_laboratorio());

        return ResponseEntity.ok(laboratoryToDTO(laboratoryService.salvar(
                        dtoToLaboratory(laboratoryDTO))));

    }

    @GetMapping(value = "/{id}" )
    public ResponseEntity<LaboratoryListDTO> listarLaboratoriosPorId(@PathVariable("id") long id) {
        validar.validLaboratorys(id);
        return ResponseEntity.ok(laboratoryListToDTO(laboratoryService.findById(id).get()));
    }

    @GetMapping
    public ResponseEntity<List<LaboratoryListDTO>> listarLaboratorios() {
        return ResponseEntity.ok(
                laboratoryService.findAll()
                        .stream()
                        .map(c -> laboratoryListToDTO(c))
                        .collect(Collectors.toList()));
    }

}