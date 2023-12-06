package com.example.dataAnalysisDeputados.entity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class DespesaDeputado {
    private int idDeputado;
    private String nome;
    private int idPartido;
    private String siglaPartido;
    private int qtd;
    private float totalGasto;

}





