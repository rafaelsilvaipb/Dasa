package br.com.dasa.api.mapper;

import br.com.dasa.api.dtos.ExamsDTO;
import br.com.dasa.api.dtos.UnidadeDTO;
import br.com.dasa.api.entities.Exames;
import br.com.dasa.api.entities.Unidade;

import static java.util.Optional.ofNullable;

public class UnidadeMapper {

    public static UnidadeDTO unidadeToDTO(Unidade unidade) {
        return UnidadeDTO.builder()
                .cod_unidade(ofNullable(unidade.getCod_unidade()).orElse(null))
                .nome_unidade(ofNullable(unidade.getNome_unidade()).orElse(null))
                .build();
    }

    public static Unidade dtoToUnidade( UnidadeDTO unidadeDTO ) {
        return Unidade.builder()
                .cod_unidade(ofNullable(unidadeDTO.getCod_unidade()).orElse(null))
                .nome_unidade(ofNullable(unidadeDTO.getNome_unidade()).orElse(null))
                .build();

    }
}
