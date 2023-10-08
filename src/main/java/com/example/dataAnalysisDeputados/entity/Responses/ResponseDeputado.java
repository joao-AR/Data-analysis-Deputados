package com.example.dataAnalysisDeputados.entity.Responses;

import com.example.dataAnalysisDeputados.entity.Deputados;
import com.example.dataAnalysisDeputados.entity.Link;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResponseDeputado {
    private  List<Deputados> dados;
    private  List<Link> links;
}
