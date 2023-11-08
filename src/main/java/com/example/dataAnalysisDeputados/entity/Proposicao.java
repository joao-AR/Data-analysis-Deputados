package com.example.dataAnalysisDeputados.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity(name = "proposicao")
@Table(name = "proposicao" ,schema = "camara")
@NoArgsConstructor
@AllArgsConstructor
public class Proposicao {
    @Id
    private Integer id;

    private int id_deputado;

    @Transient //ignorar coluna no banco
    @JsonIgnore
    private String uri;

    @Transient //ignorar coluna no banco
    @JsonIgnore
    private String siglaTipo;

    private int codTipo;

    @Transient //ignorar coluna no banco
    @JsonIgnore
    private int numero;

    private int ano;

    private String ementa;
}
