package br.com.dasa.api.util;

import br.com.dasa.api.entities.Exames;
import br.com.dasa.api.entities.Laboratory;
import br.com.dasa.api.entities.Unidade;
import br.com.dasa.api.services.ExamesService;
import br.com.dasa.api.services.LaboratoryService;
import br.com.dasa.api.services.UnidadeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

@Component
public class Populate {

    @Autowired
    LaboratoryService laboratoryService;

    @Autowired
    ExamesService examesService;

    @Autowired
    private UnidadeService unidadeService;

    public void populate() throws ParseException {

        Unidade un1 =  Unidade.builder().nome_unidade("Unidade").build();
        Unidade un2 =  Unidade.builder().nome_unidade("Unidade Barueri").build();
        Unidade un3 =  Unidade.builder().nome_unidade("Unidade Shopping Itaquera").build();
        Unidade un4 =  Unidade.builder().nome_unidade("Unidade Mooca").build();
        Unidade un5 =  Unidade.builder().nome_unidade("Unidade Taboão da Serra").build();

        Exames ex1 =  Exames.builder().nome_exame("Biópsia").build();
        Exames ex2 =  Exames.builder().nome_exame("Vacinas").build();
        Exames ex3 =  Exames.builder().nome_exame("Raio X").build();
        Exames ex4 =  Exames.builder().nome_exame("Mamografia").build();
        Exames ex5 =  Exames.builder().nome_exame("Tomografia").build();

        Laboratory lab1 = Laboratory.builder().nome_laboratorio("Delboni - ALPHAVILLE").build();
        Laboratory lab2 = Laboratory.builder().nome_laboratorio("Delboni - ALTO DE PINHEIROS").build();
        Laboratory lab3 = Laboratory.builder().nome_laboratorio("Delboni - ALTO DE SANTANA").build();
        Laboratory lab4 = Laboratory.builder().nome_laboratorio("Delboni - ATENDIMENTO MÓVEL").build();

        List<Exames> listExames = new ArrayList<Exames>();
        listExames.add(ex1);
        listExames.add(ex2);
        listExames.add(ex3);
        listExames.add(ex4);
        listExames.add(ex5);

        List<Laboratory> listLaboratory = new ArrayList<Laboratory>();
        listLaboratory.add(lab1);
        listLaboratory.add(lab2);
        listLaboratory.add(lab3);
        listLaboratory.add(lab4);

        List<Unidade> listUnidade = new ArrayList<Unidade>();
        listUnidade.add(un1);
        listUnidade.add(un2);
        listUnidade.add(un3);
        listUnidade.add(un4);
        listUnidade.add(un5);

        for (Exames ex : listExames){
            examesService.salvar(ex);
        }

        for (Unidade ex : listUnidade){
            unidadeService.salvar(ex);
        }

        for (Laboratory lab : listLaboratory){
            laboratoryService.salvar(lab);
            lab.setExames(listExames);
            lab.setUnidades(listUnidade);
            laboratoryService.salvar(lab);
        }

    }
}