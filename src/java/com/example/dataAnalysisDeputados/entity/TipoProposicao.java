package com.example.dataAnalysisDeputados.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TipoProposicao {
    private int cod;
    private String sigla;
    private String nome;
    private String descricao;
}
