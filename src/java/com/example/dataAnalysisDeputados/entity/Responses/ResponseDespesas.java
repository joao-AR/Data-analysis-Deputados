package com.example.dataAnalysisDeputados.entity.Responses;

import com.example.dataAnalysisDeputados.entity.Despesas;
import com.example.dataAnalysisDeputados.entity.Link;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResponseDespesas {
    private  List<Despesas> dados;
    @JsonIgnore
    private  List<Link> links;
}
