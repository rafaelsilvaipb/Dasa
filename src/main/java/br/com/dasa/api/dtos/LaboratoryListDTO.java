package br.com.dasa.api.dtos;

import br.com.dasa.api.entities.Exames;
import br.com.dasa.api.entities.Unidade;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class LaboratoryListDTO {
    private Long cod_laboratorio;
    private String nome_laboratorio;
    private List<Exames> exames;
    private List<Unidade> unidades;

}