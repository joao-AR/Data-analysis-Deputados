package com.example.dataAnalysisDeputados.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Proposicao {
    private int id;
    private int id_deputado;
    @JsonIgnore
    private String uri;
    @JsonIgnore
    private String siglaTipo;
    private int codTipo;
    @JsonIgnore
    private int numero;
    private int ano;
    private String ementa;
}
