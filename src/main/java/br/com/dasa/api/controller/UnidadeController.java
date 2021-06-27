package br.com.dasa.api.controller;


import br.com.dasa.api.dtos.AssociarDTO;
import br.com.dasa.api.dtos.LaboratoryListDTO;
import br.com.dasa.api.dtos.UnidadeDTO;
import br.com.dasa.api.entities.Exames;
import br.com.dasa.api.entities.Laboratory;
import br.com.dasa.api.entities.Unidade;
import br.com.dasa.api.error.FoundException;
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
import java.util.ArrayList;
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

    @PostMapping
    public ResponseEntity<UnidadeDTO> adicionar(@RequestBody UnidadeDTO unidadeDTO) throws ParseException {
        return ResponseEntity.ok(unidadeToDTO(unidadeService.salvar(dtoToUnidade(unidadeDTO))));
    }

    @PostMapping(value = "lote/")
    public void adicionarLote(@RequestBody List<UnidadeDTO> ListExamsDTO, BindingResult result) throws ParseException {
        for(UnidadeDTO e : ListExamsDTO){
            adicionar(e);
        }
    }

    @PutMapping
    public ResponseEntity<UnidadeDTO> alterar(@RequestBody UnidadeDTO unidadeDTO) throws ParseException {
        validar.validUnidade(unidadeDTO.getCod_unidade());
        return ResponseEntity.ok(unidadeToDTO(unidadeService.salvar(dtoToUnidade(unidadeDTO))));
    }


    @PutMapping(value = "lote/")
    public void alterarLote(@RequestBody List<UnidadeDTO> listExamsDTO) throws ParseException {
        for(UnidadeDTO e : listExamsDTO){
            alterar(e);
        }

    }


//    @DeleteMapping(value = "/{id}" )
//    public void deletar(@PathVariable("id") long id) throws ParseException {
//        validar.validUnidade(id);
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
    public ResponseEntity<List<UnidadeDTO>> listarExames() {
        return ResponseEntity.ok(
                unidadeService.findAll()
                        .stream()
                        .map(c -> unidadeToDTO(c))
                        .collect(Collectors.toList()));
    }


    @PutMapping(value = "associar/" )
    public void associar(@RequestBody AssociarDTO associarDTO) {
        validar.validExams(associarDTO.getIdExame());
        validar.validLaboratorys(associarDTO.getIdLaboratory());
        validar.validUnidade(associarDTO.getIdUnidade());

        Unidade unidade = unidadeService.findById(associarDTO.getIdUnidade()).get();
        Exames ex = examesService.findById(associarDTO.getIdExame()).get();
        Laboratory laboratory = laboratoryService.findById(associarDTO.getIdLaboratory()).get();
        laboratory.getUnidades().add(unidade);
        laboratory.setUnidades(laboratory.getUnidades().stream().distinct().collect(Collectors.toList()));
        laboratory.getExames().add(ex);
        laboratory.setExames(laboratory.getExames().stream().distinct().collect(Collectors.toList()));
        laboratoryService.salvar(laboratory);
    }
//TODO
    @PutMapping(value = "desassociar/" )
    public void desassociar(@RequestBody AssociarDTO associarDTO) throws ParseException {
        validar.validExams(associarDTO.getIdExame());
        validar.validLaboratorys(associarDTO.getIdLaboratory());

        Unidade unidade = unidadeService.findById(associarDTO.getIdUnidade()).get();
        Exames ex = examesService.findById(associarDTO.getIdExame()).get();
        Laboratory laboratory = laboratoryService.findById(associarDTO.getIdLaboratory()).get();
        laboratory.getUnidades().remove(unidade);
        laboratory.getExames().remove(ex);
        laboratoryService.salvar(laboratory);
    }

    @GetMapping(value = "/{nome}" )
    public ResponseEntity<List<LaboratoryListDTO>> listaLaboratorioPorNomeDoExame(@PathVariable("nome") String nome) throws ParseException {

        List<LaboratoryListDTO> lablist = new ArrayList();

        for(Laboratory lab : laboratoryService.findAll()){
            for(Unidade ex :  lab.getUnidades()){
                if(ex.getNome_unidade().equals(nome)){
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


    private void validHasAssociateList(Unidade unidade) {
        List<Laboratory> lab = laboratoryService.findAllByUnidades(unidade);

        if (!lab.isEmpty())
            throw new FoundException("Não é possível alterar o status do exame, ele está associado com " + lab.size() +
                    " laboratório(os).");
    }

}