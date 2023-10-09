package com.example.dataAnalysisDeputados.controller;

import com.example.dataAnalysisDeputados.entity.AutorProposicao;
import com.example.dataAnalysisDeputados.entity.Despesas;
import com.example.dataAnalysisDeputados.entity.Partidos;
import com.example.dataAnalysisDeputados.entity.Responses.ResponsePartidos;
import com.example.dataAnalysisDeputados.entity.Responses.ResponseTipoProposicao;
import com.example.dataAnalysisDeputados.entity.TipoProposicao;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
@RestController
public class TipoProposicaoController {
    @GetMapping("/tipoProp")
    public List<TipoProposicao> getTipoProposicao(){

        String url = "https://dadosabertos.camara.leg.br/api/v2/referencias/tiposProposicao";
        RestTemplate template = new RestTemplate();
        ResponseTipoProposicao response = template.getForObject(url, ResponseTipoProposicao.class);
        List<TipoProposicao> tipoProp = response.getDados();
        return tipoProp;
    }

}
