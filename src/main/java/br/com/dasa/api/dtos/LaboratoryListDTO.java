package br.com.dasa.api.dtos;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

@JsonIgnoreProperties(ignoreUnknown = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class LaboratoryListDTO {
    private Long cod_laboratorio;
    private String nome_laboratorio;

}