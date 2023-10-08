package com.example.dataAnalysisDeputados.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Deputados {
    private int id;

    public int getId_partido() {
        return id_partido;
    }

    public void setId_partido(int id_partido) {
        this.id_partido = id_partido;
    }

    private int id_partido;
    @JsonIgnore
    private String uri;
    private String nome;
    private String siglaPartido;
    @JsonIgnore
    private String uriPartido;
    @JsonIgnore
    private String siglaUf;
    @JsonIgnore
    private int idLegislatura;
    private String urlFoto;
    private String email ;
}
