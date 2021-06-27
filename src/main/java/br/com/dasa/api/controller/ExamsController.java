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


//    @DeleteMapping(value = "/{id}" )
//    public void deletar(@PathVariable("id") long id) throws ParseException {
//        validar.validExams(id);
//
//        Exams exams = examsService.findByIdAndStatus(id, Status.ATIVO).get();
//        validHasAssociateList(exams);
//        exams.setStatus(Status.INATIVO);
//        examsService.salvar(exams);
//    }

//
//    @DeleteMapping(value = "lote/")
//    public void deletarLote(@RequestBody List<Long> listaId) throws ParseException {
//        for(Long l : listaId){
//            deletar(l);
//        }
//    }

    @GetMapping
    public ResponseEntity<List<ExamsDTO>> listarExames() {
        return ResponseEntity.ok(
                examsService.findAll()
                        .stream()
                        .map(c -> examsToDTO(c))
                        .collect(Collectors.toList()));
    }


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

    @GetMapping(value = "/{nome}" )
    public ResponseEntity<List<LaboratoryListDTO>> listaLaboratorioPorNomeDoExame(@PathVariable("nome") String nome) throws ParseException {

        List<LaboratoryListDTO> lablist = new ArrayList();

        for(Laboratory lab : laboratoryService.findAll()){
            for(Exames ex :  lab.getExames()){
                if(ex.getNome_exame().equals(nome)){
                    lablist.add(LaboratoryListDTO.builder().cod_laboratorio(lab.getCod_laboratorio()).nome_laboratorio(lab.getNome_laboratorio()).build());
                }
            }
        }

        return ResponseEntity.ok(lablist);
    }


    @PostMapping(value = "populate/")
    public void populateInit() throws ParseException {
        populate.populate();
    }


    private void validHasAssociateList(Exames exams) {
        List<Laboratory> lab = laboratoryService.findAllByExams(exams);

        if (!lab.isEmpty())
            throw new FoundException("Não é possível alterar o status do exame, ele está associado com " + lab.size() +
                    " laboratório(os).");
    }

}