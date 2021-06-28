package br.com.dasa.api.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;


@Entity
@Table(name = "laboratory")
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class Laboratory implements Serializable {

    private static final long serialVersionUID = 3960436649365666213L;

    @Id
    @GeneratedValue(generator = "LAB_SEQ", strategy = GenerationType.IDENTITY)
    private Long cod_laboratorio;

    private String nome_laboratorio;

    @ManyToMany
    private List<Exames> exames;

    @ManyToMany
    private List<Unidade> unidades;


}