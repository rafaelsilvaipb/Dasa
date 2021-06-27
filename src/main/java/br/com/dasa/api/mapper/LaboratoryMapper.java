package br.com.dasa.api.mapper;

import br.com.dasa.api.dtos.LaboratoryDTO;
import br.com.dasa.api.entities.Laboratory;

import static java.util.Optional.ofNullable;

public class LaboratoryMapper {

    public static LaboratoryDTO laboratoryToDTO(Laboratory laboratory) {
        return LaboratoryDTO.builder()
                .cod_laboratorio(ofNullable(laboratory.getCod_laboratorio()).orElse(null))
                .nome_laboratorio(ofNullable(laboratory.getNome_laboratorio()).orElse(null))
                .build();
    }

    public static Laboratory dtoToLaboratory( LaboratoryDTO laboratoryDTO ) {
        return Laboratory.builder()
                .cod_laboratorio(ofNullable(laboratoryDTO.getCod_laboratorio()).orElse(null))
                .nome_laboratorio(ofNullable(laboratoryDTO.getNome_laboratorio()).orElse(null))
                .build();

    }
}
