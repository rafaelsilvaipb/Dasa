package br.com.dasa.api.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "exams")
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class Exames implements Serializable {

    private static final long serialVersionUID = 3960436649365666213L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long cod_exame;

    private String nome_exame;

}