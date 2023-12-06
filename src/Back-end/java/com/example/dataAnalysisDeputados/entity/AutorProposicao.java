package com.example.dataAnalysisDeputados.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AutorProposicao {
    private String uri;
    private String nome;
    private String tipo;
    private String ordemAssinatura;
    private String proponente;
}
