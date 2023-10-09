package com.example.dataAnalysisDeputados.controller;

import com.example.dataAnalysisDeputados.entity.AutorProposicao;
import com.example.dataAnalysisDeputados.entity.Partidos;
import com.example.dataAnalysisDeputados.entity.Responses.ResponseAutoresProposicao;
import com.example.dataAnalysisDeputados.entity.Responses.ResponsePartidos;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@RestController
public class AutorProposicaoController {

    public List<AutorProposicao> getAutoresP(int id_prop){
        String url = String.format("https://dadosabertos.camara.leg.br/api/v2/proposicoes/%d/autores",id_prop);
        System.out.println(url);
        RestTemplate template = new RestTemplate();
        ResponseAutoresProposicao response = template.getForObject(url, ResponseAutoresProposicao.class);
        List<AutorProposicao> autoresProp = response.getDados();

        return autoresProp;
    }

}
