package com.example.dataAnalysisDeputados.controller;

import com.example.dataAnalysisDeputados.entity.Deputados;
import com.example.dataAnalysisDeputados.entity.ResponseDeputado;
import com.example.dataAnalysisDeputados.entity.ResponseDespesas;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@RestController
public class DeputadosController {
    @GetMapping("/deputados")
    public List<Deputados> getDeputadosList(){
        String url = "https://dadosabertos.camara.leg.br/api/v2/deputados?itens=50&dataInicio=2023-01-01&ordem=ASC&ordenarPor=id";
        RestTemplate template = new RestTemplate();
        ResponseDeputado response = template.getForObject(url, ResponseDeputado.class);
        List<Deputados> deputados = response.getDados();
        return deputados;
    }
}
