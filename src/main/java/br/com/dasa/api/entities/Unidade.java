package br.com.dasa.api.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "unidade")
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class Unidade implements Serializable {

    private static final long serialVersionUID = 3960436649365666213L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long cod_unidade;

    private String nome_unidade;

}