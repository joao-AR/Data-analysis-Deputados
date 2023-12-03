package com.example.dataAnalysisDeputados.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import lombok.*;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@Data
@Entity(name = "partidos")
@Table(name = "partido", schema = "camara")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Partidos {
    @Id // PK
    Integer id;
    String sigla;
    String nome;
    
    @JsonIgnore // ignora no json
    @Transient //ignorar coluna no banco
    String uri;

    public Partidos(Integer id, String sigla, String nome) {
        this.id = id;
        this.sigla = sigla;
        this.nome = nome;
    }
}
