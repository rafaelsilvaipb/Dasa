package br.com.dasa.api.controller;


import br.com.dasa.api.dtos.LaboratoryDTO;
import br.com.dasa.api.services.LaboratoryService;
import br.com.dasa.api.util.Validar;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.List;
import java.util.stream.Collectors;

import static br.com.dasa.api.mapper.LaboratoryMapper.dtoToLaboratory;
import static br.com.dasa.api.mapper.LaboratoryMapper.laboratoryToDTO;
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

    @PostMapping(value = "lote/")
    public void adicionarLote(@RequestBody List<LaboratoryDTO> listLaboratoryDTO) throws ParseException {
        for(LaboratoryDTO lab : listLaboratoryDTO){
            adicionar(lab);
        }
    }


    @PutMapping
    public ResponseEntity<LaboratoryDTO> alterar(@RequestBody LaboratoryDTO laboratoryDTO) throws ParseException {
        validar.validLaboratorys(laboratoryDTO.getCod_laboratorio());

        return ResponseEntity.ok(laboratoryToDTO(laboratoryService.salvar(
                        dtoToLaboratory(laboratoryDTO))));

    }

    @PutMapping(value = "lote/")
    public void alterarLote(@RequestBody List<LaboratoryDTO> listLaboratoryDTO) throws ParseException {
        for(LaboratoryDTO lab : listLaboratoryDTO){
            alterar(lab);
        }

    }

//    @DeleteMapping(value = "/{id}" )
//    public void deletar(@PathVariable("id") long id) throws ParseException {
//        validar.validLaboratorys(id);
//
//        Laboratory laboratory = laboratoryService.findByIdAndStatus(id, Status.ATIVO).get();
//        laboratory.setStatus(Status.INATIVO);
//        laboratoryService.salvar(laboratory);
//    }
//
//    @DeleteMapping(value = "lote/")
//    public void deletarLote(@RequestBody List<Long> listaId) throws ParseException {
//        for(Long l : listaId){
//            deletar(l);
//        }
//    }

    @GetMapping
    public ResponseEntity<List<LaboratoryDTO>> listarLaboratorios() {
        return ResponseEntity.ok(
                laboratoryService.findAll()
                .stream()
                .map(c -> laboratoryToDTO(c))
                .collect(Collectors.toList()));
    }

}