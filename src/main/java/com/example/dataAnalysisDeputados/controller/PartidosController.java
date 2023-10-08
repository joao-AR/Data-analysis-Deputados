package com.example.dataAnalysisDeputados.controller;

import com.example.dataAnalysisDeputados.entity.Partidos;
import com.example.dataAnalysisDeputados.entity.Responses.ResponsePartidos;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.List;
@RestController
public class PartidosController {
    @GetMapping("/partidos")
    public List<Partidos> getPartidos(){
        String url = "https://dadosabertos.camara.leg.br/api/v2/partidos?itens=1000&ordem=ASC&ordenarPor=id";
        RestTemplate template = new RestTemplate();
        ResponsePartidos response = template.getForObject(url, ResponsePartidos.class);
        List<Partidos> partidos = response.getDados();
        return partidos;
    }
}
