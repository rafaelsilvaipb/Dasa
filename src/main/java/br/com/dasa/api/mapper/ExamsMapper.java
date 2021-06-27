package br.com.dasa.api.mapper;

import br.com.dasa.api.dtos.ExamsDTO;
import br.com.dasa.api.entities.Exames;

import static java.util.Optional.ofNullable;

public class ExamsMapper {

    public static ExamsDTO examsToDTO(Exames exames) {
        return ExamsDTO.builder()
                .cod_exame(ofNullable(exames.getCod_exame()).orElse(null))
                .nome_exame(ofNullable(exames.getNome_exame()).orElse(null))
                .build();
    }

    public static Exames dtoToExams( ExamsDTO examsDTO ) {
        return Exames.builder()
                .cod_exame(ofNullable(examsDTO.getCod_exame()).orElse(null))
                .nome_exame(ofNullable(examsDTO.getNome_exame()).orElse(null))
                .build();

    }
}
