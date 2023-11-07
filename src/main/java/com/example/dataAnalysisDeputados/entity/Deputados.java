package com.example.dataAnalysisDeputados.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import lombok.*;

@Data
@Getter
@Setter
@Entity(name = "deputados")
@Table(name="deputado", schema = "camara")
@NoArgsConstructor
@AllArgsConstructor
public class Deputados {
    @Id
    private Integer id;

    private int id_partido;

    @Transient //ignorar coluna no banco
    @JsonIgnore
    private String uri;

    private String nome;

    private String siglaPartido;

    @Transient //ignorar coluna no banco
    @JsonIgnore
    private String uriPartido;

    @Transient //ignorar coluna no banco
    @JsonIgnore
    private String siglaUf;

    @Transient //ignorar coluna no banco
    @JsonIgnore
    private int idLegislatura;

    private String urlFoto;

    private String email ;
}
