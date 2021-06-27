package br.com.dasa.api.controller;


import br.com.dasa.api.dtos.ExamsDTO;
import br.com.dasa.api.dtos.LaboratoryListDTO;
import br.com.dasa.api.dtos.LaboratoryListSemExamesEUnidadesDTO;
import br.com.dasa.api.entities.Exames;
import br.com.dasa.api.entities.Laboratory;
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

    @PostMapping(value = "lote/")
    public void adicionarLote(@RequestBody List<ExamsDTO> ListExamsDTO, BindingResult result) throws ParseException {
        for(ExamsDTO e : ListExamsDTO){
            adicionar(e);
        }
    }

    @PutMapping
    public ResponseEntity<ExamsDTO> alterar(@RequestBody ExamsDTO examsDTO) throws ParseException {
        validar.validExams(examsDTO.getCod_exame());
        return ResponseEntity.ok(examsToDTO(examsService.salvar(dtoToExams(examsDTO))));
    }


    @PutMapping(value = "lote/")
    public void alterarLote(@RequestBody List<ExamsDTO> listExamsDTO) throws ParseException {
        for(ExamsDTO e : listExamsDTO){
            alterar(e);
        }

    }


    @GetMapping(value = "/{id}" )
    public ExamsDTO unidadePorID(@PathVariable("id") long id) throws ParseException {
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

    @GetMapping(value = "nome/{nome}" )
    public ResponseEntity<List<LaboratoryListSemExamesEUnidadesDTO>> listaLaboratorioPorNomeDoExame(@PathVariable("nome") String nome) throws ParseException {

        List<LaboratoryListSemExamesEUnidadesDTO> lablist = new ArrayList();

        for(Laboratory lab : laboratoryService.findAll()){
            for(Exames ex :  lab.getExames()){
                if(ex.getNome_exame().equals(nome)){
                    lablist.add(LaboratoryListSemExamesEUnidadesDTO.builder().cod_laboratorio(lab.getCod_laboratorio()).nome_laboratorio(lab.getNome_laboratorio()).build());
                }
            }
        }

        return ResponseEntity.ok(lablist);
    }


}